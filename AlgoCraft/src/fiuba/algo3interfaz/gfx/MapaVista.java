package fiuba.algo3interfaz.gfx;

import java.awt.image.BufferedImage;
import java.util.Random;

public class MapaVista {
	private int[][] mapa;
	private int ancho;
	private int alto;
	
	private SpriteSheet spritesTierra = new SpriteSheet(ImageLoader.loadImage("/textures/tierra.png"));
	private SpriteSheet spritesEspacio = new SpriteSheet(ImageLoader.loadImage("/textures/espacio.png"));
	private final static int CANTIDAD_SPRITES = 15;
	
	public MapaVista(int ancho, int alto){
		this.mapa = new int[ancho][alto];
		this.ancho = ancho;
		this.alto = alto;
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapa[i][j] = miRNG.nextInt(CANTIDAD_SPRITES);
	}
	
	public BufferedImage obtenerSprite(int x, int y){
		
		
		return spritesTierra.crop(0, 32*mapa[x][y], 32, 32);
		// descomenten este de aca para ver el espacio, esta super feo aviso xD
		//return spritesEspacio.crop(0, 32*mapa[x][y], 32, 32);
		
	}
}
