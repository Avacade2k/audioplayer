import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
  
public class audioplayer extends JPanel implements ActionListener { 
	
	//Declaring elements
	static JFrame window = 	 new JFrame("Interface");
	JLabel info = 			 new JLabel("WAV AudioPlayer");
	static JButton[] btns =	{new JButton("Add music"), new JButton("Play"), new JButton("Stop")};
	String[] btnCommands=   { "add", "play", "stop"}, BorderLayouts = {BorderLayout.LINE_START, BorderLayout.CENTER ,BorderLayout.LINE_END};
	static ArrayList<String> musics1 = 	new ArrayList<>();
	static JComboBox<String> list = 	new JComboBox<>();
	
	public static void setMusics1(String music) {
		musics1.add(music);
	}
	public static void setList(String item) {
		list.addItem(item);
	}
	public static JFrame getWindow() {
		return window;
	}
	public static JComboBox<String> getList() {
		return list;
	}
	public static ArrayList<String> getMusics1() {
		return musics1;
	}
	public static void setplayBtn(String name) {
		btns[1].setText(name);
	}
	public static void enableBtn() {
		btns[1].setEnabled(true);
		btns[2].setEnabled(true);
	}
	
	audioplayer(){
		//Constructing interface
		this.setBackground(Color.black);
		window.add(this);
		info.setFont(new Font("",Font.ITALIC,10));
		window.add(info, BorderLayout.PAGE_END);
		
		//Setting up buttons
		for (int z = 0 ; z < btns.length ; z++) {
			btns[z].setActionCommand(btnCommands[z]);
			btns[z].addActionListener(this);
			if (z < 2) btns[z].setBackground(Color.black);
			else		btns[z].setBackground(Color.red);
			btns[z].setForeground(Color.white);
			window.add(btns[z],BorderLayouts[z]);
			if (z != 0) btns[z].setEnabled(false);
			}
		
		list.setActionCommand("listAL");
		list.addActionListener(this);
		list.setBackground(Color.gray);
		list.setForeground(Color.white);
		window.add(list,BorderLayout.PAGE_START);
		
		//Interface window
		window.setSize(400,200);
		window.setLocation(200,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand() == "add")			manageBtns.addBtn();
		else if(ae.getActionCommand() == "play")	manageBtns.playBtn();
		else if(ae.getActionCommand() == "stop")	manageBtns.stopBtn();
		else if(ae.getSource() == list)				manageBtns.listBtn(list.getSelectedItem());	
		}
	} 
