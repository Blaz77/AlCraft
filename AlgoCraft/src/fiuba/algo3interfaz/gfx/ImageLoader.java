package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path){
		try {
			BufferedImage src = ImageIO.read(ImageLoader.class.getResource(path));
			java.awt.image.ColorConvertOp cco = new java.awt.image.ColorConvertOp(null);
			BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
			dest = cco.filter(src, dest);
			return dest;
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}	
	
}
