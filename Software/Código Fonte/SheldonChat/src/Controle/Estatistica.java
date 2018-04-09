/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import DAO.EstatisticaJpaController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "estatistica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estatistica.findAll", query = "SELECT e FROM Estatistica e"),
    @NamedQuery(name = "Estatistica.findById", query = "SELECT e FROM Estatistica e WHERE e.id = :id"),
    @NamedQuery(name = "Estatistica.findByData", query = "SELECT e FROM Estatistica e WHERE e.data = :data"),
    @NamedQuery(name = "Estatistica.findByNomePaciente", query = "SELECT e FROM Estatistica e WHERE e.nomePaciente = :nomePaciente"),
    @NamedQuery(name = "Estatistica.findByNomeBot", query = "SELECT e FROM Estatistica e WHERE e.nomeBot = :nomeBot"),
    @NamedQuery(name = "Estatistica.findByNota1", query = "SELECT e FROM Estatistica e WHERE e.nota1 = :nota1"),
    @NamedQuery(name = "Estatistica.findByNota2", query = "SELECT e FROM Estatistica e WHERE e.nota2 = :nota2"),
    @NamedQuery(name = "Estatistica.findByNota3", query = "SELECT e FROM Estatistica e WHERE e.nota3 = :nota3")})
public class Estatistica implements Serializable {
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
    @Column(name = "NomeBot", nullable = false, length = 30)
    private String nomeBot;
    @Column(name = "Nota1")
    private Double nota1;
    @Column(name = "Nota2")
    private Double nota2;
    @Column(name = "Nota3")
    private Double nota3;
    @JoinColumn(name = "idBot", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Bot idBot;
    @JoinColumn(name = "idPaciente", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Paciente idPaciente;

    public Estatistica() {
    }

    public Estatistica(Long id) {
        this.id = id;
    }

    public Estatistica(Long id, Date data, String nomePaciente, String nomeBot) {
        this.id = id;
        this.data = data;
        this.nomePaciente = nomePaciente;
        this.nomeBot = nomeBot;
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

    public String getNomeBot() {
        return nomeBot;
    }

    public void setNomeBot(String nomeBot) {
        String oldNomeBot = this.nomeBot;
        this.nomeBot = nomeBot;
        changeSupport.firePropertyChange("nomeBot", oldNomeBot, nomeBot);
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        Double oldNota1 = this.nota1;
        this.nota1 = nota1;
        changeSupport.firePropertyChange("nota1", oldNota1, nota1);
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        Double oldNota2 = this.nota2;
        this.nota2 = nota2;
        changeSupport.firePropertyChange("nota2", oldNota2, nota2);
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        Double oldNota3 = this.nota3;
        this.nota3 = nota3;
        changeSupport.firePropertyChange("nota3", oldNota3, nota3);
    }

    public Bot getIdBot() {
        return idBot;
    }

    public void setIdBot(Bot idBot) {
        Bot oldIdBot = this.idBot;
        this.idBot = idBot;
        changeSupport.firePropertyChange("idBot", oldIdBot, idBot);
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
        if (!(object instanceof Estatistica)) {
            return false;
        }
        Estatistica other = (Estatistica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controle.Estatistica[ id=" + id + " ]";
    }
    
    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            estatisticaJpaController.create(this);
            //JOptionPane.showMessageDialog(null, "Estatística gerada com sucesso!", "Criação", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            estatisticaJpaController.edit(this);
            //JOptionPane.showMessageDialog(null, "Estatística atualizada", "Atualização", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            estatisticaJpaController.destroy(this.getId());
            //JOptionPane.showMessageDialog(null, "Estatística excluída", "Exclusão", 1);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            Estatistica estatisticaAux = estatisticaJpaController.findEstatistica(id);
            if (estatisticaAux != null) {
                this.setId(estatisticaAux.getId());
                this.setData(estatisticaAux.getData());
                this.setIdPaciente(estatisticaAux.getIdPaciente());
                this.setNomePaciente(estatisticaAux.getNomePaciente());
                this.setIdBot(estatisticaAux.getIdBot());
                this.setNomeBot(estatisticaAux.getNomeBot());
                this.setNota1(estatisticaAux.getNota1());
                this.setNota2(estatisticaAux.getNota2());
                this.setNota3(estatisticaAux.getNota3());
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Estatística encontrada");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public Estatistica encontradoNome(String nomePaciente) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            Estatistica estatisticaAux = (Estatistica) estatisticaJpaController.findEstatistica(nomePaciente);
            if (estatisticaAux != null) {
                this.setId(estatisticaAux.getId());
                this.setData(estatisticaAux.getData());
                this.setIdPaciente(estatisticaAux.getIdPaciente());
                this.setNomePaciente(estatisticaAux.getNomePaciente());
                this.setIdBot(estatisticaAux.getIdBot());
                this.setNomeBot(estatisticaAux.getNomeBot());
                this.setNota1(estatisticaAux.getNota1());
                this.setNota2(estatisticaAux.getNota2());
                this.setNota3(estatisticaAux.getNota3());
                return estatisticaAux;
            } else {
                JOptionPane.showMessageDialog(null, "Estatística encontrada");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
     public List<Estatistica> consultadoGeral() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            return estatisticaJpaController.consultaGeral();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
        public List<Estatistica> consultadoPorNome(String nomePaciente) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            return estatisticaJpaController.consultaPorNome(nomePaciente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
        
         public List<Estatistica> consultadoPorData(Date data) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCA13ProjectPU");
            EstatisticaJpaController estatisticaJpaController = new EstatisticaJpaController(emf);
            return estatisticaJpaController.consultaPorData(data);
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
