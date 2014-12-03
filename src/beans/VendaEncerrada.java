/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author Anderson
 */
public class VendaEncerrada extends Venda {
    
    private int ordemVenda;

    public int getOrdemVenda() {
        return ordemVenda;
    }

    public void setOrdemVenda(int ordemVenda) {
        this.ordemVenda = ordemVenda;
    }
    
    public double getTotal(){
        return getPreco() * getQuantidade();
    }
    
}
