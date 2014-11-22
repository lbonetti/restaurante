/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import beans.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson
 */
public class VendaDAO extends GenericDAO{
    public VendaDAO(){
        super();
    }
    /*
    public boolean inserir(Venda venda){
        String sql = "INSERT INTO Venda(idVenda, descricao, precoVenda) VALUES (?, ?, ?)";
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, venda.getIdVenda());
            this.stmte.setString(2, venda.getDescricao());
            this.stmte.setDouble(3, venda.getPrecoVenda());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean editar(Venda Venda){
        String sql = "UPDATE Venda SET descricao = ?, precoVenda=? WHERE idVenda = ?";
        try{
            this.prepareStmte(sql);
            this.stmte.setString(1, Venda.getDescricao());
            this.stmte.setDouble(2, Venda.getPrecoVenda());
            this.stmte.setInt(3, Venda.getIdVenda());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
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
    }
    
    public Venda getVendaById(int idVenda){
        Venda Venda = new Venda();
        String sql = "SELECT * FROM Venda WHERE idVenda = ?";
        
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, idVenda);
            ResultSet rs = this.stmte.executeQuery();
            rs.first();
            Venda.setIdVenda(rs.getInt("idVenda"));
            Venda.setDescricao(rs.getString("descricao"));
            Venda.setPrecoVenda(rs.getDouble("precoVenda"));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado");
        }
        return Venda;
    }
    
    private int getVendaCount(){
        String sql="SELECT COUNT(idVenda) AS quantidade FROM Venda";
        try{
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            rs.next();
            int i = rs.getInt("quantidade");
            return i;
        }
        catch (Exception e){
            return 0;
        }
    }
    
    public Venda[] getVendas(){
        Venda [] Venda;
        String sql="SELECT * FROM Venda";
        try{
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            int linhas = this.getVendaCount();
            Venda = new Venda[linhas];
            int x = 0;
            while(rs.next()){
                Venda p = new Venda();
                p.setIdVenda(rs.getInt("idVenda"));
                p.setDescricao(rs.getString("descricao"));
                p.setPrecoVenda(rs.getDouble("precoVenda"));
                Venda[x] = p;
                x++;
            }
            return Venda;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public int getNextID() {
        String sql="SELECT ifnull(max(idVenda),0)+1 AS id FROM Venda";
        try{
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
        catch (SQLException e){
            return -1;
        }        
    }*/
}
