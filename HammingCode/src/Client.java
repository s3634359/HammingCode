import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField inputText;
	static JTextArea mainArea;

	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	static String ipAddress;
	private JLabel lblHammingCodeDetection;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();	// Create new frame for client.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		try {
			s = new Socket("127.0.0.1", 1201);	// Request a connection to Server through TCP
												// Because both programs run in the same device, ip address should be
												// 127.0.0.1 or localhost
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = ""; 					// Create a string for taking messages from the server
			while (!msgin.equals("exit")) { 	
				msgin = din.readUTF();			// Store messages in msgin string.
				mainArea.setText(mainArea.getText().trim()+"\n Server:                   "+msgin); // Display input from server
			}
		} catch (Exception e) {		
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnCheck = new JButton("Check");			// Create a button for transmission.
		btnCheck.addActionListener(new ActionListener() { 	// Set an action when the check button is clicked
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgout = "";
					msgout = inputText.getText().trim();
					dout.writeUTF(msgout);
				}
				catch(Exception e) {
					
				}
			}
		});
		btnCheck.setBackground(new Color(51, 255, 102));
		btnCheck.setBounds(325, 216, 97, 23);
		contentPane.add(btnCheck);
		
		mainArea = new JTextArea();		// Create a text area for output
		mainArea.setBounds(23, 51, 388, 141);
		contentPane.add(mainArea);
		
		inputText = new JTextField();		// Create a text field for input
		inputText.setBounds(23, 217, 281, 23);
		contentPane.add(inputText);
		inputText.setColumns(10);
		
		lblHammingCodeDetection = new JLabel("HAMMING CODE CLIENT");	// Create a label for description
		lblHammingCodeDetection.setForeground(Color.RED);
		lblHammingCodeDetection.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHammingCodeDetection.setBounds(23, 10, 400, 36);
		contentPane.add(lblHammingCodeDetection);
	}
	
}
