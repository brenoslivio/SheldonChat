/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atividades;

import Atividades.Bolha;
import Controle.Estatistica;
import Main.Main;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Port;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Breno
 */
public class GameFrame extends javax.swing.JFrame {

    Estatistica estatistica;

    Clip clip;
    static boolean ativar = false;
    static boolean ultimo = false;
    static boolean fechando = false;
    static boolean falando = false;
    static int contThread = 0;
    static boolean mainExe = false;
    static boolean recognizerBoo = true;
    static boolean pedido1 = false, pedido2 = false, pedido2n = false, pedido3 = false, agradecimento = false, agradecimenton = false, preparar = false;
    static boolean numero1 = false, numero2 = false, numero3 = false;
    boolean exe = true;
    Integer pontos = 0;
    Label label = new Label();
    Point point;
    static Bolha bolhaj = new Bolha();
    static boolean bolha = false, bThread1 = false, bThread2 = false, bThread3 = false, bThread4 = false, bThread5 = false, bAcabou = false;
    static boolean bolha1 = true, bolha2 = true, bolha3 = true, bolha4 = true;
    boolean sapoaberto = false;
    boolean bolha1e = false, bolha2e = false, bolha3e = false, bolha4e = false;
    static Thread th1 = new Thread(bolhaj);
    static Thread th2 = new Thread(bolhaj);
    static Thread th3 = new Thread(bolhaj);
    static Thread th4 = new Thread(bolhaj);
    static Thread th5 = new Thread(bolhaj);
    /**
     * Creates new form GameFrame
     */
    private Image dbImage;
    private Graphics dbg;
    static ConfigurationManager cm;

    public GameFrame(Estatistica estatistica) {
        this.setEstatistica(estatistica);

        initComponents();
        setTitle("O Sapo Comedor de Bolhas");
        URL imageUrl = this.getClass().getResource("/Imagens/frameIcon.png");
        ImageIcon img = new ImageIcon(imageUrl);
        setIconImage(img.getImage());
        //setVisible(true);
        //setSize(600, 600);
        setLocationRelativeTo(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL imageUrlSapoF = this.getClass().getResource("/Imagens/fechado.gif");
        Image image = toolkit.getImage(imageUrlSapoF);
        Cursor c = toolkit.createCustomCursor(image, new Point(16, 16), "Sapo Fechado");
        setCursor(c);
        myMouseListener mml = new myMouseListener();
        addMouseListener(mml);
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        label.setBounds(200, 70, 220, 20);
        add(label);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            pedido1 = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                2500);
    }

    public static void numero3() {
        numero3 = true;
        numero2();
    }

    public static void numero2() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            numero2 = true;
                            numero3 = false;
                            numero1();
                        } catch (Exception e) {
                        }
                    }
                },
                1000);
    }

    public static void numero1() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            numero1 = true;
                            numero2 = false;
                            falando();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                1000);
    }

    public static void falando() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            falando = true;
                            preparar = false;
                            numero1 = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                1000);
    }

    public void fechando() {
        getEstatistica().setNota1(pontos.doubleValue() / 2);
        getEstatistica().atualizado();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            dispose();
                            th1.interrupt();
                            th2.interrupt();
                            th3.interrupt();
                            th4.interrupt();
                            th5.interrupt();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                2000);
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {

        URL imageUrlBack = this.getClass().getResource("/Imagens/Lagoa.jpg");
        ImageIcon iconBack = new ImageIcon(imageUrlBack);
        Image imgBack = iconBack.getImage();
        g.drawImage(imgBack, 0, 0, null);

        label.setText("Bolhas Estouradas: " + pontos);

        if (pedido1) {
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Pedido1.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 300, null);
        }

        if (preparar) {
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/preparar.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 270, null);
        }

        if (bolhaj.y <= -550 && !preparar && bThread1 && pontos >= 1 || bolhaj.y <= -550 && !preparar && bThread2 && pontos >= 1) {
            bolha = false;
            pedido2 = true;
        } else {
            pedido2 = false;
        }
        if (pedido2) {
            bolhaj.setBoolean(false);
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Pedido2.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 360, null);
        }

        if (bolhaj.y <= -550 && !preparar && bThread1 && pontos == 0 || bolhaj.y <= -550 && !preparar && bThread2 && pontos == 0) {
            bolha = false;
            pedido2n = true;
        } else {
            pedido2n = false;
        }
        if (pedido2n) {
            bolhaj.setBoolean(false);
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Imagem1n.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 450, null);
        }

        if (bolhaj.y <= -550 && !preparar && bThread3 && pontos >= 1 || bolhaj.y <= -550 && !preparar && bThread4 && pontos >= 1) {
            bolha = false;
            pedido3 = true;
        } else {
            pedido3 = false;
        }
        if (pedido3) {
            bolhaj.setBoolean(false);
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Pedido3.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 300, null);
        }

        if (bolhaj.y <= -550 && !preparar && bThread3 && pontos == 0 || bolhaj.y <= -550 && !preparar && bThread4 && pontos == 0) {
            bolha = false;
            pedido2n = true;
        } else {
            pedido2n = false;
        }
        if (pedido2n) {
            bolhaj.setBoolean(false);
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Imagem1n.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 450, null);
        }

        if (bolhaj.y <= -550 && ultimo && pontos >= 1) {
            agradecimento = true;
        } else {
            agradecimento = false;
        }
        if (agradecimento) {
            bolhaj.setBoolean(false);
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Agradecimento.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 495, y - 300, null);
            if (!fechando) {
                fechando();
                fechando = true;
            }
        }

        if (bolhaj.y <= -550 && ultimo && pontos == 0) {
            agradecimenton = true;
        } else {
            agradecimenton = false;
        }
        if (agradecimenton) {
            bolhaj.setBoolean(false);
            URL imageUrlPedido1 = this.getClass().getResource("/Imagens/Agradecimenton.png");
            ImageIcon iconPedido1 = new ImageIcon(imageUrlPedido1);
            Image imgPedido1 = iconPedido1.getImage();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            g.drawImage(imgPedido1, x - 700, y - 355, null);
            if (!fechando) {
                fechando();
                fechando = true;
            }
        }

        if (numero3) {
            URL imageUrlNumber = this.getClass().getResource("/Imagens/numbeThree.png");
            ImageIcon iconNumber = new ImageIcon(imageUrlNumber);
            Image imgNumber = iconNumber.getImage();
            g.drawImage(imgNumber, 235, 235, null);
            bolha = true;
        }
        if (numero2) {
            URL imageUrlNumber = this.getClass().getResource("/Imagens/numberTwo.png");
            ImageIcon iconNumber = new ImageIcon(imageUrlNumber);
            Image imgNumber = iconNumber.getImage();
            g.drawImage(imgNumber, 235, 235, null);
        }
        if (numero1) {
            URL imageUrlNumber = this.getClass().getResource("/Imagens/numberOne.png");
            ImageIcon iconNumber = new ImageIcon(imageUrlNumber);
            Image imgNumber = iconNumber.getImage();
            g.drawImage(imgNumber, 235, 235, null);
        }

        if (bolhaj.y <= -550 && ativar) {
            falando = false;
            exe = true;
            ativar = false;
        }

        if (falando) {
            bolhaj.setBoolean(true);
            if (exe) {
                String[] args = {};
                this.mainA(args);
                exe = false;
            }

            if (bolha1e) {
                try {
                    InputStream in = Main.class.getResourceAsStream("/Sons/BubbleEffect.wav");
                    InputStream bufferedIn = new BufferedInputStream(in);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                }

                pontos++;
                //g.dispose();
                bolha1 = false;
                bolha1e = false;
            }
            if (bolha1) {
                URL imageBolha1 = this.getClass().getResource("/Imagens/Bolha.png");
                ImageIcon icon1 = new ImageIcon(imageBolha1);
                Image img1 = icon1.getImage();
                Ellipse2D circle1 = new Ellipse2D.Double(bolhaj.x - 10, bolhaj.y - 10, 80, 80);
                g.drawImage(img1, bolhaj.x, bolhaj.y, null);

                //System.out.println(bolhaj.y);
                if (point != null) {
                    if (circle1.contains(point)) {
                        bolha1e = true;
                    }
                }
            }
            if (bolha2e) {
                try {
                    InputStream in = Main.class.getResourceAsStream("/Sons/BubbleEffect.wav");
                    InputStream bufferedIn = new BufferedInputStream(in);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                }

                pontos++;
                //g.dispose();
                bolha2 = false;
                bolha2e = false;
            }
            if (bolha2) {
                URL imageBolha2 = this.getClass().getResource("/Imagens/Bolha.png");
                ImageIcon icon2 = new ImageIcon(imageBolha2);
                Image img2 = icon2.getImage();
                Ellipse2D circle2 = new Ellipse2D.Double(bolhaj.x + 140, bolhaj.y + 290, 80, 80);
                g.drawImage(img2, bolhaj.x + 150, bolhaj.y + 300, null);

                if (point != null) {
                    if (circle2.contains(point)) {
                        bolha2e = true;
                    }
                }
            }

            if (bolha3e) {
                try {
                    InputStream in = Main.class.getResourceAsStream("/Sons/BubbleEffect.wav");
                    InputStream bufferedIn = new BufferedInputStream(in);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                }

                pontos++;
                //g.dispose();
                bolha3 = false;
                bolha3e = false;
            }
            if (bolha3) {
                URL imageBolha3 = this.getClass().getResource("/Imagens/Bolha.png");
                ImageIcon icon3 = new ImageIcon(imageBolha3);
                Image img3 = icon3.getImage();
                Ellipse2D circle3 = new Ellipse2D.Double(bolhaj.x + 280, bolhaj.y + 140, 80, 80);
                g.drawImage(img3, bolhaj.x + 290, bolhaj.y + 150, null);

                if (point != null) {
                    if (circle3.contains(point)) {
                        bolha3e = true;
                    }
                }
            }

            if (bolha4e) {
                try {
                    InputStream in = Main.class.getResourceAsStream("/Sons/BubbleEffect.wav");
                    InputStream bufferedIn = new BufferedInputStream(in);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                }

                pontos++;
                //g.dispose();
                bolha4 = false;
                bolha4e = false;
            }
            if (bolha4) {
                URL imageBolha4 = this.getClass().getResource("/Imagens/Bolha.png");
                ImageIcon icon4 = new ImageIcon(imageBolha4);
                Image img4 = icon4.getImage();
                Ellipse2D circle4 = new Ellipse2D.Double(bolhaj.x + 470, bolhaj.y + 490, 80, 80);
                g.drawImage(img4, bolhaj.x + 480, bolhaj.y + 500, null);

                if (point != null) {
                    if (circle4.contains(point)) {
                        bolha4e = true;
                    }
                }
            }
        }

        repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String[] options = new String[2];
        options[0] = new String("Sim");
        options[1] = new String("Não");

        if (JOptionPane.showOptionDialog(null, "Deseja realmente sair?\nTudo o que fez será salvo!", "Pergunta", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.OK_OPTION) {
            getEstatistica().setNota1(pontos.doubleValue() / 2);
            getEstatistica().atualizado();
            dispose();
            th1.interrupt();
            th2.interrupt();
            th3.interrupt();
            th4.interrupt();
            th5.interrupt();
        }
    }//GEN-LAST:event_formWindowClosing

    class myMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent arg0) {
            point = arg0.getPoint();
            Toolkit toolkit1 = Toolkit.getDefaultToolkit();
            URL imageUrlSapoA = this.getClass().getResource("/Imagens/aberto.gif");
            Image image1 = toolkit1.getImage(imageUrlSapoA);
            Cursor c1 = toolkit1.createCustomCursor(image1, new Point(16, 16), "Sapo Aberto");
            setCursor(c1);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {
                                Toolkit toolkit2 = Toolkit.getDefaultToolkit();
                                URL imageUrlSapoF = this.getClass().getResource("/Imagens/fechado.gif");
                                Image image2 = toolkit2.getImage(imageUrlSapoF);
                                Cursor c2 = toolkit2.createCustomCursor(image2, new Point(16, 16), "Sapo Fechado");
                                setCursor(c2);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(rootPane, e);
                            }
                        }
                    },
                    500);
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
        }
    }

    public void setEstatistica(Estatistica estatistica) {
        this.estatistica = estatistica;
    }

    public Estatistica getEstatistica() {
        return this.estatistica;
    }

    /**
     * @param args the command line arguments
     */
    public void mainA(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                if (!mainExe) {
                    new GameFrame(getEstatistica()).setVisible(true);
                    mainExe = true;
                }

                if (bThread1) {
                    th1.start();
                }
                if (bThread2) {
                    th1.interrupt();
                    th2.start();
                    bolhaj.y = 800;
                    bolhaj.x = 400;
                }
                if (bThread3) {
                    th2.interrupt();
                    th3.start();
                    bolhaj.y = 700;
                    bolhaj.x = -300;
                }
                if (bThread4) {
                    th3.interrupt();
                    th4.start();
                    bolhaj.y = 800;
                    bolhaj.x = 300;
                }
                if (bThread5) {
                    th4.interrupt();
                    th5.start();
                    bolhaj.y = 678;
                    bolhaj.x = 200;
                    ultimo = true;
                }
                if (bAcabou) {
                    th5.interrupt();
                }
            }

        }
        );

        if (recognizerBoo) {
            try {
                URL url;
                if (args.length > 0) {
                    url = new File(args[0]).toURI().toURL();

                } else {
                    url = GameFrame.class
                            .getResource("helloworld.config.xml");
                }

                cm = new ConfigurationManager(url);

                Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
                Microphone microphone = (Microphone) cm.lookup("microphone");

                recognizer.allocate();

                if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                    if (microphone.startRecording()) {
                        while (true) {

                            Result result = recognizer.recognize();

                            if (result != null) {
                                String resultText = result.getBestFinalResultNoFiller();
                                if (resultText.equals("")) {
                                    //bolha = false;
                                } else {
                                    if (!bolha) {
                                        contThread++;
                                        //System.out.println(contThread);
                                        preparar = true;
                                        if (contThread == 1) {
                                            numero3();
                                            bThread1 = true;
                                        } else if (contThread == 2) {
                                            numero3();
                                            bThread1 = false;
                                            bolha1 = true;
                                            bolha2 = true;
                                            bolha3 = true;
                                            bolha4 = true;
                                            bThread2 = true;
                                        } else if (contThread == 3) {
                                            numero3();
                                            bThread2 = false;
                                            bolha1 = true;
                                            bolha2 = true;
                                            bolha3 = true;
                                            bolha4 = true;
                                            bThread3 = true;
                                            pedido2 = false;
                                        } else if (contThread == 4) {
                                            numero3();
                                            bThread3 = false;
                                            bolha1 = true;
                                            bolha2 = true;
                                            bolha3 = true;
                                            bolha4 = true;
                                            bThread4 = true;
                                        } else if (contThread == 5) {
                                            numero3();
                                            bThread4 = false;
                                            bolha1 = true;
                                            bolha2 = true;
                                            bolha3 = true;
                                            bolha4 = true;
                                            bThread5 = true;
                                        } else if (contThread == 6) {
                                            bThread5 = false;
                                            bAcabou = true;
                                        }

                                        recognizerBoo = false;
                                        pedido1 = false;
                                        ativar = true;

                                        bolha = true;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Microfone não está conectado.");
                    recognizer.deallocate();
                    System.exit(1);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
