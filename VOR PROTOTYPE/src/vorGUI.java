import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;

public class vorGUI extends JFrame{
	private int radial = 0;
	private JPanel top;
	private JButton obs;
	private JLabel intendedRadial = new JLabel("Radial: " + radial);
	
	public vorGUI(){
		this.top = new JPanel();
		top.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(intendedRadial);
		top.add(north, "North");
	}
	
	public static void main(String[] args){
		JFrame window = new JFrame("VOR Radar");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    vorGUI vor = new vorGUI();
	    window.setContentPane(vor.top);
	    window.pack();
	    window.setVisible(true);
	  	//new vorGUI().setVisible(true);
		window.setSize(400,400);
	}
	
}
