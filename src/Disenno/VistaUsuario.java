/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenno;

import Chat.MServidor;
import Chat.VCliente;
import Tablas.LikeTableModel;
import Tablas.TablaMensajeModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import peccorina.Cliente;
import peccorina.SistemaPeccorina;
import peccorina.Usuario;
import peccorina.Vip;

/**
 *
 * @author alumno
 */
public class VistaUsuario extends javax.swing.JFrame {

    /**
     * Creates new form VistaUsuario
     */
    Cliente u;
    SistemaPeccorina sistema;
    ImageIcon ii;
    int mousepX;
    int mousepY;
    int nPareja;
    ArrayList<Cliente> pareja;
    private MServidor servidor = null;
    private TablaMensajeModel etm;
    private LikeTableModel lk;

    public VistaUsuario(Usuario user, ImageIcon ii) {
        this.sistema = new SistemaPeccorina();
        this.u = (Cliente) user;
        this.ii = ii;

        this.pareja = new ArrayList<Cliente>();
        cargarLikesTabla();
        cargarMensajes();

        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        generarFrame();

    }

    private VistaUsuario() {

    }

    private void generarFrame() {

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            generarImagenesUsuario();
            generarBordes(1);
            generarDatos();
            cargarPareja(0);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }

    public int getnPareja() {
        return nPareja;
    }

    private void cargarMensajes() {

        etm = new TablaMensajeModel(sistema.VerMensajes(u));

    }

    private void cargarLikesTabla() {

        lk = new LikeTableModel(sistema.VerLikes(u));

    }

    private void cargarPareja(int i) {
        if (pareja != null) {
            pareja.removeAll(pareja);
        }
        sistema.buscarPareja(u, this);
        generarDatosPareja(i);
    }

    public void annadirPareja(Cliente u) {
        pareja.add(u);
    }

    private void generarDatosPareja(int i) {

        if (pareja.size() <= nPareja) {
            nPareja = 0;
            cargarPareja(nPareja);
        } else {

            jlbNombre.setText(pareja.get(i).getNombreUsuario());
            jlbCorreo.setText(pareja.get(i).getCorreoUsuario());
            jlbSexo.setText(pareja.get(i).getSexoUsuario());
            jlbEdad.setText(Integer.toString(pareja.get(i).getEdadUsuario()));
            jlbCiudad.setText(pareja.get(i).getCiudadUsuario());

            if (u instanceof Cliente) {
                jlbTelefono.setText("Mejore su usuario a VIP");
                jlbOrientacion.setText("Mejore su usuario a VIP");
            }
            if (u instanceof Vip) {
                jlbTelefono.setText(pareja.get(i).getTelefonoUsuario());
                jlbOrientacion.setText(pareja.get(i).getOrientacionSexual());

            }
            generarImagenesPareja(i);
        }

    }

    private void generarDatos() {
        if (u instanceof Cliente) {
            jlNombreUser.setText(u.getNombreUsuario());
            jlNombre.setText(u.getNombreUsuario());
            jlCorreo.setText(u.getCorreoUsuario());
            jlSexo.setText(u.getSexoUsuario());
            jlEdad.setText(Integer.toString(u.getEdadUsuario()));
            jlCiudad.setText(u.getCiudadUsuario());
            jlTelefono.setText(u.getTelefonoUsuario());
            jlOrientacion.setText(u.getOrientacionSexual());
            jlContLike.setText(Integer.toString(u.getRecibirLikeContador()));
            jlChat.setVisible(false);
            jtfPuerto.setVisible(false);
        }

        if (u instanceof Vip) {
            jlChat.setVisible(true);
            jtfPuerto.setVisible(true);
        }
    }

    private void cambiarPanel(JPanel panel) {
        jpContenedor.removeAll();
        jpContenedor.add(panel);
        jpContenedor.repaint();
        jpContenedor.revalidate();
    }

    private void generarImagenesUsuario() {
        //IMAGEN USUARIO
        ImageIcon like = new ImageIcon(getClass().getResource("/Imagenes/like.png"));
        Image conversion = like.getImage().getScaledInstance(jlLike.getWidth(), jlLike.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon likeFin = new ImageIcon(conversion);
        jlLike.setIcon(likeFin);
        Icon fotoUser = new ImageIcon(ii.getImage().getScaledInstance(jlFoto.getWidth(), jlFoto.getHeight(), Image.SCALE_SMOOTH));
        jlFoto.setIcon(fotoUser);

        if (!u.getRoll().equals("vip")) {
            //IMAGEN VIP
            ImageIcon vip = new ImageIcon(getClass().getResource("/Imagenes/vip.png"));
            Image vipC = vip.getImage().getScaledInstance(jlVip.getWidth(), jlVip.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon vipFin = new ImageIcon(vipC);
            jlVip.setIcon(vipFin);
        } else {
            jlVip.setVisible(false);
        }
    }

    private void generarImagenesPareja(int i) {
        //IMAGE PAREJA
        ImageIcon ip = sistema.buscarFoto(pareja.get(i).getCorreoUsuario());
        Icon fotoPareja = new ImageIcon(ip.getImage().getScaledInstance(jlbFoto.getWidth(), jlbFoto.getHeight(), Image.SCALE_SMOOTH));
        jlbFoto.setIcon(fotoPareja);
        //IMAGEN LIKE
        boolean fav = sistema.comprobarLike(u, pareja.get(i));
        if (fav) {
            ImageIcon like = new ImageIcon(getClass().getResource("/Imagenes/fav.png"));
            Image conversion = like.getImage().getScaledInstance(jlbLike.getWidth(), jlbLike.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon likeFin = new ImageIcon(conversion);
            jlbLike.setIcon(likeFin);
        } else {
            ImageIcon like = new ImageIcon(getClass().getResource("/Imagenes/like.png"));
            Image conversion = like.getImage().getScaledInstance(jlbLike.getWidth(), jlbLike.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon likeFin = new ImageIcon(conversion);
            jlbLike.setIcon(likeFin);
        }
    }

    private void generarBordes(int seleccion) {
        if (seleccion == 1) {
            jpPerfil.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
            jpBusqueda.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.WHITE));
            jpMensaje.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.WHITE));
        }

        if (seleccion == 2) {
            jpPerfil.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.WHITE));
            jpBusqueda.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
            jpMensaje.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.WHITE));
        }

        if (seleccion == 3) {
            jpPerfil.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.WHITE));
            jpBusqueda.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.WHITE));
            jpMensaje.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
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

        jpPrincipal = new javax.swing.JPanel();
        jpMenu = new javax.swing.JPanel();
        jpPerfil = new javax.swing.JPanel();
        jlPerfil = new javax.swing.JLabel();
        jpBusqueda = new javax.swing.JPanel();
        jlBusqueda = new javax.swing.JLabel();
        jpMensaje = new javax.swing.JPanel();
        jlMensaje = new javax.swing.JLabel();
        jlNombreUser = new javax.swing.JLabel();
        jlExit = new javax.swing.JLabel();
        jlMinimizar = new javax.swing.JLabel();
        jpContenedor = new javax.swing.JPanel();
        jpUser = new javax.swing.JPanel();
        jlLike = new javax.swing.JLabel();
        jlContLike = new javax.swing.JLabel();
        jlFoto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlActualizar = new javax.swing.JLabel();
        jlNombre = new javax.swing.JLabel();
        jlCorreo = new javax.swing.JLabel();
        jlSexo = new javax.swing.JLabel();
        jlEdad = new javax.swing.JLabel();
        jlCiudad = new javax.swing.JLabel();
        jlTelefono = new javax.swing.JLabel();
        jlOrientacion = new javax.swing.JLabel();
        jlVip = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLikes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jpBuscar = new javax.swing.JPanel();
        jlbFoto = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlbLike = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jlbOrientacion = new javax.swing.JLabel();
        jlbSexo = new javax.swing.JLabel();
        jlbNombre = new javax.swing.JLabel();
        jlbCiudad = new javax.swing.JLabel();
        jlbCorreo = new javax.swing.JLabel();
        jlbEdad = new javax.swing.JLabel();
        jlbTelefono = new javax.swing.JLabel();
        jpOtro = new javax.swing.JPanel();
        jlbPareja = new javax.swing.JLabel();
        jpMensajes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMensaje = new javax.swing.JTextArea();
        jtfCorreoDestino = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jlBorrar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtMensajes = new javax.swing.JTable();
        jlEnviar = new javax.swing.JLabel();
        jlChat = new javax.swing.JLabel();
        jtfPuerto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jpPrincipal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpPrincipalMouseDragged(evt);
            }
        });
        jpPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpPrincipalMousePressed(evt);
            }
        });

        jpMenu.setBackground(new java.awt.Color(72, 59, 134));

        jpPerfil.setBackground(new java.awt.Color(67, 114, 146));

        jlPerfil.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jlPerfil.setForeground(new java.awt.Color(255, 255, 255));
        jlPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPerfil.setText("Perfil");
        jlPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlPerfilMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpPerfilLayout = new javax.swing.GroupLayout(jpPerfil);
        jpPerfil.setLayout(jpPerfilLayout);
        jpPerfilLayout.setHorizontalGroup(
            jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
        );
        jpPerfilLayout.setVerticalGroup(
            jpPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        jpBusqueda.setBackground(new java.awt.Color(87, 79, 124));

        jlBusqueda.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        jlBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBusqueda.setText("Buscar");
        jlBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlBusquedaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpBusquedaLayout = new javax.swing.GroupLayout(jpBusqueda);
        jpBusqueda.setLayout(jpBusquedaLayout);
        jpBusquedaLayout.setHorizontalGroup(
            jpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
        );
        jpBusquedaLayout.setVerticalGroup(
            jpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        jpMensaje.setBackground(new java.awt.Color(87, 79, 124));

        jlMensaje.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlMensaje.setForeground(new java.awt.Color(255, 255, 255));
        jlMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMensaje.setText("Mensajes");
        jlMensaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlMensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlMensajeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpMensajeLayout = new javax.swing.GroupLayout(jpMensaje);
        jpMensaje.setLayout(jpMensajeLayout);
        jpMensajeLayout.setHorizontalGroup(
            jpMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        jpMensajeLayout.setVerticalGroup(
            jpMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlMensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        jlNombreUser.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jlNombreUser.setForeground(new java.awt.Color(255, 255, 255));
        jlNombreUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNombreUser.setText("texto");

        jlExit.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
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

        jlMinimizar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        jlMinimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMinimizar.setText("_");
        jlMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlMinimizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpMenuLayout = new javax.swing.GroupLayout(jpMenu);
        jpMenu.setLayout(jpMenuLayout);
        jpMenuLayout.setHorizontalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenuLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jpPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlNombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlExit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpMenuLayout.setVerticalGroup(
            jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMenuLayout.createSequentialGroup()
                .addGroup(jpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNombreUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpContenedor.setBackground(new java.awt.Color(255, 255, 255));
        jpContenedor.setLayout(new java.awt.CardLayout());

        jpUser.setBackground(new java.awt.Color(67, 114, 146));
        jpUser.setPreferredSize(new java.awt.Dimension(1256, 825));

        jlLike.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlLike.setText("LIKE");

        jlContLike.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlContLike.setForeground(new java.awt.Color(255, 255, 255));
        jlContLike.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlContLike.setText("CONTADOR");
        jlContLike.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jlFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlFoto.setText("FOTO USUARIO");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Correo:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Edad:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ciudad:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Teléfono:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Sexo:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Orientación:");

        jlActualizar.setBackground(new java.awt.Color(67, 114, 146));
        jlActualizar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlActualizar.setForeground(new java.awt.Color(255, 255, 255));
        jlActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlActualizar.setText("ACTUALIZAR DATOS");
        jlActualizar.setToolTipText("");
        jlActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jlActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlActualizar.setOpaque(true);
        jlActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlActualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlActualizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jlNombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlNombre.setForeground(new java.awt.Color(255, 255, 255));
        jlNombre.setText("DATA");

        jlCorreo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlCorreo.setForeground(new java.awt.Color(255, 255, 255));
        jlCorreo.setText("DATA");

        jlSexo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlSexo.setForeground(new java.awt.Color(255, 255, 255));
        jlSexo.setText("DATA");

        jlEdad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlEdad.setForeground(new java.awt.Color(255, 255, 255));
        jlEdad.setText("DATA");

        jlCiudad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlCiudad.setForeground(new java.awt.Color(255, 255, 255));
        jlCiudad.setText("DATA");

        jlTelefono.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlTelefono.setForeground(new java.awt.Color(255, 255, 255));
        jlTelefono.setText("DATA");

        jlOrientacion.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlOrientacion.setForeground(new java.awt.Color(255, 255, 255));
        jlOrientacion.setText("DATA");

        jlVip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlVipMouseClicked(evt);
            }
        });

        tablaLikes.setModel(lk);
        jScrollPane3.setViewportView(tablaLikes);

        jButton1.setText("Actualizar tabla de likes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpUserLayout = new javax.swing.GroupLayout(jpUser);
        jpUser.setLayout(jpUserLayout);
        jpUserLayout.setHorizontalGroup(
            jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUserLayout.createSequentialGroup()
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUserLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUserLayout.createSequentialGroup()
                                .addComponent(jlLike, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(jlContLike, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpUserLayout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUserLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(140, 140, 140)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUserLayout.createSequentialGroup()
                        .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpUserLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(50, 50, 50)
                        .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlSexo, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(jlEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlCorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpUserLayout.createSequentialGroup()
                        .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jlVip, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpUserLayout.createSequentialGroup()
                                .addGap(0, 126, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlOrientacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(100, 100, 100))
        );
        jpUserLayout.setVerticalGroup(
            jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUserLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlLike, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlContLike, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(jpUserLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpUserLayout.createSequentialGroup()
                        .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3))
                    .addComponent(jlCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlSexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlEdad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlCiudad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jlOrientacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUserLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUserLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jlVip, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );

        jpContenedor.add(jpUser, "card2");

        jpBuscar.setBackground(new java.awt.Color(67, 114, 146));

        jlbFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbFoto.setText("FOTO");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sexo:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Orientación:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ciudad:");

        jlbLike.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbLike.setText("DAR LIKE");
        jlbLike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbLikeMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Correo:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Edad:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Telefono:");

        jlbOrientacion.setBackground(new java.awt.Color(255, 255, 255));
        jlbOrientacion.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbOrientacion.setForeground(new java.awt.Color(255, 255, 255));
        jlbOrientacion.setText("DATA");

        jlbSexo.setBackground(new java.awt.Color(255, 255, 255));
        jlbSexo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbSexo.setForeground(new java.awt.Color(255, 255, 255));
        jlbSexo.setText("DATA");

        jlbNombre.setBackground(new java.awt.Color(255, 255, 255));
        jlbNombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbNombre.setForeground(new java.awt.Color(255, 255, 255));
        jlbNombre.setText("DATA");

        jlbCiudad.setBackground(new java.awt.Color(255, 255, 255));
        jlbCiudad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbCiudad.setForeground(new java.awt.Color(255, 255, 255));
        jlbCiudad.setText("DATA");

        jlbCorreo.setBackground(new java.awt.Color(255, 255, 255));
        jlbCorreo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbCorreo.setForeground(new java.awt.Color(255, 255, 255));
        jlbCorreo.setText("DATA");

        jlbEdad.setBackground(new java.awt.Color(255, 255, 255));
        jlbEdad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbEdad.setForeground(new java.awt.Color(255, 255, 255));
        jlbEdad.setText("DATA");

        jlbTelefono.setBackground(new java.awt.Color(255, 255, 255));
        jlbTelefono.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlbTelefono.setForeground(new java.awt.Color(255, 255, 255));
        jlbTelefono.setText("DATA");

        jpOtro.setOpaque(false);

        jlbPareja.setBackground(new java.awt.Color(67, 114, 146));
        jlbPareja.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlbPareja.setForeground(new java.awt.Color(255, 255, 255));
        jlbPareja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPareja.setText("SIGUIENTE");
        jlbPareja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jlbPareja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbPareja.setOpaque(true);
        jlbPareja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbParejaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbParejaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbParejaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpOtroLayout = new javax.swing.GroupLayout(jpOtro);
        jpOtro.setLayout(jpOtroLayout);
        jpOtroLayout.setHorizontalGroup(
            jpOtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbPareja, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        jpOtroLayout.setVerticalGroup(
            jpOtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbPareja, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpBuscarLayout = new javax.swing.GroupLayout(jpBuscar);
        jpBuscar.setLayout(jpBuscarLayout);
        jpBuscarLayout.setHorizontalGroup(
            jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(jpBuscarLayout.createSequentialGroup()
                .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBuscarLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jlbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpBuscarLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jlbLike, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(170, 170, 170)
                .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jlbOrientacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpBuscarLayout.createSequentialGroup()
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(50, 50, 50)
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jpBuscarLayout.setVerticalGroup(
            jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarLayout.createSequentialGroup()
                .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBuscarLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jlbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jlbLike, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 155, Short.MAX_VALUE))
                    .addGroup(jpBuscarLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jlbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jlbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpBuscarLayout.createSequentialGroup()
                                .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpBuscarLayout.createSequentialGroup()
                                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jlbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel16))
                                    .addComponent(jlbCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addComponent(jLabel17))
                            .addComponent(jlbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jpBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbOrientacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );

        jpContenedor.add(jpBuscar, "card3");

        jpMensajes.setBackground(new java.awt.Color(67, 114, 146));

        jtaMensaje.setColumns(20);
        jtaMensaje.setLineWrap(true);
        jtaMensaje.setRows(5);
        jtaMensaje.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        jScrollPane1.setViewportView(jtaMensaje);

        jtfCorreoDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCorreoDestinoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Correo del destinatario");

        jlBorrar.setBackground(new java.awt.Color(67, 114, 146));
        jlBorrar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlBorrar.setForeground(new java.awt.Color(255, 255, 255));
        jlBorrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBorrar.setText("BORRAR");
        jlBorrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jlBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlBorrar.setOpaque(true);
        jlBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlBorrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlBorrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlBorrarMouseExited(evt);
            }
        });

        jtMensajes.setModel(etm);
        jScrollPane2.setViewportView(jtMensajes);

        jlEnviar.setBackground(new java.awt.Color(67, 114, 146));
        jlEnviar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlEnviar.setForeground(new java.awt.Color(255, 255, 255));
        jlEnviar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlEnviar.setText("ENVIAR");
        jlEnviar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jlEnviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlEnviar.setOpaque(true);
        jlEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlEnviarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlEnviarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlEnviarMouseExited(evt);
            }
        });

        jlChat.setBackground(new java.awt.Color(67, 114, 146));
        jlChat.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jlChat.setForeground(new java.awt.Color(255, 255, 255));
        jlChat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlChat.setText("CHAT");
        jlChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jlChat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlChat.setOpaque(true);
        jlChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlChatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlChatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlChatMouseExited(evt);
            }
        });

        jtfPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPuertoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMensajesLayout = new javax.swing.GroupLayout(jpMensajes);
        jpMensajes.setLayout(jpMensajesLayout);
        jpMensajesLayout.setHorizontalGroup(
            jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMensajesLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpMensajesLayout.createSequentialGroup()
                        .addComponent(jlBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(jlEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jtfCorreoDestino, javax.swing.GroupLayout.Alignment.LEADING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMensajesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlChat, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );
        jpMensajesLayout.setVerticalGroup(
            jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMensajesLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMensajesLayout.createSequentialGroup()
                        .addComponent(jtfCorreoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jlChat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jpContenedor.add(jpMensajes, "card4");

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addComponent(jpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpPrincipalMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpPrincipalMouseDragged
        // TODO add your handling code here:
        int coordX = evt.getXOnScreen();
        int coordY = evt.getYOnScreen();

        this.setLocation(coordX - mousepX, coordY - mousepY);
    }//GEN-LAST:event_jpPrincipalMouseDragged

    private void jpPrincipalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpPrincipalMousePressed
        // TODO add your handling code here:
        mousepX = evt.getX();
        mousepY = evt.getY();
    }//GEN-LAST:event_jpPrincipalMousePressed

    private void jlBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBusquedaMouseClicked
        // TODO add your handling code here:
        Color active = new Color(67, 114, 146);
        Color pasive = new Color(87, 79, 124);
        cambiarPanel(jpBuscar);
        jpBusqueda.setBackground(active);
        jlBusqueda.setFont(new Font("Arial", Font.BOLD, 36));
        jpPerfil.setBackground(pasive);
        jlPerfil.setFont(new Font("Arial", Font.BOLD, 24));
        jpMensaje.setBackground(pasive);
        jlMensaje.setFont(new Font("Arial", Font.BOLD, 24));
        generarBordes(2);
    }//GEN-LAST:event_jlBusquedaMouseClicked

    private void jlMensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMensajeMouseClicked
        // TODO add your handling code here:
        Color active = new Color(67, 114, 146);
        Color pasive = new Color(87, 79, 124);
        cambiarPanel(jpMensajes);
        jpMensaje.setBackground(active);
        jlMensaje.setFont(new Font("Arial", Font.BOLD, 36));
        jpPerfil.setBackground(pasive);
        jlPerfil.setFont(new Font("Arial", Font.BOLD, 24));
        jpBusqueda.setBackground(pasive);
        jlBusqueda.setFont(new Font("Arial", Font.BOLD, 24));
        generarBordes(3);
    }//GEN-LAST:event_jlMensajeMouseClicked

    private void jlPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlPerfilMouseClicked
        // TODO add your handling code here:
        Color active = new Color(67, 114, 146);
        Color pasive = new Color(87, 79, 124);
        cambiarPanel(jpUser);
        jpPerfil.setBackground(active);
        jlPerfil.setFont(new Font("Arial", Font.BOLD, 36));
        jpBusqueda.setBackground(pasive);
        jlBusqueda.setFont(new Font("Arial", Font.BOLD, 24));
        jpMensaje.setBackground(pasive);
        jlMensaje.setFont(new Font("Arial", Font.BOLD, 24));
        actualizarTabla();
        generarBordes(1);
    }//GEN-LAST:event_jlPerfilMouseClicked

    private void jlExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseClicked
        // TODO add your handling code here:
        int seleccion;
        ImageIcon icono = new ImageIcon("src/Imagenes/Icono.png");
        seleccion = JOptionPane.showConfirmDialog(this, "¿Desea salir?", "Peccorina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icono);
        if (seleccion == JOptionPane.OK_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jlExitMouseClicked

    private void jlExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseEntered
        // TODO add your handling code here:
        Color color = new Color(136, 199, 224);
        jlExit.setForeground(color);
    }//GEN-LAST:event_jlExitMouseEntered

    private void jlExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseExited
        // TODO add your handling code here:
        jlExit.setForeground(Color.WHITE);
    }//GEN-LAST:event_jlExitMouseExited

    private void jlMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMinimizarMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jlMinimizarMouseClicked

    private void jlMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMinimizarMouseEntered
        // TODO add your handling code here:
        Color color = new Color(136, 199, 224);
        jlMinimizar.setForeground(color);
    }//GEN-LAST:event_jlMinimizarMouseEntered

    private void jlMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMinimizarMouseExited
        // TODO add your handling code here:
        jlMinimizar.setForeground(Color.WHITE);
    }//GEN-LAST:event_jlMinimizarMouseExited

    private void jlActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlActualizarMouseClicked
        // TODO add your handling code here:
        int seleccion;

        DialogActualizar da = new DialogActualizar(this, true, u, ii);
        da.setVisible(true);
        if (da.getConfirmar()) {
            sistema.modificarUsuario(u);
            if (da.getFoto()) {
                sistema.actualizarFoto(u);
            }
            da.dispose();
        }

        generarFrame();
    }//GEN-LAST:event_jlActualizarMouseClicked

    private void jlbParejaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbParejaMouseClicked
        // TODO add your handling code here:
        nPareja = nPareja + 1;
        cargarPareja(nPareja);
    }//GEN-LAST:event_jlbParejaMouseClicked

    private void jlbParejaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbParejaMouseEntered
        // TODO add your handling code here:
        Color color = new Color(27, 91, 93);
        jlbPareja.setBackground(color);
    }//GEN-LAST:event_jlbParejaMouseEntered

    private void jlbParejaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbParejaMouseExited
        // TODO add your handling code here:
        Color color = new Color(67, 114, 146);
        jlbPareja.setBackground(color);
    }//GEN-LAST:event_jlbParejaMouseExited

    private void jlbLikeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbLikeMouseClicked
        // TODO add your handling code here:
        if (u.getRoll().equals("cliente")) {
            if (u.getDarLikeContador() < 5) {
                int like;
                for (int i = 0; i < pareja.size(); i++) {
                    if (pareja.get(i).getCorreoUsuario().equals(jlbCorreo.getText())) {
                        boolean fav = sistema.comprobarLike(u, pareja.get(i));
                        if (!fav) {
                            like = pareja.get(i).getRecibirLikeContador() + 1;
                            pareja.get(i).setRecibirLikeContador(like);

                            like = u.getDarLikeContador() + 1;
                            u.setDarLikeContador(like);

                            sistema.actualizarCliente(pareja.get(i));
                            sistema.actualizarFav(u, pareja.get(i));

                            sistema.actualizarCliente(u);

                            ImageIcon image = new ImageIcon(getClass().getResource("/Imagenes/fav.png"));
                            Image conversion = image.getImage().getScaledInstance(jlbLike.getWidth(), jlbLike.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon likeFin = new ImageIcon(conversion);
                            jlbLike.setIcon(likeFin);
                            jlbCorreo.getText();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Límite de likes alcanzado, si quieres seguir compra la versión VIP");
            }
        }

        if (u.getRoll().equals("vip")) {
            int like;
            for (int i = 0; i < pareja.size(); i++) {
                if (pareja.get(i).getCorreoUsuario().equals(jlbCorreo.getText())) {
                    boolean fav = sistema.comprobarLike(u, pareja.get(i));
                    if (!fav) {
                        like = pareja.get(i).getRecibirLikeContador() + 1;
                        pareja.get(i).setRecibirLikeContador(like);

                        like = u.getDarLikeContador() + 1;
                        u.setDarLikeContador(like);

                        sistema.actualizarCliente(pareja.get(i));
                        sistema.actualizarFav(u, pareja.get(i));

                        sistema.actualizarCliente(u);

                        ImageIcon image = new ImageIcon(getClass().getResource("/Imagenes/fav.png"));
                        Image conversion = image.getImage().getScaledInstance(jlbLike.getWidth(), jlbLike.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon likeFin = new ImageIcon(conversion);
                        jlbLike.setIcon(likeFin);
                        jlbCorreo.getText();
                    }
                }
            }
        }

    }//GEN-LAST:event_jlbLikeMouseClicked

    private void jlActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlActualizarMouseEntered
        // TODO add your handling code here:
        Color color = new Color(27, 91, 93);
        jlActualizar.setBackground(color);
    }//GEN-LAST:event_jlActualizarMouseEntered

    private void jlActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlActualizarMouseExited
        // TODO add your handling code here:
        Color color = new Color(67, 114, 146);
        jlActualizar.setBackground(color);
    }//GEN-LAST:event_jlActualizarMouseExited

    private void jlBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBorrarMouseClicked
        // TODO add your handling code here:
        jtaMensaje.setText("");
    }//GEN-LAST:event_jlBorrarMouseClicked

    private void jlBorrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBorrarMouseEntered
        // TODO add your handling code here:
        Color color = new Color(27, 91, 93);
        jlBorrar.setBackground(color);
    }//GEN-LAST:event_jlBorrarMouseEntered

    private void jlBorrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBorrarMouseExited
        // TODO add your handling code here:
        Color color = new Color(67, 114, 146);
        jlBorrar.setBackground(color);
    }//GEN-LAST:event_jlBorrarMouseExited

    private void jlEnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEnviarMouseClicked
        // TODO add your handling code here:
        mandarMensaje();
        jtaMensaje.setText("");
        jtfCorreoDestino.setText("");
    }//GEN-LAST:event_jlEnviarMouseClicked

    private void jlEnviarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEnviarMouseEntered
        // TODO add your handling code here:
        Color color = new Color(27, 91, 93);
        jlEnviar.setBackground(color);
    }//GEN-LAST:event_jlEnviarMouseEntered

    private void jlEnviarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEnviarMouseExited
        // TODO add your handling code here:
        Color color = new Color(67, 114, 146);
        jlEnviar.setBackground(color);
    }//GEN-LAST:event_jlEnviarMouseExited

    private void jlChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChatMouseClicked
        // TODO add your handling code here:
        if (!jtfPuerto.getText().isEmpty()) {
            if (servidor == null) {
                int port = Integer.parseInt(jtfPuerto.getText());
                servidor = new MServidor(port);
                servidor.start();
                (new VCliente(servidor)).setVisible(true);

            } else {
                (new VCliente(servidor)).setVisible(true);
                servidor.start();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Introduzca un puerto de conexión");
        }
    }//GEN-LAST:event_jlChatMouseClicked

    private void jlChatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChatMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jlChatMouseEntered

    private void jlChatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChatMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jlChatMouseExited

    private void jtfPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPuertoActionPerformed

    private void jtfCorreoDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCorreoDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCorreoDestinoActionPerformed

    private void jlVipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlVipMouseClicked
        // TODO add your handling code here:
        int seleccion = JOptionPane.showConfirmDialog(this, "¿Desea adquirir la versión VIP?", "Peccorina", JOptionPane.YES_NO_OPTION);

        if (seleccion == JOptionPane.YES_OPTION) {
            sistema.crearVip(u);
            JOptionPane.showMessageDialog(this, "Su usuario ha sido actualizado a vip, se reiniciará el programa para actualizar los datos");
            this.dispose();
        }
    }//GEN-LAST:event_jlVipMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaUsuario().setVisible(true);
            }
        });
    }

    private void mandarMensaje() {

        sistema.enviarMensaje(u.getCorreoUsuario(), jtaMensaje.getText(), jtfCorreoDestino.getText());
        actualizarTabla();
    }

    private void actualizarTabla() {

        etm = new TablaMensajeModel(sistema.VerMensajes(u));
        lk = new LikeTableModel(sistema.VerLikes(u));
        
        generarDatos();
        jtMensajes.setModel(etm);
        tablaLikes.setModel(lk);
        jlContLike.setText(Integer.toString(sistema.getLike(u)));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlActualizar;
    private javax.swing.JLabel jlBorrar;
    private javax.swing.JLabel jlBusqueda;
    private javax.swing.JLabel jlChat;
    private javax.swing.JLabel jlCiudad;
    private javax.swing.JLabel jlContLike;
    private javax.swing.JLabel jlCorreo;
    private javax.swing.JLabel jlEdad;
    private javax.swing.JLabel jlEnviar;
    private javax.swing.JLabel jlExit;
    private javax.swing.JLabel jlFoto;
    private javax.swing.JLabel jlLike;
    private javax.swing.JLabel jlMensaje;
    private javax.swing.JLabel jlMinimizar;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlNombreUser;
    private javax.swing.JLabel jlOrientacion;
    private javax.swing.JLabel jlPerfil;
    private javax.swing.JLabel jlSexo;
    private javax.swing.JLabel jlTelefono;
    private javax.swing.JLabel jlVip;
    private javax.swing.JLabel jlbCiudad;
    private javax.swing.JLabel jlbCorreo;
    private javax.swing.JLabel jlbEdad;
    private javax.swing.JLabel jlbFoto;
    private javax.swing.JLabel jlbLike;
    private javax.swing.JLabel jlbNombre;
    private javax.swing.JLabel jlbOrientacion;
    private javax.swing.JLabel jlbPareja;
    private javax.swing.JLabel jlbSexo;
    private javax.swing.JLabel jlbTelefono;
    private javax.swing.JPanel jpBuscar;
    private javax.swing.JPanel jpBusqueda;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JPanel jpMensaje;
    private javax.swing.JPanel jpMensajes;
    private javax.swing.JPanel jpMenu;
    private javax.swing.JPanel jpOtro;
    private javax.swing.JPanel jpPerfil;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpUser;
    private javax.swing.JTable jtMensajes;
    private javax.swing.JTextArea jtaMensaje;
    private javax.swing.JTextField jtfCorreoDestino;
    private javax.swing.JTextField jtfPuerto;
    private javax.swing.JTable tablaLikes;
    // End of variables declaration//GEN-END:variables

}
