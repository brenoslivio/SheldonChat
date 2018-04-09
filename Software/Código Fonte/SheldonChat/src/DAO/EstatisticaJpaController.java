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
import Controle.Bot;
import Controle.Estatistica;
import Controle.Paciente;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Breno
 */
public class EstatisticaJpaController implements Serializable {

    public EstatisticaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estatistica estatistica) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bot idBot = estatistica.getIdBot();
            if (idBot != null) {
                idBot = em.getReference(idBot.getClass(), idBot.getId());
                estatistica.setIdBot(idBot);
            }
            Paciente idPaciente = estatistica.getIdPaciente();
            if (idPaciente != null) {
                idPaciente = em.getReference(idPaciente.getClass(), idPaciente.getId());
                estatistica.setIdPaciente(idPaciente);
            }
            em.persist(estatistica);
            if (idBot != null) {
                idBot.getEstatisticaCollection().add(estatistica);
                idBot = em.merge(idBot);
            }
            if (idPaciente != null) {
                idPaciente.getEstatisticaCollection().add(estatistica);
                idPaciente = em.merge(idPaciente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstatistica(estatistica.getId()) != null) {
                throw new PreexistingEntityException("Estatistica " + estatistica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estatistica estatistica) throws NonexistentEntityException, Exception {
        /*EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estatistica persistentEstatistica = em.find(Estatistica.class, estatistica.getId());
            Bot idBotOld = persistentEstatistica.getIdBot();
            Bot idBotNew = estatistica.getIdBot();
            Paciente idPacienteOld = persistentEstatistica.getIdPaciente();
            Paciente idPacienteNew = estatistica.getIdPaciente();
            if (idBotNew != null) {
                idBotNew = em.getReference(idBotNew.getClass(), idBotNew.getId());
                estatistica.setIdBot(idBotNew);
            }
            if (idPacienteNew != null) {
                idPacienteNew = em.getReference(idPacienteNew.getClass(), idPacienteNew.getId());
                estatistica.setIdPaciente(idPacienteNew);
            }
            estatistica = em.merge(estatistica);
            if (idBotOld != null && !idBotOld.equals(idBotNew)) {
                idBotOld.getEstatisticaCollection().remove(estatistica);
                idBotOld = em.merge(idBotOld);
            }
            if (idBotNew != null && !idBotNew.equals(idBotOld)) {
                idBotNew.getEstatisticaCollection().add(estatistica);
                idBotNew = em.merge(idBotNew);
            }
            if (idPacienteOld != null && !idPacienteOld.equals(idPacienteNew)) {
                idPacienteOld.getEstatisticaCollection().remove(estatistica);
                idPacienteOld = em.merge(idPacienteOld);
            }
            if (idPacienteNew != null && !idPacienteNew.equals(idPacienteOld)) {
                idPacienteNew.getEstatisticaCollection().add(estatistica);
                idPacienteNew = em.merge(idPacienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estatistica.getId();
                if (findEstatistica(id) == null) {
                    throw new NonexistentEntityException("The estatistica with id " + id + " no longer exists.");
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
            estatistica = em.merge(estatistica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estatistica.getId();
                if (findEstatistica(id) == null) {
                    throw new NonexistentEntityException("The estatistica with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estatistica estatistica;
            try {
                estatistica = em.getReference(Estatistica.class, id);
                estatistica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estatistica with id " + id + " no longer exists.", enfe);
            }
            Bot idBot = estatistica.getIdBot();
            if (idBot != null) {
                idBot.getEstatisticaCollection().remove(estatistica);
                idBot = em.merge(idBot);
            }
            Paciente idPaciente = estatistica.getIdPaciente();
            if (idPaciente != null) {
                idPaciente.getEstatisticaCollection().remove(estatistica);
                idPaciente = em.merge(idPaciente);
            }
            em.remove(estatistica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estatistica> findEstatisticaEntities() {
        return findEstatisticaEntities(true, -1, -1);
    }

    public List<Estatistica> findEstatisticaEntities(int maxResults, int firstResult) {
        return findEstatisticaEntities(false, maxResults, firstResult);
    }

    private List<Estatistica> findEstatisticaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estatistica.class));
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

    public Estatistica findEstatistica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estatistica.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstatisticaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estatistica> rt = cq.from(Estatistica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Estatistica findEstatistica(String nomePaciente) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Estatistica.findByEstatistica", Estatistica.class).setParameter("nomePaciente", nomePaciente).getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Estatistica> consultaGeral() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Estatistica.findAll", Estatistica.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Estatistica> consultaPorNome(String nomePaciente) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Estatistica.findByNomePaciente", Estatistica.class).setParameter("nomePaciente",nomePaciente);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Estatistica> consultaPorData(Date data) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Estatistica.findByData", Estatistica.class).setParameter("data",data);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
