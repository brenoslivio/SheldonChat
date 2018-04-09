/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controle.Bot;
import Controle.Logs;
import Controle.Paciente;
import Controle.Responsavel;
import Interface.Admin;
import Interface.Conversa;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.sound.sampled.*;
import javax.swing.UIManager;

/**
 *
 * @author Breno
 */
public class Main {

    public static void main(String[] args) {

        Clip clip;
        boolean pacienteSel = false;
        boolean encontradoPaciente = false;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {

            InputStream in = Main.class.getResourceAsStream("/Sons/Intro.wav");
            InputStream bufferedIn = new BufferedInputStream(in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            //Thread.sleep(4000);
            Admin admin = new Admin();

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                //System.out.println("MySQL JDBC Driver not found !!");
                return;
            }
            //System.out.println("MySQL JDBC Driver Registered!");
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autismo", "root", "");
                java.sql.Statement stmtp = connection.createStatement();
                java.sql.Statement stmtb = connection.createStatement();
                java.sql.Statement stmtr = connection.createStatement();
                java.sql.Statement stmtl = connection.createStatement();
                String sqlp = "SELECT id FROM paciente";
                String sqlb = "SELECT id FROM bot";
                String sqlr = "SELECT id FROM responsavel";
                String sqll = "SELECT id FROM logs";
                ResultSet rsp = stmtp.executeQuery(sqlp);
                ResultSet rsb = stmtb.executeQuery(sqlb);
                ResultSet rsr = stmtr.executeQuery(sqlr);
                ResultSet rsl = stmtl.executeQuery(sqll);

                if (rsp.first() && rsr.first() && rsb.first()) {
                    Paciente paciente = new Paciente();
                    Responsavel responsavel = new Responsavel();
                    Bot bot = new Bot();
                    Logs logs = new Logs();
                    rsp.beforeFirst();
                    rsr.beforeFirst();
                    rsb.beforeFirst();

                    while (rsp.next() && !pacienteSel) {
                        Long idp = rsp.getLong("id");
                        paciente.encontradoId(idp);
                        if (paciente.getSelecionado()) {
                            pacienteSel = true;
                        }
                    }

                    Logs logs1 = new Logs();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
                    String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    String dateLog = day + "/" + month + "/" + year;

                    Date date = formatter.parse(dateLog);

                    while (rsr.next()) {
                        Long idr = rsr.getLong("id");
                        responsavel.encontradoId(idr);
                    }

                    while (rsb.next()) {
                        Long idb = rsb.getLong("id");
                        bot.encontradoId(idb);
                    }

                    rsl.last();
                    while (!encontradoPaciente && !rsl.isBeforeFirst()) {
                        Long idl = rsl.getLong("id");
                        logs.encontradoId(idl);

                        if (logs.getData().before(date) && logs.getNomePaciente().equals(paciente.getNomePaciente())) {
                            String pathfilePaciente = "C:" + File.separator + "SheldonChat" + File.separator + "Logs" + File.separator + paciente.getNomePaciente() + File.separator + paciente.getNomePaciente() + " Log " + day + "-" + month + "-" + year + ".txt";
                            File filepaciente = new File(pathfilePaciente);
                            filepaciente.createNewFile();
                            BufferedWriter outputP = new BufferedWriter(new FileWriter(filepaciente));
                            outputP.write("LOG DE CONVERSA - DATA: " + dateLog + " - Paciente: " + paciente.getNomePaciente() + "\n\n");
                            outputP.close();

                            String pathfileResponsavel = "C:" + File.separator + "SheldonChat" + File.separator + "Logs" + File.separator + paciente.getNomePaciente() + File.separator + paciente.getNomePaciente() + " Anotação " + day + "-" + month + "-" + year + ".txt";
                            File fileresponsavel = new File(pathfileResponsavel);
                            fileresponsavel.createNewFile();
                            BufferedWriter outputR = new BufferedWriter(new FileWriter(fileresponsavel));
                            outputR.write("ANOTAÇÕES DO RESPONSÁVEL - DATA: " + dateLog + " - Paciente: " + paciente.getNomePaciente() + "\n\n");
                            outputR.close();

                            logs1.setData(formatter.parse(dateLog));
                            logs1.setIdPaciente(paciente);
                            logs1.setNomePaciente(paciente.getNomePaciente());
                            logs1.setEnderecoPaciente(filepaciente.getAbsolutePath());
                            logs1.setEnderecoResponsavel(fileresponsavel.getAbsolutePath());
                            logs1.armazenado();
                            encontradoPaciente = true;
                        }

                        if (logs.getData().equals(date) && logs.getNomePaciente().equals(paciente.getNomePaciente())) {
                            encontradoPaciente = true;
                        }
                        rsl.previous();
                    }

                    if (pacienteSel) {
                        if (logs.getData().before(date)) {
                            clip.stop();
                            Conversa conversa = new Conversa(paciente, bot, responsavel, logs1);
                            conversa.setVisible(true);
                            conversa.startRunning();

                        } else {
                            clip.stop();
                            Conversa conversa = new Conversa(paciente, bot, responsavel, logs);
                            conversa.setVisible(true);
                            conversa.startRunning();
                        }
                    } else {
                        admin.setVisible(true);
                    }
                } else {
                    admin.setVisible(true);
                }
            } catch (SQLException e) {
                //System.out.println("Connection Failed! Check output console");
                return;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    //System.out.println("Connection closed !!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //Admin admin = new Admin(false);
            //admin.setVisible(true);

            /*if (SplashScreen.getSplashScreen() != null) {
             SplashScreen.getSplashScreen().close();
             }*/
            clip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
