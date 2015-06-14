package fiuba.algo3.atributos.unidades;

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
