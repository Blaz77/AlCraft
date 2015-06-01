package fiuba.algo3.tp_final;

public class Celda {

	private int x;
	private int y;
	private Recurso recurso = new Recurso();
	private TipoTerreno tipoTerreno = TipoTerreno.TERRESTRE;
	private Edificio edificio = null; // UGH

	public Celda(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/*
	public int distancia2(Punto otroPunto) {
		return Math.abs(this.x - otroPunto.x) + Math.abs(this.y - otroPunto.y);
	}
	*/
	public double distancia(Celda otroPunto) {
		return Math.sqrt( Math.pow(this.x - otroPunto.x, 2) + Math.pow(this.y - otroPunto.y, 2) );
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
		
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}	
	
	public Recurso getRecurso() {
		return recurso;
	}

	public TipoTerreno getTerreno(){
		return tipoTerreno;
	}
	
	public void setTerreno(TipoTerreno tipoTerreno){
		this.tipoTerreno = tipoTerreno;
	}

	
}
