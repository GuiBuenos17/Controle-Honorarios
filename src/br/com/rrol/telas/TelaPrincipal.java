/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rrol.telas;

import java.sql.*;
import br.com.rrol.dal.ModuloConexao;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import javax.swing.table.*;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Advogados
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public static int idClienteSelecionado;
    public static String func;
    private TelaProcesso telaCliente;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
        CustomTable();

    }

    private void setarIdCli() {
        int setar = tblCasos.getSelectedRow();
        idClienteSelecionado = Integer.parseInt(tblCasos.getModel().getValueAt(setar, 0).toString());

        TelaProcesso cliente = new TelaProcesso();
        cliente.setVisible(true);
    }

    private void pesquisarCliente() {

        if (func == null) {
            String sql = "SELECT idprocesso as Id, nome as Nome, gcpj, contrato as Contrato, advogado as Advogado, stsacordo as Sts_Acordo FROM tbprocessos WHERE LOWER(nome) LIKE LOWER(?) OR LOWER(gcpj) LIKE LOWER(?) OR LOWER(contrato) LIKE LOWER(?) OR LOWER(cpf_cnpj) LIKE LOWER(?)";

            try {
                pst = conexao.prepareStatement(sql);
                // passando o conteúdo da caixa de pesquisa para o ?
                // atenção ao "%" - continuação da string sql

                String searchText = txtPesquisar.getText() + "%";
                pst.setString(1, searchText.toLowerCase());
                pst.setString(2, searchText.toLowerCase());
                pst.setString(3, searchText.toLowerCase());
                pst.setString(4, searchText.toLowerCase());
                rs = pst.executeQuery();

                // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
                tblCasos.setModel(DbUtils.resultSetToTableModel(rs));
                
                 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.print(e);
            }
        } else {

            String sql = "SELECT idprocesso as Id, nome as Nome, gcpj, contrato as Contrato, advogado as Advogado, stsacordo as Sts_Acordo FROM tbprocessos WHERE LOWER(advogado) LIKE LOWER (?) AND LOWER(nome) LIKE LOWER(?) OR LOWER(gcpj) LIKE LOWER(?) OR LOWER(contrato) LIKE LOWER(?)";

            try {
                pst = conexao.prepareStatement(sql);
                // passando o conteúdo da caixa de pesquisa para o ?
                // atenção ao "%" - continuação da string sql
                String advBanco = cboAdv.getSelectedItem().toString();
                pst.setString(1, advBanco);
                String searchText = txtPesquisar.getText() + "%";
                pst.setString(2, searchText.toLowerCase());
                pst.setString(3, searchText.toLowerCase());
                pst.setString(4, searchText.toLowerCase());

                rs = pst.executeQuery();

                // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
                if (rs.next()) {
                    String advTbl = rs.getString(5);
                    if (advTbl.equals(advBanco)) {

                        tblCasos.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.print(e);
            }

        }
    }

    private void pesquisarClieAdv() {
        
        String sql = "SELECT idprocesso, nome, gcpj, contrato, advogado, stsacordo FROM tbprocessos WHERE advogado = ?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, cboAdv.getSelectedItem().toString());

            rs = pst.executeQuery();

            tblCasos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.print(e);
        }
    }

    private void pesquisarMax() {
        
        String sql = "SELECT COUNT(*) FROM TBPROCESSOS where advogado = ?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, cboAdv.getSelectedItem().toString());

            rs = pst.executeQuery();

            if (rs.next()) {  // Mova o cursor para a primeira linha
                String count = rs.getString(1); // Obtenha o valor da primeira coluna
                lblCount.setText(count);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.print(e);
        }
    }

    private void CustomTable() {

        tblCasos.setBackground(java.awt.Color.WHITE);

        TableColumnModel columnModel = tblCasos.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(200);

        tblCasos.setBorder(null);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCasos = new javax.swing.JTable();
        txtPesquisar = new javax.swing.JTextField();
        cboAdv = new javax.swing.JComboBox<>();
        lblCount = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadUsu = new javax.swing.JMenuItem();
        menCadPro = new javax.swing.JMenuItem();
        menHon = new javax.swing.JMenu();
        menHonSolc = new javax.swing.JMenuItem();
        menHonImp = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelAdv = new javax.swing.JMenuItem();
        menFerias = new javax.swing.JMenu();
        menFeriasPed = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(new java.awt.Color(0, 51, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCasos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCasos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblCasos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "GCPJ", "Contrato", "Advogado", "ST.Acordo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCasos.getTableHeader().setResizingAllowed(false);
        tblCasos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCasosMouseClicked(evt);
            }
        });
        tblCasos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCasosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCasos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 190, 600, 340));

        txtPesquisar.setBorder(null);
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });
        jPanel1.add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 130, 278, 18));

        cboAdv.setBorder(null);
        cboAdv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboAdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAdvActionPerformed(evt);
            }
        });
        jPanel1.add(cboAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 568, 130, 20));

        lblCount.setBackground(new java.awt.Color(255, 255, 255));
        lblCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 605, 38, 37));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rrol/icones/principal4.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 660));

        menCad.setText("Cadastro");

        menCadUsu.setText("Cadastro Usuário");
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        menCadPro.setText("Cadastro Processo");
        menCadPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadProActionPerformed(evt);
            }
        });
        menCad.add(menCadPro);

        jMenuBar1.add(menCad);

        menHon.setText("Honorarios");

        menHonSolc.setText("Solicitação de tela");
        menHonSolc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menHonSolcActionPerformed(evt);
            }
        });
        menHon.add(menHonSolc);

        menHonImp.setText("Impressão de tela");
        menHonImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menHonImpActionPerformed(evt);
            }
        });
        menHon.add(menHonImp);

        jMenuBar1.add(menHon);

        menRel.setText("Relatórios");

        menRelAdv.setText("Advogados");
        menRel.add(menRelAdv);

        jMenuBar1.add(menRel);

        menFerias.setText("Ferias");
        menFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFeriasActionPerformed(evt);
            }
        });

        menFeriasPed.setText("menPed");
        menFeriasPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFeriasPedActionPerformed(evt);
            }
        });
        menFerias.add(menFeriasPed);

        jMenuBar1.add(menFerias);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(922, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        // TODO add your handling code here:
        new TelaPesquisa().setVisible(true);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadProActionPerformed
        // TODO add your handling code here:
        new TelaCadProcesso().setVisible(true);
    }//GEN-LAST:event_menCadProActionPerformed

    private void menHonImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menHonImpActionPerformed
        // TODO add your handling code here:
        new TelaImpressao().setVisible(true);
    }//GEN-LAST:event_menHonImpActionPerformed

    private void menHonSolcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menHonSolcActionPerformed
        // TODO add your handling code here:
        new TelaMudMes().setVisible(true);
    }//GEN-LAST:event_menHonSolcActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        System.out.println(func);
    }//GEN-LAST:event_formWindowOpened

    private void tblCasosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCasosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCasosKeyPressed

    private void tblCasosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCasosMouseClicked
        // TODO add your handling code here:
        setarIdCli();

    }//GEN-LAST:event_tblCasosMouseClicked

    private void cboAdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAdvActionPerformed
        // TODO add your handling code here:
        pesquisarClieAdv();
        pesquisarMax();
    }//GEN-LAST:event_cboAdvActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisarCliente();
        TableColumnModel columnModel = tblCasos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(90);

        tblCasos.setBorder(null);
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void menFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFeriasActionPerformed
        // TODO add your handling code here:
        new TelaTestes().setVisible(true);
    }//GEN-LAST:event_menFeriasActionPerformed

    private void menFeriasPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFeriasPedActionPerformed
        // TODO add your handling code here:
        new TelaTestes().setVisible(true);
    }//GEN-LAST:event_menFeriasPedActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> cboAdv;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCount;
    public static javax.swing.JMenu menCad;
    public static javax.swing.JMenuItem menCadPro;
    public static javax.swing.JMenuItem menCadUsu;
    public static javax.swing.JMenu menFerias;
    public static javax.swing.JMenuItem menFeriasPed;
    public static javax.swing.JMenu menHon;
    public static javax.swing.JMenuItem menHonImp;
    public static javax.swing.JMenuItem menHonSolc;
    private javax.swing.JMenu menRel;
    private javax.swing.JMenuItem menRelAdv;
    public static javax.swing.JTable tblCasos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
