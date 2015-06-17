package fiuba.algo3interfaz.gfx;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.raza.TipoRaza;


public class HUD {
	
	private TipoRaza raza;
	private Color color;

	public HUD(Jugador jugador){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
	}
	
	public void tick(){
		
	}
	
	public void render() {
		
	}
}
