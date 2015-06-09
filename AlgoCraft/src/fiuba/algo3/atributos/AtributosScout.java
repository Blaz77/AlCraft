package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosScout extends AtributosUnidadAtaque {
	
	public AtributosScout(){
		// fields ObjetoVivo:
		this.costoMineral = 300;
		this.costoGasVespeno = 150;
		this.turnosConstruccion = 9;
		this.vidaMaxima = 150;
		this.escudoMaximo = 100;
		this.nombre = "Scout";
		
		// fields de Unidad:
		this.costoPoblacion = 3;
		//this.movPorTuno = 1; //?Inventado?!!!
		//this.rangoVision = 7;
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this);
	}
}
