package fiuba.algo3.atributos;

public abstract class AtributosUnidadAtaque extends AtributosUnidad {

	int ataquesPorTurno;
	int danioAAire; 
	int danioATierra;	
	int rangoAire;
	int rangoTierra;
	
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
