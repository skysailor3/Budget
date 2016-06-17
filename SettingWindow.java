package Budget;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class SettingWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnPolish;
	private JButton btnEnglish;
	private JButton btnGerman;
	private JPanel buttonsPanel;
	private final ButtonGroup GroupButtons = new ButtonGroup();
	private final ButtonGroup GroupCheckBox = new ButtonGroup();
	static private ResourceBundle rb = ResourceBundle.getBundle("Budget.sources.lang_pl_PL");
	private static SettingWindow frame;
	
	public SettingWindow() {
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SettingWindow.class.getResource("/Budget/sources/purse_icon_small.png")));
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 153);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new TitledBorder(null, rb.getString("language"), TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addGap(62))
		);
		
		btnPolish = new JButton("");
		GroupButtons.add(btnPolish);
		btnPolish.setIcon(new ImageIcon(SettingWindow.class.getResource("/Budget/sources/polish_check_icon.png")));
		btnPolish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setProperFlagIcon("pl");
			}
		});
		
		btnEnglish = new JButton("");
		btnEnglish.setIcon(new ImageIcon(SettingWindow.class.getResource("/Budget/sources/english_flag_icon2.png")));
		GroupButtons.add(btnEnglish);
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setProperFlagIcon("en");
			}
		});
		
		btnGerman = new JButton("");
		btnGerman.setIcon(new ImageIcon(SettingWindow.class.getResource("/Budget/sources/german_flag_icon2.png")));
		GroupButtons.add(btnGerman);
		btnGerman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setProperFlagIcon("de");
			}
		});
		
		GroupLayout gl_ButtonsPanel = new GroupLayout(buttonsPanel);
		gl_ButtonsPanel.setHorizontalGroup(
			gl_ButtonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ButtonsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPolish)
					.addGap(18)
					.addComponent(btnEnglish)
					.addGap(18)
					.addComponent(btnGerman)
					.addContainerGap(200, Short.MAX_VALUE))
		);
		gl_ButtonsPanel.setVerticalGroup(
			gl_ButtonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ButtonsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ButtonsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGerman)
						.addComponent(btnEnglish)
						.addComponent(btnPolish))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		buttonsPanel.setLayout(gl_ButtonsPanel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setProperFlagIcon(String l) {
		String polishLangOFF = "/Budget/sources/polish_flag_icon2.png";
		String polishLangON = "/Budget/sources/polish_check_icon.png";
		String englishLangOFF = "/Budget/sources/english_flag_icon2.png";
		String englishLangON = "/Budget/sources/english_check_icon.png";
		String germanLangOFF = "/Budget/sources/german_flag_icon2.png";
		String germanLangON = "/Budget/sources/german_check_icon.png";

		if (l == "pl") {
			btnPolish.setIcon(new ImageIcon(SettingWindow.class
					.getResource(polishLangON)));
			btnEnglish.setIcon(new ImageIcon(SettingWindow.class
					.getResource(englishLangOFF)));
			btnGerman.setIcon(new ImageIcon(SettingWindow.class
					.getResource(germanLangOFF)));
			rb = ResourceBundle.getBundle("Budget.sources.lang_pl_PL");
			System.out.println(rb.getString("language"));
			buttonsPanel.revalidate();
			buttonsPanel.repaint();
		} else if (l == "en") {
			btnPolish.setIcon(new ImageIcon(SettingWindow.class
					.getResource(polishLangOFF)));
			btnEnglish.setIcon(new ImageIcon(SettingWindow.class
					.getResource(englishLangON)));
			btnGerman.setIcon(new ImageIcon(SettingWindow.class
					.getResource(germanLangOFF)));
			rb = ResourceBundle.getBundle("Budget.sources.lang_en_EN");
			System.out.println(rb.getString("language"));
			buttonsPanel.revalidate();
			buttonsPanel.repaint();
		} else if (l == "de") {
			btnPolish.setIcon(new ImageIcon(SettingWindow.class
					.getResource(polishLangOFF)));
			btnEnglish.setIcon(new ImageIcon(SettingWindow.class
					.getResource(englishLangOFF)));
			btnGerman.setIcon(new ImageIcon(SettingWindow.class
					.getResource(germanLangON)));
		}
	}

}
