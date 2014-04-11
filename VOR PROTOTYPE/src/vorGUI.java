import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;

public class vorGUI extends JFrame implements ActionListener{
	private JPanel top;
	private JButton minusOBS = new JButton(" ");
	private JButton plusOBS = new JButton(" ");
	
	public vorGUI(){
		vorPrototype VOR = new vorPrototype(120, "--.", true);
		minusOBS.addActionListener(this);
		plusOBS.addActionListener(this);
		this.top = new JPanel();
		top.setLayout(new BorderLayout());
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == minusOBS){
			//to do
		}
		else if(ae.getSource() == plusOBS){
			//to do
		}
	}
	
	/*
	 * Function: This method is going to "paint" or draw the VOR radar to the 
	 * JPanel. The name of this function must be paint, otherwise program will not work
	 */
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		g.drawOval(100, 100, 200, 200);
	}
	
	public static void main(String[] args){
		vorGUI radarGUI = new vorGUI();
		radarGUI.paint(null);
		
	}
}
