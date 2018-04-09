/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import DAO.ResponsavelJpaController;
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
@Table(name = "responsavel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsavel.findAll", query = "SELECT r FROM Responsavel r"),
    @NamedQuery(name = "Responsavel.findById", query = "SELECT r FROM Responsavel r WHERE r.id = :id"),
    @NamedQuery(name = "Responsavel.findByNomeResponsavel", query = "SELECT r FROM Responsavel r WHERE r.nomeResponsavel = :nomeResponsavel"),
    @NamedQuery(name = "Responsavel.findBySenhaResponsavel", query = "SELECT r FROM Responsavel r WHERE r.senhaResponsavel = :senhaResponsavel")})
public class Responsavel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NomeResponsavel", nullable = false, length = 30)
    private String nomeResponsavel;
    @Basic(optional = false)
    @Column(name = "SenhaResponsavel", nullable = false, length = 20)
    private String senhaResponsavel;

    public Responsavel() {
    }

    public Responsavel(Long id) {
        this.id = id;
    }

    public Responsavel(Long id, String nomeResponsavel, String senhaResponsavel) {
        this.id = id;
        this.nomeResponsavel = nomeResponsavel;
        this.senhaResponsavel = senhaResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getSenhaResponsavel() {
        return senhaResponsavel;
    }

    public void setSenhaResponsavel(String senhaResponsavel) {
        this.senhaResponsavel = senhaResponsavel;
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
        if (!(object instanceof Responsavel)) {
            return false;
        }
        Responsavel other = (Responsavel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Responsavel[ id=" + id + " ]";
    }
    
    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            ResponsavelJpaController responsavelJpaController = new ResponsavelJpaController(emf);
            responsavelJpaController.create(this);
            JOptionPane.showMessageDialog(null, "Responsável registrado com sucesso!", "Registro", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            ResponsavelJpaController responsavelJpaController = new ResponsavelJpaController(emf);
            responsavelJpaController.edit(this);
            JOptionPane.showMessageDialog(null, "Responsável atualizado");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            ResponsavelJpaController responsavelJpaController = new ResponsavelJpaController(emf);
            responsavelJpaController.destroy(this.getId());
            JOptionPane.showMessageDialog(null, "Responsável excluído");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            ResponsavelJpaController responsavelJpaController = new ResponsavelJpaController(emf);
            Responsavel responsavelAux = responsavelJpaController.findResponsavel(id);
            if (responsavelAux != null) {
                this.setId(responsavelAux.getId());
                this.setNomeResponsavel(responsavelAux.getNomeResponsavel());
                this.setSenhaResponsavel(responsavelAux.getSenhaResponsavel());
                return true;
            } else {
                //JOptionPane.showMessageDialog(null, "Responsável encontrado");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public Responsavel encontradoNome(String nomeResponsavel) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            ResponsavelJpaController responsavelJpaController = new ResponsavelJpaController(emf);
            Responsavel responsavelAux = (Responsavel) responsavelJpaController.findNomeResponsavel(nomeResponsavel);
            if (responsavelAux != null) {
                this.setId(responsavelAux.getId());
                this.setNomeResponsavel(responsavelAux.getNomeResponsavel());
                this.setSenhaResponsavel(responsavelAux.getSenhaResponsavel());
                return responsavelAux;
            } else {
                JOptionPane.showMessageDialog(null, "Responsável encontrado");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
