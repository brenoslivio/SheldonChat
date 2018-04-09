/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import DAO.LogsJpaController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Breno
 */
@Entity
@Table(name = "logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findById", query = "SELECT l FROM Logs l WHERE l.id = :id"),
    @NamedQuery(name = "Logs.findByData", query = "SELECT l FROM Logs l WHERE l.data = :data"),
    @NamedQuery(name = "Logs.findByNomePaciente", query = "SELECT l FROM Logs l WHERE l.nomePaciente = :nomePaciente"),
    @NamedQuery(name = "Logs.findByEnderecoPaciente", query = "SELECT l FROM Logs l WHERE l.enderecoPaciente = :enderecoPaciente"),
    @NamedQuery(name = "Logs.findByEnderecoResponsavel", query = "SELECT l FROM Logs l WHERE l.enderecoResponsavel = :enderecoResponsavel")})
public class Logs implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "Data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "NomePaciente", nullable = false, length = 30)
    private String nomePaciente;
    @Basic(optional = false)
    @Column(name = "EnderecoPaciente", nullable = false, length = 1000)
    private String enderecoPaciente;
    @Basic(optional = false)
    @Column(name = "EnderecoResponsavel", nullable = false, length = 1000)
    private String enderecoResponsavel;
    @JoinColumn(name = "idPaciente", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Paciente idPaciente;

    public Logs() {
    }

    public Logs(Long id) {
        this.id = id;
    }

    public Logs(Long id, Date data, String nomePaciente, String enderecoPaciente, String enderecoResponsavel) {
        this.id = id;
        this.data = data;
        this.nomePaciente = nomePaciente;
        this.enderecoPaciente = enderecoPaciente;
        this.enderecoResponsavel = enderecoResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        String oldNomePaciente = this.nomePaciente;
        this.nomePaciente = nomePaciente;
        changeSupport.firePropertyChange("nomePaciente", oldNomePaciente, nomePaciente);
    }

    public String getEnderecoPaciente() {
        return enderecoPaciente;
    }

    public void setEnderecoPaciente(String enderecoPaciente) {
        String oldEnderecoPaciente = this.enderecoPaciente;
        this.enderecoPaciente = enderecoPaciente;
        changeSupport.firePropertyChange("enderecoPaciente", oldEnderecoPaciente, enderecoPaciente);
    }

    public String getEnderecoResponsavel() {
        return enderecoResponsavel;
    }

    public void setEnderecoResponsavel(String enderecoResponsavel) {
        String oldEnderecoResponsavel = this.enderecoResponsavel;
        this.enderecoResponsavel = enderecoResponsavel;
        changeSupport.firePropertyChange("enderecoResponsavel", oldEnderecoResponsavel, enderecoResponsavel);
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        Paciente oldIdPaciente = this.idPaciente;
        this.idPaciente = idPaciente;
        changeSupport.firePropertyChange("idPaciente", oldIdPaciente, idPaciente);
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
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Logs[ id=" + id + " ]";
    }
    
    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            LogsJpaController logsJpaController = new LogsJpaController(emf);
            logsJpaController.create(this);
            //JOptionPane.showMessageDialog(null, "BOT registrado com sucesso!", "Registro", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            LogsJpaController logsJpaController = new LogsJpaController(emf);
            logsJpaController.edit(this);
            //JOptionPane.showMessageDialog(null, "BOT atualizado");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            LogsJpaController logsJpaController = new LogsJpaController(emf);
            logsJpaController.destroy(this.getId());
            //JOptionPane.showMessageDialog(null, "BOT excluído");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            LogsJpaController logsJpaController = new LogsJpaController(emf);
            Logs logsAux = logsJpaController.findLogs(id);
            if (logsAux != null) {
                this.setId(logsAux.getId());
                this.setData(logsAux.getData());
                this.setIdPaciente(logsAux.getIdPaciente());
                this.setNomePaciente(logsAux.getNomePaciente());
                this.setEnderecoPaciente(logsAux.getEnderecoPaciente());
                this.setEnderecoResponsavel(logsAux.getEnderecoResponsavel());
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

    public Logs encontradoNome(String nomePaciente) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            LogsJpaController logsJpaController = new LogsJpaController(emf);
            Logs logsAux = (Logs) logsJpaController.findNomePaciente(nomePaciente);
            if (logsAux != null) {
                this.setId(logsAux.getId());
                this.setData(logsAux.getData());
                this.setIdPaciente(logsAux.getIdPaciente());
                this.setNomePaciente(logsAux.getNomePaciente());
                this.setEnderecoPaciente(logsAux.getEnderecoPaciente());
                this.setEnderecoResponsavel(logsAux.getEnderecoResponsavel());
                return logsAux;
            } else {
                //JOptionPane.showMessageDialog(null, "UHUL");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
