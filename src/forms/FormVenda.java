/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import beans.Mesa;
import beans.Produto;
import beans.Venda;
import dao.MesaDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anderson
 */
public class FormVenda extends javax.swing.JFrame {

    /**
     * Creates new form FormVenda
     */
    private Mesa mesa = new Mesa();
    private final MesaDAO mesaDAO = new MesaDAO();
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private String operacao = "";
    private int rowSelected = 0;

    public FormVenda() {
        initComponents();
    }

    private void loadVendaMesa(int idmesa) {

        ArrayList<Venda> vendaMesa = new ArrayList<Venda>();

        vendaMesa = vendaDAO.getVendasByMesa(idmesa);
        Produto p = new Produto();
        DefaultTableModel tabelaVenda = (DefaultTableModel) tblItens.getModel();

        for (Venda v : vendaMesa) {
            if (v != null) {
                p = produtoDAO.getProdutoById(v.getIdProduto());
                DecimalFormat df = new DecimalFormat("#,###.00");
                String qtd = df.format(v.getQuantidade());
                String tot = df.format(v.getQuantidade() * v.getPreco());
                Object[] objects = new Object[]{
                    v.getDateTime(),
                    v.getIdProduto(),
                    p.getDescricao(),
                    qtd,
                    tot};
                tabelaVenda.addRow(objects);
            }
        }
    }

    private void cancelar(int Nivel) {
        edtPreco.setText("");
        edtQuantidade.setText("");
        lblDescProduto.setText("");
        btnEncerrarVenda.setEnabled(false);

        if (Nivel == 1) {
            btnInserir.setEnabled(true);
            btnEditar.setEnabled(true);
            btnSalvar.setEnabled(false);
            btnExcluir.setEnabled(false);
            edtCodigo.setEnabled(true);
            edtQuantidade.setEnabled(false);
            edtPreco.setEnabled(false);
            edtCodigo.requestFocus();
            edtCodigo.setText("");
            tblItens.setEnabled(true);
            if (tblItens.getRowCount() > 0)
                btnEncerrarVenda.setEnabled(true);
            else
                btnEncerrarVenda.setEnabled(false);
        }

        if (Nivel == 2) {
            edtIdMesa.requestFocus();
        }
    }

    private void consultarProduto(String op) {
        try {
            DefaultTableModel tabelaVenda = (DefaultTableModel) tblItens.getModel();
            int idproduto = 0;
            //se estiver editando, pega o codigo do jtable
            if (op.equals("E")) {
                idproduto = Integer.parseInt(tabelaVenda.getValueAt(tblItens.getSelectedRow(), 1).toString());
            } else if (op.equals("I")) {
                //senao pega do textfield
                idproduto = Integer.parseInt(edtCodigo.getText());
            }

            Produto p = new Produto();
            p = produtoDAO.getProdutoById(idproduto);
            if (p.getIdproduto() == 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                edtCodigo.requestFocus();
                edtCodigo.selectAll();
                return;
            }

            operacao = op;

            // <editor-fold defaultstate="collapsed" desc="Habilita e desabilita campos">
            btnSalvar.setEnabled(true);
            btnInserir.setEnabled(false);
            btnEditar.setEnabled(false);

            edtCodigo.setEnabled(false);
            edtPreco.setEnabled(true);
            edtQuantidade.setEnabled(true);
            // </editor-fold>

            double precoVenda = 0;

            if (op.equals("I")) {
                btnExcluir.setEnabled(false);
                precoVenda = p.getPrecoVenda();

            } else if (op.equals("E")) {
                btnExcluir.setEnabled(true);
                double q, t;
                q = Double.parseDouble(tabelaVenda.getValueAt(tblItens.getSelectedRow(), 3).toString().replace(".", "").replace(",", "."));
                t = Double.parseDouble(tabelaVenda.getValueAt(tblItens.getSelectedRow(), 4).toString().replace(".", "").replace(",", "."));
                precoVenda = t / q;
                edtQuantidade.setText(Double.toString(q));
                edtCodigo.setText(Integer.toString(idproduto));
            }

            lblDescProduto.setText(p.getDescricao());
            edtPreco.setText(Double.toString(precoVenda));
            edtQuantidade.requestFocus();
            rowSelected = tblItens.getSelectedRow();
            tblItens.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
            edtCodigo.setText("");
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
        edtIdMesa = new javax.swing.JTextField();
        lblDescMesa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItens = new javax.swing.JTable();
        btnInserir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        edtCodigo = new javax.swing.JTextField();
        edtPreco = new javax.swing.JTextField();
        edtQuantidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDescProduto = new javax.swing.JLabel();
        btnCarregar = new javax.swing.JButton();
        btnEncerrarVenda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Mesa.:");

        edtIdMesa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edtIdMesaFocusGained(evt);
            }
        });
        edtIdMesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtIdMesaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtIdMesaKeyTyped(evt);
            }
        });

        tblItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Código", "Descrição", "Quantidade", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItens.setFocusable(false);
        tblItens.setMaximumSize(new java.awt.Dimension(605, 0));
        tblItens.setMinimumSize(new java.awt.Dimension(605, 0));
        tblItens.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblItens);
        if (tblItens.getColumnModel().getColumnCount() > 0) {
            tblItens.getColumnModel().getColumn(1).setMinWidth(50);
            tblItens.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblItens.getColumnModel().getColumn(1).setMaxWidth(50);
            tblItens.getColumnModel().getColumn(2).setMinWidth(240);
            tblItens.getColumnModel().getColumn(2).setPreferredWidth(240);
            tblItens.getColumnModel().getColumn(2).setMaxWidth(240);
            tblItens.getColumnModel().getColumn(3).setMinWidth(120);
            tblItens.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblItens.getColumnModel().getColumn(3).setMaxWidth(120);
            tblItens.getColumnModel().getColumn(4).setMinWidth(120);
            tblItens.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblItens.getColumnModel().getColumn(4).setMaxWidth(120);
        }

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add-32.png"))); // NOI18N
        btnInserir.setText("Inserir");
        btnInserir.setEnabled(false);
        btnInserir.setMaximumSize(new java.awt.Dimension(111, 41));
        btnInserir.setMinimumSize(new java.awt.Dimension(111, 41));
        btnInserir.setPreferredSize(new java.awt.Dimension(111, 41));
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit-32.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.setMaximumSize(new java.awt.Dimension(111, 41));
        btnEditar.setMinimumSize(new java.awt.Dimension(111, 41));
        btnEditar.setPreferredSize(new java.awt.Dimension(111, 41));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Accept-32.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.setMaximumSize(new java.awt.Dimension(111, 41));
        btnSalvar.setMinimumSize(new java.awt.Dimension(111, 41));
        btnSalvar.setPreferredSize(new java.awt.Dimension(111, 41));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cancel-32.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete-32.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.setMaximumSize(new java.awt.Dimension(111, 41));
        btnExcluir.setMinimumSize(new java.awt.Dimension(111, 41));
        btnExcluir.setPreferredSize(new java.awt.Dimension(111, 41));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        edtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtCodigoKeyTyped(evt);
            }
        });

        edtPreco.setEditable(false);

        edtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtQuantidadeKeyTyped(evt);
            }
        });

        jLabel2.setText("Código:");

        jLabel3.setText("Preço:");

        jLabel4.setText("Quantidade:");

        btnCarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/busca.png"))); // NOI18N
        btnCarregar.setRequestFocusEnabled(false);
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        btnEncerrarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Accept-32.png"))); // NOI18N
        btnEncerrarVenda.setText("Fechar Venda");
        btnEncerrarVenda.setEnabled(false);
        btnEncerrarVenda.setMaximumSize(new java.awt.Dimension(111, 41));
        btnEncerrarVenda.setMinimumSize(new java.awt.Dimension(111, 41));
        btnEncerrarVenda.setPreferredSize(new java.awt.Dimension(111, 41));
        btnEncerrarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncerrarVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(edtIdMesa, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                    .addComponent(edtCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblDescMesa, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)))))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnEncerrarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(edtIdMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDescProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEncerrarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addGap(8, 8, 8))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        consultarProduto("I");
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Venda v = new Venda();
        Produto p = new Produto();
        DefaultTableModel tabelaVenda = (DefaultTableModel) tblItens.getModel();
        p = produtoDAO.getProdutoById(Integer.parseInt(edtCodigo.getText()));

        if (operacao.equals("I")) {
            Calendar c = new GregorianCalendar();
            //mes no Calendar inicia no 0
            c.setTime(new Date());
            v.setData(c.getTime());
            v.setIdMesa(Integer.parseInt(edtIdMesa.getText()));
            v.setIdProduto(Integer.parseInt(edtCodigo.getText()));
            v.setPreco(Double.parseDouble(edtPreco.getText()));
            v.setQuantidade(Double.parseDouble(edtQuantidade.getText()));
            if (vendaDAO.inserir(v)) {

                DecimalFormat df = new DecimalFormat("#,###.00");
                String qtd = df.format(v.getQuantidade());
                String tot = df.format(v.getQuantidade() * v.getPreco());
                Object[] objects = new Object[]{
                    v.getDateTime(),
                    v.getIdProduto(),
                    p.getDescricao(),
                    qtd,
                    tot};
                tabelaVenda.addRow(objects);
            }

            Mesa m = new Mesa();
            m = mesaDAO.getMesaById(v.getIdMesa());
            m.setStatus("O");
            mesaDAO.editar(m);

        } else if (operacao.equals("E")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date d = null;
            try {
                d = dateFormat.parse(tabelaVenda.getValueAt(rowSelected, 0).toString());
            } catch (ParseException ex) {
                Logger.getLogger(FormVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
            v.setData(d);
            v.setIdMesa(Integer.parseInt(edtIdMesa.getText()));
            v.setIdProduto(Integer.parseInt(edtCodigo.getText()));
            v.setPreco(Double.parseDouble(edtPreco.getText()));
            v.setQuantidade(Double.parseDouble(edtQuantidade.getText()));
            if (vendaDAO.editar(v)) {

                DecimalFormat df = new DecimalFormat("#,###.00");
                String qtd = df.format(v.getQuantidade());
                String tot = df.format(v.getQuantidade() * v.getPreco());
                Object[] objects = new Object[]{
                    v.getDateTime(),
                    v.getIdProduto(),
                    p.getDescricao(),
                    qtd,
                    tot};
                tabelaVenda.setValueAt(qtd, rowSelected, 3);
                tabelaVenda.setValueAt(tot, rowSelected, 4);
            }
        }

        cancelar(1);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (edtQuantidade.isEnabled()) {
            cancelar(1);
        } else if (edtCodigo.isEnabled()) {
            cancelar(2);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void edtIdMesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIdMesaKeyPressed
        // limpa a descricao da mesa quando alguma coisa for digitada
        lblDescMesa.setText("");
    }//GEN-LAST:event_edtIdMesaKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        DefaultTableModel tabelaVenda = (DefaultTableModel) tblItens.getModel();
        Venda v = new Venda();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = null;
        try {
            d = dateFormat.parse(tabelaVenda.getValueAt(rowSelected, 0).toString());
        } catch (ParseException ex) {
            Logger.getLogger(FormVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        v.setData(d);
        v.setIdMesa(Integer.parseInt(edtIdMesa.getText()));
        v.setIdProduto(Integer.parseInt(tabelaVenda.getValueAt(rowSelected, 1).toString()));
        if (vendaDAO.excluir(v)) {
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            tabelaVenda.removeRow(rowSelected);
            cancelar(1);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void edtIdMesaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtIdMesaFocusGained
        DefaultTableModel model = (DefaultTableModel) tblItens.getModel();
        model.setRowCount(0);
        btnCancelar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnInserir.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnEncerrarVenda.setEnabled(false);
        tblItens.setEnabled(false);
        edtCodigo.setEnabled(false);
        edtPreco.setEnabled(false);
        edtQuantidade.setEnabled(false);
        btnCarregar.setEnabled(true);
        edtCodigo.setText("");
        edtIdMesa.selectAll();
    }//GEN-LAST:event_edtIdMesaFocusGained

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        consultarProduto("E");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
       
        int idmesa = Integer.parseInt(edtIdMesa.getText());
        mesa = mesaDAO.getMesaById(idmesa);
        if (mesa.getIdmesa() == 0) {
            JOptionPane.showMessageDialog(null, "Mesa não encontrada");
            edtIdMesa.selectAll();
            return;
        }

        loadVendaMesa(idmesa);

        if (tblItens.getRowCount() > 0) {
            btnEncerrarVenda.setEnabled(true);
        }

        lblDescMesa.setText(mesa.getDescricao());
        edtCodigo.setEnabled(true);
        edtCodigo.requestFocus();
        tblItens.setEnabled(true);
        btnInserir.setEnabled(true);
        btnEditar.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnCarregar.setEnabled(false);
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnEncerrarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncerrarVendaActionPerformed
        vendaDAO.encerrarVenda();
        edtIdMesa.requestFocus();
    }//GEN-LAST:event_btnEncerrarVendaActionPerformed

    private void edtIdMesaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIdMesaKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_edtIdMesaKeyTyped

    private void edtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_edtCodigoKeyTyped

    private void edtQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtQuantidadeKeyTyped
        String caracteres = "0987654321.";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_edtQuantidadeKeyTyped

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
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEncerrarVenda;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtIdMesa;
    private javax.swing.JTextField edtPreco;
    private javax.swing.JTextField edtQuantidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescMesa;
    private javax.swing.JLabel lblDescProduto;
    private javax.swing.JTable tblItens;
    // End of variables declaration//GEN-END:variables

}
