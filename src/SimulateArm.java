import javax.swing.JFrame;

public class SimulateArm {

	public static void main(String [] args) {
		
		RobotDrawer robotDrawer = new RobotDrawer();
		JFrame frame = new JFrame("Arm Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(robotDrawer);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		double i = 0;
		try {
//			while (frame.isShowing()) {
				
				robotDrawer.setArmPostions(0, 120, 120);				
				Thread.sleep(1000);			
//			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		robotDrawer.setArmPostions(10, 45, 90);
		
	}
}
