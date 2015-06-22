package fiuba.algo3interfaz.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;

public class CopyOfSpriteSheet {

	private final static String TIERRA_PATH = "/textures/tierra.png";
	private final static String ESPACIO_PATH = "/textures/espacio.png";
	private final static String HIBRIDO_PATH = "/textures/hibrido.png";
	private final static String MINERAL_PATH = "/textures/cristal2.png";
	private final static String VESPENO_PATH = "/textures/volcan2.png";
	private final static String MARCA_MAPA_PATH = "/textures/marcaMapa.png";
	private static final String PLAYER_PALETTE_PATH = "/palettes/player_palette.png";
	private static final String TERRAN_PATH = "/textures/ocupantesTerran.png";
	private static final String PROTOSS_PATH = "/textures/ocupantesProtoss.png";
	private static final String ZERG_PATH = null;
	//private static final String ZERG_PATH = "/textures/ocupantesZerg.png"; 

    public static final BufferedImage spriteMarcaMapa = ImageLoader.loadImage(MARCA_MAPA_PATH);

	private static java.awt.Color[] playerPalette = new Color[72];// = ImageLoader.loadImage(PLAYER_PALETTE_PATH).getRGB(0, 0, 9, 8, null, 0, 9);
    
	public static CopyOfSpriteSheet spritesTierra = new CopyOfSpriteSheet(TIERRA_PATH);
	public static CopyOfSpriteSheet spritesEspacio = new CopyOfSpriteSheet(ESPACIO_PATH);
	public static CopyOfSpriteSheet spritesHibrido = new CopyOfSpriteSheet(HIBRIDO_PATH);
	
	public static CopyOfSpriteSheet spritesMineral = new CopyOfSpriteSheet(MINERAL_PATH);
	public static CopyOfSpriteSheet spritesVespeno = new CopyOfSpriteSheet(VESPENO_PATH);
	
	public static CopyOfSpriteSheet spritesTerran = new CopyOfSpriteSheet(TERRAN_PATH);
	public static CopyOfSpriteSheet spritesProtoss = new CopyOfSpriteSheet(PROTOSS_PATH);

	private static HashMap<Jugador, CopyOfSpriteSheet> spritesJugador = new HashMap<Jugador, CopyOfSpriteSheet>();
	private static HashMap<Tipo, CopyOfSpriteSheet>	spritesRecurso = new HashMap<Tipo, CopyOfSpriteSheet>(){
		{
            put(Tipo.MINERAL, spritesMineral);
            put(Tipo.VESPENO, spritesVespeno);
		}
	};
	private static HashMap<TipoRaza, CopyOfSpriteSheet> spritesRaza = new HashMap<TipoRaza, CopyOfSpriteSheet>(){
        {
        			
            put(TipoRaza.TERRAN, spritesTerran);
            put(TipoRaza.PROTOSS, spritesProtoss);
            
        }
    };	
    
	public static BufferedImage getSpriteMarcaMapa() {
		return spriteMarcaMapa;
	}

	public static CopyOfSpriteSheet getSpritesTierra() {
		return spritesTierra;
	}

	public static CopyOfSpriteSheet getSpritesEspacio() {
		return spritesEspacio;
	}

	public static CopyOfSpriteSheet getSpritesHibrido() {
		return spritesHibrido;
	}

	public static CopyOfSpriteSheet getSpritesJugador(Jugador jugador) {
		// TODO Auto-generated method stub
		return spritesJugador.get(jugador);
	}
	
	public static CopyOfSpriteSheet getSprites(Object object) {
		return spritesRaza.get(object);
	}	
	
	public static void inicializar() {
		
		int[] paletteImg = ImageLoader.loadImage(PLAYER_PALETTE_PATH).getRGB(0, 0, 9, 8, null, 0, 9);
		for (int i = 0; i < playerPalette.length; i++){
			Color c = new Color(paletteImg[i], true);
			playerPalette[i] = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
		}
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
	
    private void swapColors(fiuba.algo3.juego.Color color ){
    	fiuba.algo3.juego.Color[] colores = fiuba.algo3.juego.Color.values();
    	int colorIndex = 0;
    	for (int i = 0; i < colores.length; i++  ){
    		if (colores[i] == color){
    			colorIndex = i+1;
    			break;
    		}
    	}
    	for (BufferedImage sprite: this.sheet)
    		_swapColors(sprite,  colorIndex, playerPalette);
    }
    	
    private static void _swapColors( BufferedImage img, int colorIndex, Color... mapping ){
    
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
	
	public CopyOfSpriteSheet(String path){
		this(ImageLoader.loadImage(path));
	}
	
	public CopyOfSpriteSheet(BufferedImage bi){
		
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

	public static void crearSpritesJugador(Jugador jugador) {
		
		String path = null;
		TipoRaza raza = jugador.getRaza();
		fiuba.algo3.juego.Color color = jugador.getColor();
		
		if (raza == TipoRaza.TERRAN)
			path = TERRAN_PATH;
		else if (raza == TipoRaza.PROTOSS)
			path = PROTOSS_PATH;
		else
			path = ZERG_PATH;
		
		CopyOfSpriteSheet sheetRaza = new CopyOfSpriteSheet(path);
		sheetRaza.swapColors(color);
		spritesJugador.put(jugador, sheetRaza);
	}

	public static CopyOfSpriteSheet getSpritesRecurso(Tipo tipo) {
		return spritesRecurso.get(tipo);
		
	}

}
