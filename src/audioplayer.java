import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
  
public class audioplayer extends JPanel implements ActionListener { 
	
	//declaring elements
	JFrame window = new JFrame("Interface");
	JLabel info = new JLabel("WAV AudioPlayer");
	JButton addButton = new JButton("Add music");
	JButton playButton = new JButton("Play");
	JButton stopButton = new JButton("Stop");
	JButton pauseButton = new JButton("Pause");
	JComboBox list = new JComboBox();
	JFileChooser browser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Sound","wav");
	int returnValue;
	String[] musics = new String[99];
	int index = 0;
	File selectedFile;
	File sound;
	AudioInputStream inputStream;
	Clip clip;
	boolean isPlaying = false;
	long clipTime;
	
	audioplayer(){
		//constructing interface, defining colors and placement
		this.setBackground(Color.black);
		window.add(this);
		
		addButton.addActionListener(this);
		playButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		
		info.setFont(new Font("",Font.ITALIC,10));
		window.add(info, BorderLayout.PAGE_END);
		
		addButton.setBackground(Color.black);
		playButton.setBackground(Color.black);
		stopButton.setBackground(Color.red);
		addButton.setForeground(Color.white);
		playButton.setForeground(Color.white);
		stopButton.setForeground(Color.white);
		window.add(addButton,BorderLayout.LINE_START);
		window.add(playButton,BorderLayout.CENTER);
		window.add(stopButton,BorderLayout.LINE_END);
		
		list.setBackground(Color.gray);
		list.setForeground(Color.white);
		window.add(list,BorderLayout.PAGE_START);
		
		browser.setFileFilter(filter);
		
		window.setSize(400,200);
		window.setLocation(200,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		//ae = actionEvent
		if(ae.getSource()==addButton) {
			returnValue = browser.showOpenDialog(window);
			//adding music to the list
			if(returnValue == browser.APPROVE_OPTION) {
				selectedFile = browser.getSelectedFile();
				musics[index] = selectedFile.toString();
				list.addItem("Song - "+index);
				index++;
			}
		}
		else if(ae.getSource() == playButton) {
			//playing music
			try {
				if(list.getSelectedIndex()>=0) {
					if(isPlaying) {
						//pausing music if playing currently
						clipTime = clip.getMicrosecondPosition();
						clip.stop();
						playButton.setText("Play");
						isPlaying = false;
					}
					else{
						//playing audio if not playing currently
						sound = new File(musics[list.getSelectedIndex()]);
						inputStream = AudioSystem.getAudioInputStream(sound);
						clip = AudioSystem.getClip();
						clip.open(inputStream);
						clip.setMicrosecondPosition(clipTime);
						clip.start();
						playButton.setText("Pause");
						isPlaying = true;
					}
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		else if(ae.getSource() == stopButton) {
			//stops music
			clip.stop();
			clipTime = 0;
			playButton.setText("Play");
			isPlaying = false;
		}
	}

} 