import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class finalVORGUI extends JFrame{
	private static int x, y;
	private static draw object = new draw(10, 200, 50, 50, 90, 180);
	private int radial;
	private JPanel top;
	
	public finalVORGUI(){
		JLabel intendedRadial = new JLabel("Radial: " + radial);
		radial = 0;
	}
	
	public static void main(String[] args){
		finalVORGUI vor = new finalVORGUI();
		JFrame frame = new JFrame("VOR");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(400,400);
		frame.add(object);
		object.addMouseListener(new AL());
	}
	
	static class AL extends MouseAdapter{
		public int test = 0;
		public void mouseClicked(MouseEvent e){
			x = e.getX();
			y = e.getY();
			//object.drawMinusOBS(x, y);
			test--;
			System.out.println(test); //test to see if click works
		}
		public void mouseEntered(MouseEvent e){
			object.MouseOver();
		}
		public void mouseExited(MouseEvent e){
			object.MouseOff();
		}
	}
}
