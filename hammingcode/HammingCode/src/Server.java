import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

	private JPanel contentPane;
	private JLabel lblHammingCodeDetection;
	static JTextArea mainArea;

	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	/**
	 * Create the frame.
	 */
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainArea = new JTextArea();
		mainArea.setBounds(23, 56, 388, 182);
		contentPane.add(mainArea);

		lblHammingCodeDetection = new JLabel("AUTO HAMMING CODE SERVER");
		lblHammingCodeDetection.setForeground(Color.BLUE);
		lblHammingCodeDetection.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHammingCodeDetection.setBounds(22, 10, 400, 36);
		contentPane.add(lblHammingCodeDetection);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server(); // Create new frame for server.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		String msgin = "";
		int[] digits = null;
		int errors; // Create int for error counting
		String correctedData = null;
		try {
			ss = new ServerSocket(1201); // Server starts at 1201 port number
			s = ss.accept(); // Server will accepts the connections

			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());

			while (!msgin.equals("exit")) {

				msgin = din.readUTF(); // store input from client in msgin string.
				errors = 0; // initialize error number every time server receives data.

				digits = new int[msgin.length()]; // Create an integer array
				for (int i = 0; i < msgin.length(); i++) {
					digits[i] = msgin.charAt(i) - '0'; // Store each digit into an integer array.
				}
				for (int i = 0; i < digits.length; i++) {
					mainArea.setText(mainArea.getText().trim() + digits[i]);
				}
				mainArea.setText(mainArea.getText().trim() + " is received."); // Display received data.

				if (digits.length == 11) { // Error checking runs when client sends the right data.

					if ((digits[10] + digits[8] + digits[6] + digits[4] + digits[2] + digits[0]) % 2 == 1) {
						dout.writeUTF("An error is detected in R1 and corrected");

						if (digits[10] == 0) {
							digits[10] = 1;
						} else {
							digits[10] = 0;
						}
						errors++; // Count errors;
					} // If an error is detected in R1, notice it and flip R1.

					if ((digits[9] + digits[8] + digits[5] + digits[4] + digits[1] + digits[0]) % 2 == 1) {

						dout.writeUTF("An error is detected in R2 and corrected");

						if (digits[9] == 0) {
							digits[9] = 1;
						} else {
							digits[9] = 0;
						}
						errors++;
					} // If an error is detected in R2, notice it and flip R2.

					if ((digits[7] + digits[6] + digits[5] + digits[4]) % 2 == 1) {
						dout.writeUTF("An error is detected in R3 and corrected");

						if (digits[7] == 0) {
							digits[7] = 1;
						} else {
							digits[7] = 0;
						}
						errors++;
					} // If an error is detected in R3, notice it and flip R3.

					if ((digits[3] + digits[2] + digits[1] + digits[0]) % 2 == 1) {
						dout.writeUTF("An error is detected in R4 and corrected");

						if (digits[3] == 0) {
							digits[3] = 1;
						} else {
							digits[3] = 0;
						}
						errors++;
					} // If an error is detected in R4, notice it and flip R4.

					if (errors == 0) { // If no error is detected, let client know data transmission is successful.
						mainArea.setText(mainArea.getText().trim() + "\n" + "no error is detected in received data.");
						dout.writeUTF("Data is succesfully sent and errors are not found.");	
					} else {
						mainArea.setText( // Display corrected data on server side if any errors are detected.
								mainArea.getText().trim() + "\n" + "Errors are detected and corrected." + "\n");
						mainArea.setText(mainArea.getText().trim() + "\n" + "Corrected data is : ");
						for (int i = 0; i < digits.length; i++) {
							mainArea.setText(mainArea.getText().trim() + digits[i]);
						}
						mainArea.setText(mainArea.getText().trim() + "\n" + "\u00A0");

						StringBuffer sb = new StringBuffer();
						for (int i = 0; i < digits.length; i++) {
							sb.append(digits[i]);
						}
						correctedData = sb.toString(); // store an integer array in a string variable.
						dout.writeUTF("Corrected Data is " + correctedData);
					} // If errors are detected, send corrected data back to client.
				} else {
					dout.writeUTF("Your input is wrong.");
					dout.writeUTF("Enter 11 binary numbers!");
				} // If client's input is wrong, alter it.
			}
		} catch (Exception e) {
		}
	}
}