/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aula14;

import beans.Mesa;
import dao.MesaDAO;
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
        
        Mesa u = new Mesa();
        u.setIdusuario(2);
        
        MesaDAO usuarioDAO = new MesaDAO();
        
        if(usuarioDAO.excluir(u) == true){
            System.out.println("Usuario excluido");
        }
        else{
            System.out.println("Erro ao excluir usuario");
        }
    }
    
}
