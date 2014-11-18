/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import beans.Mesa;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Professor
 */
public class MesaDAO extends GenericDAO {
    
    public MesaDAO(){
        super();
    }
    
    public boolean inserir(Mesa mesa){
        String sql = "INSERT INTO mesa(idmesa, status) VALUES (?, 'l')";
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, mesa.getIdmesa());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean editar(Mesa mesa){
        String sql = "UPDATE mesa SET descricao = ?, status=? WHERE idmesa = ?";
        try{
            this.prepareStmte(sql);
            this.stmte.setString(1, mesa.getDescricao());
            this.stmte.setString(2, mesa.getStatus());
            this.stmte.setInt(3, mesa.getIdmesa());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean excluir(Mesa mesa){
        String sql = "DELETE FROM mesa WHERE idmesa = ?";
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, mesa.getIdmesa());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public Mesa getMesaById(int idmesa){
        Mesa mesa = new Mesa();
        String sql = "SELECT * FROM mesa WHERE idmesa = ?";
        
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, idmesa);
            ResultSet rs = this.stmte.executeQuery();
            rs.first();
            mesa.setIdmesa(rs.getInt("idmesa"));
            mesa.setDescricao(rs.getString("descricao"));
            mesa.setStatus(rs.getString("status"));
        }
        catch(Exception e){
            
        }
        return mesa;
    }
    
    private int getMesaCount(){
        String sql="SELECT COUNT(idmesa) AS quantidade FROM mesa";
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
    
    public Mesa[] getMesas(){
        Mesa [] mesa;
        String sql="SELECT * FROM mesa";
        try{
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            int linhas = this.getMesaCount();
            mesa = new Mesa[linhas];
            int x = 0;
            while(rs.next()){
                Mesa m = new Mesa();
                m.setIdmesa(rs.getInt("idmesa"));
                m.setDescricao(rs.getString("descricao"));
                m.setStatus(rs.getString("status"));
                mesa[x] = m;
                x++;
            }
            return mesa;
        }
        catch(Exception e){
            return null;
        }
    }
}
