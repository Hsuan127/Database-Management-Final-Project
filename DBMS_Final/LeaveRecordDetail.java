import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class LeaveRecordDetail extends JFrame{
	private JFrame frame;
	private Statement stat;
	private Connection conn;
	private Container contentPane;
	String id = Login.id;
	String agentID = LeaveRecord.agentID;
	String startDate = LeaveRecord.startDate;
	String endDate = LeaveRecord.endDate;
	String leaveType = LeaveRecord.leaveType;
	String leaveReason = LeaveRecord.leaveReason;
	String approve = LeaveRecord.approve;
	String leave = LeaveRecord.leave;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveRecordDetail window = new LeaveRecordDetail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LeaveRecordDetail() {
		
		
		frame = new JFrame("請假詳情");
		initialize();
	

	}
	
		private void initialize() {
			frame.setSize(500,350);
			frame.setLocationRelativeTo(null);
//			frame.getContentPane();
			contentPane = frame.getContentPane();
			contentPane.setLayout(null);
			
			JLabel lblEmpID = new JLabel("員工ID:");
			lblEmpID.setBounds(34, 38, 63, 15);
			contentPane.add(lblEmpID);
			
//			JLabel lblEmpDept = new JLabel("員工部門:");
//			lblEmpDept.setBounds(34, 88, 63, 15);
//			frame.getContentPane().add(lblEmpDept);
			
			JLabel lblStartDate = new JLabel("起始時間:");
			lblStartDate.setBounds(34, 63, 63, 15);
			frame.getContentPane().add(lblStartDate);
			
			JLabel lblEndDate = new JLabel("結束時間:");
			lblEndDate.setBounds(34, 88, 63, 15);
			frame.getContentPane().add(lblEndDate);
			
			JLabel lblAgentid = new JLabel("代理人ID:");
			lblAgentid.setBounds(34, 113, 63, 15);
			frame.getContentPane().add(lblAgentid);
			
			JLabel lblLeaveType = new JLabel("假別:");
			lblLeaveType.setBounds(34, 138, 63, 15);
			frame.getContentPane().add(lblLeaveType);
			
			JLabel lblLeaveReason = new JLabel("請假理由:");
			lblLeaveReason.setBounds(34, 163, 63, 15);
			frame.getContentPane().add(lblLeaveReason);
			
			
			
			
//				System.out.println(id);
				JLabel lblempid = new JLabel(id);
				lblempid.setBounds(97, 38, 98, 15);
				frame.getContentPane().add(lblempid);
				
			

				JLabel lblagentid = new JLabel(agentID);
				lblagentid.setBounds(97, 113, 98, 15);
				frame.getContentPane().add(lblagentid);
				
				
				
				JLabel lblApproveStatus = new JLabel("核准流程:");
				lblApproveStatus.setBounds(34, 254, 63, 15);
				frame.getContentPane().add(lblApproveStatus);
				
				JLabel lblLeaveStatus = new JLabel("核准狀態:");
				lblLeaveStatus.setBounds(34, 279, 63, 15);
				frame.getContentPane().add(lblLeaveStatus);
				
				JLabel lblsd = new JLabel(startDate);
				lblsd.setBounds(97, 63, 144, 15);
				frame.getContentPane().add(lblsd);
				
				JLabel lbled = new JLabel(endDate);
				lbled.setBounds(97, 88, 144, 15);
				frame.getContentPane().add(lbled);
				
				JLabel lblLT = new JLabel(leaveType);
				lblLT.setBounds(97, 138, 98, 15);
				frame.getContentPane().add(lblLT);
				
				JLabel lblAS = new JLabel(approve);
				lblAS.setBounds(97, 254, 111, 15);
				frame.getContentPane().add(lblAS);
				
				JLabel lblLS = new JLabel(leave);
				lblLS.setBounds(97, 279, 111, 15);
				frame.getContentPane().add(lblLS);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(97, 163, 379, 79);
//				scrollPane.setVerticalScrollBarPolicy(
//						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//				scrollPane.setHorizontalScrollBarPolicy(
//						JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				frame.getContentPane().add(scrollPane);
				
				JTextArea textArea = new JTextArea(leaveReason);
				textArea.setBackground(SystemColor.control);
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);
				
	
			
				frame.setVisible(true);
			
			}
}

