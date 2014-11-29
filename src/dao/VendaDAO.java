/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Venda;
import beans.VendaC;
import beans.VendaEncerrada;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson
 */
public class VendaDAO extends GenericDAO {

    public VendaDAO() {
        super();
    }

    public boolean inserir(Venda venda) {
        String sql = "INSERT INTO vendaandamento(dataA, idproduto, idmesa, quantidade, preco) VALUES (?, ?, ?, ?, ?)";
        try {
            this.prepareStmte(sql);

            Calendar c = new GregorianCalendar();
            c.setTime(venda.getData());
            c.set(Calendar.MILLISECOND, 0);
            Timestamp t = new Timestamp(c.getTimeInMillis());

            this.stmte.setTimestamp(1, t);
            this.stmte.setInt(2, venda.getIdProduto());
            this.stmte.setInt(3, venda.getIdMesa());
            this.stmte.setDouble(4, venda.getQuantidade());
            this.stmte.setDouble(5, venda.getPreco());

            this.stmte.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean editar(Venda venda) {
        String sql = "UPDATE vendaandamento SET quantidade = ?, preco=? WHERE dataA = ? and idproduto = ? and idmesa = ?";
        try {
            this.prepareStmte(sql);
            this.stmte.setDouble(1, venda.getQuantidade());
            this.stmte.setDouble(2, venda.getPreco());

            //nao encontrei outro jeito de remover os milliseconds
            Calendar c = new GregorianCalendar();
            c.setTime(venda.getData());
            c.set(Calendar.MILLISECOND, 0);
            Timestamp t = new Timestamp(c.getTimeInMillis());

            this.stmte.setTimestamp(3, t);

            this.stmte.setInt(4, venda.getIdProduto());
            this.stmte.setInt(5, venda.getIdMesa());

            this.stmte.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    } /**/


    public boolean excluir(Venda venda) {
        String sql = "DELETE FROM vendaandamento WHERE dataA = ? and idproduto = ? and idmesa = ?";
        try {
            this.prepareStmte(sql);
            Calendar c = new GregorianCalendar();
            c.setTime(venda.getData());
            //nao encontrei outro jeito de remover os milliseconds
            c.set(Calendar.MILLISECOND, 0);
            Timestamp t = new Timestamp(c.getTimeInMillis());

            this.stmte.setTimestamp(1, t);
            this.stmte.setInt(2, venda.getIdProduto());
            this.stmte.setInt(3, venda.getIdMesa());

            this.stmte.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
            return false;
        }
    }
    
    public boolean encerrarVenda() {
        String sql = "DELETE FROM vendaandamento";
        try {
            this.prepareStmte(sql);
            this.stmte.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
            return false;
        }
    }
    /**/

    /*
     public boolean excluir(Venda Venda){
     String sql = "DELETE FROM Venda WHERE idVenda = ?";
     try{
     this.prepareStmte(sql);
     this.stmte.setInt(1, Venda.getIdVenda());
     this.stmte.execute();
     return true;
     }
     catch(Exception e){
     return false;
     }
     }*/

    /**
     *
     * @param data
     * @param idProduto
     * @param idMesa
     * @return
     */
    public Venda getVendaByIds(Date data, int idProduto, int idMesa) {
        Venda venda = new Venda();
        String sql = "SELECT * FROM vendaandamento WHERE dataA = ? and idproduto = ? and idmesa = ?";
        try {
            this.prepareStmte(sql);
            Calendar c = new GregorianCalendar();
            c.setTime(data);
            c.set(Calendar.MILLISECOND, 0);
            Timestamp t = new Timestamp(c.getTimeInMillis());

            this.stmte.setTimestamp(3, t);

            this.stmte.setInt(4, idProduto);
            this.stmte.setInt(5, idMesa);
            ResultSet rs = this.stmte.executeQuery();
            if (rs.first()) {
                venda.setData(data);
                venda.setIdMesa(idMesa);
                venda.setIdProduto(idProduto);
                venda.setPreco(rs.getDouble("preco"));
                venda.setQuantidade(rs.getDouble("quantidade"));
            } else {
                JOptionPane.showMessageDialog(null, "Registro não encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
        }
        return venda;
    }

    public int getCountVendaEncerrada() {
        String sql = "SELECT COUNT(*) AS quantidade FROM vendaencerrada";
        try {
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("quantidade");
            }

            return i;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            return 0;
        }
    }

    public ArrayList<Venda> getVendasByMesa(int idMesa) {

        ArrayList<Venda> r = new ArrayList<>();
        String sql = "SELECT * FROM vendaandamento WHERE idmesa = ?";
        try {
            this.prepareStmte(sql);
            this.stmte.setInt(1, idMesa);
            ResultSet rs = this.stmte.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setData(rs.getTimestamp("dataA"));
                venda.setIdMesa(idMesa);
                venda.setIdProduto(rs.getInt("idproduto"));
                venda.setPreco(rs.getDouble("preco"));
                venda.setQuantidade(rs.getDouble("quantidade"));
                r.add(venda);
            }
            //
            //   JOptionPane.showMessageDialog(null, "Registro não encontrado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
        }
        return r;
    }

    public ArrayList<VendaC> getVendasCab(String ordem) {

        ArrayList<VendaC> v = new ArrayList<>();

        String sql = "SELECT v.ordemVenda, v.idmesa, sum(round(v.preco * v.quantidade,2)) as Total FROM vendaencerrada v group by 1,2 \n" +
                     "order by "+ordem;
        try {
            this.prepareStmte(sql);            
            ResultSet rs = this.stmte.executeQuery();
            int index = 0;
            while (rs.next()) {
                VendaC cab = new VendaC();
                cab.setOrdemVenda(rs.getInt("ordemVenda"));
                cab.setIdmesa(rs.getInt("idmesa"));
                cab.setTotal(rs.getDouble("total"));
                v.add(cab);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
        }

        return v;
    }

    public ArrayList<VendaEncerrada> getVendasItens(int ordemVenda) {
        ArrayList<VendaEncerrada> v = new ArrayList<>();

        String sql = "SELECT * FROM vendaencerrada v WHERE v.ordemVenda = ?";
        try {
            this.prepareStmte(sql);
            this.stmte.setInt(1, ordemVenda);
            ResultSet rs = this.stmte.executeQuery();
            int index = 0;
            while (rs.next()) {
                VendaEncerrada venda = new VendaEncerrada();
                venda.setOrdemVenda(rs.getInt("ordemVenda"));
                venda.setData(rs.getTimestamp("dataA"));
                venda.setIdMesa(rs.getInt("idmesa"));
                venda.setIdProduto(rs.getInt("idproduto"));
                venda.setPreco(rs.getDouble("preco"));
                venda.setQuantidade(rs.getDouble("quantidade"));
                v.add(venda);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
        }

        return v;
    }
}
