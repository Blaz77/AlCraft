package fiuba.algo3.terreno;

// La idea de esto es eliminar las clases Terreno e hijas,
// pues tenian poco comportamiento y nada de estado que
// ameritara tener instancias de las mismas.
public enum TipoTerreno {
	//      Edificable, Transitable, Sobrevolable
	TIERRA (      true,        true,         true), 
	ESPACIO(     false,       false,         true);
	
	private boolean esEdificable;
	private boolean esTransitable;
	private boolean esSobrevolable;

	private TipoTerreno(boolean ed, boolean tr, boolean sv){
		this.esEdificable = ed;
		this.esTransitable = tr;
		this.esSobrevolable = sv;
	}

	public boolean esEdificable() {
		return esEdificable;
	}
	
	public boolean esTransitable() {
		return esTransitable;
	}
	
	public boolean esSobrevolable() {
		return esSobrevolable;
	}
	
}
