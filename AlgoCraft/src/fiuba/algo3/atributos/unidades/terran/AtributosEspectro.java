package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;

public class AtributosEspectro extends AtributosUnidadAtaque {
	
	public AtributosEspectro(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 8);
		this.vida = new AtributosVida(120);
		this.nombre = "Espectro";
		
		// fields de Unidad:
		this.costoPoblacion = 2;
		this.movPorTuno = 1; //?Inventado?!!!
		//this.rangoVision = 7;
		
		// fields de UnidadAtaque:
		this.ataquesPorTurno = 1;//?Inventado?!!!
		this.danioAAire = 20; 
		this.danioATierra = 8;
		this.rangoAire = 5;
		this.rangoTierra = 5;
	}
	
	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
	
	@Override
	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return danioAire;
	}
}
