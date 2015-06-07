package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorMarine;

public class AtributosBarraca extends AtributosEdificioEntrenadorUnidades {

	public AtributosBarraca(){
		super();
		this.vidaMaxima = 1000; 
		this.nombre = "Barraca";
		this.unidadesEntrenables.add(new ConstructorMarine());
	}

	@Override
	public boolean tieneEscudo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRangoEfectivo(int RangoAire, int RangoTierra) {
		return RangoTierra;
	}

}
