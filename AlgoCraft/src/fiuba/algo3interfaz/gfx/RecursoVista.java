package fiuba.algo3interfaz.gfx;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.input.btnConstruirMouseListener;


public class RecursoVista extends JPanel {
	
	private Jugador jugador;

	private JPanel panel;
	private JLabel lblMineral;
	private JLabel lblGasVespeno;
	private JLabel lblPoblacion;
	
	/* Precarga de imagenes */
	private static BufferedImage picFondo = ImageLoader.loadImage("/textures/recurso_fondo.png");


	public RecursoVista(Jugador jugador, JPanel panel){
		this.jugador = jugador;
		this.panel = panel;
		
		this.setPreferredSize(new Dimension(640, 480));
		this.setFocusable(false);
		this.setVisible(false);
		this.setOpaque(false);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 4));
		
		/* Labels invisibles para mostrar los ToolTips */
		
		lblMineral = new JLabel();
		lblMineral.setPreferredSize(new Dimension(130, 40));
		lblMineral.setToolTipText("Mineral");
		lblMineral.setVisible(true);
		add(lblMineral);
		
		lblGasVespeno = new JLabel();
		lblGasVespeno.setPreferredSize(new Dimension(125, 40));
		lblGasVespeno.setToolTipText("Gas Vespeno");
		lblGasVespeno.setVisible(true);
		add(lblGasVespeno);
		
		lblPoblacion = new JLabel();
		lblPoblacion.setPreferredSize(new Dimension(110, 40));
		lblPoblacion.setToolTipText("Poblacion / Capacidad");
		lblPoblacion.setVisible(true);
		add(lblPoblacion);
		
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		
		dibujarFondo(g);
		dibujarDatosJugador(g);
		
	}
	
	private void dibujarFondo(Graphics g) {
		
		g.drawImage(picFondo, (panel.getWidth() - picFondo.getWidth()), 0, null);
		
	}
	
	private void dibujarDatosJugador(Graphics g) {
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 24));
		g.setColor(java.awt.Color.GREEN);
		
		g.drawString(String.valueOf(this.jugador.getMinerales()), panel.getWidth() - 350, 30);
		g.drawString(String.valueOf(this.jugador.getGasVespeno()), panel.getWidth() - 210, 30);
		g.setFont(new Font("SansSerif", Font.PLAIN, 18));
		g.drawString(String.valueOf(this.jugador.getPoblacion()) + " / " +
					String.valueOf(this.jugador.getCapacidadPoblacion()), panel.getWidth() - 75, 28);
	}
}
