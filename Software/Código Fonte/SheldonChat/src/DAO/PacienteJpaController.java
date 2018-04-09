/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Controle.Logs;
import java.util.ArrayList;
import java.util.Collection;
import Controle.Estatistica;
import Controle.Paciente;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Breno
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) throws PreexistingEntityException, Exception {
        if (paciente.getLogsCollection() == null) {
            paciente.setLogsCollection(new ArrayList<Logs>());
        }
        if (paciente.getEstatisticaCollection() == null) {
            paciente.setEstatisticaCollection(new ArrayList<Estatistica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Logs> attachedLogsCollection = new ArrayList<Logs>();
            for (Logs logsCollectionLogsToAttach : paciente.getLogsCollection()) {
                logsCollectionLogsToAttach = em.getReference(logsCollectionLogsToAttach.getClass(), logsCollectionLogsToAttach.getId());
                attachedLogsCollection.add(logsCollectionLogsToAttach);
            }
            paciente.setLogsCollection(attachedLogsCollection);
            Collection<Estatistica> attachedEstatisticaCollection = new ArrayList<Estatistica>();
            for (Estatistica estatisticaCollectionEstatisticaToAttach : paciente.getEstatisticaCollection()) {
                estatisticaCollectionEstatisticaToAttach = em.getReference(estatisticaCollectionEstatisticaToAttach.getClass(), estatisticaCollectionEstatisticaToAttach.getId());
                attachedEstatisticaCollection.add(estatisticaCollectionEstatisticaToAttach);
            }
            paciente.setEstatisticaCollection(attachedEstatisticaCollection);
            em.persist(paciente);
            for (Logs logsCollectionLogs : paciente.getLogsCollection()) {
                Paciente oldIdPacienteOfLogsCollectionLogs = logsCollectionLogs.getIdPaciente();
                logsCollectionLogs.setIdPaciente(paciente);
                logsCollectionLogs = em.merge(logsCollectionLogs);
                if (oldIdPacienteOfLogsCollectionLogs != null) {
                    oldIdPacienteOfLogsCollectionLogs.getLogsCollection().remove(logsCollectionLogs);
                    oldIdPacienteOfLogsCollectionLogs = em.merge(oldIdPacienteOfLogsCollectionLogs);
                }
            }
            for (Estatistica estatisticaCollectionEstatistica : paciente.getEstatisticaCollection()) {
                Paciente oldIdPacienteOfEstatisticaCollectionEstatistica = estatisticaCollectionEstatistica.getIdPaciente();
                estatisticaCollectionEstatistica.setIdPaciente(paciente);
                estatisticaCollectionEstatistica = em.merge(estatisticaCollectionEstatistica);
                if (oldIdPacienteOfEstatisticaCollectionEstatistica != null) {
                    oldIdPacienteOfEstatisticaCollectionEstatistica.getEstatisticaCollection().remove(estatisticaCollectionEstatistica);
                    oldIdPacienteOfEstatisticaCollectionEstatistica = em.merge(oldIdPacienteOfEstatisticaCollectionEstatistica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaciente(paciente.getId()) != null) {
                throw new PreexistingEntityException("Paciente " + paciente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        /*EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            Collection<Logs> logsCollectionOld = persistentPaciente.getLogsCollection();
            Collection<Logs> logsCollectionNew = paciente.getLogsCollection();
            Collection<Estatistica> estatisticaCollectionOld = persistentPaciente.getEstatisticaCollection();
            Collection<Estatistica> estatisticaCollectionNew = paciente.getEstatisticaCollection();
            List<String> illegalOrphanMessages = null;
            for (Logs logsCollectionOldLogs : logsCollectionOld) {
                if (!logsCollectionNew.contains(logsCollectionOldLogs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Logs " + logsCollectionOldLogs + " since its idPaciente field is not nullable.");
                }
            }
            for (Estatistica estatisticaCollectionOldEstatistica : estatisticaCollectionOld) {
                if (!estatisticaCollectionNew.contains(estatisticaCollectionOldEstatistica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estatistica " + estatisticaCollectionOldEstatistica + " since its idPaciente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Logs> attachedLogsCollectionNew = new ArrayList<Logs>();
            for (Logs logsCollectionNewLogsToAttach : logsCollectionNew) {
                logsCollectionNewLogsToAttach = em.getReference(logsCollectionNewLogsToAttach.getClass(), logsCollectionNewLogsToAttach.getId());
                attachedLogsCollectionNew.add(logsCollectionNewLogsToAttach);
            }
            logsCollectionNew = attachedLogsCollectionNew;
            paciente.setLogsCollection(logsCollectionNew);
            Collection<Estatistica> attachedEstatisticaCollectionNew = new ArrayList<Estatistica>();
            for (Estatistica estatisticaCollectionNewEstatisticaToAttach : estatisticaCollectionNew) {
                estatisticaCollectionNewEstatisticaToAttach = em.getReference(estatisticaCollectionNewEstatisticaToAttach.getClass(), estatisticaCollectionNewEstatisticaToAttach.getId());
                attachedEstatisticaCollectionNew.add(estatisticaCollectionNewEstatisticaToAttach);
            }
            estatisticaCollectionNew = attachedEstatisticaCollectionNew;
            paciente.setEstatisticaCollection(estatisticaCollectionNew);
            paciente = em.merge(paciente);
            for (Logs logsCollectionNewLogs : logsCollectionNew) {
                if (!logsCollectionOld.contains(logsCollectionNewLogs)) {
                    Paciente oldIdPacienteOfLogsCollectionNewLogs = logsCollectionNewLogs.getIdPaciente();
                    logsCollectionNewLogs.setIdPaciente(paciente);
                    logsCollectionNewLogs = em.merge(logsCollectionNewLogs);
                    if (oldIdPacienteOfLogsCollectionNewLogs != null && !oldIdPacienteOfLogsCollectionNewLogs.equals(paciente)) {
                        oldIdPacienteOfLogsCollectionNewLogs.getLogsCollection().remove(logsCollectionNewLogs);
                        oldIdPacienteOfLogsCollectionNewLogs = em.merge(oldIdPacienteOfLogsCollectionNewLogs);
                    }
                }
            }
            for (Estatistica estatisticaCollectionNewEstatistica : estatisticaCollectionNew) {
                if (!estatisticaCollectionOld.contains(estatisticaCollectionNewEstatistica)) {
                    Paciente oldIdPacienteOfEstatisticaCollectionNewEstatistica = estatisticaCollectionNewEstatistica.getIdPaciente();
                    estatisticaCollectionNewEstatistica.setIdPaciente(paciente);
                    estatisticaCollectionNewEstatistica = em.merge(estatisticaCollectionNewEstatistica);
                    if (oldIdPacienteOfEstatisticaCollectionNewEstatistica != null && !oldIdPacienteOfEstatisticaCollectionNewEstatistica.equals(paciente)) {
                        oldIdPacienteOfEstatisticaCollectionNewEstatistica.getEstatisticaCollection().remove(estatisticaCollectionNewEstatistica);
                        oldIdPacienteOfEstatisticaCollectionNewEstatistica = em.merge(oldIdPacienteOfEstatisticaCollectionNewEstatistica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paciente = em.merge(paciente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        /*EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Logs> logsCollectionOrphanCheck = paciente.getLogsCollection();
            for (Logs logsCollectionOrphanCheckLogs : logsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Logs " + logsCollectionOrphanCheckLogs + " in its logsCollection field has a non-nullable idPaciente field.");
            }
            Collection<Estatistica> estatisticaCollectionOrphanCheck = paciente.getEstatisticaCollection();
            for (Estatistica estatisticaCollectionOrphanCheckEstatistica : estatisticaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Estatistica " + estatisticaCollectionOrphanCheckEstatistica + " in its estatisticaCollection field has a non-nullable idPaciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Paciente findPaciente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Paciente findNomePaciente(String nomePaciente) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Paciente.findByNomePaciente", Paciente.class).setParameter("nomePaciente", nomePaciente).getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
