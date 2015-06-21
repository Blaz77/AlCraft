package fiuba.algo3interfaz.music;

import java.nio.file.Paths;
import java.util.HashMap;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MP3Player {

	private static MediaPlayer currentMusic;
	
	private static HashMap<String, MediaPlayer> mps = new HashMap<String, MediaPlayer>();

	private static final String CARPETA_MUSICA = "res/music/";
	
	private static MediaPlayer crearMediaPlayer(String fileName) {
		
		String aMediaSrc = Paths.get(CARPETA_MUSICA + fileName).toUri().toString();
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

	public static void play() {
		currentMusic.play();
		
	}
	
	public static void play(String fileName){
		if (currentMusic != null) currentMusic.stop();
		setMusic(fileName);
		play();
	}
}


