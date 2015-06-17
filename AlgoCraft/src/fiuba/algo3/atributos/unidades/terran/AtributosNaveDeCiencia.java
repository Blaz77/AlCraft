package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMagia;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.Magia;
import fiuba.algo3.magia.MagiaEMP;
import fiuba.algo3.magia.MagiaRadiacion;

public class AtributosNaveDeCiencia extends AtributosUnidad {
	
	private AtributosMagia magia;
	
	public AtributosNaveDeCiencia() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 225, 10);
		this.vida = new AtributosVida(200);
		this.nombre = "Nave de ciencia";

		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,	//costoPoblacion
				1,	//movPorTuno
				10);//rangoVision
		this.costoAlmacenamiento = 0;
		
		// fields de UnidadMagica
		this.magia = new AtributosMagia(
				50,		//energiaInicial
				200,	//energiaMaxima
				10,		//energiaARegenerarPorTurno
				10,		//rangoDeMagia
				new MagiaEMP(),
				new MagiaRadiacion());
	}
	
	@Override
	public IMagia getMagia() {
		return new Magia(this.magia);
	}
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return false;
	}

	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
}
