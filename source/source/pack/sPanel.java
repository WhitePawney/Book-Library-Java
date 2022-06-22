package source.pack;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class sPanel extends JPanel{
	
	//private JPanel searchPanel;
	private JTextField searchField;
	
	public sPanel() {
		
		JPanel searchPanel = new JPanel();
		
		JLabel lblSearchLabel = new JLabel("Search Field\r\n");
		searchField = new JTextField();
		
		searchPanel.setLayout(null);
		
		
		lblSearchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchLabel.setBounds(10, 60, 115, 14);
		searchPanel.add(lblSearchLabel);
		
		
		searchField.setBounds(126, 59, 96, 20);		
		searchField.setColumns(10);
		searchPanel.add(searchField);
	
	}
	
	

}
