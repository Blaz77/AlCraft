package fiuba.algo3.atributos;

public class AtributosUnidadTransporte extends AtributosUnidad {

	int capacidadAlmacenamiento; //capacidadTransporte

	@Override
	public boolean puedeAlmacenar(){ //puedeTransportar()
		return true;
	}
	
	public int getCapacidadAlmacenamiento(){
		return capacidadAlmacenamiento;
	}
}
