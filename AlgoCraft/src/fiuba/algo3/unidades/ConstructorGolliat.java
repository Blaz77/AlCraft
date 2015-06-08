package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorGolliat extends Constructor {

	public ConstructorGolliat() {
		//this.costoGas = 50;
		//this.costoMinerales = 100;
		//this.costoPoblacion = 2;
		//this.turnosNecesarios = 6;
	}

	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new Golliat(j, posicion);
		//new UnidadAtaque(j, pos, this.atributos)
	}
	
	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosTerran)this.entrenador.getPropietario().getAtributos()).getGolliat();		
	}

}
