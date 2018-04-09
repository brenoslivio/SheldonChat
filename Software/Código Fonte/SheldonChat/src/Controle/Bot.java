/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import DAO.BotJpaController;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Breno
 */
@Entity
@Table(name = "bot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bot.findAll", query = "SELECT b FROM Bot b"),
    @NamedQuery(name = "Bot.findById", query = "SELECT b FROM Bot b WHERE b.id = :id"),
    @NamedQuery(name = "Bot.findByNomeBot", query = "SELECT b FROM Bot b WHERE b.nomeBot = :nomeBot"),
    @NamedQuery(name = "Bot.findBySexoBot", query = "SELECT b FROM Bot b WHERE b.sexoBot = :sexoBot")})
public class Bot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NomeBot", nullable = false, length = 30)
    private String nomeBot;
    @Basic(optional = false)
    @Column(name = "SexoBot", nullable = false, length = 20)
    private String sexoBot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBot")
    private Collection<Estatistica> estatisticaCollection;

    public Bot() {
    }

    public Bot(Long id) {
        this.id = id;
    }

    public Bot(Long id, String nomeBot, String sexoBot) {
        this.id = id;
        this.nomeBot = nomeBot;
        this.sexoBot = sexoBot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBot() {
        return nomeBot;
    }

    public void setNomeBot(String nomeBot) {
        this.nomeBot = nomeBot;
    }

    public String getSexoBot() {
        return sexoBot;
    }

    public void setSexoBot(String sexoBot) {
        this.sexoBot = sexoBot;
    }

    @XmlTransient
    public Collection<Estatistica> getEstatisticaCollection() {
        return estatisticaCollection;
    }

    public void setEstatisticaCollection(Collection<Estatistica> estatisticaCollection) {
        this.estatisticaCollection = estatisticaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bot)) {
            return false;
        }
        Bot other = (Bot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Bot[ id=" + id + " ]";
    }
    
    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            BotJpaController botJpaController = new BotJpaController(emf);
            botJpaController.create(this);
            JOptionPane.showMessageDialog(null, "BOT registrado com sucesso!", "Registro", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            BotJpaController botJpaController = new BotJpaController(emf);
            botJpaController.edit(this);
            JOptionPane.showMessageDialog(null, "BOT atualizado");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            BotJpaController botJpaController = new BotJpaController(emf);
            botJpaController.destroy(this.getId());
            JOptionPane.showMessageDialog(null, "BOT excluído");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            BotJpaController botJpaController = new BotJpaController(emf);
            Bot botAux = botJpaController.findBot(id);
            if (botAux != null) {
                this.setId(botAux.getId());
                this.setNomeBot(botAux.getNomeBot());
                this.setSexoBot(botAux.getSexoBot());
                return true;
            } else {
                //JOptionPane.showMessageDialog(null, "BOT encontrado");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public Bot encontradoNome(String nomeBot) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            BotJpaController botJpaController = new BotJpaController(emf);
            Bot botAux = (Bot) botJpaController.findNomeBot(nomeBot);
            if (botAux != null) {
                this.setId(botAux.getId());
                this.setNomeBot(botAux.getNomeBot());
                this.setSexoBot(botAux.getSexoBot());
                return botAux;
            } else {
                JOptionPane.showMessageDialog(null, "BOT encontrado");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
