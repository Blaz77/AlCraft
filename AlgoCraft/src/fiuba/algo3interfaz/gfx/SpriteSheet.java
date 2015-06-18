package fiuba.algo3interfaz.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;

import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.TipoTerreno;

public class SpriteSheet {

	private static final int FACTOR_OSCURIDAD = 30;
	private final static String TIERRA_PATH = "/textures/tierra.png";
	private final static String ESPACIO_PATH = "/textures/espacio.png";
	private final static String HIBRIDO_PATH = "/textures/hibrido.png";
	
	public final static int CANTIDAD_SPRITES = 15;
	public final static int ANCHO_SPRITE = 32;
	public final static int ALTO_SPRITE = 32;

	public static final BufferedImage[] spritesTierra = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesEspacio = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesHibrido = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesTierraSombra = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesEspacioSombra = new BufferedImage[CANTIDAD_SPRITES];
	public static final BufferedImage[] spritesHibridoSombra = new BufferedImage[CANTIDAD_SPRITES];
	
	public static BufferedImage[] spritesTerreno(Terreno terreno){
		if (terreno == Terreno.TIERRA)
			return spritesTierra;  // Podria meter esto en  terreno... tal vez, no se si se permite en MVC
		if (terreno == Terreno.ESPACIO)
			return spritesEspacio;
		throw new RuntimeException();
	}
	
	public static void inicializar() {
		SpriteSheet spriteSheetTierra = new SpriteSheet(ImageLoader.loadImage(TIERRA_PATH));
		SpriteSheet spriteSheetEspacio = new SpriteSheet(ImageLoader.loadImage(ESPACIO_PATH));
		SpriteSheet spriteSheetHibrido = new SpriteSheet(ImageLoader.loadImage(HIBRIDO_PATH));
		
		SpriteSheet spriteSheetTierraSombra = new SpriteSheet(ImageLoader.loadImage(TIERRA_PATH));
		SpriteSheet spriteSheetEspacioSombra = new SpriteSheet(ImageLoader.loadImage(ESPACIO_PATH));
		SpriteSheet spriteSheetHibridoSombra = new SpriteSheet(ImageLoader.loadImage(HIBRIDO_PATH));
		
		for (int i = 0; i < CANTIDAD_SPRITES; i++){
			spritesTierra[i] = spriteSheetTierra.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesEspacio[i] = spriteSheetEspacio.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesHibrido[i] = spriteSheetHibrido.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			
			spritesTierraSombra[i] = spriteSheetTierra.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesEspacioSombra[i] = spriteSheetEspacio.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			spritesHibridoSombra[i] = spriteSheetHibrido.crop(0, i * ALTO_SPRITE, ANCHO_SPRITE, ALTO_SPRITE);
			
			//oscurecer(spritesTierraSombra[i]);
			//oscurecer(spritesEspacioSombra[i]);
			//oscurecer(spritesHibridoSombra[i]);
		}
	}

	public static void oscurecer(BufferedImage buffered) {
		
        for (int i = 0; i < buffered.getWidth(); i++) {
            for (int j = 0; j < buffered.getHeight(); j++) {                    
                int rgb = buffered.getRGB(i, j);
                int alpha = (rgb >> 24) & 0x000000FF;
                Color c = new Color(rgb);
                if (alpha != 0) {
                    int red = Math.max(c.getRed() - FACTOR_OSCURIDAD, 0);
                    int green = Math.max(c.getGreen() - FACTOR_OSCURIDAD,0);
                    int blue = Math.max(c.getBlue() - FACTOR_OSCURIDAD, 0);
                    c = new Color(red, green, blue);
                    buffered.setRGB(i, j, c.getRGB());
                }
            }
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
