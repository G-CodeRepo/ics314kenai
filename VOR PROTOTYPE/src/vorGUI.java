import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;
/**
 * A GUI for the VOR radar
 * The VOR radar will let the pilot know how far on the left, or right
 * his/her intended radial is. The GUI will also implement an OBS button 
 * that will let the pilot change his/her intended radial
 * @author Kent Kawahara
 * @author Adrian Tai
 * @author Gerald Abut
 *
 */
public class vorGUI extends JFrame{
	private int radial = 0;
	private JPanel top;
	private JButton minusOBS;
	private JButton plusOBS;
	private JLabel intendedRadial = new JLabel("Radial: " + radial);
		
	/**
	 * Function: The constructor for the VOR
	 * The constructor will set the layout of the VOR radar
	 */
	public vorGUI(){
		this.top = new JPanel();
		top.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(intendedRadial);
		top.add(north, "North");
		minusOBS = new obs(10,200,50,50,90,180);
		plusOBS = new obs(20,100,50,50,90,180);
		top.add(minusOBS);
		top.add(plusOBS);
	}
	
	/**
	 * A private class that will set the OBS button
	 * It was very difficult trying to build a program that could build a 
	 * circular button. I used this site's code to help me out:
	 * http://www.dreamincode.net/forums/topic/207123-change-the-shape-of-jbutton/
	 * @author Kent
	 *
	 */
	private class obs extends JButton{
		private int x;
		private int y;
		private int width;
		private int height;
		private int startAngle;
		private int arcAngle;
		public obs(int x, int y, int width, int height, int startAngle, int arcAngle){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.startAngle = startAngle;
			this.arcAngle = arcAngle;
		}
		
		public void paint(Graphics g){ 
			g.setColor(Color.black);
			g.fillArc(x, y, width, height, startAngle, arcAngle);
			//g.fillArc(10, 200, 50, 50, 90, 180); 
		} 
	}
	

	
	/**
	 * Function: Main method currently used to just test the VOR GUI
	 * @param args
	 */
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
