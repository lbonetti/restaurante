/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Este Código foi adaptado do programa apresentado no livro Estruturas de Dados e Algoritmos em Java
// Robert Lafore
package dao;

import beans.Produto;

/**
 *
 * @adaptador aleaguado
 */
public class EstruturaDadosProduto {

    ProdutoDAO produtoDAO = new ProdutoDAO();
    private Produto[] p;
    private int nElems;               // controla a qty de itens no vetor
//--------------------------------------------------------------

    public EstruturaDadosProduto() // Método construtor
    {
        p = produtoDAO.getProdutos();                    // Instancia um novo array
        nElems = p.length;                        // coloca no inicio a variavel nElems c/ zero elemento
    }

    //--------------------------------------------------------------
    // Rotina de Ordenação pelo método de Inserção (InsertSort)
    //-----------------------------------------------------------
    public Produto[] insertionSortCodigo() {
        int in, out;

        for (out = 1; out < nElems; out++) // vamos rodar até o final do vetor
        {
            Produto temp = p[out];            // jogamos em uma variável temporária o item marcado
            in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
            while (in > 0 && p[in - 1].getIdproduto() >= temp.getIdproduto()) // enquanto o item a esquerda for maior que o marcado ...
            {
                p[in] = p[in - 1];            // ...nós fazemos eles pularem uma casa a direita
                --in;
            }                           // ... quando o item for menor, ele nao pula!
            p[in] = temp;                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
            // onde estava o último item a esquerda maior que o marcado
        }  // end for
        return p;
    }  // end insertionSort()
//--------------------------------------------------------------
    //--------------------------------------------------------------
    // Rotina de Ordenação pelo método de Inserção (InsertSort)
    //-----------------------------------------------------------

    public Produto[] insertionSortDescricao() {
        int in, out;

        for (out = 1; out < nElems; out++) // vamos rodar até o final do vetor
        {
            Produto temp = p[out];            // jogamos em uma variável temporária o item marcado
            in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
            while (in > 0 && p[in - 1].getDescricao().compareToIgnoreCase(temp.getDescricao()) > 0) // enquanto o item a esquerda for maior que o marcado ...
            {
                p[in] = p[in - 1];            // ...nós fazemos eles pularem uma casa a direita
                --in;
            }                           // ... quando o item for menor, ele nao pula!
            p[in] = temp;                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
            // onde estava o último item a esquerda maior que o marcado
        }  // end for
        return p;
    }  // end insertionSort()
//--------------------------------------------------------------   
//-----------------------------------------------------------

    public Produto[] insertionSortPreco() {
        int in, out;

        for (out = 1; out < nElems; out++) // vamos rodar até o final do vetor
        {
            Produto temp = p[out];            // jogamos em uma variável temporária o item marcado
            in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
            while (in > 0 && p[in - 1].getPrecoVenda() > temp.getPrecoVenda()) // enquanto o item a esquerda for maior que o marcado ...
            {
                p[in] = p[in - 1];            // ...nós fazemos eles pularem uma casa a direita
                --in;
            }                           // ... quando o item for menor, ele nao pula!
            p[in] = temp;                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
            // onde estava o último item a esquerda maior que o marcado
        }  // end for
        return p;
    }  // end insertionSort()
//--------------------------------------------------------------   

    //--------------------------------------------------------------
//Método de pesquisa binária ....
//--------------------------------------------------------------
    public Produto find(int idmesa) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (p[curIn].getIdproduto() == idmesa) {
                return p[curIn];              // encontrei!
            } else if (lowerBound > upperBound) {
                return null;             // não pude encontra-lo
            } else // divide o range
            {
                if (p[curIn].getIdproduto() < idmesa) {
                    lowerBound = curIn + 1; // esta na metade de cima
                } else {
                    upperBound = curIn - 1; // esta na metade de baixo
                }
            }  // fim do else de divisão de range
        }  // fim do while
    }  // fim do método de pesquisa binária()
//--------------------------------------------------------------
}
