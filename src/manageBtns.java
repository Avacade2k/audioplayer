import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class manageBtns {
	
	static JFileChooser browser = 		new JFileChooser();
	static AudioInputStream inputStream;
	static Clip clip = null;
	static boolean isPlaying = false;
	static boolean stoppedByClick = false;
	static long clipTime;
	static File sound = null;

	public static void playBtn() {
		//Playing music
		try {
			if(isPlaying) {
				//Pausing music if playing currently
				stoppedByClick = true;
				clipTime = clip.getMicrosecondPosition();
				clip.stop();
				audioplayer.setplayBtn("play");
				isPlaying = false;
			}
			else{
				//Playing audio if not playing currently
				stoppedByClick = false;
				sound = new File(audioplayer.getMusics1().get(audioplayer.getList().getSelectedIndex()));
				inputStream = AudioSystem.getAudioInputStream(sound);
				clip = AudioSystem.getClip();
				clip.open(inputStream);
				clip.setMicrosecondPosition(clipTime);
				clip.start();
				clip.addLineListener(e -> {
				    if (e.getType() == LineEvent.Type.STOP) if(!stoppedByClick) stopAudio();
				  });
				audioplayer.setplayBtn("Pause");
				isPlaying = true;
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public static void stopBtn() {
		stopAudio();
	}
	public static void listBtn(Object target) {
		if(clip!=null) {
			if (!target.equals(sound.getName())){
				stopAudio();
			}
		}
		
	}
	
	public static void stopAudio() {
		clip.stop();
		clipTime = 0;
		audioplayer.setplayBtn("play");
		isPlaying = false;
	}
	
	public static void addBtn() {
		browser.setFileFilter(new FileNameExtensionFilter("WAV Sound","wav"));
		browser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		//Adding music to the list
		if(browser.showOpenDialog(audioplayer.getWindow()) == JFileChooser.APPROVE_OPTION) {
			if(((DefaultComboBoxModel<String>)audioplayer.getList().getModel()).getIndexOf(browser.getSelectedFile().getName()) == -1) { // check if the item already exist in our list (to get rid of duplicates)
				audioplayer.setList((browser.getSelectedFile().getName())); // 
				audioplayer.setMusics1((browser.getSelectedFile().toString())); // use of Arraylist instead of array & removed unnecessary index counter
				audioplayer.enableBtn();
				}
			}
		}
}
