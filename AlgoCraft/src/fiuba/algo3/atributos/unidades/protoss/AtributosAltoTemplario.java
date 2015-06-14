package fiuba.algo3.atributos.unidades.protoss;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMagia;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.magia.MagiaAlucinacion;
import fiuba.algo3.magia.MagiaEMP;
import fiuba.algo3.magia.MagiaRadiacion;
import fiuba.algo3.magia.MagiaTormentaPsionica;

public class AtributosAltoTemplario extends AtributosUnidadMagica {
	
	public AtributosAltoTemplario() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				50,		// costoMineral
				150,	// costoGasVespeno
				7);		// turnosConstruccion
		this.vida = new AtributosVida(
				40,		// vidaMaxima
				40);	// escudoMaximo
		this.nombre = "Alto templario";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,		// costoPoblacion
				1,		// movPorTuno
				7);		// rangoVision
		
		// fields de UnidadMagica
		this.magia = new AtributosMagia(
				200,	//energiaMaxima
				15,		//energiaARegenerarPorTurno
				10,		//rangoDeMagia
				new MagiaTormentaPsionica(),
				new MagiaAlucinacion());
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}
	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
