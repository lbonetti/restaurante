/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import beans.Produto;
import beans.Venda;
import beans.VendaC;
import beans.VendaEncerrada;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anderson
 */
public class FormRelatorioVenda extends javax.swing.JFrame {

    /**
     * Creates new form FormRelVenda
     */
    
   
    VendaDAO vendaDAO = new VendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private int Linha;

    public int getLinha() {
        return Linha;
    }

    public void setLinha(int Linha) {
        this.Linha = Linha;
        updateItensVenda();
    }       
    
    public FormRelatorioVenda() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItens = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();
        btnCarregar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbtOrdemVenda = new javax.swing.JRadioButton();
        rbtOrdemMesa = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tblItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Produto", "Quantidade", "Preço", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblItens);
        if (tblItens.getColumnModel().getColumnCount() > 0) {
            tblItens.getColumnModel().getColumn(0).setMinWidth(140);
            tblItens.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblItens.getColumnModel().getColumn(0).setMaxWidth(140);
            tblItens.getColumnModel().getColumn(1).setMinWidth(220);
            tblItens.getColumnModel().getColumn(1).setPreferredWidth(220);
            tblItens.getColumnModel().getColumn(1).setMaxWidth(220);
            tblItens.getColumnModel().getColumn(2).setMinWidth(100);
            tblItens.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblItens.getColumnModel().getColumn(2).setMaxWidth(100);
            tblItens.getColumnModel().getColumn(3).setMinWidth(100);
            tblItens.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblItens.getColumnModel().getColumn(3).setMaxWidth(100);
            tblItens.getColumnModel().getColumn(4).setMinWidth(100);
            tblItens.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblItens.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        tblVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ordem Venda", "Mesa", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVendas.getTableHeader().setReorderingAllowed(false);
        tblVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendasMouseClicked(evt);
            }
        });
        tblVendas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblVendasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblVendas);

        btnCarregar.setText("Buscar Vendas");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ordem:");

        buttonGroup1.add(rbtOrdemVenda);
        rbtOrdemVenda.setSelected(true);
        rbtOrdemVenda.setText("Ordem Venda");

        buttonGroup1.add(rbtOrdemMesa);
        rbtOrdemMesa.setText("Mesa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtOrdemMesa)
                    .addComponent(rbtOrdemVenda)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtOrdemVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtOrdemMesa)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateItensVenda(){
        DefaultTableModel tabelaVenda = (DefaultTableModel) tblVendas.getModel();
        DefaultTableModel tabelaItens = (DefaultTableModel) tblItens.getModel();
        tabelaItens.setRowCount(0);
        int ordemVenda = Integer.parseInt(tabelaVenda.getValueAt(getLinha(), 0).toString());
        ArrayList<VendaEncerrada> venda = vendaDAO.getVendasItens(ordemVenda);

        for (VendaEncerrada v : venda) {
            if (v != null) {     
                
                DecimalFormat df = new DecimalFormat("#,###.00");
                String qtd = df.format(v.getQuantidade());
                String pre = df.format(v.getPreco());
                String tot;
                tot = df.format(v.getQuantidade() * v.getPreco());
                
                Produto p = new Produto();
                p = produtoDAO.getProdutoById(v.getIdProduto());
                
                Object[] objects = new Object[]{
                    v.getData(),
                    p.getDescricao(),
                    qtd,
                    pre,
                    tot
                };
                tabelaItens.addRow(objects);
            }
        }
    }
    
    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        String ordem;
        if (rbtOrdemVenda.isSelected())
            ordem = "ordemVenda";
        else
            ordem = "idmesa";
                   
        ArrayList<VendaC> VendaCab = vendaDAO.getVendasCab(ordem);        
        DefaultTableModel tabelaVenda = (DefaultTableModel) tblVendas.getModel();
        tabelaVenda.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#,###.00");
        for (VendaC v : VendaCab) {
            if (v != null) {      
                Object[] objects = new Object[]{
                    v.getOrdemVenda(),
                    v.getIdmesa(),
                    df.format(v.getTotal())};
                tabelaVenda.addRow(objects);
            }
        }        
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void tblVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendasMouseClicked
        setLinha(tblVendas.getSelectedRow());
    }//GEN-LAST:event_tblVendasMouseClicked

    private void tblVendasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVendasKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_UP))
            setLinha(tblVendas.getSelectedRow());
    }//GEN-LAST:event_tblVendasKeyReleased

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
            java.util.logging.Logger.getLogger(FormRelatorioVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRelatorioVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRelatorioVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRelatorioVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRelatorioVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCarregar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbtOrdemMesa;
    private javax.swing.JRadioButton rbtOrdemVenda;
    private javax.swing.JTable tblItens;
    private javax.swing.JTable tblVendas;
    // End of variables declaration//GEN-END:variables
}
