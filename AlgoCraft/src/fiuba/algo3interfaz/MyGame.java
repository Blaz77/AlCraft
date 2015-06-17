package fiuba.algo3interfaz;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fiuba.algo3interfaz.gfx.MapaVista;
import fiuba.algo3interfaz.gfx.SpriteSheet;

public class MyGame extends JPanel implements Runnable {

	private MapaVista mapaVista;

    public MyGame(){
        setDoubleBuffered(true);
        // Initialize the resources
		SpriteSheet.inicializar();
        
        mapaVista = new MapaVista(10, 10);
        
        new Thread(this).start();
    }

    public void update(long elapsedTime){
    	mapaVista.tick();
    }

    private void GameLoop(){
    	
    }

    public void run(){
        GameLoop();
    }

    public void paint(Graphics g){
    	mapaVista.render(g);
    }

    public static void main(String[] args){
        JFrame f = new JFrame("MyGame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(640, 480);
        f.add(new MyGame());
        f.setVisible(true);
    }
}