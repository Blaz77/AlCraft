package fiuba.algo3interfaz.gfx;

import java.awt.image.BufferedImage;

import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.TipoTerreno;

public class SpriteSheet {

	private final static String TIERRA_PATH = "/textures/tierra.png";
	private final static String ESPACIO_PATH = "/textures/espacio.png";
	private final static String HIBRIDO_PATH = "/textures/hibrido.png";
	
	public final static int CANTIDAD_SPRITES = 15;
	public final static int ANCHO_SPRITE = 32;
	public final static int ALTO_SPRITE = 32;

	public static final BufferedImage[] spritesTierra = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesEspacio = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesHibrido = new BufferedImage[CANTIDAD_SPRITES];
	
	public static BufferedImage[] spritesTerreno(Terreno terreno){
		if (terreno.getTipo() == TipoTerreno.TIERRA)
			return spritesTierra;
		if (terreno.getTipo() == TipoTerreno.ESPACIO)
			return spritesEspacio;
		throw new RuntimeException();
	}
	
	public static void inicializar() {
		SpriteSheet spriteSheetTierra = new SpriteSheet(ImageLoader.loadImage(TIERRA_PATH));
		SpriteSheet spriteSheetEspacio = new SpriteSheet(ImageLoader.loadImage(ESPACIO_PATH));
		SpriteSheet spriteSheetHibrido = new SpriteSheet(ImageLoader.loadImage(HIBRIDO_PATH));
		
		for (int i = 0; i < CANTIDAD_SPRITES; i++){
			spritesTierra[i] = spriteSheetTierra.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesEspacio[i] = spriteSheetEspacio.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesHibrido[i] = spriteSheetHibrido.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
		}
	}
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	// Corta un sprite del sheet
	public BufferedImage crop(int x, int y, int ancho, int alto){
		return sheet.getSubimage(x, y, ancho, alto);
	}

	
}
