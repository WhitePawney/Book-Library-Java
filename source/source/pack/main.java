package source.pack;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;



public class main {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
		
			public void run() {					
				
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // NIMBUS LOOK AND FEEL
				}
				loginFrame.login();				
				}
		
			});
	}
}
