package fiuba.algo3.atributos.unidades;

import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.componentes.AtaqueNull;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.MagiaNull;
import fiuba.algo3.componentes.VoluntadDelSer;

public abstract class AtributosUnidad extends AtributosObjetoVivo {

	protected AtributosVoluntadDelSer voluntadDelSer;
	protected int costoAlmacenamiento; //costoTransporte
	
	
	public VoluntadDelSer getVoluntadDelSer(){
		return new VoluntadDelSer(this.voluntadDelSer);
	}
	
	public IAtaque getAtaque() {
		return new AtaqueNull();
	}
	
	public IMagia getMagia() {
		return new MagiaNull();
	}
	
	public int getCostoAlmacenamiento(){
		return costoAlmacenamiento;
	}

	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return false;
	}
	
	public boolean puedeHacerMagia(){
		return false;
	}
		
	public boolean puedeAlmacenar(){ //puedeTransportar()
		return false;
	}
}
