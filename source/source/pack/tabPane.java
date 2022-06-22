package source.pack;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class tabPane extends JTabbedPane {
	
	private JTextField searchField;
	private JPanel searchPanel, insertPanel;
	
	public tabPane()
	{
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);				
			
		//searchPanel = new JPanel();
		
		//tabbedPane.addTab("Search Book", null, searchPanel, null);
		//searchPanel.setLayout(null);
			
		/*JLabel lblSearchLabel = new JLabel("Search Field\r\n");
		lblSearchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchLabel.setBounds(10, 60, 115, 14);
		searchPanel.add(lblSearchLabel);
		
		searchField = new JTextField();			
		searchField.setBounds(126, 59, 96, 20);		
		searchField.setColumns(10);
		searchPanel.add(searchField);
		
		insertPanel = new JPanel();
		tabbedPane.addTab("Insert Book ", null, insertPanel, null);
		insertPanel.setLayout(null);*/
	}
	

	

}
