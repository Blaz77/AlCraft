package fiuba.algo3interfaz.gfx;

import fiuba.algo3.juego.Color;
import fiuba.algo3.raza.TipoRaza;

public enum AdaptadorColor {

	ROJO		( new java.awt.Color(255, 62, 62)	),
	AZUL		( new java.awt.Color(109, 118, 216)	),
	AZUL_TEAL	( new java.awt.Color(153, 217, 234)	),
	PURPURA		( new java.awt.Color(193, 179, 255)	),
	NARANJA		( new java.awt.Color(255, 147, 74)	),
	MARRON		( new java.awt.Color(184, 107, 88)	),
	BLANCO		( new java.awt.Color(255, 255, 255)	),
	AMARILLO	( new java.awt.Color(255, 242, 0)	);
	
	private java.awt.Color colorAwtAsociado;
	
	private AdaptadorColor(java.awt.Color _colorAwtAsociado){
		this.colorAwtAsociado = _colorAwtAsociado;
	}
	
	public java.awt.Color getColorAwtAsociado() {
		return this.colorAwtAsociado;
	}
	
}
