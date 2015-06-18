package fiuba.algo3.componentes;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class EstadoConstruyendoEdificio implements Estado {
	
	private int cantVidaSumadaPorTurno;
	private int cantEscudoSumadoPorTurno;
	private ObjetoVivo portador;
	private AtributosEdificio atributos;
	
	public EstadoConstruyendoEdificio(AtributosEdificio atributos){
		this.atributos = atributos;
	}
	
	public void activar(ObjetoVivo portador) {
		this.portador = portador;
		this.cantVidaSumadaPorTurno = (int) Math.ceil(portador.getVidaMaxima()/ 				
								(float) atributos.getCosto().getTurnosConstruccion());
		this.cantEscudoSumadoPorTurno = (int) Math.ceil(portador.getEscudoMaximo()/ 
								(float) atributos.getCosto().getTurnosConstruccion());
		
	}

	public void pasarTurno() throws Exception {
		portador.regenerarVida(cantVidaSumadaPorTurno);
		portador.regenerarEscudo(cantEscudoSumadoPorTurno);
		if (portador.getVida() == portador.getVidaMaxima() &&
			portador.getEscudo() == portador.getEscudoMaximo()) {
			throw new Exception();
		}
	}

	public void desactivar() {
		Edificio edificio = new Edificio(portador.getPropietario() ,portador.getPosicion(), this.atributos);
		portador.destruir();
		portador.getPropietario().getMapa().setOcupante(edificio, portador.getPosicion());
		portador.getPropietario().agregarEdificio(edificio);
	}

}
