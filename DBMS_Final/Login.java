import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	static String id;
	private Statement psCheck;
	private String message;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("員工請假系統");
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(80, 100, 510, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel system = new JLabel("員工請假系統");
		system.setForeground(new Color(0, 0, 0));
		system.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		system.setBounds(156, 44, 166, 49);
		frame.getContentPane().add(system);
		
		JLabel ID = new JLabel("ID:");
		ID.setFont(new Font("新細明體", Font.BOLD, 21));
		ID.setBounds(131, 119, 74, 35);
		frame.getContentPane().add(ID);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("新細明體", Font.BOLD, 21));
		password.setBounds(131, 219, 123, 40);
		frame.getContentPane().add(password);
		
		textField = new JTextField();
		textField.setBounds(131, 158, 216, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(131, 259, 216, 35);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton login = new JButton("登入");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps;
				ResultSet rs;
				
				String userID = textField.getText();
				String password = textField_1.getText();
				id = textField.getText();
				String query = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE.emp_ID = ? AND EMPLOYEE.emp_password = ?";
				try {
					ps = SimpleDataSource.getConnection().prepareStatement(query);
					ps.setString(1, userID);
					ps.setString(2, password);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						new Function();
						frame.dispose();
						try {
						       Connection conn1 = DriverManager.getConnection( 
						            "jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
						          
						          psCheck = conn1.createStatement();
						          final String queryCheck6 = 
						            "SELECT COUNT(leave_ID) as num " + 
						               "FROM LEAVE where manager_id = '"+id+"'";
						          
						          System.out.println(queryCheck6);
						          
						          ResultSet set = psCheck.executeQuery(queryCheck6);
						          
						          System.out.println("L");
						          
						          set.next();
						          String num = set.getString("num");
						          
						          System.out.println("LL");
						          
						          message = "尚有"+num+"筆請假申請未審核";
						          
						          JOptionPane.showMessageDialog(null,message,"未審核",2);
						              
						        
						          
						          //empname1 = set.getString("emp_name");
						          
						          //System.out.println(empname1);
						          
						      }catch(SQLException f2){
						       
						      }
					}
					else {
						JOptionPane.showMessageDialog(null,"請輸入正確的帳號密碼","登入失敗",2);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		login.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		login.setBounds(184, 335, 114, 35);
		frame.getContentPane().add(login);
	}

}
