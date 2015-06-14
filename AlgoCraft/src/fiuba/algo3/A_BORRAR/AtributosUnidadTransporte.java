package fiuba.algo3.A_BORRAR;

public class AtributosUnidadTransporte extends AtributosUnidad {

	protected int capacidadAlmacenamiento; //capacidadTransporte

	@Override
	public boolean puedeAlmacenar(){ //puedeTransportar()
		return true;
	}
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return false;
	}
	
	public int getCapacidadAlmacenamiento(){
		return capacidadAlmacenamiento;
	}
}
