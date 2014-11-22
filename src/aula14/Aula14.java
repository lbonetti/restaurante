/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aula14;

import beans.Mesa;
import beans.Produto;
import dao.MesaDAO;
import dao.ProdutoDAO;
import java.sql.Connection;
import jdbc.ConnectionFactory;

/**
 *
 * @author Professor
 */
public class Aula14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //teste mesa
        /*Mesa mesa = new Mesa();
        MesaDAO mesaDao = new MesaDAO();
        
        mesa.setDescricao("Mesa 1");
        mesa.setIdmesa(mesaDao.getNextID());
        mesa.setStatus("L");
        
        if(mesaDao.inserir(mesa) == true){
            System.out.println("Usuario adicionado");
        }
        else{
            System.out.println("Erro ao adicionar usuario");
        }*/
        
        //teste produto
        /*Produto produto = new Produto();
        ProdutoDAO pd = new ProdutoDAO();
        
        produto.setIdproduto(pd.getNextID());
        produto.setDescricao("Produto 1");
        produto.setPrecoVenda(10.4f);
         if(pd.inserir(produto) == true){
            System.out.println("Usuario adicionado");
        }
        else{
            System.out.println("Erro ao adicionar usuario");
        }*/
        
        /*
        Usuario usuario = new Usuario();
        usuario.setIdusuario(3);
        usuario.setNome("Joao");
        
        MesaDAO usuarioDAO = new MesaDAO();
        
        if(usuarioDAO.inserir(usuario) == true){
            System.out.println("Usuario adicionado");
        }
        else{
            System.out.println("Erro ao adicionar usuario");
        }
        */
        /*
        Mesa u = new Mesa();
        u.setIdusuario(2);
        
        MesaDAO usuarioDAO = new MesaDAO();
        
        if(usuarioDAO.excluir(u) == true){
            System.out.println("Usuario excluido");
        }
        else{
            System.out.println("Erro ao excluir usuario");
        }*/
    }
    
}
