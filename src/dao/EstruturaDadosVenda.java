/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Este Código foi adaptado do programa apresentado no livro Estruturas de Dados e Algoritmos em Java
// Robert Lafore
package dao;

import beans.VendaEncerrada;

/**
 *
 * @adaptador aleaguado
 */
public class EstruturaDadosVenda {

    VendaDAO vendaDAO = new VendaDAO();
    private VendaEncerrada[] v;
    private int nElems;               // controla a qty de itens no vetor
//--------------------------------------------------------------

    public EstruturaDadosVenda() // Método construtor
    {
        v = vendaDAO.getVendasEncerradas();                    // Instancia um novo array
        nElems = v.length;                        // coloca no inicio a variavel nElems c/ zero elemento
    }
    
    

    /*
     //--------------------------------------------------------------
     // Rotina de Ordenação pelo método de Inserção (InsertSort)
     //-----------------------------------------------------------
     public Mesa[] insertionSortCrescente()
     {
     int in, out;

     for(out=1; out<nElems; out++)     // vamos rodar até o final do vetor
     {
     Mesa temp = m[out];            // jogamos em uma variável temporária o item marcado
     in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
     while(in>0 && m[in-1].getIdmesa() >= temp.getIdmesa()) // enquanto o item a esquerda for maior que o marcado ...
     {
     m[in] = m[in-1];            // ...nós fazemos eles pularem uma casa a direita
     --in;                       
     }                           // ... quando o item for menor, ele nao pula!
     m[in] = temp;                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
     // onde estava o último item a esquerda maior que o marcado
     }  // end for
     return m;
     }  // end insertionSort()
     */

    /*
     //--------------------------------------------------------------
     // Rotina de Ordenação pelo método de Inserção (InsertSort)
     //-----------------------------------------------------------
     public Mesa[] insertionSortDecrescente()
     {
     int in, out;

     for(out=1; out<nElems; out++)     // vamos rodar até o final do vetor
     {
     Mesa temp = m[out];            // jogamos em uma variável temporária o item marcado
     in = out;                      // inicializamos a variavel in, que ira receber o valor a esquerda
     while(in>0 && m[in-1].getIdmesa() <= temp.getIdmesa()) // enquanto o item a esquerda for maior que o marcado ...
     {
     m[in] = m[in-1];            // ...nós fazemos eles pularem uma casa a direita
     --in;                       
     }                           // ... quando o item for menor, ele nao pula!
     m[in] = temp;                  // ...inserimos o valor marcado na posição que ficou "livre", ou seja,
     // onde estava o último item a esquerda maior que o marcado
     }  // end for
     return m;
     }  // end insertionSort()
     //--------------------------------------------------------------   

     private void swap(int one, int two) //Simples rotina onde é efetuada a troca!!!
     {
     Mesa temp = m[one];
     m[one] = m[two];
     m[two] = temp;
     }
     */
   //--------------------------------------------------------------
//Método de pesquisa binária ....
//--------------------------------------------------------------
/*   public int find(long searchKey)
     {
     int lowerBound = 0;
     int upperBound = nElems-1;
     int curIn;

     while(true)
     {
     curIn = (lowerBound + upperBound ) / 2;
     if(a[curIn]==searchKey)
     return curIn;              // encontrei!
     else if(lowerBound > upperBound)
     return nElems;             // não pude encontra-lo
     else                          // divide o range
     {
     if(a[curIn] < searchKey)
     lowerBound = curIn + 1; // esta na metade de cima
     else
     upperBound = curIn - 1; // esta na metade de baixo
     }  // fim do else de divisão de range
     }  // fim do while
     }  // fim do método de pesquisa binária()*/
//--------------------------------------------------------------
}
