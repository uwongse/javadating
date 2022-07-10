/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import peccorina.Mensajes;

/**
 *
 * @author alumno
 */
public class TablaMensajeModel extends AbstractTableModel {

    private String[] columns = {"Emisor", "Mensaje", "Receptor"};
    private ArrayList<Mensajes> mensaje;

    public TablaMensajeModel(ArrayList<Mensajes> mensaje) {
        this.mensaje = mensaje;

    }

    public void annadirMensaje(Mensajes m) {
        this.mensaje.add(m);
        fireTableDataChanged();
    }

    public void eliminarMensaje(int rowIndex) {
        mensaje.remove(rowIndex);
        fireTableDataChanged();
    }

    public Mensajes obtenerRegistroNota(int rowIndex) {
        return mensaje.get(rowIndex);
    }

    public ArrayList<Mensajes> getMensaje() {
        return mensaje;
    }

    @Override
    public int getRowCount() {
        return mensaje.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override

    public Object getValueAt(int rowIndex, int colIndex) {

        Mensajes pa = mensaje.get(rowIndex);

        switch (colIndex) {
            case 0:
                return pa.getCorreoEmisor();
            case 1:
                return pa.getMensaje();
            case 2:
                return pa.getCorreReceptor();

            default:
                return pa;
        }
    }

    @Override
    public String getColumnName(int colIndex) {
        return columns[colIndex];
    }

}
