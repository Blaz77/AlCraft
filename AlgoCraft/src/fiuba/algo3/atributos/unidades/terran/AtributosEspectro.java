package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.ocupantes.Tipo;

public class AtributosEspectro extends AtributosUnidad {
	
	private AtributosAtaque ataque;
	
	public AtributosEspectro(){
		// fields ObjetoVivo:
		this.tipo = Tipo.ESPECTRO;
		
		this.costo = new AtributosCosto(
				150,	// costoMineral
				100,	// costoGasVespeno
				8,		// turnosConstruccion
				2);		// costoPoblacion
		
		this.vida = new AtributosVida(120);
		
		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				1,		// movPorTuno
				7,		// rangoVision
				0);		// costoAlmacenamiento
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				20, //Danio A Aire
				8,  //Danio A Tierra
				5,  //Rango a Aire
				5,  //Rango a Tierra
				1); //Ataques por Turno
	}
	
	@Override
	public IAtaque getAtaque(){
		return new Ataque(this.ataque);
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
