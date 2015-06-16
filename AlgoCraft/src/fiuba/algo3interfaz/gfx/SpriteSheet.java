package fiuba.algo3interfaz.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private final static String TIERRA_PATH = "/textures/tierra.png";
	private final static String ESPACIO_PATH = "/textures/espacio.png";
		
	public final static int CANTIDAD_SPRITES = 15;
	public final static int ANCHO_SPRITE = 32;
	public final static int ALTO_SPRITE = 32;

	public static BufferedImage[] spritesTierra = new BufferedImage[CANTIDAD_SPRITES];
	public static BufferedImage[] spritesEspacio = new BufferedImage[CANTIDAD_SPRITES];

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	// Corta un sprite del sheet
	public BufferedImage crop(int x, int y, int ancho, int alto){
		return sheet.getSubimage(x, y, ancho, alto);
	}

	public static void inicializar() {
		SpriteSheet spriteSheetTierra = new SpriteSheet(ImageLoader.loadImage(TIERRA_PATH));
		SpriteSheet spriteSheetEspacio = new SpriteSheet(ImageLoader.loadImage(ESPACIO_PATH));
		for (int i = 0; i < CANTIDAD_SPRITES; i++){
			spritesTierra[i] = spriteSheetTierra.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesEspacio[i] = spriteSheetEspacio.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
		}
	}
	
}
