package Budget;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class IncomeWindow{

	private static JPanel contentPane;
	private static JTextField amountTextField;
	private JLabel lblNewLabel;
	private JLabel label;
	private JComboBox comboBox;
	private Income i = new Income();
	private JLabel lblOpis;
	private JTextField describtionTextField;
	private JButton buttonSave;
	private GroupLayout gl_contentPane;
	private static JFrame f;
	private Double amount = 0.0;

	public IncomeWindow() throws SQLException {
		f = new JFrame();
		f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		f.setTitle("Przych\u00F3d");
		init();
		action();
		other();
	}

	public void action() throws SQLException {
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getAndFormatAmount();
					String desc = describtionTextField.getText();
					String selectedCategory = comboBox.getSelectedItem()
							.toString();
					Class.forName("com.mysql.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/operacje?"
						      + "user=root&password=");
					PreparedStatement ps = connect.prepareStatement("INSERT INTO `usersoperations` (`id_klienta`, `kwota`, `opis`, `typ`, `kategoria`) VALUES (?, ?, ?, ?, ?)");
					int id = Integer.valueOf(LoginWindow.frame.getUserID());
					ps.setInt(1, id);
					ps.setDouble(2, amount);
					ps.setString(3, desc);
					ps.setString(4, "Przychód");
					ps.setString(5, selectedCategory);
					ps.executeUpdate();
					i.addNewIncome(amount);
					f.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}

			
		});

	}

	public void init() {
		f.setBounds(100, 100, 230, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);

		amountTextField = new JTextField();
		amountTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c== KeyEvent.VK_DELETE || c==KeyEvent.VK_COMMA || c==KeyEvent.VK_PERIOD)){
					f.getToolkit().beep();
					e.consume();
				}
			}
		});

		buttonSave = new JButton("Save");

		lblNewLabel = new JLabel("Kwota");

		label = new JLabel("Kategoria");

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "Praca",
				"Sprzeda¿", "Inne" }));

		lblOpis = new JLabel("Opis");

		describtionTextField = new JTextField();
		describtionTextField.setColumns(10);
	}
	
	public void getAndFormatAmount() {
		String t = amountTextField.getText().replace( ',', '.' );
		Double d = Double.valueOf(t);
		DecimalFormat df = new DecimalFormat("#####0.00");
	    DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
	    dfs.setDecimalSeparator('.');
	    df.setDecimalFormatSymbols(dfs);
		System.out.println("Amount added: " + df.format(d));
		String f = df.format(d);
		amount = Double.valueOf(f);
	}

	public void other() {
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(label)
						.addComponent(lblOpis))
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(buttonSave)
							.addComponent(describtionTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOpis)
						.addComponent(describtionTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(buttonSave)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
