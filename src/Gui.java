import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Gui {

	private JFrame frmCiscoAclTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmCiscoAclTo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private static File selectedFile;
	static FileReader fr;
	private static String path;
	private static void filepath(File selectedFile){
		
		 path = selectedFile.getPath();
	}
	
	private void initialize() {
		frmCiscoAclTo = new JFrame();
		frmCiscoAclTo.setTitle("Cisco ACL to Juniper Konverter");
		frmCiscoAclTo.setBounds(100, 100, 450, 300);
		frmCiscoAclTo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCiscoAclTo.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(49, 169, 372, 92);
		frmCiscoAclTo.getContentPane().add(lblNewLabel);
		
		JButton btnDurchsuchen = new JButton("Durchsuchen");
		btnDurchsuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showDialog(null, "Datei auswählen");
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		         selectedFile = fileChooser.getSelectedFile();
		         filepath(selectedFile);
		          
		     
		  			lblNewLabel.setText(String.valueOf("Die Datei " + selectedFile.getName() + " wurde geladen"));
		        }
			}
		});
		
		JButton btnNewButton = new JButton("Umwandeln");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				
				
				filepath(selectedFile);
				 try {
					fr = new FileReader(path);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					Input.convertieren();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				lblNewLabel.setText(String.valueOf("Die Datei befindet sich unter " + Input.home + "/juniper.txt"));
			}
			
		});
		
		
		
		frmCiscoAclTo.getContentPane().add(btnDurchsuchen);
		btnDurchsuchen.setBounds(310, 36, 126, 25);
		frmCiscoAclTo.getContentPane().add(btnDurchsuchen);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Cisco ASA ACL  als TXT auswählen:");
		lblNewLabel_1.setBounds(49, 41, 258, 15);
		frmCiscoAclTo.getContentPane().add(lblNewLabel_1);
		
		
		
		btnNewButton.setBounds(151, 106, 174, 25);
		frmCiscoAclTo.getContentPane().add(btnNewButton);
	}
	
}
