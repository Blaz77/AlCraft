package fiuba.algo3.atributos.edificios;

import fiuba.algo3.componentes.Costo;
import fiuba.algo3.componentes.EstadoConstruyendoEdificio;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;

public class AtributosConstruccion extends AtributosEdificio {
	
	AtributosEdificio atributosEdificio;
	
	public AtributosConstruccion(AtributosEdificio atrEdificio){
		this.atributosEdificio = atrEdificio;
		this.nombre = "Construccion";
		
		this.estadosIniciales.add(new EstadoConstruyendoEdificio(atrEdificio));
	}

	@Override
	public IVida getVida() {
		return new Vida(0, atributosEdificio.getAtributosVida());
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
