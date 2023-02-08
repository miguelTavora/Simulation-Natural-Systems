package menu;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JOptionPane;


public class PlaySong extends Thread {
	private Clip clip;
	private String song0;
	

	//construtor que faz load das musicas
	public PlaySong() {
		song0 = "src\\menu\\music1.wav";
		
	}

	//metodo que corre a thread paralelamente ao programa
	public void run() {

		play(song0);


	}

	//metodo corre com ficheiro guardado em memoria
	public void play(String path) {
		File musica= new File(path);//caminho para o ficheiro
		if (musica.exists()) {//verifica a existencia da musica
			try {
				clip= AudioSystem.getClip();
				//obtem fluxo entrada audio com codificação, converte o fluxo de entrada de audio fornecida
				AudioInputStream input= AudioSystem.getAudioInputStream(musica);
				clip.open(input);
				play();
				while (aTocar());
				clip.close();
			} catch (Exception e) {}

		}
		else
			JOptionPane.showMessageDialog(null, "Ficheiro de musica não existente!");

	}
	//metodo que faz com que a musica corra
	public boolean aTocar() {
		return (clip.getFramePosition()< clip.getFrameLength());
	}
	//metodo que começa a musica
	private void play() {
		clip.start();
		
	}
	
	public void stop_Music() {
		clip.stop();
	}
	
	public static void main(String[] args) {
		PlaySong song = new PlaySong();
		song.start();
	}
	
	public String getSong() {
		return this.song0;
	}



}

