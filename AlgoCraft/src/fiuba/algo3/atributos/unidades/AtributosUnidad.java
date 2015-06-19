package fiuba.algo3.atributos.unidades;

import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.componentes.AtaqueNull;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.IMovimiento;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.MagiaNull;
import fiuba.algo3.componentes.Movimiento;
import fiuba.algo3.componentes.TransporteNull;

public abstract class AtributosUnidad extends AtributosObjetoVivo {
	
	protected AtributosMovimiento movimiento;
	
	public IMovimiento getMovimiento(){
		return new Movimiento(this.movimiento);
	}
	
	public IAtaque getAtaque() {
		return new AtaqueNull();
	}
	
	public IMagia getMagia() {
		return new MagiaNull();
	}
	
	public ITransporte getTransporte() {
		return new TransporteNull();
	}
	
}
