/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atividades;

import Controle.Estatistica;
import Controle.Paciente;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.String.format;
import static java.lang.String.format;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author Breno
 */
public class AudFrame extends javax.swing.JFrame {

    private Estatistica estatistica;
    String dateLog;
    Paciente paciente;
    Boolean ouvindoMic = false;
    File wavFile;
    Clip clip;
    AudioInputStream ais;
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    TargetDataLine line;
    Timer timer;

    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    public AudFrame(Paciente paciente, Estatistica estatistica) {
        initComponents();
        URL imageUrl = this.getClass().getResource("/Imagens/frameIcon.png");
        ImageIcon img = new ImageIcon(imageUrl);
        setIconImage(img.getImage());
        setPaciente(paciente);
        setEstatistica(estatistica);
        setVisible(true);
        setTitle("Atividade: Audição e Reação");
        setLocationRelativeTo(null);
    }

    public void mostraTela(String nome) {
        CardLayout card = (CardLayout) this.jPanelL.getLayout();
        card.show(this.jPanelL, nome);
    }

    public void finish() {
        line.stop();
        line.close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelL = new javax.swing.JPanel();
        jPanelBegin = new javax.swing.JPanel();
        jPanelT2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanelT1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelIntro = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelIntro1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonMic1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButtonSug1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButtonNext1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jProgressBarSit1 = new javax.swing.JProgressBar();
        jButtonPlaySit1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelIntro2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButtonSug2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtonMic2 = new javax.swing.JButton();
        jButtonNext2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButtonBack2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButtonPlaySit2 = new javax.swing.JButton();
        jProgressBarSit2 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jLabelIntro3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButtonBack3 = new javax.swing.JButton();
        jButtonSug3 = new javax.swing.JButton();
        jButtonMic3 = new javax.swing.JButton();
        jButtonNext3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButtonPlaySit3 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jProgressBarSit3 = new javax.swing.JProgressBar();
        jPanel4 = new javax.swing.JPanel();
        jLabelIntro4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButtonBack4 = new javax.swing.JButton();
        jButtonSug4 = new javax.swing.JButton();
        jButtonMic4 = new javax.swing.JButton();
        jButtonNext4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButtonPlaySit4 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jProgressBarSit4 = new javax.swing.JProgressBar();
        jPanel5 = new javax.swing.JPanel();
        jLabelIntro5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButtonBack5 = new javax.swing.JButton();
        jButtonSug5 = new javax.swing.JButton();
        jButtonMic5 = new javax.swing.JButton();
        jButtonNext5 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButtonPlaySit5 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jProgressBarSit5 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelL.setLayout(new java.awt.CardLayout());

        jPanelT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setText("<html>Exemplo:\n\nDada a seguinte situação:\n\n<br>\"Um amigo seu disse: A minha sobremesa favorita é bolo de chocolate! Você concorda comigo?\"\n\n<br><br>Sugestões de resposta: \n\n<br>Se voce não concorda com ele, opte por não responder algo muito direto como \"Não, eu não concordo\". Responda algo como \"Bolo de chocolate é bom, mas eu prefiro sorvete de morango\", ou \"Eu não sou muito fã de bolo de chocolate. Prefiro um sorvete de morango\"\n\n<br>Se você concorda com ele, responda algo como: \"Concordo! Bolo de chocolate é muito bom mesmo! Ainda mais se tiver uma calda de chocolate por cima!\"</html>");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelT2Layout = new javax.swing.GroupLayout(jPanelT2);
        jPanelT2.setLayout(jPanelT2Layout);
        jPanelT2Layout.setHorizontalGroup(
            jPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelT2Layout.setVerticalGroup(
            jPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("<html>Não é necessário se preocupar se não souber como responder-las. <br> Em cada situação haverá um botão que mostrará algumas sugestões de resposta <br>que você poderá repetí-las ou usá-las como base para formular suas próprias respostas! <br>Procure falar durante um intervalo de 10 a 15 segundos, para ir treinando as suas habilidades comunicativas!\n<br><br>\nSe ainda assim você não se sentir confortável para responder a pergunta da situação, haverá um botão para pular para a próxima situação.</html>");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("<html>\tNessa atividade serão dadas 5 situações em que você terá que responder falando sua opinião pelo microfone. <br>Para responder é só clicar no botão \"Responder\" e falar!</html>");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelT1Layout = new javax.swing.GroupLayout(jPanelT1);
        jPanelT1.setLayout(jPanelT1Layout);
        jPanelT1Layout.setHorizontalGroup(
            jPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelT1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelT1Layout.setVerticalGroup(
            jPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelT1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelIntro.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelIntro.setText("Olá, bem-vindo ao treinamento de situações sociais cotidianas!");

        jButtonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/play.png"))); // NOI18N
        jButtonIniciar.setText("Iniciar Atividade");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBeginLayout = new javax.swing.GroupLayout(jPanelBegin);
        jPanelBegin.setLayout(jPanelBeginLayout);
        jPanelBeginLayout.setHorizontalGroup(
            jPanelBeginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBeginLayout.createSequentialGroup()
                .addGroup(jPanelBeginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBeginLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBeginLayout.createSequentialGroup()
                        .addGroup(jPanelBeginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBeginLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabelIntro))
                            .addGroup(jPanelBeginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanelT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBeginLayout.createSequentialGroup()
                                .addGap(292, 292, 292)
                                .addComponent(jButtonIniciar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelBeginLayout.setVerticalGroup(
            jPanelBeginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBeginLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelIntro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanelT1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanelT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButtonIniciar)
                .addGap(21, 21, 21))
        );

        jPanelL.add(jPanelBegin, "card0");

        jLabelIntro1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelIntro1.setText("Situação 1");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sit1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setText("Um amigo seu disse: Eu acho Matemática legal! E você? Gosta dessa matéria? ");

        jButtonMic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mic.png"))); // NOI18N
        jButtonMic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMic1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Gravar Opinião");

        jButtonSug1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/helpa.png"))); // NOI18N
        jButtonSug1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSug1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Sugestões");

        jButtonNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next.png"))); // NOI18N
        jButtonNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNext1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Próxima Situação");

        jButtonPlaySit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/playaudio.png"))); // NOI18N
        jButtonPlaySit1.setToolTipText("");
        jButtonPlaySit1.setEnabled(false);
        jButtonPlaySit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlaySit1ActionPerformed(evt);
            }
        });

        jLabel33.setText("Ouvir sua gravação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jProgressBarSit1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSug1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(116, 116, 116)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonMic1))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8))
                            .addComponent(jButtonNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonPlaySit1)
                    .addComponent(jLabel33))
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabelIntro1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabelIntro1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jButtonPlaySit1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jProgressBarSit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNext1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSug1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMic1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(28, 28, 28))
        );

        jPanelL.add(jPanel1, "card1");

        jLabelIntro2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelIntro2.setText("Situação 2");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sit2.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel10.setText("<html>Um professor de artes mostra essa pintura durante a aula e pede para que você diga<br> em voz alta a sua opinião sobre o quadro.</html>");

        jButtonSug2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/helpa.png"))); // NOI18N
        jButtonSug2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSug2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Sugestões");

        jLabel12.setText("Gravar Opinião");

        jButtonMic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mic.png"))); // NOI18N
        jButtonMic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMic2ActionPerformed(evt);
            }
        });

        jButtonNext2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next.png"))); // NOI18N
        jButtonNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNext2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Próxima Situação");

        jButtonBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/backa.png"))); // NOI18N
        jButtonBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBack2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Situação Anterior");

        jLabel34.setText("Ouvir sua gravação");

        jButtonPlaySit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/playaudio.png"))); // NOI18N
        jButtonPlaySit2.setToolTipText("");
        jButtonPlaySit2.setEnabled(false);
        jButtonPlaySit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlaySit2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabelIntro2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 98, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jButtonPlaySit2))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBarSit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14))
                            .addComponent(jButtonBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSug2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(26, 26, 26)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonMic2))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13))
                            .addComponent(jButtonNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addGap(110, 110, 110))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabelIntro2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jButtonPlaySit2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)))
                .addGap(26, 26, 26)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jProgressBarSit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNext2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSug2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMic2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addGap(28, 28, 28))
        );

        jPanelL.add(jPanel2, "card2");

        jLabelIntro3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelIntro3.setText("Situação 3");

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sit3.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel16.setText("<html>Dois de seus amigos estão discutindo sobre matérias escolares:<br>\n\nUm diz: \"Português é muito mais fácil que matemática! Não tem que ficar fazendo esses cálculos chatos!\"<br>\n\no Outro diz: \"Matemática é que mais fácil! A interpretação de problemas e<br>o uso de cálculos é muito mais simples e rápido\"<br>\n\nAmbos pedem a sua opinião sobre a discussão.</html>");

        jButtonBack3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/backa.png"))); // NOI18N
        jButtonBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBack3ActionPerformed(evt);
            }
        });

        jButtonSug3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/helpa.png"))); // NOI18N
        jButtonSug3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSug3ActionPerformed(evt);
            }
        });

        jButtonMic3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mic.png"))); // NOI18N
        jButtonMic3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMic3ActionPerformed(evt);
            }
        });

        jButtonNext3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next.png"))); // NOI18N
        jButtonNext3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNext3ActionPerformed(evt);
            }
        });

        jLabel17.setText("Próxima Situação");

        jLabel18.setText("Gravar Opinião");

        jLabel19.setText("Sugestões");

        jLabel20.setText("Situação Anterior");

        jButtonPlaySit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/playaudio.png"))); // NOI18N
        jButtonPlaySit3.setToolTipText("");
        jButtonPlaySit3.setEnabled(false);
        jButtonPlaySit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlaySit3ActionPerformed(evt);
            }
        });

        jLabel35.setText("Ouvir sua gravação");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabelIntro3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel35))
                    .addComponent(jButtonPlaySit3))
                .addGap(27, 27, 27))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel20))
                            .addComponent(jButtonBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSug3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(26, 26, 26)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonMic3))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel17))
                            .addComponent(jButtonNext3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jProgressBarSit3, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabelIntro3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButtonPlaySit3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35)))
                .addGap(13, 13, 13)
                .addComponent(jProgressBarSit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNext3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSug3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMic3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(20, 20, 20))
        );

        jPanelL.add(jPanel3, "card3");

        jLabelIntro4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelIntro4.setText("Situação 4");

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sit4.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel22.setText("<html>Durante a aula de educação física, dois de seu grupo de amigos estão decidindo entre <br> jogar futebol ou handebol. Eles pedem a sua opinião.</html>");

        jButtonBack4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/backa.png"))); // NOI18N
        jButtonBack4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBack4ActionPerformed(evt);
            }
        });

        jButtonSug4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/helpa.png"))); // NOI18N
        jButtonSug4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSug4ActionPerformed(evt);
            }
        });

        jButtonMic4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mic.png"))); // NOI18N
        jButtonMic4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMic4ActionPerformed(evt);
            }
        });

        jButtonNext4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next.png"))); // NOI18N
        jButtonNext4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNext4ActionPerformed(evt);
            }
        });

        jLabel23.setText("Próxima Situação");

        jLabel24.setText("Gravar Opinião");

        jLabel25.setText("Sugestões");

        jLabel26.setText("Situação Anterior");

        jButtonPlaySit4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/playaudio.png"))); // NOI18N
        jButtonPlaySit4.setToolTipText("");
        jButtonPlaySit4.setEnabled(false);
        jButtonPlaySit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlaySit4ActionPerformed(evt);
            }
        });

        jLabel36.setText("Ouvir sua gravação");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelIntro4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel36))
                    .addComponent(jButtonPlaySit4))
                .addGap(32, 32, 32))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel26))
                                    .addComponent(jButtonBack4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSug4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(26, 26, 26)))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jButtonMic4))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel23))
                                    .addComponent(jButtonNext4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jProgressBarSit4, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelIntro4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jButtonPlaySit4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jProgressBarSit4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack4, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jButtonNext4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSug4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMic4, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(31, 31, 31))
        );

        jPanelL.add(jPanel4, "card4");

        jLabelIntro5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelIntro5.setText("Situação 5");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sit5.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel28.setText("<html>Está um dia frio. Um de seus amigos diz:<br>\n\n\"Eu não gosto de dias frios! Da muita preguiça geral de fazer as coisas.<br> E é mais facil de ficar doente! Não acha?\"</html>");

        jButtonBack5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/backa.png"))); // NOI18N
        jButtonBack5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBack5ActionPerformed(evt);
            }
        });

        jButtonSug5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/helpa.png"))); // NOI18N
        jButtonSug5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSug5ActionPerformed(evt);
            }
        });

        jButtonMic5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mic.png"))); // NOI18N
        jButtonMic5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMic5ActionPerformed(evt);
            }
        });

        jButtonNext5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next.png"))); // NOI18N
        jButtonNext5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNext5ActionPerformed(evt);
            }
        });

        jLabel29.setText("Finalizar");

        jLabel30.setText("Gravar Opinião");

        jLabel31.setText("Sugestões");

        jLabel32.setText("Situação Anterior");

        jButtonPlaySit5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/playaudio.png"))); // NOI18N
        jButtonPlaySit5.setToolTipText("");
        jButtonPlaySit5.setEnabled(false);
        jButtonPlaySit5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlaySit5ActionPerformed(evt);
            }
        });

        jLabel37.setText("Ouvir sua gravação");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelIntro5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel37))
                    .addComponent(jButtonPlaySit5))
                .addGap(37, 37, 37))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBarSit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel32))
                            .addComponent(jButtonBack5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSug5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(26, 26, 26)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonMic5))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jButtonNext5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel29)))))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelIntro5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jButtonPlaySit5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37)))
                .addGap(20, 20, 20)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBarSit5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack5, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jButtonNext5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSug5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMic5, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addGap(31, 31, 31))
        );

        jPanelL.add(jPanel5, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        mostraTela("card1");
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButtonNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNext1ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit1.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic1.isEnabled()) {
            mostraTela("card2");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonNext1ActionPerformed

    private void jButtonBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBack2ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit2.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic2.isEnabled()) {
             mostraTela("card1");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonBack2ActionPerformed

    private void jButtonBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBack3ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit3.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic3.isEnabled()) {
             mostraTela("card2");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonBack3ActionPerformed

    private void jButtonNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNext2ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit2.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic2.isEnabled()) {
            mostraTela("card3");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonNext2ActionPerformed

    private void jButtonNext3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNext3ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit3.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic3.isEnabled()) {
            mostraTela("card4");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonNext3ActionPerformed

    private void jButtonBack4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBack4ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit3.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic3.isEnabled()) {
             mostraTela("card3");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonBack4ActionPerformed

    private void jButtonNext4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNext4ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit4.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic4.isEnabled()) {
            mostraTela("card5");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonNext4ActionPerformed

    private void jButtonBack5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBack5ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit3.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic3.isEnabled()) {
             mostraTela("card4");
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonBack5ActionPerformed

    private void jButtonNext5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNext5ActionPerformed
        if (ouvindoMic) {
            clip.stop();
            this.jButtonPlaySit5.setEnabled(true);
            ouvindoMic = false;
        }
        if (this.jButtonMic5.isEnabled()) {
            getEstatistica().setNota2(-0.1);
            getEstatistica().atualizado();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Espere a gravação terminar!", "Gravação", 1);
        }
    }//GEN-LAST:event_jButtonNext5ActionPerformed

    private void jButtonSug1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSug1ActionPerformed
        JOptionPane.showMessageDialog(null, "Seja qual for a sua opinião, procure não responder de forma monossilábica como \"Sim\" e \"Não\". Tente demonstrar uma certa empatia e interesse pela pergunta, falando a sua opinião e depois justificando-a.\n"
                + "\n"
                + "Se você concorda com ele, responda algo como: \"Sim, eu gosto muito dessa matéria. Adoro achar o resultado de um problema usando cálculos. Acho algo bem bacana.\"\n"
                + "\n"
                + "Se você não concorda com ele, responda algo como: \"Não gosto muito. Prefiro estudar sobre animais e a ciência por trás deles.\"", "Sugestão 1", 1);
    }//GEN-LAST:event_jButtonSug1ActionPerformed

    private void jButtonMic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMic1ActionPerformed
        try {
            jButtonMic1.setEnabled(false);
            jButtonNext1.setEnabled(false);
            if (ouvindoMic) {
                clip.stop();
            }

            this.jButtonPlaySit1.setEnabled(false);
            wavFile = new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit1" + dateLog + ".wav");
            timer = new Timer(160, new ActionListener() {

                private int counter = 1;

                @Override
                public void actionPerformed(ActionEvent ae) {
                    jProgressBarSit1.setValue(++counter);
                    if (counter > 100) {
                        timer.stop();
                    }
                }
            });
            timer.start();
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                JOptionPane.showMessageDialog(null, "O Microfone foi desconectado!", "Microfone", 1);
                dispose();
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            ais = new AudioInputStream(line);

            Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioSystem.write(ais, fileType, wavFile);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });

            stopper.start();

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            jButtonMic1.setEnabled(true);
                            jButtonNext1.setEnabled(true);
                            jButtonPlaySit1.setEnabled(true);
                        }
                    },
                    16000
            );
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonMic1ActionPerformed

    private void jButtonSug2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSug2ActionPerformed
        JOptionPane.showMessageDialog(null, "Se você gostar, diga algo como: \"Eu gostei dessa pintura. As cores e as formas abstratas além de o tornarem algo único e original, expressam os sentimentos do autor.\"\n"
                + "\n"
                + "Se você não gostar, diga algo como: \"Não gostei muito não. Prefiro algo mais clássico e definido.\"", "Sugestão 2", 1);
    }//GEN-LAST:event_jButtonSug2ActionPerformed

    private void jButtonMic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMic2ActionPerformed
        try {
            jButtonBack2.setEnabled(false);
            jButtonNext2.setEnabled(false);
            jButtonMic2.setEnabled(false);
            this.jButtonPlaySit2.setEnabled(false);
            wavFile = new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit2" + dateLog + ".wav");
            timer = new Timer(160, new ActionListener() {

                private int counter = 1;

                @Override
                public void actionPerformed(ActionEvent ae) {
                    jProgressBarSit2.setValue(++counter);
                    if (counter > 100) {
                        timer.stop();
                    }
                }
            });
            timer.start();
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                JOptionPane.showMessageDialog(null, "O Microfone foi desconectado!", "Microfone", 1);
                dispose();
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            ais = new AudioInputStream(line);

            Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioSystem.write(ais, fileType, wavFile);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });

            stopper.start();

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            jButtonBack2.setEnabled(true);
                            jButtonNext2.setEnabled(true);
                            jButtonMic2.setEnabled(true);
                            jButtonPlaySit2.setEnabled(true);
                        }
                    },
                    16000
            );
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonMic2ActionPerformed

    private void jButtonSug3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSug3ActionPerformed
        JOptionPane.showMessageDialog(null, "Nessa situação, você pode concordar com um deles:\n"
                + "\n"
                + "\"Eu prefiro português, os cálculos para mim são o que dificultam na matemática.\"\n"
                + "\n"
                + "\"Eu prefiro matemática, para mim são os textos grandes em português que o fazem difícil.\"", "Sugestão 3", 1);
    }//GEN-LAST:event_jButtonSug3ActionPerformed

    private void jButtonMic3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMic3ActionPerformed
        try {
            jButtonBack3.setEnabled(false);
            jButtonNext3.setEnabled(false);
            jButtonMic3.setEnabled(false);
            this.jButtonPlaySit3.setEnabled(false);
            wavFile = new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit3" + dateLog + ".wav");
            timer = new Timer(160, new ActionListener() {

                private int counter = 1;

                @Override
                public void actionPerformed(ActionEvent ae) {
                    jProgressBarSit3.setValue(++counter);
                    if (counter > 100) {
                        timer.stop();
                    }
                }
            });
            timer.start();
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                JOptionPane.showMessageDialog(null, "O Microfone foi desconectado!", "Microfone", 1);
                dispose();
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            ais = new AudioInputStream(line);

            Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioSystem.write(ais, fileType, wavFile);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });

            stopper.start();

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            jButtonBack3.setEnabled(true);
                            jButtonNext3.setEnabled(true);
                            jButtonMic3.setEnabled(true);
                            jButtonPlaySit3.setEnabled(true);
                        }
                    },
                    16000
            );
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonMic3ActionPerformed

    private void jButtonSug4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSug4ActionPerformed
        JOptionPane.showMessageDialog(null, "É possível concordar com um deles:\n"
                + "\n"
                + "\"Vamos jogar futebol, é mais rápido e dinâmico, ou seja, é mais divertido!\"\n"
                + "\n"
                + "\"Vamos jogar handebol, é menos bagunçado e mais estratégico.\"\n"
                + "\n"
                + "É possível propror algo diferente:\n"
                + "\n"
                + "\"Já jogamos futebol e handebol um monte de vezes! Vamos jogar algo diferente, tipo basquete!\"\n"
                + "\n"
                + "Ou, ainda, é possível negar ambos se você não tiver vontade de jogar:\n"
                + "\n"
                + "\"Decidam aí, hoje eu não tou a fim de jogar nada.\"", "Sugestão 4", 1);
    }//GEN-LAST:event_jButtonSug4ActionPerformed

    private void jButtonMic4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMic4ActionPerformed
        try {
            jButtonBack4.setEnabled(false);
            jButtonNext4.setEnabled(false);
            jButtonMic4.setEnabled(false);
            this.jButtonPlaySit4.setEnabled(false);
            wavFile = new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit4" + dateLog + ".wav");
            timer = new Timer(160, new ActionListener() {

                private int counter = 1;

                @Override
                public void actionPerformed(ActionEvent ae) {
                    jProgressBarSit4.setValue(++counter);
                    if (counter > 100) {
                        timer.stop();
                    }
                }
            });
            timer.start();
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                JOptionPane.showMessageDialog(null, "O Microfone foi desconectado!", "Microfone", 1);
                dispose();
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            ais = new AudioInputStream(line);

            Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioSystem.write(ais, fileType, wavFile);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });

            stopper.start();

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            jButtonBack4.setEnabled(true);
                            jButtonNext4.setEnabled(true);
                            jButtonMic4.setEnabled(true);
                            jButtonPlaySit4.setEnabled(true);
                        }
                    },
                    16000
            );
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonMic4ActionPerformed

    private void jButtonSug5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSug5ActionPerformed
        JOptionPane.showMessageDialog(null, "Se você gosta de dias frios, responda algo como: \"Eu gosto de dias frios, apesar de dar essa preguiça geral, há poucas coisas melhores do que tomar um chocolate quente no frio!\"\n"
                + "\n"
                + "Se você também não gosta de dias frios, responda algo como: \"Eu também não gosto de dias frios, a sensação de calafrios é desagradável e ficar doente é muito ruim! Prefiro Dias quentes, pois prefiro tomar sorvetes do que tomar chocolates quentes!\"", "Sugestão 5", 1);
    }//GEN-LAST:event_jButtonSug5ActionPerformed

    private void jButtonMic5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMic5ActionPerformed
        try {
            jButtonBack5.setEnabled(false);
            jButtonNext5.setEnabled(false);
            jButtonMic5.setEnabled(false);
            this.jButtonPlaySit5.setEnabled(false);
            wavFile = new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit5" + dateLog + ".wav");
            timer = new Timer(160, new ActionListener() {

                private int counter = 1;

                @Override
                public void actionPerformed(ActionEvent ae) {
                    jProgressBarSit5.setValue(++counter);
                    if (counter > 100) {
                        timer.stop();
                    }
                }
            });
            timer.start();
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                JOptionPane.showMessageDialog(null, "O Microfone foi desconectado!", "Microfone", 1);
                dispose();
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            ais = new AudioInputStream(line);

            Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioSystem.write(ais, fileType, wavFile);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            });

            stopper.start();

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            finish();
                            jButtonBack5.setEnabled(true);
                            jButtonNext5.setEnabled(true);
                            jButtonMic5.setEnabled(true);
                            jButtonPlaySit5.setEnabled(true);
                        }
                    },
                    16000
            );
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonMic5ActionPerformed

    private void jButtonPlaySit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlaySit1ActionPerformed
        try {
            if (!ouvindoMic) {
                AudioInputStream in = AudioSystem.getAudioInputStream(new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit1" + dateLog + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                ouvindoMic = false;
                                jButtonPlaySit1.setEnabled(true);
                            }
                        },
                        16000
                );
            }
            ouvindoMic = true;
            jButtonPlaySit1.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonPlaySit1ActionPerformed

    private void jButtonPlaySit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlaySit2ActionPerformed
        try {
            if (!ouvindoMic) {
                AudioInputStream in = AudioSystem.getAudioInputStream(new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit2" + dateLog + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                ouvindoMic = false;
                                jButtonPlaySit2.setEnabled(true);
                            }
                        },
                        16000
                );
            }
            ouvindoMic = true;
            jButtonPlaySit2.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonPlaySit2ActionPerformed

    private void jButtonPlaySit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlaySit3ActionPerformed
        try {
            if (!ouvindoMic) {
                AudioInputStream in = AudioSystem.getAudioInputStream(new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit3" + dateLog + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                ouvindoMic = false;
                                jButtonPlaySit3.setEnabled(true);
                            }
                        },
                        16000
                );
            }
            ouvindoMic = true;
            jButtonPlaySit3.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonPlaySit3ActionPerformed

    private void jButtonPlaySit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlaySit4ActionPerformed
        try {
            if (!ouvindoMic) {
                AudioInputStream in = AudioSystem.getAudioInputStream(new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit4" + dateLog + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                ouvindoMic = false;
                                jButtonPlaySit4.setEnabled(true);
                            }
                        },
                        16000
                );
            }
            ouvindoMic = true;
            jButtonPlaySit4.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonPlaySit4ActionPerformed

    private void jButtonPlaySit5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlaySit5ActionPerformed
        try {
            if (!ouvindoMic) {
                AudioInputStream in = AudioSystem.getAudioInputStream(new File("C:/SheldonChat/Logs/" + getPaciente().getNomePaciente() + "/Sit5" + dateLog + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                ouvindoMic = false;
                                jButtonPlaySit5.setEnabled(true);
                            }
                        },
                        16000
                );
            }
            ouvindoMic = true;
            jButtonPlaySit5.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonPlaySit5ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
            String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            dateLog = day + "-" + month + "-" + year;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String[] options = new String[2];
        options[0] = new String("Sim");
        options[1] = new String("Não");

        if (JOptionPane.showOptionDialog(null, "Deseja realmente sair?\nTudo o que fez será salvo!", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.OK_OPTION) {
            getEstatistica().setNota2(-0.1);
            getEstatistica().atualizado();
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setEstatistica(Estatistica estatistica) {
        this.estatistica = estatistica;
    }

    public Estatistica getEstatistica() {
        return this.estatistica;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack2;
    private javax.swing.JButton jButtonBack3;
    private javax.swing.JButton jButtonBack4;
    private javax.swing.JButton jButtonBack5;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButtonMic1;
    private javax.swing.JButton jButtonMic2;
    private javax.swing.JButton jButtonMic3;
    private javax.swing.JButton jButtonMic4;
    private javax.swing.JButton jButtonMic5;
    private javax.swing.JButton jButtonNext1;
    private javax.swing.JButton jButtonNext2;
    private javax.swing.JButton jButtonNext3;
    private javax.swing.JButton jButtonNext4;
    private javax.swing.JButton jButtonNext5;
    private javax.swing.JButton jButtonPlaySit1;
    private javax.swing.JButton jButtonPlaySit2;
    private javax.swing.JButton jButtonPlaySit3;
    private javax.swing.JButton jButtonPlaySit4;
    private javax.swing.JButton jButtonPlaySit5;
    private javax.swing.JButton jButtonSug1;
    private javax.swing.JButton jButtonSug2;
    private javax.swing.JButton jButtonSug3;
    private javax.swing.JButton jButtonSug4;
    private javax.swing.JButton jButtonSug5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIntro;
    private javax.swing.JLabel jLabelIntro1;
    private javax.swing.JLabel jLabelIntro2;
    private javax.swing.JLabel jLabelIntro3;
    private javax.swing.JLabel jLabelIntro4;
    private javax.swing.JLabel jLabelIntro5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBegin;
    private javax.swing.JPanel jPanelL;
    private javax.swing.JPanel jPanelT1;
    private javax.swing.JPanel jPanelT2;
    private javax.swing.JProgressBar jProgressBarSit1;
    private javax.swing.JProgressBar jProgressBarSit2;
    private javax.swing.JProgressBar jProgressBarSit3;
    private javax.swing.JProgressBar jProgressBarSit4;
    private javax.swing.JProgressBar jProgressBarSit5;
    // End of variables declaration//GEN-END:variables
}
