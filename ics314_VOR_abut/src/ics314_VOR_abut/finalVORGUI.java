package ics314_VOR_abut;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
/**
 * This class is going to draw all of the attributes of a VOR Radar
 * Instead of having an OBS button, I have decided it to be better for the 
 * user to use the arrow keys, or the "a"/"d" buttons on their keyboard to
 * rotate the VOR radar and the needle
 * Hate to admit it, I have used an outside source to assist me with this project.
 * The code can be found at: http://www.coderanch.com/t/344056/GUI/java/Rotating-image
 * @author Kent
 *
 */
public class finalVORGUI extends JPanel{
	private JPanel rotationPanel; 									//this panel is going to draw the outline of the radar, along with rotating it
	private JPanel needle; 											//this panel is going to draw the needle AND attributes of the radar
	private JPanel OBS; 											//a panel that will add two buttons that will have the effect of an OBS knob
	private int degrees; 											//a integer variable that will let the user know which radial he/she is currently on
	private int x; 													//a integer variable that will store the location of the needle. When the minus/plus button is clicked, this will update...
	private int y1;													//a integer variable that will store the first y value of the needle
	private int y2;													// .. .. ..
	final int WIDTH = 600;											//the preferred window size of the program
	final int HEIGHT = 600;											//the preferred window size of the program
	private JButton min;											//minus button for OBS
	private JButton plus;											//plus button for OBS
	private ImageIcon radar = new ImageIcon("/Users/gmachine/git/ics314kenai/ics314_VOR_abut/vor1.png");										//the outline image of the radar
	private JLabel CurrentRadial;									//a label that is going to to let the user know which radial he/she is on
	private JLabel MorseCode;										//a label that will let the user know which VOR station they are heading towards
	private VorReceiver vor;
	/**
	 * The constructor for the class
	 * It's going to set the dimension of the program to 600x600, the 
	 * background is going to be white (in order to blend in with the
	 * vor image), and it is going to add in the VOR radar and a radial
	 * indicator that will let the user know which radial he/she is on
	 */
	public finalVORGUI(){
		int deg = 270;
		this.vor = new VorReceiver(deg,"LHK");
		this.vor.setOBS(90);	// set the OBS to 30
		JLayeredPane lp = new JLayeredPane();
		lp.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.white);
		lp.setLayout(null);
		lp.setFocusable(true);
		degrees = this.vor.getCurrRadial(); //to edit: this is going to be the radial the radar is currently facing
	    CurrentRadial = new JLabel("Intended Radial: " + degrees); //A string that is always going to be above the radar. it's going to let the user know the current radial
		CurrentRadial.setBounds(220, 18, 200, 200);
		MorseCode = new JLabel("Station: TO ENTER HERE");
		MorseCode.setBounds(200,500,200,50);
		MorseCode.setText(this.vor.getMorse());
		
		//vor.printVorStatus_v1();
		rotationPanel = new JPanel();
		rotationPanel = new TurningCanvas();
		rotationPanel.setBounds(100, 100, rotationPanel.getPreferredSize().width, rotationPanel.getPreferredSize().height);
		needle = new JPanel(); 
		needle = new DrawAttributes(); 
		needle.setBounds(100,0, needle.getPreferredSize().width, needle.getPreferredSize().height);
		OBS = new JPanel();
		OBS = new AddButtons();
		OBS.setBounds(170, 350, 200, 100);
		lp.add(rotationPanel, Integer.valueOf(1));
		lp.add(needle, Integer.valueOf(2));
		lp.add(OBS,Integer.valueOf(3));
		lp.add(CurrentRadial,Integer.valueOf(4));
		lp.add(MorseCode, Integer.valueOf(5));
		add(lp);
		
		x = 172; //x is the location of the needle
		y1 = 155;
		y2 = 330;
		
	}
	
	/**
	 * Although this paintComponent may seem useless, it is very important.
	 * Without it, the background will not paint and the look of the program will
	 * be very hideous. Don't delete
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	/**
	 * A class that will create two buttons for the VOR radar, a plus and minus
	 * The plus button is going to be used to add a radial (for the intended radial),
	 * and vice versa for the minus
	 * @author Kent
	 *
	 */
	public class AddButtons extends JPanel{
		/**
		 * Constructor for the AddButtons class
		 * It is simply going to set the background transparent
		 * and add the Buttons() class to the panel
		 */
		public AddButtons(){
			setOpaque(false);
			add(new Buttons());
		}
		
		/**
		 * A class Buttons, inside of Class AddButtons
		 * The Panel in this class is going to use a BorderLayout() 
		 * because I thought it would be nice for me to be able to 
		 * arrange the buttons on a left/right axis of the Panel.
		 * This class is also going to edit the buttons a bit, just 
		 * to make them a little better
		 * @author Kent
		 *
		 */
		public class Buttons extends JPanel implements ActionListener{
			int w = 200;
			int h = 100;
			JButton min;
			JButton plus;
			public Buttons(){
				setPreferredSize(new Dimension(w,h));
				setBackground(new Color(0,0,0,0));
				setLayout(new BorderLayout());
				min = new JButton("minus");
				plus = new JButton("plus");
				//min.setPreferredSize(new Dimension(100,10));
				//plus.setPreferredSize(new Dimension(100,10));
				min.setBackground(Color.WHITE);
				plus.setBackground(Color.WHITE);
				min.setOpaque(false);
				plus.setOpaque(false);
				min.setBorderPainted(true);
				min.setFocusPainted(true);
				plus.setFocusPainted(true);
				plus.setBorderPainted(true);
				min.addActionListener(this);
				plus.addActionListener(this);
				min.setActionCommand("-");
				plus.setActionCommand("+");
				add(plus, BorderLayout.EAST);
				add(min, BorderLayout.WEST);
			}
			
			/**
			 * This is the action listener that this program will be using
			 * When the user clicks the minus button, the action listener will subtract from the
			 * degrees variable and x coordinate
			 * Doing so will update the Radial Indicator and the location of the needle, and 
			 * most importantly, rotate the image of the radar
			 * The same goes for the plus button, except it will add from the variables
			 * @author Kent
			 *
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if("-".equals(e.getActionCommand())){  
					CurrentRadial.setText("Intended Radial: " + degrees);
				//	vor.updateIncomingRadial(getDeg());
					vor.setOBS(getDeg());
					calcNeedPos();  
					degrees--;
					
			 		//x -= 2;
			 		   
			 		if(degrees <= 0){
			 			degrees = 360;
			 		}
			 		  
			 		   
			 		   
			 		   rotationPanel.repaint();
			 		   vor.printVorStatus_v1();
			 	   }  
			 	   if("+".equals(e.getActionCommand())){ 
			 		   CurrentRadial.setText("Intended Radial: " + degrees);
			 		  // vor.updateIncomingRadial(getDeg());
			 		   
			 		   vor.setOBS(getDeg());

			 		   calcNeedPos();
			 		   degrees++;
			 		   //x += 2;
			 		   
			 		   if(degrees >= 360){
			 			   degrees = 1;
			 		   }
			 		   
			 		   
			 		   rotationPanel.repaint();  
			 		   vor.printVorStatus_v1();
			 		   
			 		   
			 	   }  
			    }  
		}
	}
	
	/**
	 * A class that is going to draw the attributes of the VOR radar
	 * The radar image is just the outline, there still needs to be 8 small circles, the needle, 
	 * and the TO/FROM indicator to be drawn in. This class is going to take care of that for us
	 * @author Kent
	 *
	 */
	public class DrawAttributes extends JPanel{ 
		
		/**
		 * Constructor for this class.
		 * It is going to set the background of the Panel to transparent and 
		 * add the Attributes class to the Panel
		 */
		public DrawAttributes(){
			setOpaque(false);
			add(new Attributes());
		}
		
		/**
		 * Another class, Attributes, which will do the drawing for us
		 * @author Kent
		 *
		 */
		public class Attributes extends JPanel{
			int w = 500;
			int h = 400;
			
			/**
			 * A constructor for hits class.
			 * It is going to set the dimensions for the JPanel, and
			 * to make sure the Panel does not completely cover up the image
			 * of the radar, we set the background to Color(0,0,0,0) in order
			 * to guarantee absolute transparency
			 */
			public Attributes(){
				setPreferredSize(new Dimension(w,h));
				setBackground(new Color(0,0,0,0));
			}
			
			/**
			 * A paint class that will draw the needle, dots, and TO/FROM
			 * RenderingHints is IMPORTANT. It will make sure that everything stays
			 * focused and not blurry whenever the image rotates. I added an empty line between
			 * the 8 fillArcs, just so its easier to see that four of them draw on the left side
			 * and four draw on the right side.
			 */
			public void paintComponent(Graphics g){  
		        Graphics2D g2 = (Graphics2D) g;  
		        RenderingHints hints = new RenderingHints(null);  
		        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
		        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);  
		        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);  
		        g2.setRenderingHints(hints);  
		        g2.setStroke(new BasicStroke(3));
				//g2.drawString("TO", 190, 200);
				if(vor.isTo() == true){
			           if(vor.isAbeamed() == true){
			           // g2.setFont(new Font("default", Font.BOLD, 16));
			            //g2.setColor(Color.red);
			           // g2.drawString("BAD", 190, 200);
			           }
			           else{
			            //g2.setFont(new Font("default", Font.BOLD, 16));
			            g2.drawString("TO", 190, 200);
			           }
			          }
				else if(vor.isFrom() == true){
			      if(vor.isAbeamed() == true){
			           // g2.setFont(new Font("default", Font.BOLD, 16));
			            //g2.setColor(Color.red);
			           // g2.drawString("BAD", 190, 300);
			           }
			      else{
			       //g2.setFont(new Font("default", Font.BOLD, 13));
			       g2.drawString("FROM",190, 300);
			      }
			    }
				else if(vor.isFrom() == false || vor.isTo() == false) {
			    	  //is above the vor station no to or from
			     }
				
				g2.fillArc(140, 240, 7, 7, 0, 360);
				g2.fillArc(125, 240, 7, 7, 0, 360);
				g2.fillArc(110, 240, 7, 7, 0, 360);
				g2.fillArc(95, 240, 7, 7, 0, 360);
				
				g2.fillArc(195, 240, 7, 7, 0, 360);
				g2.fillArc(210, 240, 7, 7, 0, 360);
				g2.fillArc(225, 240, 7, 7, 0, 360);
				g2.fillArc(240, 240, 7, 7, 0, 360);

				//g2.drawString("FROM",190, 300);
				g2.drawLine(x,y1,x,y2);
		        super.paintComponent (g);  
		        g2.dispose();  
		    } 
		}
	}
	
	/**
	 * A class TurningCanvas
	 * This class will draw the radar outline, and create a rotate method in 
	 * the paintComponent class. This will make sure that the image will rotate
	 * every time one of the buttons is clicked
	 * @author Kent
	 *
	 */
	public class TurningCanvas extends JPanel{
		
		public TurningCanvas(){
			setOpaque(false);
			add(new TurningImage());
		}
		
		public class TurningImage extends JPanel{
			int w = radar.getIconWidth()-20;
			int h = radar.getIconHeight()-20;
			
			public TurningImage(){
				setPreferredSize(new Dimension(w,h));
				setBackground(Color.white);
			}

			
			public void paintComponent(Graphics g){  
		        super.paintComponent (g);  
		        Graphics2D g2 = (Graphics2D) g;  
		        RenderingHints hints = new RenderingHints(null);  
		        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
		        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);  
		        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);  
		        g2.setRenderingHints(hints);  
		        g2.rotate (Math.toRadians(degrees),w/2, h/2);  
		        g2.drawImage(radar.getImage(), 0, 0, this);
		        g2.dispose();  
		    } 
	
		}
	}
	/*
	 * Returns degrees of vor
	 */
	public int getDeg() {
		return degrees;
	}
	
	/*
	 * Calculates needle position on the GUI interface and sets it. 
	 */
	public void calcNeedPos() {
	
	   if(vor.getNeedlePosition() == 10) {
		   x = 250;
	   }
	   else if(vor.getNeedlePosition() == 8) {
		   x = 244;
	   }
	   else if(vor.getNeedlePosition() == 6) {
		   x = 228;
	   }
	   else if(vor.getNeedlePosition() == 4) {
		   x = 212;
	   }
	   else if(vor.getNeedlePosition() == 2) {
		   x = 198; //172 inital pos
	   }
	   else if(vor.getNeedlePosition() == 0) {
		   x = 172;
	   }
	   else if(vor.getNeedlePosition() == -10) {
		   x = 89; 
	   }
	   else if(vor.getNeedlePosition() == -8) {
		   x = 98; 
	   }
	   else if(vor.getNeedlePosition() == -6) {
		   x = 114;
	   }
	   else if(vor.getNeedlePosition() == -4) {
		   x = 128;
	   }
	   else if(vor.getNeedlePosition() == -2) {
		   x = 144;
	   }
	   
	   
	}
    /**
     * The main method of this class
     * This is going to make a new JFrame, which will hold the new
     * VOR radar
     * @param args
     */
    public static void main(String[] args){  
    	finalVORGUI test = new finalVORGUI();
    	JFrame frame = new JFrame("VOR Radar");  
    	frame.setContentPane(test);  
        frame.pack();  
        frame.setVisible(true);
    }  
	
}
