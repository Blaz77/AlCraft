package fiuba.algo3.atributos;

public class AtributosEspectro extends AtributosUnidadAtaque {
	
	public AtributosEspectro(){
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		this.turnosConstruccion = 8;
		this.vidaMaxima = 120;
		this.nombre = "Espectro";
		
		// fields de Unidad:
		this.costoPoblacion = 2;
		this.movPorTuno = 1; //?Inventado?!!!
		//this.rangoVision = 7;
		
		// fields de UnidadAtaque:
		this.ataquesPorTurno = 1;//?Inventado?!!!
		this.danioAAire = 20; 
		this.danioATierra = 8;
		this.rangoAire = 5;
		this.rangoTierra = 5;
	}
	
	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
	
	@Override
	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return danioAire;
	}
}
