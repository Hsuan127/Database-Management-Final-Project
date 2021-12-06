import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.JCheckBox;
//import javax.swing.JCheckBox;

//import LeaveApproval.TableModel;

public class LeaveApproval implements ActionListener {

	private JFrame frame;
	private String[] columns = { "假單編號", "員工", "請假起始時間", "請假結束時間", "假別", "理由","代理人","核准流程","核准狀態"};
	private List<Object> rows = null;
	private AbstractTableModel model;
	private JTable table;
	private Container contentPane;// 容器,存放表格等東西。
	private Toolkit tools = Toolkit.getDefaultToolkit();
	private Dimension size = tools.getScreenSize();
	private int width = size.width/ 2;
	private int height = size.height / 2;
	private Connection conn;
	private Statement stat;
	private String approve_status;
	private String leave_status;
	private JTextField empid;
	private Statement psCheck1;
	private Statement psCheck2;
	private Statement psCheck3;
	private Statement psCheck4;
	private Statement psCheck5;
	private String man_id;
    String id = Login.id;
    static String startDate;
	static String endDate;
	static String leaveType;
	static String leaveReason;
	static String agentID;
	static String approve;
	static String leave;
	static String empID;
	static String empName;
//	 private model = new DefaultTableModel(data, columnNames);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveApproval window = new LeaveApproval();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LeaveApproval() {
		frame = new JFrame("員工請假系統-請假審核");
		rows = new ArrayList<Object>();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setSize(962, 407);// 設定大小
		frame.setLocation(width / 2, height / 2);// 設定位置
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 設定關閉時動作
		frame.getContentPane().setLayout(new BorderLayout());// 設定佈局方式
		
		//frame.setVisible(true);
		
		contentPane = frame.getContentPane();
		model = new TableModel();// TableModel是自定義的類。使用自定義的類是為了更好的做自己想要做的事,比如更新資料庫==。
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// 給表格加入滾動條,橫向不加,縱向加
		contentPane.add(scroll, BorderLayout.CENTER);
		
		
		
		//JCheckBox chbox = new JCheckBox("New check box");
		//scroll.setRowHeaderView(chbox);
		// 新建一個面板,放在表格頂端,用來放置搜尋條件
		JPanel searPanel = new JPanel();
		searPanel.setBackground(SystemColor.inactiveCaptionBorder);
//		JButton searBtn = new JButton("搜尋");
//		searBtn.addActionListener(this);
//		searBtn.setActionCommand("load");

//		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
//		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		
//		JLabel IDLabel = new JLabel("再次輸入主管ID:");
//		searPanel.add(IDLabel);
//		
//		empid = new JTextField();
//		searPanel.add(empid);
//		empid.setColumns(10);
//		searPanel.add(searBtn);
		contentPane.add(searPanel, BorderLayout.NORTH);
		// 新建一個面板,放在表格底部,用來放置按鈕
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(SystemColor.inactiveCaptionBorder);
		JButton agreeBtn = new JButton("審核");
		//JButton disagreeBtn = new JButton("不同意");
		JButton exitBtn = new JButton("返回");
		agreeBtn.addActionListener(this);
		agreeBtn.setActionCommand("agree");
		//disagreeBtn.addActionListener(this);
		//disagreeBtn.setActionCommand("disagree");
		exitBtn.addActionListener(this);
		exitBtn.setActionCommand("exit");
		btnPanel.add(agreeBtn);
		//btnPanel.add(disagreeBtn);
		btnPanel.add(exitBtn);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		try {
			   
			   Connection conn = DriverManager.getConnection( 
					   "jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
			   
			   stat = conn.createStatement();
			  
			   String sql;
			  // 如果沒有搜尋條件
			    sql = "select leave_ID, emp_ID,start_date_time,end_date_time, leaveType_name,leave_reason,agent_ID,approve_status,leave_status"
			    		+ " from LEAVE,LeaveType where LEAVE.leaveType_ID=LeaveType.leaveType_ID and manager_ID = "+ id;
			    //System.out.println(sql);
			    
			   
				    ResultSet set = stat.executeQuery(sql);
				  
				    rows.clear();
				    table.addMouseListener(new java.awt.event.MouseAdapter() {
		                public void mouseClicked(java.awt.event.MouseEvent evt) {
		                    tableDisplayMouseClicked(evt);
		                }

						private void tableDisplayMouseClicked(MouseEvent evt) {
							// TODO Auto-generated method stub
							try {
								empID = table.getValueAt(table.getSelectedRow(), 1).toString();
//								System.out.println(empID);
								Connection conn1 = DriverManager.getConnection( 
										   "jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
								psCheck2 = conn1.createStatement();
								final String getEmpName = "SELECT emp_name FROM EMPLOYEE WHERE EMPLOYEE.emp_ID = '"+empID+"'";
								ResultSet set2 = psCheck2.executeQuery(getEmpName);
//								System.out.println(set2);
								startDate = table.getValueAt(table.getSelectedRow(), 2).toString();
								endDate = table.getValueAt(table.getSelectedRow(), 3).toString();
								leaveType = table.getValueAt(table.getSelectedRow(), 4).toString();
								leaveReason = table.getValueAt(table.getSelectedRow(), 5).toString();
								agentID = table.getValueAt(table.getSelectedRow(), 6).toString();
								approve = table.getValueAt(table.getSelectedRow(), 7).toString();
								leave = table.getValueAt(table.getSelectedRow(), 8).toString();
								set2.next();
								empName = set2.getString("emp_name");
								System.out.println(empName);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
		            });

//				    table.addMouseListener(new MouseListener(){
//						@Override
//						public void mouseClicked(MouseEvent e) {
//							// TODO Auto-generated method stub	 
//							
//							
//						
////							new LeaveApprovalDetail();
//							
//						}
//						@Override
//						public void mousePressed(MouseEvent e) {
//							// TODO Auto-generated method stub
//							
//						}
//
//						@Override
//						public void mouseReleased(MouseEvent e) {
//							// TODO Auto-generated method stub
//							
//						}
//
//						@Override
//						public void mouseEntered(MouseEvent e) {
//							// TODO Auto-generated method stub
//							
//						}
//
//						@Override
//						public void mouseExited(MouseEvent e) {
//							// TODO Auto-generated method stub
//							
//						}
//					});
				  
				    while (set.next()) {
				    	//String approve_status;
						int a_status = set.getInt("approve_status");
						if (a_status == 0){
							approve_status= "一階主管審核中";
						}
						else if (a_status == 1) {
							approve_status= "二階主管審核中";
						}
						else if (a_status == 2) {
							approve_status= "審核完畢";
						}
						
						//String leave_status;
						int l_status = set.getInt("leave_status");
						if (l_status == 0)
						{
							leave_status = "等待中";
						}
						else if (l_status == 1) {
							leave_status= "接受";
						}
						else if (l_status == 2) {
							leave_status= "拒絕";
						}	
				    
						//JCheckBox checkBox1 = new JCheckBox("checkBox1");
					    Object[] obj = new Object[] {set.getInt("leave_ID"),set.getString("emp_ID"),
					    set.getString("start_date_time"), set.getString("end_date_time"),
						set.getString("leaveType_name"), set.getString("leave_reason"), 
				//		set.getString("leaveType_ID"), set.getString("leave_reason"), 
						set.getString("agent_ID"),approve_status,leave_status};
					    rows.add(obj);
					    
					    
					    
				    }
			   
			   
			   model.fireTableChanged(null);// 告訴表格資料已更新,讓他重新整理顯示
			  } catch (SQLException sqle) {
			   System.err.println(sqle.getMessage());
			  } 
		finally {// 記得關閉連線
			  try {
			   if (conn != null)
			    conn.close();
			   if (stat != null)
			    stat.close();
			  } catch (SQLException e1) {
			   System.err.println("關閉連線失敗");
			  }
			  
			  }
	}
	
	class TableModel extends AbstractTableModel {
		/**
		* 獲取資料列數
		*/
		@Override
		public int getColumnCount() {
			return columns.length;
		}
		/**
		* 獲取資料行數
		*/
		@Override
		public int getRowCount() {
			return rows.size();
		}
		/**
		* 獲取某個單元格資料
		*/
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return ((Object[]) rows.get(rowIndex))[columnIndex];
		}
		// ***************** 前面三個方法是必須實現的,後面的方法看情況********/
		/**
		* 獲取表頭(如果沒有這個方法則表頭是ABCD...這樣子)
		*/
		@Override
		public String getColumnName(int column) {
			return columns[column];
		}
		/**
		* 自定義某行或某列或某個單元格是否可以被編輯,如果沒有這個方法的話就是全都不能被編輯
		*/
		@Override
		public boolean isCellEditable(int row, int col) {
		// 此處是 如果是第一列(即id)則不可編輯,其餘可以編輯
		//	if (col == 0)
		//		return false;
			// disable edit
			return false;
		}
		/**
		* 修改單元格的值。如果要編輯單元格的話就要繼承這個方法
		*/
//		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		// 把舊的值取出,如果更新了就把它存入資料庫中去,如是第四行不是整數則返回
//			Object old_val = ((Object[]) rows.get(rowIndex))[columnIndex];
//			if (old_val.toString().equals(aValue.toString()))
//				return;
//			updateToDB(aValue, rowIndex, columnIndex);
//			((Object[]) rows.get(rowIndex))[columnIndex] = aValue;
//			model.fireTableCellUpdated(rowIndex, columnIndex);// 更新表格中的資料
//			
//		}
		/**
		* 有了這一行,就不用怕‘分數’這一列會被輸入別的字元了。
		*/
//		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
//		}
		/** 把更新的內容存入資料庫 */
//		private void updateToDB(Object value, int rowIndex, int columnIndex) {
//			int id = (Integer) ((Object[]) rows.get(rowIndex))[0];// 被髮動那一行的ID
//			// 找出被改動的欄位
//			System.out.println(columnIndex);
//			String colName = "";
//			switch (columnIndex) {
//			case 1:
//				colName = "Type";
//			break;
//			case 2:
//				colName = "Expense";
//			break;
//			case 3:
//				colName = "Date";
//			break;
//			case 4:
//				colName = "Detail";
//			break;
//			default:
//				break;
//			}
//			String sql = "update LEAVE set " + colName + " = '" + value
//			+ "' where id = " + id;
//			System.out.println(sql);
//			try {
//			conn = DriverManager.getConnection( 
//					"jdbc:mysql://140.119.19.73:9306/TG10?useUnicode=true&characterEncoding=utf8","TG10","3suhvg");
//				//	"jdbc:mysql://localhost:3506/test?serverTimezone=GMT","root","li570211"); 
//			
//			stat = conn.createStatement();
//			stat.executeUpdate(sql);
//			} catch (SQLException e) {
//			System.err.println("更新失敗,確保資料不要太長");
//			e.printStackTrace();
//			} finally {
//			if (conn != null)
//			try {
//			conn.close();
//			if (stat != null)
//			stat.close();
//			} catch (SQLException e) {
//			System.err.println("資料庫關閉異常");
//			}
//			}
//		}

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if (e.getActionCommand().equals("load")) {
			  
		int pl = 0;
		//System.out.println("agree");
		if (e.getActionCommand().equals("agree")) {
//			int selectedRowIndex = table.getSelectedRow();
//			int id = (Integer) ((Object[]) rows.get(selectedRowIndex))[0];
//			System.out.println("220");
				   int result=JOptionPane.showConfirmDialog(frame,
			                 "同意請假嗎?",
			                 "確認訊息",
			                 JOptionPane.YES_NO_OPTION,
			                 JOptionPane.WARNING_MESSAGE);
//				   try {
//					   Connection conn = DriverManager.getConnection( 
//							   "jdbc:sqlserver://localhost;databaseName=leaveSystem;integratedSecurity=true;","user=" + "lydia","user=" + "lydia1212");
//					   
//				final String queryCheck1 = "SELECT LeaveType.permission_level FROM LeaveType WHERE leaveType_ID = '"+ id + "'"; 
//				   
//				   
//				   final PreparedStatement psCheck = conn.prepareStatement(queryCheck1);
//				   final ResultSet resultSet = psCheck.executeQuery();
//				   resultSet.getString(1);
//				  
//				   if (resultSet.getString("LeaveType.permission_level") == "1") {
//					   pl = 1;
//					   System.out.println("1");
//				   }
//				   else {
//					   pl = 2;
//				   }
//				   
//				   }catch(SQLException f) {
//					   
//				   }
			//同意
			if (result==JOptionPane.YES_OPTION) {       
			    int selectedRowIndex1 = table.getSelectedRow();
			    int id1 = (Integer) ((Object[]) rows.get(selectedRowIndex1))[0];
			   
			   // rows.remove(selectedRowIndex);
			   //String sql = "UPDATE from LEAVE where ID = '" + id + "'";
			  
			    try {
			    	
			 // stat = conn.createStatement();
				Connection conn = DriverManager.getConnection( 
						 "jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
				stat = conn.createStatement();
				final String queryCheck1 = "SELECT permission_level FROM LEAVE WHERE LEAVE.leave_ID = '"+id1+"'"; 
				
				   
				//System.out.println(queryCheck1);
				
				//final PreparedStatement psCheck = conn.prepareStatement(queryCheck1);
				ResultSet resultSet = stat.executeQuery(queryCheck1);
				/*
				while (resultSet.next()) {
					System.out.println(resultSet.getString("permission_level"));
				}
				*/
			
				resultSet.next();
				
				System.out.println(resultSet.getString("permission_level"));
			//	System.out.println("22");
				   //resultSet.getString(1);
				   if (resultSet.getString("permission_level").equals("1")) {
					   pl = 1;
					   System.out.println("pl=11");
				   }
				   else {
					   pl = 2;
					   System.out.println("pl=22");
				   }
				   
				   //System.out.println("1");
				   
				   }catch(SQLException f) {
					   
				   }
			    
			    if(pl == 1) {
			    	try {
			    		rows.remove(selectedRowIndex1);
			    		Connection conn = DriverManager.getConnection( 
			    				 "jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
			    		
			    		final String queryCheck2 = "UPDATE LEAVE SET approve_status = '2', leave_status='1',manager_ID=NULL WHERE leave_ID = '"+ id1 + "'";
			    		
			    		psCheck1 = conn.createStatement();
			    		psCheck1.executeQuery(queryCheck2);
//			    		rows.remove(selectedRowIndex1);
			    		
			    	}
			    	catch(SQLException f1) {
			    		
			    	}
			    }
			    else if (pl == 2) {
			    	try {
		
			    		rows.remove(selectedRowIndex1);
			    		Connection conn = DriverManager.getConnection( 
			    				"jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;","user=" + "sa","user=" + "Password3210#");
			    		
			    		psCheck2 = conn.createStatement();	
			    		final String queryCheck3 = 
			    				"SELECT manager_ID FROM EMPLOYEE WHERE emp_ID = '"
			    				+ id+"'";
			    		
			    		System.out.println(queryCheck3);
			    		
			    				    		
			    		ResultSet set = psCheck2.executeQuery(queryCheck3);
			    		set.next();
			    		man_id = set.getString("manager_ID");
			    		
			    		System.out.println(man_id);
			    		
//			    		String string2 = "null";
//			    		System.out.println(string2);
			    		
			    		if(man_id == null) {
			    			//rows.remove(selectedRowIndex1);
			    			final String queryCheck4 = 
			    					"UPDATE LEAVE SET approve_status = '2', leave_status='1', manager_ID = NULL WHERE leave_ID = '"+ id1 + "'";
				    		psCheck3 = conn.createStatement();	
				    		
				    		System.out.println(queryCheck4);
				    		
				    		psCheck3.executeQuery(queryCheck4);
			    		}
			    		else {
			    			//rows.remove(selectedRowIndex1);
			    			final String queryCheck5 = 
				    				"UPDATE LEAVE SET LEAVE.approve_status = '1', LEAVE.permission_level ='1', LEAVE.manager_ID = '"+man_id+"' WHERE leave_ID = '"+ id1 + "'";
			    			psCheck4 = conn.createStatement();				    			
			    			System.out.println(queryCheck5);			    			
				    		psCheck4.executeQuery(queryCheck5);
			    		}		    		
//			    		psCheck2.executeQuery(queryCheck3);
			    		
//			    		final String queryCheck3 = 
//			    				"UPDATE LEAVE SET LEAVE.approve_status = '1', LEAVE.permission_level ='1', LEAVE.manager_ID = (SELECT manager_ID FROM EMPLOYEE WHERE emp_ID = '"
//			    				+ empid.getText() +"') WHERE leave_ID = '"+ id1 + "'";
//			    		
//			    	
//			    		psCheck2 = conn.createStatement();
//			    		psCheck2.executeQuery(queryCheck3);
			    		
			    	}catch(SQLException f1) {
			    		
			    	}
			    }
  
			   
			 }
			        
			 
			
			//不同意
			if (result==JOptionPane.NO_OPTION) {       
			    int selectedRowIndex1 = table.getSelectedRow();
			    int id1 = (Integer) ((Object[]) rows.get(selectedRowIndex1))[0];
			   // rows.remove(selectedRowIndex);
			   //String sql = "UPDATE from LEAVE where ID = '" + id + "'";
			  
			    try {
			    	Connection conn = DriverManager.getConnection( 
		    				"jdbc:sqlserver://localhost;databaseName=DBMS_Final;integratedSecurity=true;\",\"user=\" + \"sa\",\"user=\" + \"Password3210#");
		    		
		    		final String queryCheck2 = "UPDATE LEAVE SET approve_status = '2', leave_status='2',manager_ID=NULL WHERE leave_ID = '"+ id1 + "'";
		    		
		    		psCheck1 = conn.createStatement();
		    		psCheck1.executeQuery(queryCheck2);
			    	
				 }
			    
			    catch(SQLException f1) {
			    		
			    	}
			    }
				}
			    
			    
			    model.fireTableChanged(null);
		
		
			  

			  
		  if (e.getActionCommand().equals("exit")) {
			   frame.dispose();
			   // dispose(); 
		  }
		
	

}}
