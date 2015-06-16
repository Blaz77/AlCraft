package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import fiuba.algo3interfaz.input.KeyManager;
import fiuba.algo3interfaz.input.MouseManager;

public class MapaVista {
	private CeldaVista[][] mapaVista;
	private int ancho;
	private int alto;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private int cor_x, cor_y;
	private int filaMarca = 0;
	private int columnaMarca = 0;
		
	public MapaVista(int ancho, int alto, KeyManager keyManager, MouseManager mouseManager){
		this.mapaVista = new CeldaVista[ancho][alto];
		this.ancho = ancho;
		this.alto = alto;
		this.keyManager = keyManager;
		this.mouseManager = mouseManager;
		
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapaVista[i][j] = new CeldaVista(miRNG.nextInt());
	}
	
	public BufferedImage obtenerSprite(int x, int y){
		
		return SpriteSheet.spritesTierra[(mapaVista[x][y].getIndex()) % SpriteSheet.CANTIDAD_SPRITES];
		// descomenten este de aca para ver el espacio, esta super feo aviso xD
		//return spritesEspacio.crop(0, 32*mapa[x][y], 32, 32);
		
	}

	public void tick() {
		
	}
		
	public void render(Graphics g) {
		int anchoSprite = SpriteSheet.ANCHO_SPRITE;
		int altoSprite = SpriteSheet.ALTO_SPRITE;
		int borde = 0;
		
		for (int y = 0; y < alto; y++)
			for (int x = 0; x < ancho; x++)
				// Dibujo el en la celda x,y el sprite indicado en mapaGrafico[x][y]
				g.drawImage(obtenerSprite(x, y), borde + anchoSprite * x, borde + altoSprite * y, null);
		
		// Lindo terran :D
		g.drawImage(ImageLoader.loadImage("/textures/terran.png"), 0, 0, null);
		
		mouseInput();
		keyInput();
		
		ubicarMarcaMapaEn(g);
	}

	private void keyInput() {
		if(keyManager.up)
			columnaMarca -= 1;
		if(keyManager.down)
			columnaMarca += 1;
		if(keyManager.left)
			filaMarca -= 1;
		if(keyManager.right)
			filaMarca += 1;
	}

	private void mouseInput() {
		if (mouseManager.isClicked()) {
			cor_x = mouseManager.getX();
			cor_y = mouseManager.getY();
			filaMarca = obtenerFila(cor_x);
			columnaMarca = obtenerColumna(cor_y);
			mouseManager.setClicked(false);
		}
	}

	
	
	private void ubicarMarcaMapaEn(Graphics g) {
		BufferedImage bi = ImageLoader.loadImage("/textures/marcaMapa.png");
		g.drawImage(bi, filaMarca * SpriteSheet.ANCHO_SPRITE, columnaMarca * SpriteSheet.ALTO_SPRITE, null);
	}

	private int obtenerFila(int cor_x) {

		return cor_x / SpriteSheet.ANCHO_SPRITE;
	}
	private int obtenerColumna(int cor_y) {

		return cor_y / SpriteSheet.ALTO_SPRITE;
	}
	

	
	
}
