/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.AtividadeJpaController;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Breno
 */
@Entity
@Table(name = "atividade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atividade.findAll", query = "SELECT a FROM Atividade a"),
    @NamedQuery(name = "Atividade.findById", query = "SELECT a FROM Atividade a WHERE a.id = :id"),
    @NamedQuery(name = "Atividade.findByNomeAtividade", query = "SELECT a FROM Atividade a WHERE a.nomeAtividade = :nomeAtividade"),
    @NamedQuery(name = "Atividade.findByFocoAtividade", query = "SELECT a FROM Atividade a WHERE a.focoAtividade = :focoAtividade")})
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NomeAtividade", nullable = false, length = 30)
    private String nomeAtividade;
    @Basic(optional = false)
    @Column(name = "FocoAtividade", nullable = false, length = 50)
    private String focoAtividade;

    public Atividade() {
    }

    public Atividade(Long id) {
        this.id = id;
    }

    public Atividade(Long id, String nomeAtividade, String focoAtividade) {
        this.id = id;
        this.nomeAtividade = nomeAtividade;
        this.focoAtividade = focoAtividade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getFocoAtividade() {
        return focoAtividade;
    }

    public void setFocoAtividade(String focoAtividade) {
        this.focoAtividade = focoAtividade;
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
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Atividade[ id=" + id + " ]";
    }

    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            AtividadeJpaController atividadeJpaController = new AtividadeJpaController(emf);
            atividadeJpaController.create(this);
            //JOptionPane.showMessageDialog(null, "Atividade registrada com sucesso!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            AtividadeJpaController atividadeJpaController = new AtividadeJpaController(emf);
            atividadeJpaController.edit(this);
            JOptionPane.showMessageDialog(null, "Atividade atualizada");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            AtividadeJpaController atividadeJpaController = new AtividadeJpaController(emf);
            atividadeJpaController.destroy(this.getId());
            JOptionPane.showMessageDialog(null, "Atividade excluída");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            AtividadeJpaController atividadeJpaController = new AtividadeJpaController(emf);
            Atividade atividadeAux = atividadeJpaController.findAtividade(id);
            if (atividadeAux != null) {
                this.setId(atividadeAux.getId());
                this.setNomeAtividade(atividadeAux.getNomeAtividade());
                this.setFocoAtividade(atividadeAux.getFocoAtividade());
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Atividade encontrada");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public Atividade encontradoNome(String nomeAtividade) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            AtividadeJpaController atividadeJpaController = new AtividadeJpaController(emf);
            Atividade atividadeAux = (Atividade) atividadeJpaController.findNomeAtividade(nomeAtividade);
            if (atividadeAux != null) {
                this.setId(atividadeAux.getId());
                this.setNomeAtividade(atividadeAux.getNomeAtividade());
                this.setFocoAtividade(atividadeAux.getFocoAtividade());
                return atividadeAux;
            } else {
                JOptionPane.showMessageDialog(null, "Responsável não encontrado");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
