import javax.swing.*;
import java.awt.*;

public class draw extends JPanel {
	private static int x = 10;
	private static int y = 200;
	private static int width = 50;
	private static int height = 50;
	private static int startAngle = 90;
	private static int arcAngle = 180;
	private static Color color;
	
	public draw(int x, int y, int width, int height, int startAngle, int arcAngle){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
		color = Color.black;
	}
	
	public void drawMinusOBS(int xx, int yy){
		x = xx; 
		y = yy;
		repaint();
	}
	
	public void MouseOver(){
		color = Color.gray;
		repaint();
	}
	
	public void MouseOff(){
		color = Color.black;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		g.fillArc(x, y, width, height, startAngle, arcAngle);
	}
}
