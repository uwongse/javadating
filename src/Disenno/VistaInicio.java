/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenno;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import peccorina.Admin;
import peccorina.SistemaPeccorina;
import peccorina.Usuario;

/**
 *
 * @author alumno
 */
public class VistaInicio extends javax.swing.JFrame {

    /**
     * Creates new form VistaInicio
     */
    SistemaPeccorina sistema;
    Usuario u;
    int mousepX;
    int mousepY;

    public VistaInicio() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Peccorina");
        setResizable(false);
        this.jLabel1.setVisible(false);
        sistema = new SistemaPeccorina();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTitulo = new javax.swing.JPanel();
        jtfCorreo = new javax.swing.JTextField();
        jpfContrasenna = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbInicio = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jbRegistro = new javax.swing.JLabel();
        jlExit = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jpTitulo.setBackground(new java.awt.Color(72, 59, 134));
        jpTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jpTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtfCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCorreoActionPerformed(evt);
            }
        });
        jpTitulo.add(jtfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 173, 24));

        jpfContrasenna.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpTitulo.add(jpfContrasenna, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 173, 24));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Correo:");
        jpTitulo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contrase??a:");
        jpTitulo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel1.setForeground(new java.awt.Color(255, 153, 153));
        jLabel1.setText("El correo o la contrase??a no coinciden");
        jpTitulo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbInicio.setBackground(new java.awt.Color(72, 59, 134));
        jbInicio.setForeground(new java.awt.Color(255, 255, 255));
        jbInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbInicio.setText("Iniciar sesi??n");
        jbInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jbInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbInicio.setOpaque(true);
        jbInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbInicioMouseExited(evt);
            }
        });
        jPanel2.add(jbInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        jpTitulo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 120, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbRegistro.setBackground(new java.awt.Color(72, 59, 134));
        jbRegistro.setForeground(new java.awt.Color(255, 255, 255));
        jbRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbRegistro.setText("Registrarse");
        jbRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jbRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbRegistro.setOpaque(true);
        jbRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbRegistroMouseExited(evt);
            }
        });
        jPanel3.add(jbRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        jpTitulo.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 120, 30));

        jlExit.setBackground(new java.awt.Color(27, 91, 93));
        jlExit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jlExit.setForeground(new java.awt.Color(255, 255, 255));
        jlExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlExit.setText("X");
        jlExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlExitMouseExited(evt);
            }
        });
        jpTitulo.add(jlExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(341, 0, 35, -1));

        jPanel1.setBackground(new java.awt.Color(67, 114, 146));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("P E C C O R I N A");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel2.setBackground(new java.awt.Color(153, 255, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 69, 62));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Icono.png")));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCorreoActionPerformed

    private void jlExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jlExitMouseClicked

    private void jbRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRegistroMouseClicked
        // TODO add your handling code here:
        VistaRegistro vr = new VistaRegistro();
        vr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbRegistroMouseClicked

    private void jbRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRegistroMouseEntered
        // TODO add your handling code here:
        Color color = new Color(176, 162, 219);
        jbRegistro.setBackground(color);
        jbRegistro.setForeground(Color.BLACK);
    }//GEN-LAST:event_jbRegistroMouseEntered

    private void jbRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRegistroMouseExited
        // TODO add your handling code here:
        Color color = new Color(72, 59, 134);
        jbRegistro.setBackground(color);
        jbRegistro.setForeground(Color.WHITE);
    }//GEN-LAST:event_jbRegistroMouseExited

    private void jbInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbInicioMouseEntered
        // TODO add your handling code here:
        Color color = new Color(176, 162, 219);
        jbInicio.setBackground(color);
        jbInicio.setForeground(Color.BLACK);
    }//GEN-LAST:event_jbInicioMouseEntered

    private void jbInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbInicioMouseExited
        // TODO add your handling code here:
        Color color = new Color(72, 59, 134);
        jbInicio.setBackground(color);
        jbInicio.setForeground(Color.WHITE);
    }//GEN-LAST:event_jbInicioMouseExited

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int coordX = evt.getXOnScreen();
        int coordY = evt.getYOnScreen();

        this.setLocation(coordX - mousepX, coordY - mousepY);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        mousepX = evt.getX();
        mousepY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void jbInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbInicioMouseClicked
        // TODO add your handling code here:
        u = sistema.buscarUsuario(jtfCorreo.getText(), Arrays.toString(jpfContrasenna.getPassword()));
        ImageIcon ii = sistema.buscarFoto(jtfCorreo.getText());
        if (u != null) {
            if (u instanceof Admin) {
                this.dispose();
                VistaAdmin va = new VistaAdmin(u);

            } else {
                if (u != null) {
                    this.dispose();
                    VistaUsuario vu = new VistaUsuario(u, ii);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contrase??a incorrecta");
        }
    }//GEN-LAST:event_jbInicioMouseClicked

    private void jlExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseEntered
        // TODO add your handling code here:
        Color color = new Color(153, 153, 153);
        jlExit.setBackground(color);
    }//GEN-LAST:event_jlExitMouseEntered

    private void jlExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseExited
        // TODO add your handling code here:
        jlExit.setBackground(Color.WHITE);
    }//GEN-LAST:event_jlExitMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jbInicio;
    private javax.swing.JLabel jbRegistro;
    private javax.swing.JLabel jlExit;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JPasswordField jpfContrasenna;
    private javax.swing.JTextField jtfCorreo;
    // End of variables declaration//GEN-END:variables
}
