import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class RobotDrawer extends JComponent{
	
	
	
	private final static double WRIST_LENGHT = 13;  // inches
	private final static double ARM_LENGHT  = 20; // inches
	
	private final static double ROBO_BASE_HEIGHT = 5;  // inches
	private final static double ROBO_BASE_LENGHT  = 25; // inches
	
	private final static double INITIAL_ARM_ANGLE = 45; // Degree
	private final static double INITIAL_WRIST_ANGLE = 45; // Degree	

	private final static int WindowSizeY = 800; // Degree
	private final static int WindowSizeZ = 600; // Degree
	
	private final static double PIXELS_PER_INCH = 10;  // inch
	
	private double Z_elevator = 10;
	private double theta_Arm = 0;
	private double theta_Wrist = 0;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4650699030920273565L;
	
	public Dimension getPreferredSize() {
		return new Dimension(WindowSizeY, WindowSizeZ);
	}
	
	private void drawLineInRootCoordinates(Graphics2D g2d,double y1, double z1, double y2, double z2, Color c) {
		g2d.setColor(c);
		g2d.drawLine(
				(int) (WindowSizeY/2- y1 * PIXELS_PER_INCH ),
			    (int) (WindowSizeZ - 75 - z1 * PIXELS_PER_INCH) ,
			    (int) (WindowSizeY/2 - y2 * PIXELS_PER_INCH),
			    (int) (WindowSizeZ - 75 - z2* PIXELS_PER_INCH));
	}
	
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
                                    
        drawLineInRootCoordinates(g2d,-ROBO_BASE_LENGHT/2,0,ROBO_BASE_LENGHT/2,0, Color.black);
        drawLineInRootCoordinates(g2d,-ROBO_BASE_LENGHT/2,-ROBO_BASE_HEIGHT,ROBO_BASE_LENGHT/2,-ROBO_BASE_HEIGHT, Color.black);
        drawLineInRootCoordinates(g2d,ROBO_BASE_LENGHT/2,0,ROBO_BASE_LENGHT/2,-ROBO_BASE_HEIGHT, Color.black);
        drawLineInRootCoordinates(g2d,-ROBO_BASE_LENGHT/2,0,-ROBO_BASE_LENGHT/2,-ROBO_BASE_HEIGHT, Color.black);
        
        drawLineInRootCoordinates(g2d,0,0,0,42, Color.black);
        
        // elevator
		
        drawLineInRootCoordinates(g2d,0,0,0,Z_elevator,Color.red);
		
		// Arm
		
		double ya = ARM_LENGHT * Math.cos(theta_Arm);
		double za = ARM_LENGHT * Math.sin(theta_Arm) + Z_elevator;
		
		drawLineInRootCoordinates(g2d,0,Z_elevator,ya, za,Color.BLUE);
		

		drawLineInRootCoordinates(g2d,ya, za,
				WRIST_LENGHT * Math.cos(theta_Wrist) + ya, WRIST_LENGHT * Math.sin(theta_Wrist) + za,Color.orange);
		
        
        g2d.dispose();
        
    }
    
    /*
     * 
     * Arm and Wrist angles are measured from the ground.
     * 
     * Zero degrees is pointing straight up.
     * 
     * All arguments are in inches or degrees. 
     * 
     */
    
    public void setArmPostions(double Z_elevator, double theta_Arm_degrees, double theta_Wrist_degrees) {
		theta_Arm = (theta_Arm_degrees + 90) * Math.PI/180;
		theta_Wrist = (theta_Wrist_degrees + 90) * Math.PI/180;
		
		this.Z_elevator = Z_elevator + 10;
		this.repaint();
    }
    	
}
