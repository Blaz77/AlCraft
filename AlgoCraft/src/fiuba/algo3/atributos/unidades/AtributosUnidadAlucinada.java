package fiuba.algo3.atributos.unidades;

import java.util.List;

import fiuba.algo3.componentes.AtaqueAlucinado;
import fiuba.algo3.componentes.Costo;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.IMovimiento;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.MagiaNull;
import fiuba.algo3.componentes.TransporteNull;
import fiuba.algo3.componentes.VidaAlucinada;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class AtributosUnidadAlucinada extends AtributosUnidad {

	private AtributosUnidad atributosUnidad;
	
	public AtributosUnidadAlucinada(AtributosUnidad atributos){
		this.atributosUnidad = atributos;
	}
	
	//Importante: hacer ovveride de este para Protoss y usar

	@Override//en cambio VidaConEscudo
	public IVida getVida() {
		return new VidaAlucinada(atributosUnidad.getVida()); //vida alucinada!
	}
	
	@Override
	public IAtaque getAtaque(Unidad portador) {
		return new AtaqueAlucinado(atributosUnidad.getAtaque(portador));
	}
	
	@Override
	public IMagia getMagia(Unidad portador) {
		return new MagiaNull();
	}

	@Override
	public ITransporte getTransporte(Unidad portador) {
		return new TransporteNull();
	}

	@Override
	public IMovimiento getMovimiento(Unidad portador){
		return atributosUnidad.getMovimiento(portador);
	}

	@Override
	public List<Estado> getEstadosIniciales(){
		return atributosUnidad.getEstadosIniciales();
	}

	@Override
	public Costo getCosto() {
		return atributosUnidad.getCosto();
	}

	@Override
	public Tipo getTipo() {
		return atributosUnidad.getTipo();
	}

	@Override
	public String getNombre() {
		return atributosUnidad.getNombre();
	}

	@Override
	public boolean puedeOcuparTierra(){
		return atributosUnidad.puedeOcuparTierra();
	}
	

	@Override// esAereo, puedeVolar()
	public boolean puedeOcuparEspacio(){
		return atributosUnidad.puedeOcuparEspacio();
	}

	@Override
	public boolean debeOcuparRecurso(){
		return atributosUnidad.debeOcuparRecurso();
	}

	@Override
	public boolean debeOcuparMineral(){
		return atributosUnidad.debeOcuparMineral();
	}

	@Override
	public boolean debeOcuparGasVespeno(){
		return atributosUnidad.debeOcuparGasVespeno();
	}
	
	// El enemigo que me quiere atacar me pasa sus dos rangos
	// y yo que se como soy, le devuelvo el que funciona 
	// en el ataque correspondiente.
	// -> yo soy aereo, entonces devuelvo rangoAire

	@Override// -> yo soy terrestre, entonces devuelvo rangoTierra.
	public int getRangoEfectivo(int rangoAire, int rangoTierra){
		return atributosUnidad.getRangoEfectivo(rangoAire, rangoTierra);
	}

	@Override
	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return atributosUnidad.getDanioEfectivo(danioAire, danioTierra);
	}
	
}
