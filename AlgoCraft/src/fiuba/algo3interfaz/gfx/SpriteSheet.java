package fiuba.algo3interfaz.gfx;

// DEPRECATED

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.terreno.Terreno;

public class SpriteSheet {

	private final static String TIERRA_PATH = "/textures/tierra.png";
	private final static String ESPACIO_PATH = "/textures/espacio.png";
	private final static String HIBRIDO_PATH = "/textures/hibrido.png";
	private final static String MINERAL_PATH = "/textures/cristal2.png";
	private final static String VESPENO_PATH = "/textures/volcan2.png";
	private final static String MARCA_MAPA_PATH = "/textures/marcaMapa.png";
	private static final String PLAYER_PALETTE_PATH = "/palettes/player_palette.png";

	
	
    public static final BufferedImage spriteMarcaMapa = ImageLoader.loadImage(MARCA_MAPA_PATH);
	private static java.awt.Color[] playerPalette = new Color[72];// = ImageLoader.loadImage(PLAYER_PALETTE_PATH).getRGB(0, 0, 9, 8, null, 0, 9);
    
	public static SpriteSheet spritesTierra = new SpriteSheet(TIERRA_PATH);
	public static SpriteSheet spritesEspacio = new SpriteSheet(ESPACIO_PATH);
	public static SpriteSheet spritesHibrido = new SpriteSheet(HIBRIDO_PATH);
	
	public static SpriteSheet spritesMineral = new SpriteSheet(MINERAL_PATH);
	public static SpriteSheet spritesVespeno = new SpriteSheet(VESPENO_PATH);
    
	private static HashMap<Object, SpriteSheet> Sprites = new HashMap<Object, SpriteSheet>(){
        {
            put(Terreno.TIERRA, spritesTierra);
            put(Terreno.ESPACIO, spritesEspacio);
            //put(Terreno.SOMBRA, spritesSombra);
            
            put(Tipo.MINERAL, spritesMineral);
            put(Tipo.VESPENO, spritesVespeno);
            
            //put(TipoEdificio.PUERTO_ESTELAR_TERRAN, spritesPuertoEstelarTerran);
            
        }
    };
	
	
	public static BufferedImage getSpriteMarcaMapa() {
		return spriteMarcaMapa;
	}

	public static SpriteSheet getSpritestierra() {
		return spritesTierra;
	}

	public static SpriteSheet getSpritesEspacio() {
		return spritesEspacio;
	}

	public static SpriteSheet getSpritesHibrido() {
		return spritesHibrido;
	}

	public static SpriteSheet getSpritesMineral() {
		return spritesMineral;
	}

	public static SpriteSheet getSpritesVespeno() {
		return spritesVespeno;
	}

	public static SpriteSheet getSprites(Object object) {
		return Sprites.get(object);
	}	
	
	/* Creo la paleta de colores para los palette swaps */
	public static void inicializar() {
		
		int[] paletteImg = ImageLoader.loadImage(PLAYER_PALETTE_PATH).getRGB(0, 0, 9, 8, null, 0, 9);
		for (int i = 0; i < playerPalette.length; i++){
			Color c = new Color(paletteImg[i], true);
			playerPalette[i] = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
		}
		
		/*for (int i = 0; i < spritesTierra.cantidad; i++){
			
			BufferedImage sprite = spritesTierra.sheet[i];
			
			byte[] g = {0,0,0,0};
			ColorModel icm2 = spritesEspacio.sheet[(i+ 1) % spritesTierra.cantidad].getColorModel();
			WritableRaster wr = spritesTierra.sheet[(i+ 1) % spritesTierra.cantidad].getRaster();
			boolean bAlphaPremultiplied = sprite.isAlphaPremultiplied();
			spritesTierra.sheet[i] = new BufferedImage(icm2, wr, bAlphaPremultiplied, new Hashtable());
		}*/
	}
	
	private static fiuba.algo3.juego.Color[] colores = fiuba.algo3.juego.Color.values();
	
	private static Color[] mapeo = new Color[]{
		new Color(255, 0, 255), new Color(247, 15, 15),
		new Color(222, 0, 222), new Color(191, 23, 23),
		new Color(189, 0, 189), new Color(191, 23, 23),
		new Color(156, 0, 156), new Color(162, 15, 15),
		new Color(124, 0, 124), new Color(130, 0, 0),
		new Color(91, 0, 91), new Color(107, 0, 0),
		new Color(58, 0, 58), new Color(85, 0, 0),
		new Color(25, 0, 25), new Color(38, 0, 0)
	};
	
    public static void swapColors( BufferedImage img, fiuba.algo3.juego.Color color ){
    	fiuba.algo3.juego.Color[] colores = fiuba.algo3.juego.Color.values();
    	int colorIndex = 0;
    	for (int i = 0; i < colores.length; i++  ){
    		if (colores[i] == color){
    			colorIndex = i+1;
    			break;
    		}
    	}
    	_swapColors(img,  colorIndex, playerPalette);
    }
    	
    public static void _swapColors( BufferedImage img, int colorIndex, Color... mapping ){
    
        int[] pixels = img.getRGB( 0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth() );
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        for( int i = 0; i < 8; i++ ){
            map.put( mapping[9*i].getRGB(), mapping[9*i+colorIndex].getRGB() ); 
        }


        for( int i = 0; i < pixels.length; i++ ){
            if(map.containsKey( pixels[i] ) )
                pixels[i] = map.get( pixels[i] ); 
        }

        img.setRGB( 0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth() );
    }

	
	
	/** Clase **/
	
	private BufferedImage[] sheet;
	private int anchoSprite;
	private int altoSprite;
	private int cantidad;
	
	public SpriteSheet(String path){
		this(ImageLoader.loadImage(path));
	}
	
	public SpriteSheet(BufferedImage bi){
		
		this.anchoSprite = this.altoSprite = bi.getWidth();
		this.cantidad = bi.getHeight() / this.altoSprite;
		
		this.sheet = new BufferedImage[this.cantidad];
		for (int i = 0; i < this.cantidad; i++)
			this.sheet[i] = crop(bi, i);
		
		if ((bi.getHeight() % this.altoSprite) != 0)
			throw new RuntimeException();
	}

	
	public int getAncho() {
		return anchoSprite;
	}

	public int getAlto() {
		return altoSprite;
	}

	public BufferedImage get(int index){
		return sheet[index % cantidad];
	}
	
	private BufferedImage crop(BufferedImage bi, int index){
		return bi.getSubimage(0, (index % cantidad) * altoSprite, anchoSprite, altoSprite);
	}

}
