package source.pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DateFormatter;

import net.proteanit.sql.DbUtils;

public class mainFrame extends JFrame {
	
	private JPanel contentPane;
	private sPanel searchPanel;
	private JTextField searchField;
	private JPanel insertPanel, recommendPanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table_1, table, recTable, infoTable;
	private JTextField bookField, personField, bookLendField, loanDateField, receiveDateField, phoneField, authorField, yearField, keywordField, statusField, copiesField, keyField_1, keyField_2, keyField_3, returnedBookField;
	
	@SuppressWarnings("serial")
	public mainFrame()
	{				
		super("BOOK LIBRARY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);		
		contentPane.add(tabbedPane, gbc_tabbedPane);		
		
		
		/*---------------------------------------------------------------------
		  -----------------IMPLEMENTING TEXT FORMATS INPUT JFIELDS-------------
		  ---------------------------------------------------------------------*/
		
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		DateFormatter df = new DateFormatter(format);
		Date currentDate = new Date();
		Calendar nextDate = Calendar.getInstance();
		nextDate.setTime(currentDate);
		nextDate.add(Calendar.DATE, 14);
		Date curentDatePlusSeven = nextDate.getTime();
		
		/*---------------------------------------------------------------------
		  -----------------DESIGNING SEARCH PANEL--------------------------
		  ---------------------------------------------------------------------*/
		
		searchPanel = new sPanel();		
		tabbedPane.addTab("Search&Lend Book", null, searchPanel, null);
		
		searchPanel.setLayout(null);	
		
		JLabel lblSearchLabel = new JLabel("Search Field\r\n");		
		lblSearchLabel.setFont(new Font("Tahoma", Font.BOLD, 16));	
		lblSearchLabel.setBounds(20, 19, 200, 20);
		searchPanel.add(lblSearchLabel);
		
		searchField = new JTextField();			
		searchField.setBounds(20, 50, 250, 27);		
		searchField.setColumns(10);
		searchPanel.add(searchField);
		
		JRadioButton bookRadioButton = new JRadioButton("by Book Name");
		buttonGroup.add(bookRadioButton);
		bookRadioButton.setBounds(20, 99, 120, 25);
		searchPanel.add(bookRadioButton);
		
		JRadioButton authorRadioButton = new JRadioButton("by Author");
		buttonGroup.add(authorRadioButton);
		authorRadioButton.setBounds(150, 99, 100, 25);
		searchPanel.add(authorRadioButton);		
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchButton.setHorizontalAlignment(SwingConstants.CENTER);		
		searchButton.setBounds(50, 140, 150, 30);
		searchPanel.add(searchButton);
		
		//-------------------LEND INFORMATION DESIGN----------------
		//----------------------------------------------------------
		JLabel lblLendInfoLabel = new JLabel("Lend Information\r\n");		
		lblLendInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));	
		lblLendInfoLabel.setBounds(20, 200, 200, 20);
		searchPanel.add(lblLendInfoLabel);
		
		JLabel lblPersonLabel = new JLabel("Person ID\r\n");		
		lblPersonLabel.setFont(new Font("Tahoma", Font.BOLD, 14));	
		lblPersonLabel.setBounds(20, 240, 200, 20);
		searchPanel.add(lblPersonLabel);
		
		personField = new JTextField();			
		personField.setBounds(126, 239, 150, 27);		
		personField.setColumns(10);
		searchPanel.add(personField);
		
		JLabel lblBookLendLabel = new JLabel("Book Name\r\n");		
		lblBookLendLabel.setFont(new Font("Tahoma", Font.BOLD, 14));	
		lblBookLendLabel.setBounds(20, 270, 200, 20);
		searchPanel.add(lblBookLendLabel);
		
		bookLendField = new JTextField();			
		bookLendField.setBounds(126, 269, 150, 27);		
		bookLendField.setColumns(10);
		bookLendField.setEditable(false);   //---- IMPORTANT---- 
		searchPanel.add(bookLendField);
		
		JLabel lblloanDateLabel = new JLabel("Loan Date\r\n");		
		lblloanDateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));	
		lblloanDateLabel.setBounds(20, 300, 200, 20);
		searchPanel.add(lblloanDateLabel);
		
		loanDateField = new JFormattedTextField(df);			
		loanDateField.setBounds(126, 299, 150, 27);		
		loanDateField.setColumns(10);
		((JFormattedTextField) loanDateField).setValue(currentDate);
		searchPanel.add(loanDateField);		
		
		JLabel lblreceiveDateLabel = new JLabel("Receive Date\r\n");		
		lblreceiveDateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));	
		lblreceiveDateLabel.setBounds(20, 330, 200, 20);
		searchPanel.add(lblreceiveDateLabel);
		
		receiveDateField = new JFormattedTextField(df);			
		receiveDateField.setBounds(126, 329, 150, 27);		
		receiveDateField.setColumns(10);
		((JFormattedTextField) receiveDateField).setValue(curentDatePlusSeven);
		receiveDateField.setEditable(false);   //---- IMPORTANT----
		searchPanel.add(receiveDateField);
		
		JLabel lblphoneLabel = new JLabel("Phone Number\r\n");		
		lblphoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));	
		lblphoneLabel.setBounds(20, 360, 200, 20);
		searchPanel.add(lblphoneLabel);
		
		phoneField = new JTextField();			
		phoneField.setBounds(126, 359, 150, 27);		
		phoneField.setColumns(10);
		searchPanel.add(phoneField);
		
		JButton lendButton = new JButton("Confirm Lend");
		lendButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		lendButton.setHorizontalAlignment(SwingConstants.CENTER);		
		lendButton.setBounds(50, 410, 150, 30);
		searchPanel.add(lendButton);			
		
		/*---------------------------------------------------------------------
		  -----------------MAKING THE PANELS FOR THE TABLE---------------------
		  ---------------------------------------------------------------------*/
		
		JPanel tablePanel_1 = new JPanel();
		tablePanel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Database", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tablePanel_1.setBounds(286, 44, 600, 406);
		searchPanel.add(tablePanel_1);
		tablePanel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 16, 588, 383);
		tablePanel_1.add(scrollPane_1);
		
		/*---------------------------------------------------------------------
		  -----------------MAKING SEARCH TABLE---------------------------------
		  ---------------------------------------------------------------------*/
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null ,null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Book Name", "Author", "Genre", "Year", "Status", "NO.Copies"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false ,false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane_1.setViewportView(table_1);	
		
		
		/*---------------------------------------------------------------------
		  --------GETTING BOOK NAME FIELD BY CLICK LISTENER ON TABLE-----------
		  ---------------------------------------------------------------------*/
		
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Name = table_1.getSelectedRow();
				TableModel model = table_1.getModel();
				String value1 = (String) model.getValueAt(Name, 1);  //gets specified column by click
				bookLendField.setText(value1);
			}
		});
		
		
		/*---------------------------------------------------------------------
		  -----------------IMPLEMENTING SEARCH BUTTON--------------------------
		  ---------------------------------------------------------------------*/				
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (bookRadioButton.isSelected()) {
					
					String sql = "SELECT ID ,[Book Name], Author, Genre, Year, Status, [NO.Copies] FROM bookTable WHERE [Book Name] LIKE ?";	
					
				try {
					String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
					Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+searchField.getText()+"%");
					ResultSet rs = pstmt.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(SQLException ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
			}
				if (authorRadioButton.isSelected()) {					
					String sql2 = "SELECT ID ,[Book Name], Author, Genre, Year, Status, [NO.Copies] FROM bookTable WHERE Author LIKE ?";						
					
				try {
					String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
					Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, "%"+searchField.getText()+"%");
					ResultSet rs = pstmt.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(SQLException ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
				
			}
		}
});	
		
		/*---------------------------------------------------------------------
		  -----------------IMPLEMENTING CONFIRM LEND BUTTON--------------------
		  ---------------------------------------------------------------------*/		
		
		lendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO lendTable(PersonID, Book, [Loan Date], [Receive Date], [Phone Number]) VALUES (?, ?, ?, ?, ?)";
				String sql2 = "UPDATE bookTable SET [NO.Copies] = CASE WHEN [NO.Copies] > 0 THEN ([NO.Copies] - 1) ELSE 0 END WHERE [Book Name] LIKE (?)";
				try {
					String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
					Connection conn = DriverManager.getConnection(url);
					System.out.println("Connection SQL Lite good");							
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					
					pstmt.setString(1, personField.getText());
					pstmt.setString(2, bookLendField.getText());					
					pstmt.setString(3, loanDateField.getText());					
					pstmt.setString(4, receiveDateField.getText());	
					pstmt.setInt(5, Integer.parseInt(phoneField.getText()));
					
					pstmt2.setString(1, bookLendField.getText());
					
					pstmt.executeUpdate();					
					pstmt2.executeUpdate();
					
					
				}
				catch (SQLException ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
			}
		});			
		
		/*---------------------------------------------------------------------
		  -----------------DESIGNING INSERT PANEL -----------------------------
		  ---------------------------------------------------------------------*/
		
		insertPanel = new JPanel();		
		tabbedPane.addTab("Insert Book ", null, insertPanel, null);
		
		insertPanel.setLayout(null);		
		insertPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel bookLabel = new JLabel("Book Name");
		bookLabel.setBounds(24, 60, 79, 22);
		insertPanel.add(bookLabel);
		
		bookField = new JTextField();
		bookField.setBounds(114, 61, 150, 27);
		insertPanel.add(bookField);
		bookField.setColumns(10);
		
		JLabel authorLabel = new JLabel("Author");
		authorLabel.setBounds(24, 93, 48, 14);
		insertPanel.add(authorLabel);
		
		authorField = new JTextField();
		authorField.setBounds(114, 92, 150, 27);
		insertPanel.add(authorField);
		authorField.setColumns(10);
		
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setBounds(24, 126, 48, 14);
		insertPanel.add(yearLabel);
		
		yearField = new JTextField();
		yearField.setBounds(114, 123, 150, 27);		
		yearField.setColumns(10);
		insertPanel.add(yearField);
		
		JLabel keywordLabel = new JLabel("Keywords");
		keywordLabel.setBounds(24, 156, 100, 20);
		insertPanel.add(keywordLabel);
		
		keywordField = new JTextField();
		keywordField.setBounds(114, 156, 150, 27);
		insertPanel.add(keywordField);
		
		JLabel statusLabel = new JLabel("Status");
		statusLabel.setBounds(24, 186, 100, 20);
		insertPanel.add(statusLabel);
		
		statusField = new JTextField();
		statusField.setBounds(114, 186, 150, 27);
		insertPanel.add(statusField);
		
		JLabel copiesLabel = new JLabel("No. Copies");
		copiesLabel.setBounds(24, 216, 100, 20);
		insertPanel.add(copiesLabel);
		
		copiesField = new JTextField();
		copiesField.setBounds(114, 216, 150, 27);
		insertPanel.add(copiesField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SF", "Historical","Romance", "IT", "Medicine", "Music", "Visual Arts", "Language Learning"}));
		comboBox.setBounds(114, 246, 120, 27);
		insertPanel.add(comboBox);
		
		JButton insertBtn = new JButton("INSERT");		
		insertBtn.setBounds(24, 290, 99, 27);
		insertPanel.add(insertBtn);
		
		/*---------------------------------------------------------------------
		  -----------------IMPLEMENTING INSERT BUTTON--------------------------
		  ---------------------------------------------------------------------*/		
		
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO bookTable([Book Name], Author, Genre, Year, Keywords, Status, [NO.Copies]) VALUES (?, ?, ?, ?, ?, ? ,?)";
				try {
					String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
					Connection conn = DriverManager.getConnection(url);
					System.out.println("Connection SQL Lite good");			
					
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, bookField.getText());
					pstmt.setString(2, authorField.getText());
					
					String gen;
					gen = comboBox.getSelectedItem().toString();
					pstmt.setString(3, gen);
					
					pstmt.setInt(4, Integer.parseInt(yearField.getText()));	
					pstmt.setString(5, keywordField.getText());
					
					pstmt.setString(6, statusField.getText());
					pstmt.setInt(7, Integer.parseInt(copiesField.getText()));
					
					
					pstmt.executeUpdate();
					conn.close();
				}
				catch (SQLException ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
			}
		});				
		
		/*---------------------------------------------------------------------
		  -----------------MAKING THE PANELS FOR THE TABLE (DESIGN)------------
		  ---------------------------------------------------------------------*/
		
		JPanel tablePanel_2 = new JPanel();
		tablePanel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Database", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tablePanel_2.setBounds(291, 26, 600, 406);
		insertPanel.add(tablePanel_2);
		tablePanel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 16, 588, 383);
		tablePanel_2.add(scrollPane_2);
		
		/*---------------------------------------------------------------------
		  -----------------MAKING INSERTION TABLE------------------------------
		  ---------------------------------------------------------------------*/
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null ,"", "", null, null, null},
			},
			new String[] {
				"ID" ,"Book Name", "Author", "Genre", "Year","Status","NO.Copies", "Keywords"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class ,String.class, String.class, String.class, Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false ,false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane_2.setViewportView(table);
		
		/*---------------------------------------------------------------------
		  -----------------IMPLEMENTING REFRESH BUTTON--------------------------
		  ---------------------------------------------------------------------*/
		
		JButton refreshBtn = new JButton("REFRESH");
		refreshBtn.setBounds(152, 290, 105, 27);
		insertPanel.add(refreshBtn);
		
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT * FROM bookTable";
				try {
					String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
					Connection conn = DriverManager.getConnection(url);
					System.out.println("Connection SQL Lite good");			
					
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch (SQLException ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
				
			}
		});		
		
		/*---------------------------------------------------------------------
		  -----------------DESIGNING RECOMMEND PANEL --------------------------
		  ---------------------------------------------------------------------*/
		
		recommendPanel = new JPanel();		
		tabbedPane.addTab("Recommend", null, recommendPanel, null);
		
		recommendPanel.setLayout(null);
		recommendPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel keyLabel_1 = new JLabel("Keyword 1");
		keyLabel_1.setBounds(24, 80, 79, 22);
		recommendPanel.add(keyLabel_1);
		
		keyField_1 = new JTextField();
		keyField_1.setBounds(114, 82, 120, 27);
		keyField_1.setColumns(10);
		keyField_1.setText("-");
		recommendPanel.add(keyField_1);
		
		
		JLabel keyLabel_2 = new JLabel("Keyword 2");
		keyLabel_2.setBounds(24, 110, 79, 22);
		recommendPanel.add(keyLabel_2);
		
		keyField_2 = new JTextField();
		keyField_2.setBounds(114, 112, 120, 27);
		keyField_2.setColumns(10);
		keyField_2.setText("-");
		recommendPanel.add(keyField_2);
		
		JLabel keyLabel_3 = new JLabel("Keyword 3");
		keyLabel_3.setBounds(24, 140, 79, 22);
		recommendPanel.add(keyLabel_3);
		
		keyField_3 = new JTextField();
		keyField_3.setBounds(114, 142, 120, 27);
		keyField_3.setColumns(10);
		keyField_3.setText("-");
		recommendPanel.add(keyField_3);
		
		JLabel keyGenre = new JLabel("Genre");
		keyGenre.setBounds(24, 172, 79, 22);
		recommendPanel.add(keyGenre);
		
		JComboBox comboRecBox = new JComboBox();
		comboRecBox.setModel(new DefaultComboBoxModel(new String[] {"SF", "Historical","Romance", "IT", "Medicine", "Music", "Visual Arts", "Language Learning"}));
		comboRecBox.setBounds(114, 174, 120, 27);
		recommendPanel.add(comboRecBox);
		
		/*---------------------------------------------------------------------
		  -----------------MAKING THE PANELS FOR THE TABLE (DESIGN)------------
		  ---------------------------------------------------------------------*/
		
		JPanel tablePanel_3 = new JPanel();
		tablePanel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Database", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tablePanel_3.setBounds(291, 26, 600, 406);
		recommendPanel.add(tablePanel_3);
		tablePanel_3.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 16, 588, 383);
		tablePanel_3.add(scrollPane_3);
		
		/*---------------------------------------------------------------------
		  -----------------MAKING RECOMMEND TABLE------------------------------
		  ---------------------------------------------------------------------*/
		
		recTable = new JTable();
		recTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null ,"", "", null, null},
				},
				new String[] {
					"ID" ,"Book Name", "Author","Genre", "Year", "Status", "NO.Copies"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class ,String.class, String.class, String.class, Integer.class, String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false ,false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			
			scrollPane_3.setViewportView(recTable);	
			
			/*---------------------------------------------------------------------
			  -----------------IMPLEMENTING RECOMMEND BUTTON-----------------------
			  ---------------------------------------------------------------------*/
			
			JButton recBtn = new JButton("RECOMMEND");
			recommendPanel.add(recBtn);
			
			recBtn.setBounds(54, 244, 140, 30);
			
			recBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sql = "SELECT ID, [Book Name], Author, Genre, Year, Status, [NO.Copies] FROM bookTable WHERE (Genre LIKE (?) AND (Keywords LIKE (?) OR Keywords LIKE (?) OR Keywords LIKE (?)))";
					try {
						String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
						Connection conn = DriverManager.getConnection(url);
						System.out.println("Connection SQL Lite good");			
						
						
						PreparedStatement pstmt = conn.prepareStatement(sql);
						
						
						String recGenre;
						recGenre = comboRecBox.getSelectedItem().toString();
						
						pstmt.setString(1, recGenre);
						pstmt.setString(2, "%"+keyField_1.getText()+"%");
						pstmt.setString(3, "%"+keyField_2.getText()+"%");
						pstmt.setString(4, "%"+keyField_3.getText()+"%");
						
						ResultSet rs = pstmt.executeQuery();
						recTable.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
					catch (SQLException ev) {
						JOptionPane.showMessageDialog(null, ev);
					}
					
				}
			});
		
			/*---------------------------------------------------------------------
			  -----------------DESIGNING LENT BOOKS AND INFO PANEL ----------------
			  ---------------------------------------------------------------------*/
			
			JPanel lendPanel = new JPanel();
			tabbedPane.addTab("Lend Information", null, lendPanel, null);
			
			lendPanel.setLayout(null);
			lendPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));			
			
			JButton getInfoBtn = new JButton("Get Information");			
			getInfoBtn.setBounds(30, 50, 150, 30);
			lendPanel.add(getInfoBtn);			
			
			JButton returnBtn = new JButton("Return Book");
			returnBtn.setBounds(30, 100, 150, 30);
			lendPanel.add(returnBtn);
			
			JLabel returnLabel = new JLabel("Returned Book:");
			returnLabel.setBounds(30, 200, 150, 30);
			lendPanel.add(returnLabel);
			
			
			returnedBookField = new JTextField();
			returnedBookField.setEditable(false);
			returnedBookField.setBounds(30, 230, 200, 30);
			lendPanel.add(returnedBookField);			
			
			/*---------------------------------------------------------------------
			  -----------------MAKING THE PANELS FOR THE TABLE (DESIGN)------------
			  ---------------------------------------------------------------------*/
			
			JPanel tablePanel_4 = new JPanel();
			tablePanel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Info Tabel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			tablePanel_4.setBounds(291, 26, 600, 406);
			lendPanel.add(tablePanel_4);
			tablePanel_4.setLayout(null);
			
			JScrollPane scrollPane_4 = new JScrollPane();
			scrollPane_4.setBounds(6, 16, 588, 383);
			tablePanel_4.add(scrollPane_4);
			
			/*---------------------------------------------------------------------
			  -----------------MAKING INFO TABLE----------------------------------
			  ---------------------------------------------------------------------*/
			
			infoTable = new JTable();
			infoTable.setModel(new DefaultTableModel(
					new Object[][] {
						{null ,"", "", "", null},
					},
					new String[] {
						"PersonID" ,"Book", "Loan Date","Receive Date", "Phone Number"
					}
				) {
					Class[] columnTypes = new Class[] {
						Integer.class ,String.class, String.class, String.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false ,false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				
				scrollPane_4.setViewportView(infoTable);						
				
				/*---------------------------------------------------------------------
				  -----------------GETTING BOOK NAME FIELD BY CLICK ON TABLE-----------
				  ---------------------------------------------------------------------*/
				
				infoTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int Name = infoTable.getSelectedRow();
						TableModel model = infoTable.getModel();
						String value2 = (String) model.getValueAt(Name, 1);
						returnedBookField.setText(value2);
						
					}
				});			
			
			/*---------------------------------------------------------------------
			-----------------IMPLEMENTING GET INFO BUTTON--------------------------
			---------------------------------------------------------------------*/				
				
			getInfoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sql = "SELECT * FROM lendTable";
						try {
							String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
							Connection conn = DriverManager.getConnection(url);
							System.out.println("Connection SQL Lite good");			
							
							
							PreparedStatement pstmt = conn.prepareStatement(sql);
							ResultSet rs = pstmt.executeQuery();
							infoTable.setModel(DbUtils.resultSetToTableModel(rs));
							
						}
						catch (SQLException ev) {
							JOptionPane.showMessageDialog(null, ev);
						}
						
					}
			});						
			
			/*---------------------------------------------------------------------
			-----------------IMPLEMENTING RETURN BOOK BUTTON-----------------------
			---------------------------------------------------------------------*/				
				
			returnBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sql = "DELETE FROM lendTable WHERE Book LIKE (?)";
						String sql2 = "UPDATE bookTable SET [NO.Copies] = [NO.Copies] + 1 WHERE [Book Name] LIKE (?)";
						try {
							String url = "jdbc:sqlite:F://Databases SQLite/booksDB.db";
							Connection conn = DriverManager.getConnection(url);
							System.out.println("Connection SQL Lite good");			
							
							
							PreparedStatement pstmt = conn.prepareStatement(sql);
							PreparedStatement pstmt2 = conn.prepareStatement(sql2);
							
							pstmt.setString(1, returnedBookField.getText());
							pstmt2.setString(1, returnedBookField.getText());
							
							pstmt.executeUpdate();
							pstmt2.executeUpdate();							
							
							conn.close();
							
						}
						catch (SQLException ev) {
							JOptionPane.showMessageDialog(null, ev);
						}
						
					}
			});			
				
				
				
				
	}
}
