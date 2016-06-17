package Budget;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class SpendingWindow extends JFrame {

	private static JPanel contentPane;
	private static JTextField amountTextField;
	public Spending spending;
	private JLabel lblKwota;
	private JLabel lblKategoria;
	private JComboBox comboBox;
	private JTextField textField;

	public SpendingWindow() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("Wydatek");
		initialize();
	}
	public void initialize(){
	
		setBounds(100, 100, 230, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		amountTextField = new JTextField();
		amountTextField.setColumns(10);
		
		JButton buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double amount = 0.0;
				try {
					NumberFormat format = NumberFormat
							.getNumberInstance(Locale.FRANCE);
					String t = amountTextField.getText();
					amount = format.parse(t).doubleValue();
				spending = new Spending(amount);
				String desc = textField.getText();
				String selectedCategory = comboBox.getSelectedItem().toString();
				historyOfSpendings = new History(amount, desc, "Wydatki", selectedCategory);
				dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"You are typing text. It has to be number.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		lblKwota = new JLabel("Kwota:");
		
		lblKategoria = new JLabel("Kategoria");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Jedzenie", "Op\u0142aty", "Odzie\u017C", "Edukacja", "Inne"}));
		
		JLabel labelOpis = new JLabel("Opis");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonSave, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelOpis)
								.addComponent(lblKwota)
								.addComponent(lblKategoria))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(amountTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
								.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.TRAILING, 0, 102, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKwota))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKategoria)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelOpis)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(buttonSave)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
