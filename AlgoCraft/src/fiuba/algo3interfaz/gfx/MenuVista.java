package fiuba.algo3interfaz.gfx;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.input.btnConstruirEdificioIncrementadorPoblacionMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioRecolectorGasVespenoMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioRecolectorMineralMouseListener;
import fiuba.algo3interfaz.input.btnConstruirMouseListener;


public class MenuVista extends JPanel {
	
	private TipoRaza raza;
	private Color color;
	private Jugador jugador; // dale que va, esto ya es cualquier cosa
	private Mapa mapaVisible;
	private Posicion celdaSeleccionada = new Posicion(0,0);
	

	private int anchoVentana;
	private int altoVentana;
	
	private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;

	public MenuVista(int anchoVentana, int altoVentana, JPanel panel){
		//this.panel = panel; //Experimental
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		this.setVisible(false);
		
		
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
        jTextField1 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        this.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Jugador 1");
        this.add(jLabel1);
        jLabel1.setBounds(30, 160, 105, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Jugador 2");
        this.add(jLabel2);
        jLabel2.setBounds(340, 160, 105, 29);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Raza");
        this.add(jLabel3);
        jLabel3.setBounds(340, 250, 24, 15);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Color");
        this.add(jLabel4);
        jLabel4.setBounds(340, 280, 27, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nombre");
        this.add(jLabel5);
        jLabel5.setBounds(340, 220, 43, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Raza");
        this.add(jLabel6);
        jLabel6.setBounds(30, 250, 24, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Color");
        this.add(jLabel7);
        jLabel7.setBounds(30, 280, 27, 15);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Nombre");
        this.add(jLabel8);
        jLabel8.setBounds(30, 220, 43, 15);

        jTextField1.setText("Nombre");
        this.add(jTextField1);
        jTextField1.setBounds(410, 210, 190, 30);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Terran");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        this.add(jRadioButton1);
        jRadioButton1.setBounds(410, 250, 57, 23);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Protoss");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        this.add(jRadioButton2);
        jRadioButton2.setBounds(470, 250, 61, 23);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Zerg");
        jRadioButton3.setEnabled(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        this.add(jRadioButton3);
        jRadioButton3.setBounds(550, 250, 47, 23);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ROJO", "AZUL", "AZUL_TEAL", "PURPURA", "NARANJA", "MARRON", "BLANCO", "AMARILLO" }));
        this.add(jComboBox1);
        jComboBox1.setBounds(410, 280, 190, 20);

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Terran");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        this.add(jRadioButton4);
        jRadioButton4.setBounds(100, 250, 57, 23);

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("Protoss");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        this.add(jRadioButton5);
        jRadioButton5.setBounds(170, 250, 61, 23);

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Zerg");
        jRadioButton6.setEnabled(false);
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        this.add(jRadioButton6);
        jRadioButton6.setBounds(240, 250, 47, 23);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ROJO", "AZUL", "AZUL_TEAL", "PURPURA", "NARANJA", "MARRON", "BLANCO", "AMARILLO" }));
        this.add(jComboBox2);
        jComboBox2.setBounds(100, 280, 190, 20);

        jTextField2.setText("Nombre");
        this.add(jTextField2);
        jTextField2.setBounds(100, 210, 190, 30);

        jButton1.setText("JUGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        this.add(jButton1);
        jButton1.setBounds(160, 330, 300, 40);
	}
	
	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }          
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		
	}
	
	
}
