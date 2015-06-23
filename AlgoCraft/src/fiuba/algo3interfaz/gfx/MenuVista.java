package fiuba.algo3interfaz.gfx;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.input.btnConstruirMouseListener;


public class MenuVista extends JPanel {
	
	private static final int TITLE_FONTSIZE = 24;
	private static final int TEXT_FONTSIZE = 12;
	private static final String TITLE_FONT = "Century Gothic";
	private static final String TEXT_FONT = "Tahoma";

	// Al mejor estilo VisualBasic
	private void msgbox(String s){
	   JOptionPane.showMessageDialog(null, s);
	}
	
	private TipoRaza razaJugador1;
	private TipoRaza razaJugador2;
	private Color colorJugador1;
	private Color colorJugador2;
	private String nombreJugador1;
	private String nombreJugador2;

	private Game game;

	private int anchoVentana;
	private int altoVentana;
	
	private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton btnJugar;
    private javax.swing.JCheckBox chkNombreColorJugador2;
    private javax.swing.JCheckBox chkNombreColorJugador1;
    private javax.swing.JComboBox cboColorJugador1;
    private javax.swing.JComboBox cboColorJugador2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JSpinner spnCantidadBases;
    private javax.swing.JRadioButton radRazaTerranJugador2;
    private javax.swing.JRadioButton radRazaProtossJugador2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton radRazaTerranJugador1;
    private javax.swing.JRadioButton radRazaProtossJugador1;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JTextField txtNombreJugador2;
    private javax.swing.JTextField txtNombreJugador1;
    
    private boolean validarCampos() {
    	if ((txtNombreJugador1.isEnabled() && txtNombreJugador1.getText() == "") || 
    		(txtNombreJugador1.isEnabled() && txtNombreJugador2.getText() == "")) {
    		msgbox("Debe especificar el nombre del jugador");
        	return false;
    	}
    	
    	return true;
    }
    
    private String definirNombreJugador(JCheckBox chkNombreColor, JTextField txtNombre, JComboBox cboColor, TipoRaza raza) {
    	if ( ! chkNombreColor.isSelected()) {
    		return txtNombre.getText();
    	}
    	else {
    		// El poder de los Enums
    		return Color.values()[cboColor.getSelectedIndex()].getNombrePorDefecto(raza);
    	}
    }

	public MenuVista(int anchoVentana, int altoVentana, Game game){
		this.game = game; //Dudoso
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		//this.setVisible(false);
		
		
		buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        spnCantidadBases = new javax.swing.JSpinner();
        txtNombreJugador2 = new javax.swing.JTextField();
        radRazaTerranJugador2 = new javax.swing.JRadioButton();
        radRazaProtossJugador2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        cboColorJugador1 = new javax.swing.JComboBox();
        radRazaTerranJugador1 = new javax.swing.JRadioButton();
        radRazaProtossJugador1 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        cboColorJugador2 = new javax.swing.JComboBox();
        txtNombreJugador1 = new javax.swing.JTextField();
        btnJugar = new javax.swing.JButton();
        chkNombreColorJugador1 = new javax.swing.JCheckBox();
        chkNombreColorJugador2 = new javax.swing.JCheckBox();
        
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Mapa");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Cantidad de bases");

        jLabel1.setFont(new java.awt.Font(TITLE_FONT, 0, TITLE_FONTSIZE)); // NOI18N
        jLabel1.setText("Jugador 1");

        jLabel2.setFont(new java.awt.Font(TITLE_FONT, 0, TITLE_FONTSIZE)); // NOI18N
        jLabel2.setText("Jugador 2");

        jLabel3.setFont(new java.awt.Font(TEXT_FONT, 0, TEXT_FONTSIZE)); // NOI18N
        jLabel3.setText("Raza");

        jLabel4.setFont(new java.awt.Font(TEXT_FONT, 0, TEXT_FONTSIZE)); // NOI18N
        jLabel4.setText("Color");

        jLabel5.setFont(new java.awt.Font(TEXT_FONT, 0, TEXT_FONTSIZE)); // NOI18N
        jLabel5.setText("Nombre");

        jLabel6.setFont(new java.awt.Font(TEXT_FONT, 0, TEXT_FONTSIZE)); // NOI18N
        jLabel6.setText("Raza");

        jLabel7.setFont(new java.awt.Font(TEXT_FONT, 0, TEXT_FONTSIZE)); // NOI18N
        jLabel7.setText("Color");

        jLabel8.setFont(new java.awt.Font(TEXT_FONT, 0, TEXT_FONTSIZE)); // NOI18N
        jLabel8.setText("Nombre");

        txtNombreJugador2.setText("Nombre");

        buttonGroup1.add(radRazaTerranJugador2);
        radRazaTerranJugador2.setText("Terran");
        radRazaTerranJugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRazaTerranJugador2ActionPerformed(evt);
            }
        });
        radRazaTerranJugador2.doClick();

        buttonGroup1.add(radRazaProtossJugador2);
        radRazaProtossJugador2.setText("Protoss");
        radRazaProtossJugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRazaProtossJugador2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Zerg");
        jRadioButton3.setEnabled(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        cboColorJugador2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ROJO", "AZUL", "AZUL_TEAL", "PURPURA", "NARANJA", "MARRON", "BLANCO", "AMARILLO" }));

        buttonGroup2.add(radRazaTerranJugador1);
        radRazaTerranJugador1.setText("Terran");
        radRazaTerranJugador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRazaTerranJugador1ActionPerformed(evt);
            }
        });
        radRazaTerranJugador1.doClick();

        buttonGroup2.add(radRazaProtossJugador1);
        radRazaProtossJugador1.setText("Protoss");
        radRazaProtossJugador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRazaProtossJugador1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Zerg");
        jRadioButton6.setEnabled(false);
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        cboColorJugador1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ROJO", "AZUL", "AZUL_TEAL", "PURPURA", "NARANJA", "MARRON", "BLANCO", "AMARILLO" }));

        txtNombreJugador1.setText("Nombre");

        btnJugar.setText("JUGAR");
        btnJugar.setName(""); // NOI18N
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        chkNombreColorJugador2.setText("Usar nombre del color");
        chkNombreColorJugador2.setName("chkNombreColorJugador2"); // NOI18N
        chkNombreColorJugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNombreColorJugador2ActionPerformed(evt);
            }
        });

        chkNombreColorJugador1.setText("Usar nombre del color");
        chkNombreColorJugador1.setName("chkNombreColorJugador1"); // NOI18N
        chkNombreColorJugador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNombreColorJugador1ActionPerformed(evt);
            }
        });

        spnCantidadBases.setModel(new javax.swing.SpinnerNumberModel(4, 2, 6, 1));
        
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(205, 205, 205)
                            .addComponent(jLabel2)
                            .addContainerGap(184, Short.MAX_VALUE))))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(spnCantidadBases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel8)
                                .addGap(27, 27, 27)
                                .addComponent(txtNombreJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel5)
                                .addGap(27, 27, 27)
                                .addComponent(txtNombreJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel6)
                                .addGap(46, 46, 46)
                                .addComponent(radRazaTerranJugador1)
                                .addGap(13, 13, 13)
                                .addComponent(radRazaProtossJugador1)
                                .addGap(9, 9, 9)
                                .addComponent(jRadioButton6)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel3)
                                .addGap(46, 46, 46)
                                .addComponent(radRazaTerranJugador2)
                                .addGap(3, 3, 3)
                                .addComponent(radRazaProtossJugador2)
                                .addGap(19, 19, 19)
                                .addComponent(jRadioButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7)
                                .addGap(43, 43, 43)
                                .addComponent(cboColorJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel4)
                                .addGap(43, 43, 43)
                                .addComponent(cboColorJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(chkNombreColorJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addComponent(chkNombreColorJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(106, 106, 106)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(spnCantidadBases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombreJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombreJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel5))))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(radRazaTerranJugador1)
                        .addComponent(radRazaProtossJugador1)
                        .addComponent(jRadioButton6)
                        .addComponent(jLabel3)
                        .addComponent(radRazaTerranJugador2)
                        .addComponent(radRazaProtossJugador2)
                        .addComponent(jRadioButton3))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(cboColorJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cboColorJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkNombreColorJugador1)
                        .addComponent(chkNombreColorJugador2))
                    .addGap(7, 7, 7)
                    .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE))
            );

	}
	
	private void radRazaTerranJugador2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        razaJugador2 = TipoRaza.TERRAN;
    }                                             

    private void radRazaTerranJugador1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	razaJugador1 = TipoRaza.TERRAN;
    }                                             

    private void radRazaProtossJugador2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	razaJugador2 = TipoRaza.PROTOSS;
    }                                             

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void radRazaProtossJugador1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	razaJugador1 = TipoRaza.PROTOSS;
    }                                             

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (! validarCampos()) return;

    	Opciones opciones = new Opciones();
    	opciones.setCantidadBases((Integer) spnCantidadBases.getValue());
    	nombreJugador1 = definirNombreJugador(chkNombreColorJugador1, txtNombreJugador1, cboColorJugador1, razaJugador1);
    	nombreJugador2 = definirNombreJugador(chkNombreColorJugador2, txtNombreJugador2, cboColorJugador2, razaJugador2);
    	opciones.setDatosJugador(1, nombreJugador1, Color.values()[cboColorJugador1.getSelectedIndex()], razaJugador1);
    	opciones.setDatosJugador(2, nombreJugador2, Color.values()[cboColorJugador2.getSelectedIndex()], razaJugador2);
    	
    	this.game.comenzarJuego(opciones);
    }          
    
    private void chkNombreColorJugador1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        txtNombreJugador1.setEnabled( ! chkNombreColorJugador1.isSelected());
    }                                          

    private void chkNombreColorJugador2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	txtNombreJugador2.setEnabled( ! chkNombreColorJugador2.isSelected());
    }                    
	
	public void tick(){
		
	}
	
	private static BufferedImage logo = ImageLoader.loadImage("/AlgoCraft.png"); //HUDzerg2.png, HUDprotoss2.png
	
	public void render(Graphics g) {
		g.drawImage(logo, (game.getAncho() - logo.getWidth()) / 2, 20, null);

	}
	
	
}
