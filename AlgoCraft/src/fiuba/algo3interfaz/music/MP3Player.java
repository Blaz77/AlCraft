package fiuba.algo3interfaz.music;

import java.util.HashMap;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MP3Player {

	private static MediaPlayer currentMusic;
	
	private static HashMap<String, MediaPlayer> mps = new HashMap<String, MediaPlayer>();

	private static final String CARPETA_MUSICA = "/music/";
	
	private static MediaPlayer crearMediaPlayer(String fileName) {
		
		String aMediaSrc = System.class.getResource(CARPETA_MUSICA + fileName).toString();
		final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
	    return player;
	 }
	
	private static void setMusic(String fileName){
		new JFXPanel(); // Inicializa JavaFX (media y MP no lo hacen).
		if (!mps.containsKey(fileName)) {
			mps.put(fileName, crearMediaPlayer(fileName));
		}
		currentMusic = mps.get(fileName);
	}

	public static void playCurrentMusic() {
		currentMusic.play();
		
	}
	
	public static void silence() {
		if (currentMusic != null) currentMusic.stop();
		
	}
	
	public static void play(String fileName){
		silence();
		setMusic(fileName);
		playCurrentMusic();
	}

}


