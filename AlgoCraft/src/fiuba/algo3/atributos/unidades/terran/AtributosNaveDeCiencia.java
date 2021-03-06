package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMagia;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.Magia;
import fiuba.algo3.magia.MagiaEMP;
import fiuba.algo3.magia.MagiaRadiacion;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class AtributosNaveDeCiencia extends AtributosUnidad {
	
	private AtributosMagia magia;
	
	public AtributosNaveDeCiencia() {
		// fields ObjetoVivo:
		this.tipo = Tipo.NAVE_DE_CIENCIA;
		
		this.costo = new AtributosCosto(
				100,	// costoMineral
				225,	// costoGasVespeno
				10,		// turnosConstruccion
				2);		// costoPoblacion
		
		this.vida = new AtributosVida(200);

		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				1,		// movPorTuno
				10,		// rangoVision
				0);		// costoAlmacenamiento
		
		// fields de UnidadMagica
		this.magia = new AtributosMagia(
				50,		//energiaInicial
				200,	//energiaMaxima
				10,		//energiaARegenerarPorTurno
				8,		//rangoDeMagia (por debajo del rango vision)
				new MagiaEMP(),
				new MagiaRadiacion());
	}
	
	@Override
	public IMagia getMagia(Unidad portador) {
		return new Magia(this.magia, portador);
	}
	
	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
}
