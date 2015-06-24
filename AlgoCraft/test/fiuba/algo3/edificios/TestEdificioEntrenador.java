package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import java.util.List;

import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public abstract class TestEdificioEntrenador extends TestEdificio {

	// definir el que hereda este field:
	protected Edificio entrenador;
	
	private Constructor getConstructor(Tipo tipoUnidad){
		for (Constructor constructor : entrenador.getUnidadesEntrenables())
			if (constructor.getTipo() == tipoUnidad) {
				return constructor;
			}
		return null;
	}
	
	protected void puedeEntrenarUnidad(Tipo tipoUnidad){
		for (Constructor constructor : entrenador.getUnidadesEntrenables())
			if (constructor.getTipo() == tipoUnidad) {
				assertTrue("El Tipo especificado esta entre los constructores", true);
				return;
			}
		fail();
	}
	
	protected void mientrasEntrenaUnidadNoDisponible(Tipo tipoUnidad, int turnos) {
		int index = jugador.getUnidades().size();
		getConstructor(tipoUnidad).crear();
		for(int i = 0; i < turnos; i++){
			assertEquals(index, jugador.getUnidades().size());
			entrenador.pasarTurno();
		}
	}
	
	protected void entrenarUnidadVerJugador(Tipo tipoUnidad, int turnos) {
		int index = jugador.getUnidades().size();
		getConstructor(tipoUnidad).crear();
		for(int i = 0; i < turnos; i++) entrenador.pasarTurno();
		assertEquals(jugador.getUnidades().get(index).getTipo(), tipoUnidad);
	}
	
	protected void entrenarUnidadVerMapa(Tipo tipoUnidad, int turnos) {
		int index = jugador.getUnidades().size();
		getConstructor(tipoUnidad).crear();
		for(int i = 0; i < turnos; i++) entrenador.pasarTurno();
		
		Unidad unidadCreada = jugador.getUnidades().get(index);

		assertEquals(unidadCreada, mapa.getOcupante(unidadCreada.getPosicion()));
	}
	
	protected void entrenarUnidadConsumeRecursos(Tipo tipoUnidad, int turnos,
			int costoMineral, int costoGasVespeno, int costoPoblacion) {
		//aseguro Recursos:
		jugador.agregarMinerales(1000);
		jugador.agregarGasVespeno(1000);
		jugador.aumentarCapacidadPoblacion(100);		
		
		int mineralRelativo = jugador.getMinerales();
		int gasVespenoRelativo = jugador.getGasVespeno();
		int poblacionRelativa = jugador.getPoblacion();
		
		getConstructor(tipoUnidad).crear();
		for(int i = 0; i < turnos; i++) entrenador.pasarTurno();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasVespenoRelativo - costoGasVespeno, jugador.getGasVespeno());
		assertEquals(poblacionRelativa + costoPoblacion, jugador.getPoblacion());
	}
	
	protected void entrenarSinMinerales(Tipo tipoUnidad) {
		jugador.agregarMinerales(-(jugador.getMinerales()));
		getConstructor(tipoUnidad).crear();
	}
	
	protected void entrenarSinGasVespeno(Tipo tipoUnidad) {
		jugador.agregarGasVespeno(-(jugador.getGasVespeno()));
		getConstructor(tipoUnidad).crear();
	}
	
	protected void entrenarSinCapacidadPoblacion(Tipo tipoUnidad) {
		jugador.aumentarPoblacion(jugador.getCapacidadPoblacion()-jugador.getPoblacion());
		getConstructor(tipoUnidad).crear();
	}
	
	protected void entrenarVariasUnidadesSostieneElOrden(List<Constructor> constructores, int... turnos){
		assert(constructores.size() == turnos.length);
		int index = jugador.getUnidades().size();
		
		for (Constructor c: constructores) {
			c.crear();
		}
		for (int i = 0; i < turnos.length; i++) {
			for (int j = 0; j < turnos[i]; j++) {
				entrenador.pasarTurno();
			}
			assertEquals(jugador.getUnidades().get(index+i).getTipo()
						, constructores.get(i).getTipo());
		}		
	}

}
