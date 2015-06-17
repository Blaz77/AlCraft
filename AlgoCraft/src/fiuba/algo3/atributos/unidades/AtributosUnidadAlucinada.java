package fiuba.algo3.atributos.unidades;

import java.util.List;

import fiuba.algo3.componentes.AtaqueAlucinado;
import fiuba.algo3.componentes.Costo;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.MagiaNull;
import fiuba.algo3.componentes.TransporteNull;
import fiuba.algo3.componentes.VidaAlucinada;
import fiuba.algo3.componentes.VoluntadDelSer;

public class AtributosUnidadAlucinada extends AtributosUnidad {

	private AtributosUnidad atributosUnidad;
	
	public AtributosUnidadAlucinada(AtributosUnidad atributos){
		this.atributosUnidad = atributos;
	}
	
	//Importante: hacer ovveride de este para Protoss y usar
	//en cambio VidaConEscudo
	public IVida getVida() {
		return new VidaAlucinada(atributosUnidad.getVida()); //vida alucinada!
	}
	
	public IAtaque getAtaque() {
		return new AtaqueAlucinado(atributosUnidad.getAtaque());
	}
	
	public VoluntadDelSer getVoluntadDelSer(){
		return atributosUnidad.getVoluntadDelSer();
	}
	
	public IMagia getMagia() {
		return new MagiaNull();
	}
	
	public ITransporte getTransporte() {
		return new TransporteNull();
	}
	
	public int getCostoAlmacenamiento(){
		return atributosUnidad.getCostoAlmacenamiento();
	}
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return atributosUnidad.puedeSerAlmacenada();
	}
	
	public List<Estado> getEstadosIniciales(){
		return atributosUnidad.getEstadosIniciales();
	}
	
	public Costo getCosto() {
		return atributosUnidad.getCosto();
	}
	
	public String getNombre() {
		return atributosUnidad.getNombre();
	}
	
	public boolean puedeOcuparTierra(){
		return atributosUnidad.puedeOcuparTierra();
	}
	
	// esAereo, puedeVolar()
	public boolean puedeOcuparEspacio(){
		return atributosUnidad.puedeOcuparEspacio();
	}
	
	public boolean debeOcuparRecurso(){
		return atributosUnidad.debeOcuparRecurso();
	}
	
	public boolean debeOcuparMineral(){
		return atributosUnidad.debeOcuparMineral();
	}
	
	public boolean debeOcuparGasVespeno(){
		return atributosUnidad.debeOcuparGasVespeno();
	}
	
	// El enemigo que me quiere atacar me pasa sus dos rangos
	// y yo que se como soy, le devuelvo el que funciona 
	// en el ataque correspondiente.
	// -> yo soy aereo, entonces devuelvo rangoAire
	// -> yo soy terrestre, entonces devuelvo rangoTierra.
	public int getRangoEfectivo(int rangoAire, int rangoTierra){
		return atributosUnidad.getRangoEfectivo(rangoAire, rangoTierra);
	}

	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return atributosUnidad.getDanioEfectivo(danioAire, danioTierra);
	}
	
}
