package fiuba.algo3.edificios;

public interface Componente {

	//A llamar cuando se cambia un componente por otro
	//El nuevo decide si quiere sostener el viejo
	//(P/ej: en casos de acciones/estados temporales)
	void setAnterior(Componente componenteAnterior);

}