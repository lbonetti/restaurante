/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import beans.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO extends GenericDAO{
    
    
    public ProdutoDAO(){
        super();
    }
    
    public boolean inserir(Produto produto){
        String sql = "INSERT INTO produto(idproduto) VALUES (?)";
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, produto.getIdproduto());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean editar(Produto produto){
        String sql = "UPDATE produto SET descricao = ?, precoVenda=? WHERE idproduto = ?";
        try{
            this.prepareStmte(sql);
            this.stmte.setString(1, produto.getDescricao());
            this.stmte.setDouble(2, produto.getPrecoVenda());
            this.stmte.setInt(3, produto.getIdproduto());
            this.stmte.execute();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public Produto getProdutoById(int idproduto){
        Produto produto = new Produto();
        String sql = "SELECT * FROM produto WHERE idproduto = ?";
        
        try{
            this.prepareStmte(sql);
            this.stmte.setInt(1, idproduto);
            ResultSet rs = this.stmte.executeQuery();
            rs.first();
            produto.setIdproduto(rs.getInt("idproduto"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPrecoVenda(rs.getDouble("precoVenda"));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado");
        }
        return produto;
    }
    
    private int getProdutoCount(){
        String sql="SELECT COUNT(idproduto) AS quantidade FROM produto";
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
    
    public Produto[] getProdutos(){
        Produto [] produto;
        String sql="SELECT * FROM produto";
        try{
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            int linhas = this.getProdutoCount();
            produto = new Produto[linhas];
            int x = 0;
            while(rs.next()){
                Produto p = new Produto();
                p.setIdproduto(rs.getInt("idproduto"));
                p.setDescricao(rs.getString("descricao"));
                p.setPrecoVenda(rs.getDouble("precoVenda"));
                produto[x] = p;
                x++;
            }
            return produto;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public int getNextID() {
        String sql="SELECT ifnull(max(idproduto),0)+1 AS id FROM produto";
        try{
            this.prepareStmte(sql);
            ResultSet rs = this.stmte.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
        catch (SQLException e){
            return -1;
        }        
    }
}
