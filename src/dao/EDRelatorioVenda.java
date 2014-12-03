/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.VendaC;
import beans.VendaEncerrada;
import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class EDRelatorioVenda {

    ArrayList<VendaC> VendaCab;
    ArrayList<VendaEncerrada> VendaItens;
    VendaDAO vendaDAO = new VendaDAO();

    public EDRelatorioVenda() {
        VendaCab = vendaDAO.getVendasCab();
    }
    
    public ArrayList<VendaC> getVendaCab(String Ordem, int OrdemInicial, int OrdemFinal) {
        VendaCab = vendaDAO.getVendasCab(OrdemInicial, OrdemFinal);        
        return getVendaCab(Ordem);
    }

    public ArrayList<VendaC> getVendaCab(String Ordem) {
        int in, out;

        if (Ordem.equals("O"))//ordem venda
        {
            for (out = 1; out < VendaCab.size(); out++) // vamos rodar até o final do vetor
            {
                VendaC temp = VendaCab.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaCab.get(in - 1).getOrdemVenda() >= temp.getOrdemVenda()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaCab.set(in, VendaCab.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaCab.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        
        if (Ordem.equals("M"))//Mesa
        {
            for (out = 1; out < VendaCab.size(); out++) // vamos rodar até o final do vetor
            {
                VendaC temp = VendaCab.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaCab.get(in - 1).getIdmesa() >= temp.getIdmesa()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaCab.set(in, VendaCab.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaCab.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        
        if (Ordem.equals("T"))//Total
        {
            for (out = 1; out < VendaCab.size(); out++) // vamos rodar até o final do vetor
            {
                VendaC temp = VendaCab.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaCab.get(in - 1).getTotal() >= temp.getTotal()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaCab.set(in, VendaCab.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaCab.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        return VendaCab;
    }
    
    public ArrayList<VendaEncerrada> getVendaItens(int ordemVenda, String ordem){
        VendaItens = vendaDAO.getVendasItens(ordemVenda);
        return getVendaItens(ordem);
    }
    
     public ArrayList<VendaEncerrada> getVendaItens(String Ordem){
        int in, out;

        if (Ordem.equals("D"))//Data
        {
            for (out = 1; out < VendaItens.size(); out++) // vamos rodar até o final do vetor
            {
                VendaEncerrada temp = VendaItens.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaItens.get(in - 1).getData().compareTo(temp.getData()) > 0) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaItens.set(in, VendaItens.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaItens.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        
        if (Ordem.equals("P"))//Produto
        {
            for (out = 1; out < VendaItens.size(); out++) // vamos rodar até o final do vetor
            {
                VendaEncerrada temp = VendaItens.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaItens.get(in - 1).getIdProduto() >= temp.getIdProduto()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaItens.set(in, VendaItens.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaItens.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        
        if (Ordem.equals("Q"))//Quantidade
        {
            for (out = 1; out < VendaItens.size(); out++) // vamos rodar até o final do vetor
            {
                VendaEncerrada temp = VendaItens.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaItens.get(in - 1).getQuantidade() >= temp.getQuantidade()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaItens.set(in, VendaItens.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaItens.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        
        if (Ordem.equals("V"))//Valor
        {
            for (out = 1; out < VendaItens.size(); out++) // vamos rodar até o final do vetor
            {
                VendaEncerrada temp = VendaItens.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaItens.get(in - 1).getPreco() >= temp.getPreco()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaItens.set(in, VendaItens.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaItens.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        
        if (Ordem.equals("T"))//Total
        {
            for (out = 1; out < VendaItens.size(); out++) // vamos rodar até o final do vetor
            {
                VendaEncerrada temp = VendaItens.get(out);            // jogamos em uma variável temporária o item marcado
                in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
                while (in > 0 && VendaItens.get(in - 1).getTotal() >= temp.getTotal()) // enquanto o item a esquerda for maior que o marcado ...
                {
                    VendaItens.set(in, VendaItens.get(in - 1));            // ...nós fazemos eles pularem uma casa a direita
                    --in;
                }                           // ... quando o item for menor, ele nao pula!
                VendaItens.set(in,temp);                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
                // onde estava o último item a esquerda maior que o marcado
            }  // end for
        }
        return VendaItens;
    }
    
}
