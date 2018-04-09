/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Controle.Responsavel;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Breno
 */
public class ResponsavelJpaController implements Serializable {

    public ResponsavelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsavel responsavel) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(responsavel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsavel(responsavel.getId()) != null) {
                throw new PreexistingEntityException("Responsavel " + responsavel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Responsavel responsavel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            responsavel = em.merge(responsavel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = responsavel.getId();
                if (findResponsavel(id) == null) {
                    throw new NonexistentEntityException("The responsavel with id " + id + " no longer exists.");
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
            Responsavel responsavel;
            try {
                responsavel = em.getReference(Responsavel.class, id);
                responsavel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsavel with id " + id + " no longer exists.", enfe);
            }
            em.remove(responsavel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Responsavel> findResponsavelEntities() {
        return findResponsavelEntities(true, -1, -1);
    }

    public List<Responsavel> findResponsavelEntities(int maxResults, int firstResult) {
        return findResponsavelEntities(false, maxResults, firstResult);
    }

    private List<Responsavel> findResponsavelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Responsavel.class));
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

    public Responsavel findResponsavel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsavel.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsavelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Responsavel> rt = cq.from(Responsavel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Responsavel findNomeResponsavel(String nomeResponsavel) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Responsavel.findByNomeResponsavel", Responsavel.class).setParameter("nomeResponsavel", nomeResponsavel).getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
