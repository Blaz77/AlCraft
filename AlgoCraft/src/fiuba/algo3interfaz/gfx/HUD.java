package fiuba.algo3interfaz.gfx;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.raza.TipoRaza;


public class HUD {
	
	private TipoRaza raza;
	private Color color;
	private Jugador jugador; // dale que va, esto ya es cualquier cosa
	private Mapa mapaVisible;
	private Posicion celdaSeleccionada = new Posicion(0,0);
	

	private int anchoVentana;
	private int altoVentana;

	public HUD(Jugador jugador, int anchoVentana, int altoVentana){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
		this.jugador = jugador;
		this.mapaVisible = jugador.getMapa();
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		
		dibujarHUD(g);
		dibujarDatosJugador(g);
		//dibujarMinimapa(g); Tal vez en otra vista
		dibujarDatosCeldaSeleccionada(g);
		dibujarAccionesPosibles(g);
	}
	
	private static BufferedImage hudTest = ImageLoader.loadImage("/HUDs/HUDterran2.png"); //HUDzerg2.png, HUDprotoss2.png
	private void dibujarHUD(Graphics g) {
		
		g.drawImage(hudTest, 0, this.altoVentana - hudTest.getHeight(), null);
		
	}
	
	private void dibujarDatosJugador(Graphics g) {

		g.setFont(new Font("Serif", Font.PLAIN, 36));
		g.setColor(java.awt.Color.WHITE);
		g.drawString(this.jugador.getNombre(), 120, 380);
		
	}
	
	private void dibujarDatosCeldaSeleccionada(Graphics g) {
		g.setFont(new Font("Serif", Font.PLAIN, 18));
		g.setColor(java.awt.Color.WHITE);
		
		g.drawString(this.mapaVisible.getTerreno(celdaSeleccionada).name(),
				120, 400);
		g.drawString(this.mapaVisible.getOcupante(celdaSeleccionada).getTipo().name(),
				120, 420);
	}

	private void dibujarAccionesPosibles(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public void actualizar(Posicion nuevaCeldaSeleccionada) {
		this.celdaSeleccionada = nuevaCeldaSeleccionada;
	}


	public void recibirClick(int x, int y) {
		
	}
}
