/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.Bot;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Controle.Estatistica;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Breno
 */
public class BotJpaController implements Serializable {

    public BotJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bot bot) throws PreexistingEntityException, Exception {
        if (bot.getEstatisticaCollection() == null) {
            bot.setEstatisticaCollection(new ArrayList<Estatistica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Estatistica> attachedEstatisticaCollection = new ArrayList<Estatistica>();
            for (Estatistica estatisticaCollectionEstatisticaToAttach : bot.getEstatisticaCollection()) {
                estatisticaCollectionEstatisticaToAttach = em.getReference(estatisticaCollectionEstatisticaToAttach.getClass(), estatisticaCollectionEstatisticaToAttach.getId());
                attachedEstatisticaCollection.add(estatisticaCollectionEstatisticaToAttach);
            }
            bot.setEstatisticaCollection(attachedEstatisticaCollection);
            em.persist(bot);
            for (Estatistica estatisticaCollectionEstatistica : bot.getEstatisticaCollection()) {
                Bot oldIdBotOfEstatisticaCollectionEstatistica = estatisticaCollectionEstatistica.getIdBot();
                estatisticaCollectionEstatistica.setIdBot(bot);
                estatisticaCollectionEstatistica = em.merge(estatisticaCollectionEstatistica);
                if (oldIdBotOfEstatisticaCollectionEstatistica != null) {
                    oldIdBotOfEstatisticaCollectionEstatistica.getEstatisticaCollection().remove(estatisticaCollectionEstatistica);
                    oldIdBotOfEstatisticaCollectionEstatistica = em.merge(oldIdBotOfEstatisticaCollectionEstatistica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBot(bot.getId()) != null) {
                throw new PreexistingEntityException("Bot " + bot + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bot bot) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bot persistentBot = em.find(Bot.class, bot.getId());
            Collection<Estatistica> estatisticaCollectionOld = persistentBot.getEstatisticaCollection();
            Collection<Estatistica> estatisticaCollectionNew = bot.getEstatisticaCollection();
            List<String> illegalOrphanMessages = null;
            for (Estatistica estatisticaCollectionOldEstatistica : estatisticaCollectionOld) {
                if (!estatisticaCollectionNew.contains(estatisticaCollectionOldEstatistica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estatistica " + estatisticaCollectionOldEstatistica + " since its idBot field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Estatistica> attachedEstatisticaCollectionNew = new ArrayList<Estatistica>();
            for (Estatistica estatisticaCollectionNewEstatisticaToAttach : estatisticaCollectionNew) {
                estatisticaCollectionNewEstatisticaToAttach = em.getReference(estatisticaCollectionNewEstatisticaToAttach.getClass(), estatisticaCollectionNewEstatisticaToAttach.getId());
                attachedEstatisticaCollectionNew.add(estatisticaCollectionNewEstatisticaToAttach);
            }
            estatisticaCollectionNew = attachedEstatisticaCollectionNew;
            bot.setEstatisticaCollection(estatisticaCollectionNew);
            bot = em.merge(bot);
            for (Estatistica estatisticaCollectionNewEstatistica : estatisticaCollectionNew) {
                if (!estatisticaCollectionOld.contains(estatisticaCollectionNewEstatistica)) {
                    Bot oldIdBotOfEstatisticaCollectionNewEstatistica = estatisticaCollectionNewEstatistica.getIdBot();
                    estatisticaCollectionNewEstatistica.setIdBot(bot);
                    estatisticaCollectionNewEstatistica = em.merge(estatisticaCollectionNewEstatistica);
                    if (oldIdBotOfEstatisticaCollectionNewEstatistica != null && !oldIdBotOfEstatisticaCollectionNewEstatistica.equals(bot)) {
                        oldIdBotOfEstatisticaCollectionNewEstatistica.getEstatisticaCollection().remove(estatisticaCollectionNewEstatistica);
                        oldIdBotOfEstatisticaCollectionNewEstatistica = em.merge(oldIdBotOfEstatisticaCollectionNewEstatistica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bot.getId();
                if (findBot(id) == null) {
                    throw new NonexistentEntityException("The bot with id " + id + " no longer exists.");
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bot bot;
            try {
                bot = em.getReference(Bot.class, id);
                bot.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bot with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Estatistica> estatisticaCollectionOrphanCheck = bot.getEstatisticaCollection();
            for (Estatistica estatisticaCollectionOrphanCheckEstatistica : estatisticaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bot (" + bot + ") cannot be destroyed since the Estatistica " + estatisticaCollectionOrphanCheckEstatistica + " in its estatisticaCollection field has a non-nullable idBot field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(bot);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bot> findBotEntities() {
        return findBotEntities(true, -1, -1);
    }

    public List<Bot> findBotEntities(int maxResults, int firstResult) {
        return findBotEntities(false, maxResults, firstResult);
    }

    private List<Bot> findBotEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bot.class));
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

    public Bot findBot(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bot.class, id);
        } finally {
            em.close();
        }
    }

    public int getBotCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bot> rt = cq.from(Bot.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Bot findNomeBot(String nomeBot) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Bot.findByNomeBot", Bot.class).setParameter("nomeBot", nomeBot).getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
