package fiuba.algo3.componentes;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class EstadoConstruyendoEdificio implements Estado {
	
	private int cantVidaSumadaPorTurno;
	private int cantEscudoSumadoPorTurno;
	private ObjetoVivo portador;
	private AtributosEdificio atributos;
	private int turnosRestantes;
	
	public EstadoConstruyendoEdificio(AtributosEdificio atributos){
		this.atributos = atributos;
		this.turnosRestantes = atributos.getCosto().getTurnosConstruccion();
	}
	
	public void activar(ObjetoVivo portador) {
		this.portador = portador;
		this.cantVidaSumadaPorTurno = (int) Math.ceil(portador.getVidaMaxima()/ 				
								(float) atributos.getCosto().getTurnosConstruccion());
		this.cantEscudoSumadoPorTurno = (int) Math.ceil(portador.getEscudoMaximo()/ 
								(float) atributos.getCosto().getTurnosConstruccion());
		
	}

	public void pasarTurno() throws Exception {
		this.turnosRestantes--;
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
		portador.getPropietario().getMapa().reemplazar(portador.getPosicion(), edificio);
		portador.getPropietario().agregarEdificio(edificio);
	}

	@Override
	public String getDescripcion() {
		return String.format("Construyendo... %d%%", 100 - turnosRestantes * 100 / atributos.getCosto().getTurnosConstruccion());
		// quizas poner informacion de turnos faltantes o porcentaje terminado etc.
	}

}
