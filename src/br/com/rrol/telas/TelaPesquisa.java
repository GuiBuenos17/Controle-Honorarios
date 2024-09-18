/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rrol.telas;

import br.com.rrol.dal.ModuloConexao;
import static br.com.rrol.telas.TelaProcesso.tblAcordos;
import static br.com.rrol.telas.TelaProcesso.txtId;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Advogados
 */
public class TelaPesquisa extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String funcao = "geral";

    /**
     * Creates new form TelaPesquisa
     */
    public TelaPesquisa() {
        initComponents();
        conexao = ModuloConexao.conector();
        setarUsuario();
        criarPainelUsuarios();
    }

    private void setarUsuario() {

        String sql = "SELECT iduser, nome, funcao FROM tbusuarios order by iduser";

        try {
            pst = conexao.prepareStatement(sql);

            rs = pst.executeQuery();

            //tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.print(e);
        }
    }

    private void criarPainelUsuarios() {

        if (funcao.equals("geral")) {
            String sql = "SELECT nome FROM tbusuarios order by nome";  // Ajuste conforme sua necessidade

            try {
                // Configurando a consulta
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                // Limpa o painel antes de adicionar novos componentes
                jPanel6.removeAll();

                // Define o layout do painel principal como FlowLayout
                // Espaçamento horizontal de 15 pixels e vertical de 15 pixels
                FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
                jPanel6.setLayout(flowLayout);

                // Remove qualquer borda vazia que poderia adicionar espaços ao redor do painel
                jPanel6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                // Loop para criar um painel para cada usuário
                while (rs.next()) {
                    // Recupera o nome do usuário
                    String nomeUsuario = rs.getString("nome");

                    // Cria um painel de 110,25 para cada usuário
                    JPanel painelUsuario = new JPanel();
                    painelUsuario.setPreferredSize(new Dimension(110, 25));
                    painelUsuario.setBackground(new java.awt.Color(0, 121, 208));
                    painelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    painelUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda para visualização
                    
                    // Adiciona o nome do usuário em um JLabel dentro do painel
                    JLabel lblNome = new JLabel(nomeUsuario);
                    lblNome.setForeground(Color.white);
                    painelUsuario.add(lblNome);

                    // Adiciona o painel do usuário ao jPanel6
                    jPanel6.add(painelUsuario);
                }

                // Ajusta o tamanho preferido do jPanel6 com base no número de usuários e espaçamento
                int numUsuarios = jPanel6.getComponentCount();
                int panelWidth = 678;  // Largura fixa do painel principal
                int panelHeight = (int) (Math.ceil(numUsuarios / 5.0) * (85 + 15)); // Ajuste de altura com espaçamento vertical de 15 pixels
                jPanel6.setPreferredSize(new Dimension(panelWidth, panelHeight));

                // Atualiza o painel para exibir os novos componentes
                jPanel6.revalidate();
                jPanel6.repaint();

                // Atualiza a rolagem para ser apenas vertical
                jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        } else if (funcao.equals("adm")) {
            String sql = "SELECT nome FROM tbusuarios where funcao = 'Adm' order by nome";  // Ajuste conforme sua necessidade

            try {
                // Configurando a consulta
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                // Limpa o painel antes de adicionar novos componentes
                jPanel6.removeAll();

                // Define o layout do painel principal como FlowLayout
                // Espaçamento horizontal de 15 pixels e vertical de 15 pixels
                FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
                jPanel6.setLayout(flowLayout);

                // Remove qualquer borda vazia que poderia adicionar espaços ao redor do painel
                jPanel6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                // Loop para criar um painel para cada usuário
                while (rs.next()) {
                    // Recupera o nome do usuário
                    String nomeUsuario = rs.getString("nome");

                    // Cria um painel de 110,25 para cada usuário
                    JPanel painelUsuario = new JPanel();
                    painelUsuario.setPreferredSize(new Dimension(110, 25));
                    painelUsuario.setBackground(new java.awt.Color(0, 121, 208));
                    painelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    painelUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda para visualização

                    // Adiciona o nome do usuário em um JLabel dentro do painel
                    JLabel lblNome = new JLabel(nomeUsuario);
                    lblNome.setForeground(Color.white);
                    painelUsuario.add(lblNome);

                    // Adiciona o painel do usuário ao jPanel6
                    jPanel6.add(painelUsuario);
                }

                // Ajusta o tamanho preferido do jPanel6 com base no número de usuários e espaçamento
                int numUsuarios = jPanel6.getComponentCount();
                int panelWidth = 678;  // Largura fixa do painel principal
                int panelHeight = (int) (Math.ceil(numUsuarios / 5.0) * (85 + 15)); // Ajuste de altura com espaçamento vertical de 15 pixels
                jPanel6.setPreferredSize(new Dimension(panelWidth, panelHeight));

                // Atualiza o painel para exibir os novos componentes
                jPanel6.revalidate();
                jPanel6.repaint();

                // Atualiza a rolagem para ser apenas vertical
                jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        } else if (funcao.equals("adv")) {
            String sql = "SELECT nome FROM tbusuarios where funcao = 'Adv' order by nome";  // Ajuste conforme sua necessidade

            try {
                // Configurando a consulta
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                // Limpa o painel antes de adicionar novos componentes
                jPanel6.removeAll();

                // Define o layout do painel principal como FlowLayout
                // Espaçamento horizontal de 15 pixels e vertical de 15 pixels
                FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
                jPanel6.setLayout(flowLayout);

                // Remove qualquer borda vazia que poderia adicionar espaços ao redor do painel
                jPanel6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                // Loop para criar um painel para cada usuário
                while (rs.next()) {
                    // Recupera o nome do usuário
                    String nomeUsuario = rs.getString("nome");

                    // Cria um painel de 110,25 para cada usuário
                    JPanel painelUsuario = new JPanel();
                    painelUsuario.setPreferredSize(new Dimension(110, 25));
                    painelUsuario.setBackground(new java.awt.Color(0, 121, 208));
                    painelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    painelUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda para visualização

                    // Adiciona o nome do usuário em um JLabel dentro do painel
                    JLabel lblNome = new JLabel(nomeUsuario);
                    lblNome.setForeground(Color.white);
                    painelUsuario.add(lblNome);

                    // Adiciona o painel do usuário ao jPanel6
                    jPanel6.add(painelUsuario);
                }

                // Ajusta o tamanho preferido do jPanel6 com base no número de usuários e espaçamento
                int numUsuarios = jPanel6.getComponentCount();
                int panelWidth = 678;  // Largura fixa do painel principal
                int panelHeight = (int) (Math.ceil(numUsuarios / 5.0) * (85 + 15)); // Ajuste de altura com espaçamento vertical de 15 pixels
                jPanel6.setPreferredSize(new Dimension(panelWidth, panelHeight));

                // Atualiza o painel para exibir os novos componentes
                jPanel6.revalidate();
                jPanel6.repaint();

                // Atualiza a rolagem para ser apenas vertical
                jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        } else if (funcao.equals("Cont")) {
            String sql = "SELECT nome FROM tbusuarios where funcao = 'Contabilizacao' order by nome";  // Ajuste conforme sua necessidade

            try {
                // Configurando a consulta
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                // Limpa o painel antes de adicionar novos componentes
                jPanel6.removeAll();

                // Define o layout do painel principal como FlowLayout
                // Espaçamento horizontal de 15 pixels e vertical de 15 pixels
                FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
                jPanel6.setLayout(flowLayout);

                // Remove qualquer borda vazia que poderia adicionar espaços ao redor do painel
                jPanel6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                // Loop para criar um painel para cada usuário
                while (rs.next()) {
                    // Recupera o nome do usuário
                    String nomeUsuario = rs.getString("nome");

                    // Cria um painel de 110,25 para cada usuário
                    JPanel painelUsuario = new JPanel();
                    painelUsuario.setPreferredSize(new Dimension(110, 25));
                    painelUsuario.setBackground(new java.awt.Color(0, 121, 208));
                    painelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    painelUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda para visualização

                    // Adiciona o nome do usuário em um JLabel dentro do painel
                    JLabel lblNome = new JLabel(nomeUsuario);
                    lblNome.setForeground(Color.white);
                    painelUsuario.add(lblNome);

                    // Adiciona o painel do usuário ao jPanel6
                    jPanel6.add(painelUsuario);
                }

                // Ajusta o tamanho preferido do jPanel6 com base no número de usuários e espaçamento
                int numUsuarios = jPanel6.getComponentCount();
                int panelWidth = 678;  // Largura fixa do painel principal
                int panelHeight = (int) (Math.ceil(numUsuarios / 5.0) * (85 + 15)); // Ajuste de altura com espaçamento vertical de 15 pixels
                jPanel6.setPreferredSize(new Dimension(panelWidth, panelHeight));

                // Atualiza o painel para exibir os novos componentes
                jPanel6.revalidate();
                jPanel6.repaint();

                // Atualiza a rolagem para ser apenas vertical
                jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        } else if (funcao.equals("estagi")) {
            String sql = "SELECT nome FROM tbusuarios where funcao = 'Estagi' order by nome";  // Ajuste conforme sua necessidade

            try {
                // Configurando a consulta
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                // Limpa o painel antes de adicionar novos componentes
                jPanel6.removeAll();

                // Define o layout do painel principal como FlowLayout
                // Espaçamento horizontal de 15 pixels e vertical de 15 pixels
                FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 15, 15);
                jPanel6.setLayout(flowLayout);

                // Remove qualquer borda vazia que poderia adicionar espaços ao redor do painel
                jPanel6.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                // Loop para criar um painel para cada usuário
                while (rs.next()) {
                    // Recupera o nome do usuário
                    String nomeUsuario = rs.getString("nome");

                    // Cria um painel de 110,25 para cada usuário
                    JPanel painelUsuario = new JPanel();
                    painelUsuario.setBackground(new java.awt.Color(0, 121, 208));
                    painelUsuario.setPreferredSize(new Dimension(110, 25));
                    painelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    painelUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda para visualização
                    
                    // Adiciona o nome do usuário em um JLabel dentro do painel
                    JLabel lblNome = new JLabel(nomeUsuario);
                    lblNome.setForeground(Color.white);
                    painelUsuario.add(lblNome);

                    // Adiciona o painel do usuário ao jPanel6
                    jPanel6.add(painelUsuario);
                }

                // Ajusta o tamanho preferido do jPanel6 com base no número de usuários e espaçamento
                int numUsuarios = jPanel6.getComponentCount();
                int panelWidth = 678;  // Largura fixa do painel principal
                int panelHeight = (int) (Math.ceil(numUsuarios / 5.0) * (85 + 15)); // Ajuste de altura com espaçamento vertical de 15 pixels
                jPanel6.setPreferredSize(new Dimension(panelWidth, panelHeight));

                // Atualiza o painel para exibir os novos componentes
                jPanel6.revalidate();
                jPanel6.repaint();

                // Atualiza a rolagem para ser apenas vertical
                jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
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

        jPanel1 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JPanel();
        txtFechar = new javax.swing.JLabel();
        btnGeral = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAdm = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnAdv = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnCont = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnEstagi = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFechar.setBackground(new java.awt.Color(255, 255, 255));
        btnFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFecharMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFecharMouseEntered(evt);
            }
        });

        txtFechar.setFont(new java.awt.Font("Roboto Medium", 0, 11)); // NOI18N
        txtFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFechar.setText("X");

        javax.swing.GroupLayout btnFecharLayout = new javax.swing.GroupLayout(btnFechar);
        btnFechar.setLayout(btnFecharLayout);
        btnFecharLayout.setHorizontalGroup(
            btnFecharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFechar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        btnFecharLayout.setVerticalGroup(
            btnFecharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jPanel1.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 20));

        btnGeral.setBackground(new java.awt.Color(51, 204, 255));
        btnGeral.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGeral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGeralMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Geral\n");

        javax.swing.GroupLayout btnGeralLayout = new javax.swing.GroupLayout(btnGeral);
        btnGeral.setLayout(btnGeralLayout);
        btnGeralLayout.setHorizontalGroup(
            btnGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        btnGeralLayout.setVerticalGroup(
            btnGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnGeral, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 100, 110, 30));

        btnAdm.setBackground(new java.awt.Color(0, 121, 208));
        btnAdm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdmMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Administração");

        javax.swing.GroupLayout btnAdmLayout = new javax.swing.GroupLayout(btnAdm);
        btnAdm.setLayout(btnAdmLayout);
        btnAdmLayout.setHorizontalGroup(
            btnAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        btnAdmLayout.setVerticalGroup(
            btnAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 100, -1, 30));

        btnAdv.setBackground(new java.awt.Color(0, 121, 208));
        btnAdv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdvMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Advogados");

        javax.swing.GroupLayout btnAdvLayout = new javax.swing.GroupLayout(btnAdv);
        btnAdv.setLayout(btnAdvLayout);
        btnAdvLayout.setHorizontalGroup(
            btnAdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        btnAdvLayout.setVerticalGroup(
            btnAdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnAdv, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 100, -1, 30));

        btnCont.setBackground(new java.awt.Color(0, 121, 208));
        btnCont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contabilização");

        javax.swing.GroupLayout btnContLayout = new javax.swing.GroupLayout(btnCont);
        btnCont.setLayout(btnContLayout);
        btnContLayout.setHorizontalGroup(
            btnContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(btnContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );
        btnContLayout.setVerticalGroup(
            btnContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btnContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        jPanel1.add(btnCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 100, -1, 30));

        btnEstagi.setBackground(new java.awt.Color(0, 121, 208));
        btnEstagi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstagi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstagiMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Estagiários");

        javax.swing.GroupLayout btnEstagiLayout = new javax.swing.GroupLayout(btnEstagi);
        btnEstagi.setLayout(btnEstagiLayout);
        btnEstagiLayout.setHorizontalGroup(
            btnEstagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        btnEstagiLayout.setVerticalGroup(
            btnEstagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(btnEstagi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, 30));

        jScrollPane1.setBorder(null);

        jPanel6.setBackground(new java.awt.Color(182, 252, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel6);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 660, 310));

        jLabel1.setBackground(new java.awt.Color(182, 252, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rrol/icones/ferias10.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseEntered
        btnFechar.setBackground(Color.red);
        txtFechar.setForeground(Color.white);

    }//GEN-LAST:event_btnFecharMouseEntered

    private void btnFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseExited
        btnFechar.setBackground(Color.white);
        txtFechar.setForeground(Color.black);
    }//GEN-LAST:event_btnFecharMouseExited

    private void btnFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharMouseClicked
        dispose();
    }//GEN-LAST:event_btnFecharMouseClicked

    private void btnAdmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmMouseClicked

        btnAdm.setBackground(new java.awt.Color(51, 204, 255));
        btnGeral.setBackground(new java.awt.Color(0, 121, 208));
        btnAdv.setBackground(new java.awt.Color(0, 121, 208));
        btnCont.setBackground(new java.awt.Color(0, 121, 208));
        btnEstagi.setBackground(new java.awt.Color(0, 121, 208));
        funcao = "adm";
        criarPainelUsuarios();

    }//GEN-LAST:event_btnAdmMouseClicked

    private void btnGeralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGeralMouseClicked
        // TODO add your handling code here:
        btnGeral.setBackground(new java.awt.Color(51, 204, 255));
        btnAdm.setBackground(new java.awt.Color(0, 121, 208));
        btnAdv.setBackground(new java.awt.Color(0, 121, 208));
        btnCont.setBackground(new java.awt.Color(0, 121, 208));
        btnEstagi.setBackground(new java.awt.Color(0, 121, 208));
        funcao = "geral";
        criarPainelUsuarios();
    }//GEN-LAST:event_btnGeralMouseClicked

    private void btnAdvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdvMouseClicked

        btnAdv.setBackground(new java.awt.Color(51, 204, 255));
        btnGeral.setBackground(new java.awt.Color(0, 121, 208));
        btnAdm.setBackground(new java.awt.Color(0, 121, 208));
        btnCont.setBackground(new java.awt.Color(0, 121, 208));
        btnEstagi.setBackground(new java.awt.Color(0, 121, 208));
        funcao = "adv";
        criarPainelUsuarios();
    }//GEN-LAST:event_btnAdvMouseClicked

    private void btnContMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContMouseClicked
        btnCont.setBackground(new java.awt.Color(51, 204, 255));
        btnGeral.setBackground(new java.awt.Color(0, 121, 208));
        btnAdv.setBackground(new java.awt.Color(0, 121, 208));
        btnAdm.setBackground(new java.awt.Color(0, 121, 208));
        btnEstagi.setBackground(new java.awt.Color(0, 121, 208));
        funcao = "Cont";
        criarPainelUsuarios();
    }//GEN-LAST:event_btnContMouseClicked

    private void btnEstagiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstagiMouseClicked
        btnEstagi.setBackground(new java.awt.Color(51, 204, 255));
        btnGeral.setBackground(new java.awt.Color(0, 121, 208));
        btnAdv.setBackground(new java.awt.Color(0, 121, 208));
        btnCont.setBackground(new java.awt.Color(0, 121, 208));
        btnAdm.setBackground(new java.awt.Color(0, 121, 208));
        funcao = "estagi";
        criarPainelUsuarios();
        
    }//GEN-LAST:event_btnEstagiMouseClicked

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
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesquisa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAdm;
    private javax.swing.JPanel btnAdv;
    private javax.swing.JPanel btnCont;
    private javax.swing.JPanel btnEstagi;
    private javax.swing.JPanel btnFechar;
    private javax.swing.JPanel btnGeral;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtFechar;
    // End of variables declaration//GEN-END:variables
}
