/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import DAO.PacienteJpaController;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Breno
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findById", query = "SELECT p FROM Paciente p WHERE p.id = :id"),
    @NamedQuery(name = "Paciente.findByNomePaciente", query = "SELECT p FROM Paciente p WHERE p.nomePaciente = :nomePaciente"),
    @NamedQuery(name = "Paciente.findBySexoPaciente", query = "SELECT p FROM Paciente p WHERE p.sexoPaciente = :sexoPaciente"),
    @NamedQuery(name = "Paciente.findByIdadePaciente", query = "SELECT p FROM Paciente p WHERE p.idadePaciente = :idadePaciente"),
    @NamedQuery(name = "Paciente.findByFotoPaciente", query = "SELECT p FROM Paciente p WHERE p.fotoPaciente = :fotoPaciente"),
    @NamedQuery(name = "Paciente.findBySelecionado", query = "SELECT p FROM Paciente p WHERE p.selecionado = :selecionado")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NomePaciente", nullable = false, length = 30)
    private String nomePaciente;
    @Basic(optional = false)
    @Column(name = "SexoPaciente", nullable = false, length = 20)
    private String sexoPaciente;
    @Basic(optional = false)
    @Column(name = "IdadePaciente", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date idadePaciente;
    @Column(name = "FotoPaciente", length = 1000)
    private String fotoPaciente;
    @Basic(optional = false)
    @Column(name = "Selecionado", nullable = false)
    private boolean selecionado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private Collection<Logs> logsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private Collection<Estatistica> estatisticaCollection;

    public Paciente() {
    }

    public Paciente(Long id) {
        this.id = id;
    }

    public Paciente(Long id, String nomePaciente, String sexoPaciente, Date idadePaciente, boolean selecionado) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.sexoPaciente = sexoPaciente;
        this.idadePaciente = idadePaciente;
        this.selecionado = selecionado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getSexoPaciente() {
        return sexoPaciente;
    }

    public void setSexoPaciente(String sexoPaciente) {
        this.sexoPaciente = sexoPaciente;
    }

    public Date getIdadePaciente() {
        return idadePaciente;
    }

    public void setIdadePaciente(Date idadePaciente) {
        this.idadePaciente = idadePaciente;
    }

    public String getFotoPaciente() {
        return fotoPaciente;
    }

    public void setFotoPaciente(String fotoPaciente) {
        this.fotoPaciente = fotoPaciente;
    }

    public boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @XmlTransient
    public Collection<Logs> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(Collection<Logs> logsCollection) {
        this.logsCollection = logsCollection;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Paciente[ id=" + id + " ]";
    }
    
    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            PacienteJpaController pacienteJpaController = new PacienteJpaController(emf);
            pacienteJpaController.create(this);
            JOptionPane.showMessageDialog(null, "Paciente registrado com sucesso!", "Registro", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            PacienteJpaController pacienteJpaController = new PacienteJpaController(emf);
            pacienteJpaController.edit(this);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            PacienteJpaController pacienteJpaController = new PacienteJpaController(emf);
            pacienteJpaController.destroy(this.getId());
            JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!", "Exclusão", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            PacienteJpaController pacienteJpaController = new PacienteJpaController(emf);
            Paciente pacienteAux = pacienteJpaController.findPaciente(id);
            if (pacienteAux != null) {
                this.setId(pacienteAux.getId());
                this.setNomePaciente(pacienteAux.getNomePaciente());
                this.setIdadePaciente(pacienteAux.getIdadePaciente());
                this.setSexoPaciente(pacienteAux.getSexoPaciente());
                this.setSelecionado(pacienteAux.getSelecionado());
                this.setFotoPaciente(pacienteAux.getFotoPaciente());
                return true;
            } else {
                //JOptionPane.showMessageDialog(null, "Paciente encontrado");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public Paciente encontradoNome(String nomePaciente) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            PacienteJpaController pacienteJpaController = new PacienteJpaController(emf);
            Paciente pacienteAux = (Paciente) pacienteJpaController.findNomePaciente(nomePaciente);
            if (pacienteAux != null) {
                this.setId(pacienteAux.getId());
                this.setNomePaciente(pacienteAux.getNomePaciente());
                this.setIdadePaciente(pacienteAux.getIdadePaciente());
                this.setSexoPaciente(pacienteAux.getSexoPaciente());
                this.setSelecionado(pacienteAux.getSelecionado());
                this.setFotoPaciente(pacienteAux.getFotoPaciente());
                return pacienteAux;
            } else {
                JOptionPane.showMessageDialog(null, "Paciente encontrado");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
