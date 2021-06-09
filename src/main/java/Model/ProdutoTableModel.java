/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.ProdutoDAO;
import Objetos.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class ProdutoTableModel extends AbstractTableModel {

    //nesse objeto instanciado se cria uma lista
    private List<Produto> dados = new ArrayList<>();
    //Um vetor da coluna
    private String[] colunas = {"Descrição", "Quantidade", "Valor"};

    //Esse método colocará os nomes da coluna na tabela
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    //Esse  método contará as lihas da tabela.
    @Override
    public int getRowCount() {
        return dados.size();
    }

    //Esse método pegara a quantidade de colunas na tabela
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //Esse método irá carregar o que tem em cada coluna de cada linha
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getDescricao();
            case 1:
                return dados.get(linha).getQuantidade();
            case 2:
                return dados.get(linha).getValor();
        }
        return null;
    }

    //Serve para mudar um valor de dentro da tabela
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setDescricao((String) valor);
                break;
            case 1:
                dados.get(linha).setQuantidade(Integer.parseInt((String) valor));
                break;
            case 2:
                dados.get(linha).setValor(Double.parseDouble((String) valor));
                break;

        }
        this.fireTableRowsUpdated(linha, linha);
    }

    //Método para adicionar linhas para a tabela
    //No final será substituido pelo add do produto DAO
    public void addLinha(Produto p) {
        this.dados.add(p);
        this.fireTableDataChanged();
    }

    //Método para remover a linha da tabela
    //No final será substituido pelo delete no ProdutoDAO
    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Produto pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

   private void lerDados() {
        ProdutoDAO pdao = new ProdutoDAO();

        for (Produto p : pdao.read()) {
            this.addLinha(p);

        }
        this.fireTableDataChanged();
    }
   
   //Esse método serve para atualizar a tabela.
   public void recarregaTabela(){
       this.dados.clear();
       lerDados();
       this.fireTableDataChanged();
   }

}
