package fiuba.algo3.atributos;

public class AtributosAtaque {
	
	private int ataquesPorTurno;
	private int danioAAire; 
	private int danioATierra;	
	private int rangoAire;
	private int rangoTierra;
	
	public AtributosAtaque(int danioAAire, int danioATierra, 
			int rangoAire, int rangoTierra, int ataquesPorTurno) {
		this.danioAAire = danioAAire; 
		this.danioATierra = danioATierra;	
		this.rangoAire = rangoAire;
		this.rangoTierra = rangoTierra;
		this.ataquesPorTurno = ataquesPorTurno;
	}
	
	public int getAtaquesPorTurno(){
		return ataquesPorTurno;
	}
	
	public int getDanioAtkAire(){
		return danioAAire;
	}
	
	public int getDanioAtkTierra(){
		return danioATierra;
	}
	
	public int getRangoAtkAire(){
		return rangoAire;
	}
	
	public int getRangoAtkTierra(){
		return rangoTierra;
	}

}
