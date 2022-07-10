/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import peccorina.Cliente;
import peccorina.Mensajes;

/**
 *
 * @author mario
 */
public class LikeTableModel  extends AbstractTableModel{
     private String[] columns = {"Te ha dado like"};
    private ArrayList<Cliente> cliente;

    public LikeTableModel(ArrayList<Cliente> cliente) {
        this.cliente = cliente;

    }

    public void annadirMensaje(Cliente m) {
        this.cliente.add(m);
        fireTableDataChanged();
    }

    public void eliminarMensaje(int rowIndex) {
        cliente.remove(rowIndex);
        fireTableDataChanged();
    }

    public Cliente obtenerRegistroNota(int rowIndex) {
        return cliente.get(rowIndex);
    }

    public ArrayList<Cliente> getMensaje() {
        return cliente;
    }

    @Override
    public int getRowCount() {
        return cliente.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override

    public Object getValueAt(int rowIndex, int colIndex) {

        Cliente pa = cliente.get(rowIndex);

        switch (colIndex) {
            case 0:
                return pa.getCorreoUsuario();
           
            default:
                return pa;
        }
    }

    @Override
    public String getColumnName(int colIndex) {
        return columns[colIndex];
    }

    
    
}
