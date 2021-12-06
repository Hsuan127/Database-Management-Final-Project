import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
public class LeaveApplication extends JFrame {
	private static final long serialVersionUID = 6294689542092367723L;
	
	JLabel type = new JLabel("假別：");
	JLabel empIDLabel = new JLabel("員工ID：");
	JLabel startdateLabes = new JLabel("請假起始日：");
	JLabel endDateLabel = new JLabel("請假結束日：");
	
	JTextField jttype = new JTextField("",20);
	JTextField jtname = new JTextField("",20);
	JTextField startDate = new JTextField("",10);
	JTextField endDate = new JTextField("",10);
	
	JButton buttonadd = new JButton("增加");
	JButton buttonreturn = new JButton("返回");
	private JTextField agentid;
	private JTextField empid;
	private final JLabel reasonLabel = new JLabel("請假原因:");
	private final JTextArea reason = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveApplication window = new LeaveApplication();
					window.setVisible(true);
					window.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public LeaveApplication() {
		getContentPane().setBackground(new Color(255, 255, 224));
		JPanel jpname = new JPanel();
		JPanel jpexpense = new JPanel();
		jpexpense.setBackground(new Color(255, 255, 224));
		jpexpense.setBounds(-36, 23, 506, 35);
		JPanel jpedetail = new JPanel();
		jpedetail.setBounds(0, 391, 564, 45);
		
		String[] selections = { "病假", "事假", "喪假", "公假", "婚假", "產假","陪產假","生理假" };
		empIDLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		
//		jpname.add(jlname);
//		jpname.add(jtname);
		
		jpexpense.add(empIDLabel);

		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		
		this.setTitle("員工請假系統-請假申請");
		//this.setLocationRelativeTo(null);
	//	getContentPane().setLocation(null);
		
		getContentPane().setLayout(null);
		
		
//		this.add(jpname);
		getContentPane().add(jpexpense);
		
		empid = new JTextField();
		jpexpense.add(empid);
		empid.setColumns(10);
		
		JLabel agentidLabel = new JLabel("代理人ID:");
		agentidLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		jpexpense.add(agentidLabel);
		
		agentid = new JTextField();
		jpexpense.add(agentid);
		agentid.setColumns(10);
		JPanel jpexpenseday = new JPanel();
		jpexpenseday.setBackground(new Color(255, 255, 224));
		jpexpenseday.setBounds(0, 90, 506, 35);
		dateChooser1.register(startDate);
		dateChooser2.register(endDate);
		startdateLabes.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		jpexpenseday.add(startdateLabes);
		jpexpenseday.add(startDate);
		getContentPane().add(jpexpenseday);
		endDateLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		jpexpenseday.add(endDateLabel);
		jpexpenseday.add(endDate);
		getContentPane().add(jpedetail);
		jpedetail.setLayout(null);
		buttonadd.setBounds(181, 10, 77, 23);
		jpedetail.add(buttonadd);
		buttonreturn.setBounds(293, 10, 77, 23);
		jpedetail.add(buttonreturn);

		
			    JComboBox comboBox = new JComboBox(selections);
			    comboBox.setBounds(104, 161, 74, 30);
			    comboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			    getContentPane().add(comboBox);
			    comboBox.setSelectedIndex(-1);
			    type.setBounds(42, 162, 74, 28);
			    type.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			    getContentPane().add(type);
			    reasonLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
			    reasonLabel.setBounds(42, 219, 89, 28);
			    
			    getContentPane().add(reasonLabel);
			    scrollPane.setBounds(123, 219, 347, 136);
			    
			    getContentPane().add(scrollPane);
			    reason.setLineWrap(true);
			    reason.setWrapStyleWord(true);
			    scrollPane.setViewportView(reason);
			    JDialog.setDefaultLookAndFeelDecorated(true);
		
		buttonreturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Window window = new Window();	
				// System.out.println("Exit");
				dispose(); 
			}			
		});
		
		buttonadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(empid.getText().trim().isEmpty()||agentid.getText().trim().isEmpty()||
						startDate.getText().trim().isEmpty()||endDate.getText().trim().isEmpty()||
						reason.getText().trim().isEmpty()||comboBox.getSelectedIndex()==-1) {
//					empIDwarnLabel.setText("請輸入您的員工ID!");
//					agtIDwarnLabel.setText("請輸入您的代理人ID!");
//					SDwarnLabel.setText("請選擇您的起始請假日!");
//					EDwarnLabel.setText("請選擇您的結束請假日!");
//					reason.setText("請填入請假理由!");
//					typewarnLabel.setText("請選擇假別!");
					JOptionPane.showMessageDialog(null, "請填入完整資訊","建立失敗",2);
				}
				
/*				else if(agentid.getText().trim().isEmpty()&&startDate.getText().trim().isEmpty()&&
						endDate.getText().trim().isEmpty()&&reason.getText().trim().isEmpty()&&comboBox.getSelectedIndex()==-1) {
					agtIDwarnLabel.setText("請輸入您的代理人ID!");
					SDwarnLabel.setText("請選擇您的起始請假日!");
					EDwarnLabel.setText("請選擇您的結束請假日!");
					reason.setText("請填入請假理由!");
					typewarnLabel.setText("請選擇假別!");
					JOptionPane.showMessageDialog(null, "請填入完整資訊","建立失敗",2);
				}
				else if(startDate.getText().trim().isEmpty()&&endDate.getText().trim().isEmpty()&&
						reason.getText().trim().isEmpty()&&comboBox.getSelectedIndex()==-1)
					{
					JOptionPane.showMessageDialog(null, "請填入完整資訊","建立失敗",2);
					SDwarnLabel.setText("請選擇您的起始請假日!");
					EDwarnLabel.setText("請選擇您的結束請假日!");
					reason.setText("請填入請假理由!");
					typewarnLabel.setText("請選擇假別!");
				}
				else if(endDate.getText().trim().isEmpty()&&
						reason.getText().trim().isEmpty()&&comboBox.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "請填入完整資訊","建立失敗",2);
					EDwarnLabel.setText("請選擇您的結束請假日!");
					reason.setText("請填入請假理由!");
					typewarnLabel.setText("請選擇假別!");
				}
				else if(comboBox.getSelectedIndex()==-1&&reason.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請填入完整資訊","建立失敗",2);
					typewarnLabel.setText("請選擇假別!");
					reason.setText("請填入請假理由!");
				}
				else if(reason.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請填入完整資訊","建立失敗",2);
					reason.setText("請填入請假理由!");
				}**/
				
				
				else {
				//Add
				int index = comboBox.getSelectedIndex()+1;
				Connection conn = null; 
//				Connection connCk = null;
				Statement stat = null;
				PreparedStatement ps=null;
				String sql = "INSERT INTO LEAVE (start_date_time,end_date_time,leave_reason,leaveType_ID,emp_ID,agent_ID,manager_ID,permission_level)"
						+"SELECT ?,?,?,?,?,?,EMPLOYEE.manager_ID, LeaveType.permission_level from EMPLOYEE,LeaveType where LeaveType.leaveType_ID = "+index+" and EMPLOYEE.emp_ID ="+ empid.getText();
//				stat.execute(sql);		
						
//					"INSERT INTO LEAVE((start_date_time,end_date_time,leave_reason,leaveType_ID,emp_ID,agent_ID,manager_ID)) "
//						+ "values(?,?,?,?,?,?,?)";
//				LeaveApplication user = new LeaveApplication();
//				Optional<LeaveApplication> optionUser = Optional.ofNullable(user);
				
				
//				try{
//					Class.forName("com.mysql.jdbc.Driver");
//					System.out.println("JBDC 加載成功!");
//				}catch(Exception a){
//					System.out.println("JBDC 狗帶!");
//					a.printStackTrace();
//				}
				
				try{
					
					if(startDate.getText().isBlank())
					System.out.println("Before conn");
					conn=DriverManager.getConnection(
							"jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
					ps = conn.prepareStatement(sql);
					System.out.println("After conn");
					final String queryCheck = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE.emp_ID =?";
					final String queryCheck2 = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE.emp_ID =?";
					final PreparedStatement psCheck = conn.prepareStatement(queryCheck);
					final PreparedStatement psCheck2 = conn.prepareStatement(queryCheck2);
					psCheck.setString(1, empid.getText());
					psCheck2.setString(1, agentid.getText());
					final ResultSet resultSet = psCheck.executeQuery();
					final ResultSet resultSet2 = psCheck2.executeQuery();
					if(!resultSet.next()||!resultSet2.next()) {
						JOptionPane.showMessageDialog(null, "員工或代理人ID輸入錯誤", "建立失敗", 2);
						}
					else {
					int type = 0;
					String l_type = comboBox.getSelectedItem().toString();
					if(l_type=="病假") {
						type = 1;
					}
					else if(l_type=="事假") {
						type = 2;				
										}
					else if(l_type=="喪假") {
						type = 3;				
										}
					else if(l_type=="公假") {
						type = 4;				
										}
					else if(l_type=="婚假") {
						type = 5;				
										}
					else if(l_type=="產假") {
						type = 6;				
										}
					else if(l_type=="陪產假") {
						type = 7;				
										}
					else if(l_type=="生理假") {
						type = 8;				
										}
					
					//ps.setString(1,comboBox.getSelectedItem().toString());
					ps.setString(1,startDate.getText());
					ps.setString(2,endDate.getText());
					ps.setString(3,reason.getText());
					ps.setInt(4,type);
					ps.setString(5,empid.getText());
					ps.setString(6,agentid.getText());
					
				
					//System.out.print(ps);

					ps.executeUpdate();
					
					//System.out.println("MySQL 連接成功!");
					//stat = conn.createStatement();
					//stat.executeUpdate(sql);
					//System.out.println("插入數據成功!");
					JOptionPane.showMessageDialog(null,"新增成功","建立成功",-1,new ImageIcon("C:\\Users\\user\\Downloads\\DBMS\\bin\\check.png"));
					}
				
					
				}
				catch (SQLException b){
					b.printStackTrace();
					
				}finally{
					try{
						
						empid.setText("");
						startDate.setText("");
						endDate.setText("");
						agentid.setText("");
						comboBox.setSelectedIndex(-1);
						reason.setText("");
						conn.close();
						System.out.println("MySQL 關閉成功");
					}catch (SQLException c){
						System.out.println("MySQL 關閉失敗 ");
						c.printStackTrace();
					}
					
				}
					
					
		}}	
		}
		
				);
		this.setLocation(400,300);
		this.setSize(520,483);
		this.setVisible(true);
		
	}
}

