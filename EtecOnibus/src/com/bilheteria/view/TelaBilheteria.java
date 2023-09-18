/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bilheteria.view;

import com.bilheteria.model.qrCode;
import com.bilheteria.model.ModuloConexao;
import com.bilheteria.model.Cidades;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Classe
 *
 * @author Juliano
 * @version 1.1
 */
public class TelaBilheteria extends javax.swing.JFrame {

    Date dataHora = new Date();

    int nmrBilhete = 0;
    Connection conexao;

    /**
     * Creates new form bilhete
     */
    public TelaBilheteria() {
        initComponents();
        conexao = ModuloConexao.conectar();

    }

    private void carregarDestinos() {
        txDestino.removeAllItems();

        String sql = "SELECT * FROM destinos";
        try (PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nome_destino");
                txDestino.addItem(new Cidades(id, nom));
            }
        } catch (SQLException e) {
            // Lidar com a exceção
        }
    }

    private void carregarTerminal() {
        String destino = txDestino.getSelectedItem().toString();

        String sql = "SELECT t.nome_terminal FROM terminais t "
                + "JOIN destinos d ON d.id_terminal = t.id "
                + "WHERE d.nome_destino = ?";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, destino);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    txtTerminal.setText(rs.getString("nome_terminal"));
                    atualizarTarifa();
                } else {
                    txtTerminal.setText("Terminal não encontrado");
                }
            }
        } catch (SQLException e) {
            // Handle the exception
        }
    }

    private void atualizarTarifa() {
        String destino = txDestino.getSelectedItem().toString();
        String terminal = txtTerminal.getText();

        String sql = "SELECT t.valor FROM tarifas t "
                + "JOIN destinos d ON t.id_destino = d.id "
                + "JOIN terminais ter ON d.id_terminal = ter.id "
                + "WHERE ter.nome_terminal = ? AND d.nome_destino = ?";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, terminal);
            pst.setString(2, destino);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    double tarifa = rs.getDouble("valor");
                    lbValor.setText("R$" + tarifa);
                } else {
                    lbValor.setText("Tarifa não encontrada");
                }
            }
        } catch (SQLException e) {
            // Handle the exception
        }
    }

    private void carregaBilhete() {
        try {
            String sql = "SELECT * FROM bilhete";

            try (PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    nmrBilhete = rs.getInt("nmrbilhete");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gravaBilhete() {
        Date dtEntra = new Date();
        String dtSai = new SimpleDateFormat("yyyy-MM-dd").format(dtEntra);

        String sql = "INSERT INTO voucher (tb01_numero, tb01_data, tb01_hora, tb01_estacao, tb01_valor, tb01_status) "
                + "VALUES (?, ?, ?, ?, ?, 'Emitido Java')";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, lbNumero.getText());
            pst.setString(2, dtSai);
            pst.setString(3, lbHora.getText());
            pst.setString(4, txDestino.getSelectedItem().toString());
            pst.setString(5, lbValor.getText());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void atualizaRefer() {
        String sql = "UPDATE bilhete SET nmrbilhete = nmrbilhete + 1 WHERE id = 1";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        btnEmite = new javax.swing.JButton();
        lbData = new javax.swing.JLabel();
        lbHora = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbBilhete = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbCode = new javax.swing.JLabel();
        lbValor = new javax.swing.JLabel();
        lbNumero = new javax.swing.JLabel();
        txDestino = new javax.swing.JComboBox();
        lbLetreiro = new javax.swing.JLabel();
        txtTerminal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viação Atibaia São Paulo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 51, 255));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 190, 20));
        getContentPane().add(lbLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, 180));

        btnEmite.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEmite.setForeground(new java.awt.Color(0, 51, 255));
        btnEmite.setText("  EMITIR");
        btnEmite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmiteMouseClicked(evt);
            }
        });
        btnEmite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmiteActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmite, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 120, 50));

        lbData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(lbData, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 110, 20));

        lbHora.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(lbHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 110, 20));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GERAÇÃO DE BILHETE ÚNICO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 380, 60));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("VALOR:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 80, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("DESTINO:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 90, 30));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 220, 40));

        lbBilhete.setBackground(new java.awt.Color(255, 255, 255));
        lbBilhete.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        lbBilhete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBilheteMouseClicked(evt);
            }
        });
        getContentPane().add(lbBilhete, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 190, 300));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("TERMINAL:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 120, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("BILHETE");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 110, 30));

        lbCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCode.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        getContentPane().add(lbCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 90, 80));

        lbValor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(lbValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 70, 30));

        lbNumero.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbNumero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNumeroMouseClicked(evt);
            }
        });
        getContentPane().add(lbNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 180, 30));

        txDestino.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txDestino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txDestinoItemStateChanged(evt);
            }
        });
        txDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txDestinoMouseClicked(evt);
            }
        });
        getContentPane().add(txDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 220, 30));

        lbLetreiro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbLetreiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 770, 140));

        txtTerminal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(txtTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 220, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        this.getContentPane().setBackground(Color.white);
        this.setSize(800, 600);

        this.setIconImage(new ImageIcon(getClass().getResource("/lib/imagens/logoide.png")).getImage());

        {
            ImageIcon imagem = new ImageIcon(TelaBilheteria.class.getResource("/lib/imagens/logo.png"));
            Image imag = imagem.getImage().getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(), Image.SCALE_DEFAULT);
            lbLogo.setIcon(new ImageIcon(imag));
        }

        lbLetreiro.setVisible(false);

        {
            ImageIcon imagem = new ImageIcon(TelaBilheteria.class.getResource("/lib/imagens/onibus.png"));
            Image imag = imagem.getImage().getScaledInstance(lbLetreiro.getWidth(), lbLetreiro.getHeight(), Image.SCALE_DEFAULT);
            lbLetreiro.setIcon(new ImageIcon(imag));
        }

        lbBilhete.setVisible(false);
        lbCode.setVisible(false);

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        lbData.setText(dt.format(dataHora));

        Timer timer = new Timer(1000, new dataHora());
        timer.start();

        letreiro();
        carregarDestinos();
        carregaBilhete();

    }//GEN-LAST:event_formWindowOpened

    class dataHora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Calendar now = Calendar.getInstance();
            lbHora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        }
    }

    private void btnEmiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmiteMouseClicked

        if (txDestino.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Escolha uma estação");
            return;
        }

        if (txtTerminal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Terminal invalido");
            return;
        }

        lbBilhete.setVisible(true);
        lbCode.setVisible(true);

        nmrBilhete++;
        lbNumero.setText(String.valueOf(nmrBilhete));

     
        StringBuilder mensaBuilder = new StringBuilder();
        mensaBuilder.append("<html><br><br><br><br><br>")
                .append("<p style='font-size:6px; text-align:center;'>========================================</p>")
                .append("<p style='font-size:6px; text-align:center'>COMPROVANTE DE VENDA</p>")
                .append("<p style='font-size:6px; text-align:center'>========================================</p>")
                .append("<p style='font-size:6px; text-align:center'>BILHETE UNITÁRIO</p>")
                .append("<p style='font-size:6px; text-align:center'>DESTINO:").append(txDestino.getSelectedItem()).append("</p>")
                .append("<p style='font-size:6px; text-align:center'>========================================</p>")
                .append("<p style='font-size:6px; text-align:center'>NÚMERO:").append(lbNumero.getText()).append("</p>")
                .append("<p style='font-size:6px; text-align:center'>TERMINAL:").append(txtTerminal.getText()).append("</p>")
                .append("<p style='font-size:6px; text-align:center'>VALOR:").append(lbValor.getText()).append("</p>")
                .append("<p style='font-size:6px; text-align:center'>========================================</p>")
                .append("<p style='font-size:6px; text-align:center'>ATENÇÃO: Valido apenas para uma viagem</p>")
                .append("<p style='font-size:6px; text-align:center'>Usar preferencialmente em 72 horas</p>")
                .append("<p style='font-size:6px; text-align:center'>Nunca dobrar, molhar, amassar e nem</p>")
                .append("<p style='font-size:6px; text-align:center'>aproximar esse bilhete de álcool</p>")
                .append("<p style='font-size:6px; text-align:center'>========================================</p>")
                .append("<p style='font-size:6px; text-align:center'>Impresso em: ").append(lbData.getText()).append("  ").append(lbHora.getText()).append("</p>")
                .append("<p style='font-size:6px; text-align:center'>========================================</p></html>");

        String mensa = mensaBuilder.toString();
        lbBilhete.setText(mensa);

        movimento();

        gravaBilhete();

        atualizaRefer();
    }//GEN-LAST:event_btnEmiteMouseClicked

    private void lbBilheteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBilheteMouseClicked

        lbBilhete.setVisible(false);
        lbCode.setVisible(false);

    }//GEN-LAST:event_lbBilheteMouseClicked

    private void txDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txDestinoMouseClicked


    }//GEN-LAST:event_txDestinoMouseClicked

    private void txDestinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txDestinoItemStateChanged
        // TODO add your handling code here
        carregarTerminal();
    }//GEN-LAST:event_txDestinoItemStateChanged

    private void lbNumeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNumeroMouseClicked

    }//GEN-LAST:event_lbNumeroMouseClicked

    private void btnEmiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEmiteActionPerformed

    public void letreiro() {
        new Thread() {
            @Override
            public void run() {
                int y = 380;
                int x = 0;

                while (true) {
                    if (x < getWidth()) {
                        x = x + 1;
                        lbLetreiro.setVisible(true);
                        lbLetreiro.setLocation(x, y);
                    } else {
                        x = -lbLetreiro.getWidth();
                    }
                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }

    public void movimento() {
        lbBilhete.setVisible(true);
        lbCode.setVisible(true);

        new Thread() {
            public void run() {

                int y = 540;
                int z = 590;
                int x = -80;

                while (true) {
                    if (x < 52) {
                        x = x + 1;
                        lbBilhete.setLocation(y, x);
                        lbCode.setLocation(z, x);
                    }
                    try {
                        sleep(6);
                    } catch (Exception e) {
                    }
                }
            }

        }.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaBilheteria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbBilhete;
    private javax.swing.JLabel lbCode;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbLetreiro;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbNumero;
    private javax.swing.JLabel lbValor;
    private javax.swing.JComboBox txDestino;
    private javax.swing.JLabel txtTerminal;
    // End of variables declaration//GEN-END:variables
}
