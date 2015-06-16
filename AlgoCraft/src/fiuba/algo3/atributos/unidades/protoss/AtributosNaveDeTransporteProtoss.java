package fiuba.algo3.atributos.unidades.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosTransporte;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Transporte;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosNaveDeTransporteProtoss extends AtributosUnidad {
	
	private AtributosTransporte transporte;
	
	public AtributosNaveDeTransporteProtoss() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				200,	// costoMineral
				0,		// costoGasVespeno
				8);		// turnosConstruccion
		this.vida = new AtributosVida(
				80,		// vidaMaxima
				60);	// escudoMaximo
		this.nombre = "Nave de transporte";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,	//costoPoblacion
				4,	//movPorTuno
				7);	//rangoVision
		this.costoAlmacenamiento = 0; //tecnicamente no es almacenable
		
		// fields de UnidadTransporte:
		this.transporte = new AtributosTransporte(8); //capac.Almac.
	
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}

	@Override
	public ITransporte getTransporte() {
		return new Transporte(this.transporte);
	}
}
