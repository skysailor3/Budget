package Budget;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Budget extends JFrame {

	private GroupLayout gl_contentPane;
	private JPanel contentPane;
	private JButton buttonRozchod;
	private JButton buttonPrzychod;
	private JButton historyButtoon;
	private JButton btnStatistics;
	public static JLabel labelStan;
	private JLabel lblStan;
	private JMenuBar menuBar;
	private JMenu mnPanel;
	private JMenuItem mntmWyloguj;
	private JMenuItem mntmZamknij;
	private JMenu mnInfo;
	private JMenuItem mntmAboutMywallet;
	private JMenuItem mntmAboutAuthor;
	private JMenuItem mntmSettings;
	private String userID;
	private final State state;

	public Budget(String userID) throws SQLException{
		this.state = new State(userID);
		setVisible(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Budget.class.getResource("/Budget/sources/purse_icon.png")));
		setTitle("My Wallet");
		initialize();
		function();
		other();
	}

	public void function(){
		buttonPrzychod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					IncomeWindow incomeWindow = new IncomeWindow();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		buttonRozchod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpendingWindow spendingsWindow = new SpendingWindow();
			}
		});
		
		historyButtoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HistoryWindow historyWindow;
				try {
					historyWindow = new HistoryWindow();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		mntmAboutMywallet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This is my portfolio program. Enjoy!","About MyWallet",JOptionPane.PLAIN_MESSAGE,new ImageIcon(Budget.class.getResource("/Budget/sources/purse_icon_small.png")));
			}
		});
		
		mntmZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}

	public void initialize() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 212);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPanel = new JMenu("Panel");
		menuBar.add(mnPanel);
		
		mntmWyloguj = new JMenuItem("Log out");
		mntmWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow newLog = new LoginWindow();
				dispose();
			}
		});
		
		mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingWindow settings = new SettingWindow();
				settings.setVisible(true);
			}
		});
		mnPanel.add(mntmSettings);
		mnPanel.add(mntmWyloguj);
		
		mntmZamknij = new JMenuItem("Exit");
		mnPanel.add(mntmZamknij);
		
		mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		mntmAboutMywallet = new JMenuItem("About MyWallet");
		mnInfo.add(mntmAboutMywallet);
		
		mntmAboutAuthor = new JMenuItem("About Author");
		mntmAboutAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "My name is Arkadiusz Fabiañski, Java Programmer.\n" + "Contact:\n" + "arkadiusz.fabianski@gmail.com", "About author", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnInfo.add(mntmAboutAuthor);
		
		contentPane = new JPanel();
		contentPane.setBorder (new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		labelStan = new JLabel(state.getState());
		labelStan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelStan.setHorizontalAlignment(SwingConstants.LEFT);

		buttonPrzychod = new JButton("Dodaj przych\u00F3d");
		buttonPrzychod.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPrzychod.setIcon(new ImageIcon(Budget.class.getResource("/Budget/sources/plusIcon2.png")));
		buttonPrzychod.setForeground(Color.BLACK);

		buttonRozchod = new JButton("Dodaj wydatek");
		buttonRozchod.setHorizontalAlignment(SwingConstants.LEFT);
		buttonRozchod.setIcon(new ImageIcon(Budget.class.getResource("/Budget/sources/minusIcon2.png")));
		buttonRozchod.setForeground(Color.BLACK);

		lblStan = new JLabel("Stan:");
		lblStan.setFont(new Font("Tahoma", Font.PLAIN, 13));

		historyButtoon = new JButton("Historia");
		historyButtoon.setHorizontalAlignment(SwingConstants.LEFT);
		historyButtoon.setIcon(new ImageIcon(Budget.class.getResource("/Budget/sources/historyIcon2.png")));
		
		btnStatistics = new JButton("Statystyki");
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statistics window = new Statistics();
				window.setVisible(true);
			}
		});
		btnStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		btnStatistics.setIcon(new ImageIcon(Budget.class.getResource("/Budget/sources/statistics.png")));
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblStan, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelStan, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonPrzychod, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addComponent(buttonRozchod, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnStatistics, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(historyButtoon, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStan, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelStan, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonPrzychod, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(historyButtoon, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonRozchod)
						.addComponent(btnStatistics, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(146, Short.MAX_VALUE))
		);
	}
	public void other(){
		contentPane.setLayout(gl_contentPane);	
	}
	
	public String getCurrentUserID(){
		return userID;
	}

}
