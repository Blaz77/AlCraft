package fiuba.algo3.atributos;

public abstract class AtributosUnidad extends AtributosObjetoVivo {

	int movPorTuno;
	int rangoVision;  //quizas pasar a ObjetoVivo, chequear si edificios tienen vision
	
	
	public int getMovPorTurno(){
		return movPorTuno;
	}
	
	public int getRangoVision(){
		return rangoVision;
	}

	public boolean puedeAtacar(){
		return false;
	}
	
	public boolean puedeHacerMagia(){
		return false;
	}
		
}
