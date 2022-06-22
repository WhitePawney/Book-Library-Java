package source.pack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {
	
	private static JLabel l1, l2;
	
	public static void login()
	{
		JFrame loginf = new JFrame("Login");
		
		l1 = new JLabel ("Username");
		l1.setBounds(30, 15, 100, 30); //x and y axis, width, length;
		
		l2 = new JLabel ("Password");
		l2.setBounds(30, 50, 100, 30);
		
		JTextField F_user = new JTextField();  //creates text field for Username
		F_user.setBounds(130, 15, 200, 30);		
		
		JTextField F_pass = new JPasswordField();  //creates text field for pass
		F_pass.setBounds(130, 50, 200, 30);
		
		JButton login_but = new JButton("Login");
		login_but.setBounds(130, 90, 80, 25);
		login_but.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String user = F_user.getText();
				String password = F_pass.getText();
				if (user.equals("admin") && password.equals("1234")) {
					System.out.println("Good login");
					loginf.dispatchEvent(new WindowEvent(loginf, WindowEvent.WINDOW_CLOSING));
					
					//HERE IS THE MAIN FRAME
					mainFrame x = new mainFrame();
					x.setVisible(true);
					// ----------------------------
					
				}
				else System.out.println("Bad login");
			}
			
			
		});
		
		/*--------------------------------------------------------------------------------------------------
		  ---------------------------ADDING THE COMPONENTS TO THE PANEL-------------------------------------
		  -------------------------------------------------------------------------------------------------*/
		
		loginf.add(F_pass);
		loginf.add(login_but);
		loginf.add(F_user);		
		loginf.add(l1);
		loginf.add(l2);
		
		loginf.setSize(400, 200);
		loginf.setLayout(null);
		loginf.setVisible(true);
		loginf.setLocationRelativeTo(null);						
	}
}

