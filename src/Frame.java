import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(566,398);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCopyTo = new JLabel("Copy To:");
		lblCopyTo.setSize(80,40);
		lblCopyTo.setLocation(10, 21);
		contentPane.add(lblCopyTo);
		
		JRadioButton rdbtnD = new JRadioButton("D:");
		rdbtnD.setBounds(28, 55, 109, 23);
		contentPane.add(rdbtnD);
		
		JRadioButton rdbtnE = new JRadioButton("E:");
		rdbtnE.setBounds(28, 81, 109, 23);
		contentPane.add(rdbtnE);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(420, 46, 124, 40);
		contentPane.add(btnStart);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(5, 111, 539, 23);
		contentPane.add(progressBar);
		
		textField = new JTextField();
		textField.setBounds(194, 56, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDestinationFolder = new JLabel("Destination Folder");
		lblDestinationFolder.setBounds(156, 31, 124, 20);
		contentPane.add(lblDestinationFolder);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(5, 132, 539, 217);
		contentPane.add(textArea);
		
		JLabel lblBhum = new JLabel("BHUM");
		lblBhum.setBounds(138, 59, 46, 14);
		contentPane.add(lblBhum);
	}
}
