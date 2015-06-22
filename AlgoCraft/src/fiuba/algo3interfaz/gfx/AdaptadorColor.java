package fiuba.algo3interfaz.gfx;

import fiuba.algo3.juego.Color;
import fiuba.algo3.raza.TipoRaza;

public enum AdaptadorColor {

	ROJO		( java.awt.Color.RED	),
	AZUL		( java.awt.Color.RED	),
	AZUL_TEAL	( java.awt.Color.RED	),
	PURPURA		( java.awt.Color.RED	),
	NARANJA		( java.awt.Color.RED	),
	MARRON		( java.awt.Color.RED	),
	BLANCO		( java.awt.Color.RED	),
	AMARILLO	( java.awt.Color.RED	);
	
	private java.awt.Color colorAwtAsociado;
	
	private AdaptadorColor(java.awt.Color _colorAwtAsociado){
		this.colorAwtAsociado = _colorAwtAsociado;
	}
	
	public java.awt.Color getColorAwtAsociado() {
		return this.colorAwtAsociado;
	}
	
}
