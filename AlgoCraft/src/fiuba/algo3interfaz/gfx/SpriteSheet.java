package fiuba.algo3interfaz.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Hashtable;

import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.Terreno;

public class SpriteSheet {

	private final static String TIERRA_PATH = "/textures/tierra.png";
	private final static String ESPACIO_PATH = "/textures/espacio.png";
	private final static String HIBRIDO_PATH = "/textures/hibrido.png";
	private final static String MINERAL_PATH = "/textures/cristal2.png";
	private final static String VESPENO_PATH = "/textures/volcan2.png";
	private final static String MARCA_MAPA_PATH = "/textures/marcaMapa.png";

    public static final BufferedImage spriteMarcaMapa = ImageLoader.loadImage(MARCA_MAPA_PATH);
    
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
            
            put(TipoOcupante.MINERAL, spritesMineral);
            put(TipoOcupante.VESPENO, spritesVespeno);
            
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
	
	public static void inicializar() {
		for (int i = 0; i < spritesTierra.cantidad; i++){
			
			BufferedImage sprite = spritesTierra.sheet[i];
			
			byte[] g = {0,0,0,0};
			ColorModel icm2 = spritesEspacio.sheet[(i+ 1) % spritesTierra.cantidad].getColorModel();
			WritableRaster wr = spritesTierra.sheet[(i+ 1) % spritesTierra.cantidad].getRaster();
			boolean bAlphaPremultiplied = sprite.isAlphaPremultiplied();
			spritesTierra.sheet[i] = new BufferedImage(icm2, wr, bAlphaPremultiplied, new Hashtable());
		}
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
