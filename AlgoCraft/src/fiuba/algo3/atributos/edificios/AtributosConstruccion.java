package fiuba.algo3.atributos.edificios;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.componentes.Costo;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoConstruyendoEdificio;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.ocupantes.recurso.TipoObjetoVivo;

public class AtributosConstruccion extends AtributosEdificio {
	
	AtributosEdificio atributosEdificio;
	
	public AtributosConstruccion(AtributosEdificio atrEdificio){
		this.atributosEdificio = atrEdificio;
		this.nombre = "Construccion";
		this.especie = TipoObjetoVivo.CENTRO_DE_MINERAL; //MegaTemporal
	}

	@Override
	public IVida getVida() {
		IVida vida = atributosEdificio.getVida();
		vida.recibirDanio(vida.getEscudoMaximo() + vida.getVidaMaxima() - 1);
		return vida;
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoConstruyendoEdificio(atributosEdificio));
	}
	
	public Costo getCosto() {
		return atributosEdificio.getCosto();
	}
	
	public boolean puedeOcuparTierra(){
		return atributosEdificio.puedeOcuparTierra();
	}
	
	// esAereo, puedeVolar()
	public boolean puedeOcuparEspacio(){
		return atributosEdificio.puedeOcuparEspacio();
	}
	
	public boolean debeOcuparRecurso(){
		return atributosEdificio.debeOcuparRecurso();
	}
	
	public boolean debeOcuparMineral(){
		return atributosEdificio.debeOcuparMineral();
	}
	
	public boolean debeOcuparGasVespeno(){
		return atributosEdificio.debeOcuparGasVespeno();
	}
	
	public int getRangoEfectivo(int rangoAire, int rangoTierra){
		return atributosEdificio.getRangoEfectivo(rangoAire, rangoTierra);
	}

	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return atributosEdificio.getDanioEfectivo(danioAire, danioTierra);
	}
}
