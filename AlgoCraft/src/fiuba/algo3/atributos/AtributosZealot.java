package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosZealot extends AtributosUnidadAtaque {

	public AtributosZealot(){
		//fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 4;
		this.vidaMaxima = 100;
		this.escudoMaximo = 60;
		this.nombre = "Zealot";
		
		//fields de Unidad:
		this.costoPoblacion = 2;
		this.movPorTuno = 1; //?Inventado?!!!
		this.rangoVision = 7;
		
		//fields de UnidadAtaque:
		this.ataquesPorTurno = 1;//?Inventado?!!!
		this.danioAAire = 8; 
		this.danioATierra = 0;
		this.rangoAire = 1;
		this.rangoTierra = 1;
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this);
	}
	

}
