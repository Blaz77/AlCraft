package fiuba.algo3.atributos.unidades;

public abstract class AtributosUnidadAtaque extends AtributosUnidad {

	protected int ataquesPorTurno;
	protected int danioAAire; 
	protected int danioATierra;	
	protected int rangoAire;
	protected int rangoTierra;
	
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
	
	@Override
	public boolean puedeAtacar(){
		return true;
	}
	

}
