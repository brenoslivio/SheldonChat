/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Controle.Bot;
import Controle.Estatistica;
import Controle.Logs;
import Controle.Paciente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class Graficos extends javax.swing.JFrame {
    private Paciente paciente;
    private Bot bot;
    private Logs logs;

    /**
     * Creates new form Graficos
     */
    public Graficos(Paciente paciente, Bot bot, Logs logs) {
        this.setPaciente(paciente);
        this.setBot(bot);
        this.setLogs(logs);
        initComponents();
        setLocationRelativeTo(null);
        URL imageUrl = this.getClass().getResource("/Imagens/frameIcon.png");
        ImageIcon img = new ImageIcon(imageUrl);
        setIconImage(img.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonAud = new javax.swing.JButton();
        jButtonSapo = new javax.swing.JButton();
        jButtonEdu = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAud.setText("Gráfico Audição e Reação");
        jButtonAud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAudActionPerformed(evt);
            }
        });

        jButtonSapo.setText("Gráfico Atividade do Sapo");
        jButtonSapo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSapoActionPerformed(evt);
            }
        });

        jButtonEdu.setText("Gráfico Atividade Reflexão e Educação");
        jButtonEdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEduActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lagoa2.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mic2.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/diploma.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButtonEdu)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButtonSapo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAud, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEdu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAud, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonSapo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSapoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSapoActionPerformed
                //O tamanho do JFrame é editável, então altere esses valores com o tamanho da janela do que achar melhor.
        
JFrame f = new JFrame();

Estatistica estatistica = new Estatistica();

double media = 0;
int i = 0;
int count = 0;
boolean nulo = false;
DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

for (Estatistica estatisticaConsultadoNome : estatistica.consultadoPorNome(getPaciente().getNomePaciente())) {
    if (estatisticaConsultadoNome.getNota1() == null) {
        nulo = true;
        count = 1;
        break;
    } else {
    ++count; 
    }
        }

double[] values = new double[count];
String[] names = new String[count];

    
for (Estatistica estatisticaConsultadoNome : estatistica.consultadoPorNome(getPaciente().getNomePaciente())){
    
    if (nulo == true) {
    values[i] = 0;
    //System.out.println(values[i]);
    names[i] = "";
    //System.out.println(names[i]);
    JOptionPane.showMessageDialog(null, "Ainda não existem notas para esse Paciente!", "Estatística", 1);
    break;
    
    } else {
        
    //System.out.println(estatisticaConsultadoNome.getNota1());
    values[i] = estatisticaConsultadoNome.getNota1();
    
    //System.out.println(df.format(estatisticaConsultadoNome.getData()));
    names[i] = (df.format(estatisticaConsultadoNome.getData()));
   
    media = media + values[i];
    
    
    ++i;
    
    }
}

if (nulo == false) {
    f.getContentPane().add(new ChartPanel(values, names, "Atividade do Sapo", "Média: " + media/i)); //Título do gráfico

    WindowListener wndCloser = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        //System.exit(0);
      }
    };
    int larguraBarras = 85 * i;    //Na classe ChartPanel, o Tamanho de cada barra do gráfico está definido como 85
    if (larguraBarras > 625) {
        f.setSize(larguraBarras + 20, 350);
    } else {
        f.setSize(625, 350);
    }
    f.setLocationRelativeTo(null);
    f.addWindowListener(wndCloser);
    f.setVisible(true);
    f.setResizable(false);
}
    }//GEN-LAST:event_jButtonSapoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
                setTitle("Paciente " + getPaciente().getNomePaciente());
        try {
            FileInputStream fstream = new FileInputStream(getLogs().getEnderecoPaciente());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String texto = "";
            while ((strLine = br.readLine()) != null) {
                texto += strLine + "\n";
            }
            

            FileInputStream fstream2 = new FileInputStream(getLogs().getEnderecoResponsavel());
            DataInputStream in2 = new DataInputStream(fstream2);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
            String strLine2;
            String texto2 = "";
            while ((strLine2 = br2.readLine()) != null) {
                texto2 += strLine2 + "\n";
            }
           
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButtonEduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEduActionPerformed
                
JFrame f = new JFrame();

Estatistica estatistica = new Estatistica();

double media = 0;
int i = 0;
int count = 0;
boolean nulo = false;
DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

for (Estatistica estatisticaConsultadoNome : estatistica.consultadoPorNome(getPaciente().getNomePaciente())) {
    if (estatisticaConsultadoNome.getNota3() == null) {
        nulo = true;
        count = 1;
        break;
    } else {
    ++count; 
    }
        }

double[] values = new double[count];
String[] names = new String[count];

    
for (Estatistica estatisticaConsultadoNome : estatistica.consultadoPorNome(getPaciente().getNomePaciente())){
    
    if (nulo == true) {
    values[i] = 0;
    //System.out.println(values[i]);
    names[i] = "";
    //System.out.println(names[i]);
    JOptionPane.showMessageDialog(null, "Ainda não existem notas para esse Paciente!", "Estatística", 1);
    break;
    
    } else {
        
    //System.out.println(estatisticaConsultadoNome.getNota3());
    values[i] = estatisticaConsultadoNome.getNota3();
    
    //System.out.println(df.format(estatisticaConsultadoNome.getData()));
    names[i] = (df.format(estatisticaConsultadoNome.getData()));
    
    media = media + values[i];
    
    ++i;
    }
}

if (nulo == false) {
    f.getContentPane().add(new ChartPanel(values, names, "Atividade Reflexão e Educação", "Média: " + media/i)); //Título do gráfico

    WindowListener wndCloser = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        //System.exit(0);
      }
    };
        int larguraBarras = 85 * i;    //Na classe ChartPanel, o Tamanho de cada barra do gráfico está definido como 85
    if (larguraBarras > 625) {
        f.setSize(larguraBarras + 20, 350);
    } else {
        f.setSize(625, 350);
    }
    f.setLocationRelativeTo(null);
    f.addWindowListener(wndCloser);
    f.setVisible(true);
    f.setResizable(false);
}
    }//GEN-LAST:event_jButtonEduActionPerformed

    private void jButtonAudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAudActionPerformed
                
JFrame f = new JFrame();

Estatistica estatistica = new Estatistica();

double media = 0;
int i = 0;
int count = 0;
boolean nulo = false;
DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

for (Estatistica estatisticaConsultadoNome : estatistica.consultadoPorNome(getPaciente().getNomePaciente())) {
    if (estatisticaConsultadoNome.getNota2() == null) {
        nulo = true;
        count = 1;
        break;
    } else {
    ++count; 
    }
        }

double[] values = new double[count];
String[] names = new String[count];

    
for (Estatistica estatisticaConsultadoNome : estatistica.consultadoPorNome(getPaciente().getNomePaciente())){
    
    if (nulo == true) {
    values[i] = 0;
    //System.out.println(values[i]);
    names[i] = "";
    //System.out.println(names[i]);
    JOptionPane.showMessageDialog(null, "Ainda não existem notas para esse Paciente!", "Estatística", 1);
    break;
    }
    if (estatisticaConsultadoNome.getNota2() < 0) {
        values[i] = 0;
        names[i] = (df.format(estatisticaConsultadoNome.getData()));
        media = media + values[i];
        ++i;
        
    } else{
        
    //System.out.println(estatisticaConsultadoNome.getNota2());
    values[i] = estatisticaConsultadoNome.getNota2();
    
    //System.out.println(df.format(estatisticaConsultadoNome.getData()));
    names[i] = (df.format(estatisticaConsultadoNome.getData()));
    
    media = media + values[i];
    
    ++i;
    }
        
}

if (nulo == false) {
    f.getContentPane().add(new ChartPanel(values, names, "Atividade Audição e Reação", "Média: " + media/i)); //Título do gráfico

    WindowListener wndCloser = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        //System.exit(0);
      }
    };
        int larguraBarras = 85 * i;    //Na classe ChartPanel, o Tamanho de cada barra do gráfico está definido como 85
    if (larguraBarras > 625) {
        f.setSize(larguraBarras + 20, 350);
    } else {
        f.setSize(625, 350);
    }
    f.setLocationRelativeTo(null);
    f.addWindowListener(wndCloser);
    f.setVisible(true);
    f.setResizable(false);
}
    }//GEN-LAST:event_jButtonAudActionPerformed

   
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Bot getBot() {
        return this.bot;
    }

    public void setLogs(Logs logs) {
        this.logs = logs;
    }

    public Logs getLogs() {
        return this.logs;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAud;
    private javax.swing.JButton jButtonEdu;
    private javax.swing.JButton jButtonSapo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
