package Budget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Point;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLoginEnter;
	private JPasswordField textFieldPasswordEnter;
	public static LoginWindow frame;
	private ResultSet rs;
	private String userID = "";
	private Budget bgui;

	public static void main(String[] args) {
		frame = new LoginWindow();
	}

	public LoginWindow() {
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(
						LoginWindow.class
								.getResource("/Budget/sources/purse_icon.png")));
		setTitle("MyWallet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 223, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		init();
	}

	public void init() {
		JLabel labelLogin = new JLabel("Login:");
		labelLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		labelLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldLoginEnter = new JTextField();
		textFieldLoginEnter.setColumns(10);

		textFieldPasswordEnter = new JPasswordField();

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterWindow window = new RegisterWindow();
				window.setVisible(true);
			}
		});

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager
							.getConnection("jdbc:mysql://localhost/test2?"
									+ "user=root&password=");
					PreparedStatement ps = con
							.prepareStatement("SELECT * FROM `Data` WHERE `Login`= ? AND `Password`= ?");
					ps.setString(1, textFieldLoginEnter.getText());
					ps.setString(2, String.valueOf(textFieldPasswordEnter
							.getPassword()));
					rs = ps.executeQuery();
					if (rs.next()) {
						System.out.println("Success! " + "Login: "
								+ rs.getString("Login") + " / Password: "
								+ rs.getString("Password") + " / ID: "
								+ rs.getString("id"));
						String id = rs.getString("id");
						userID = id;
					} else {
						JOptionPane.showMessageDialog(null,
								"Wrong login or password", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					bgui = new Budget(userID);
					frame.dispose();
				} catch (SQLException e) {
					Logger.getLogger(LoginWindow.class.getName()).log(
							Level.SEVERE, null, e);
					System.out.println("B³¹d SQLException!");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																labelLogin,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblPassword,
																GroupLayout.DEFAULT_SIZE,
																87,
																Short.MAX_VALUE)
														.addComponent(
																btnRegister,
																GroupLayout.DEFAULT_SIZE,
																87,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																textFieldLoginEnter,
																GroupLayout.DEFAULT_SIZE,
																80,
																Short.MAX_VALUE)
														.addComponent(
																textFieldPasswordEnter,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																80,
																Short.MAX_VALUE)
														.addComponent(
																btnEnter,
																GroupLayout.DEFAULT_SIZE,
																82,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																textFieldLoginEnter,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																labelLogin))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblPassword,
																GroupLayout.PREFERRED_SIZE,
																26,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																textFieldPasswordEnter,
																GroupLayout.PREFERRED_SIZE,
																22,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnRegister)
														.addComponent(btnEnter))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public String getUserID() {
		System.out.println("LoginWindow.getUserID() = " + this.userID);
		return userID;
	}
}
