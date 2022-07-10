/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import peccorina.Cliente;

/**
 *
 * @author mario
 */
public class AdminTableModel extends AbstractTableModel {

    private ArrayList<Cliente> cliente;
    private String[] columns = {"Correo", "Sexo", "Edad", "Orientación", "Ciudad", "Teléfono", "Likedado", "Likerecibido"};

    public AdminTableModel(ArrayList<Cliente> cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Cliente> getCliente() {
        return cliente;
    }

    public void annadirCliente(Cliente m) {
        this.cliente.add(m);
        fireTableDataChanged();
    }

    public void eliminarCliente(int rowIndex) {
        cliente.remove(rowIndex);
        fireTableDataChanged();
    }

    public Cliente obtenerCliente(int rowIndex) {
        return cliente.get(rowIndex);
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
            case 1:
                return pa.getSexoUsuario();
            case 2:
                return pa.getEdadUsuario();
            case 3:
                return pa.getOrientacionSexual();
            case 4:
                return pa.getCiudadUsuario();
            case 5:
                return pa.getTelefonoUsuario();
            case 6:
                return pa.getDarLikeContador();
            case 7:
                return pa.getRecibirLikeContador();

            default:
                return pa;
        }
    }

    @Override
    public String getColumnName(int colIndex) {
        return columns[colIndex];
    }

}
