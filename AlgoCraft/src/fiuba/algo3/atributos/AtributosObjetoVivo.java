package fiuba.algo3.atributos;

import java.util.LinkedList;

import fiuba.algo3.componentes.Costo;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;

/*
  Superclase de los atributos ('stats' en la mayoria de los juegos en ingles)
  de un tipo de unidad/edificio perteneciente a un jugador.
  
  Instanciadas todas las hijas (indirectamente) al crear cada jugador, y referenciadas
  por éste y por todas sus unidades/edificios.
 */
public abstract class AtributosObjetoVivo { //AtributosObjetoDeJuego?
	
	protected LinkedList<Estado> estadosIniciales = new LinkedList<Estado>();
	protected AtributosVida vida;
	protected AtributosCosto costo;
	
	protected String nombre;

	
	public LinkedList<Estado> getEstadosIniciales(){
		/*LinkedList<Estado> copiaEstados = new LinkedList<Estado>();
		for (Estado estado : this.estadosIniciales) {
			copiaEstados.add(estado.clone());
		}
		return copiaEstados;*/
		return new LinkedList<Estado>(estadosIniciales);
	}
	
	public Costo getCosto() {
		return new Costo(this.costo);
	}
	
	//Importante: hacer ovveride de este para Protoss y usar
	//en cambio VidaConEscudo
	public IVida getVida() {
		return new Vida(this.vida);
	}
	
	//SUPER EXTRA PECHUGA DE TEMPORAL!!!!
	public AtributosVida getAtributosVida() {
		return this.vida;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean puedeOcuparTierra(){
		return true;
	}
	
	// esAereo, puedeVolar()
	public boolean puedeOcuparEspacio(){
		return false;
	}
	
	public boolean debeOcuparRecurso(){
		return false;
	}
	
	public boolean debeOcuparMineral(){
		return false;
	}
	
	public boolean debeOcuparGasVespeno(){
		return false;
	}
	
	// El enemigo que me quiere atacar me pasa sus dos rangos
	// y yo que se como soy, le devuelvo el que funciona 
	// en el ataque correspondiente.
	// -> yo soy aereo, entonces devuelvo rangoAire
	// -> yo soy terrestre, entonces devuelvo rangoTierra.
	public int getRangoEfectivo(int rangoAire, int rangoTierra){
		return rangoTierra;
	}

	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return danioTierra;
	}

}
