import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DB_week12 implements ActionListener{
	   private JFrame frame = new JFrame();
	   private JLabel idLabel=new JLabel("���̵�");
	   private JLabel pwdLabel=new JLabel("��й�ȣ");
	   private JTextField idInput=new JTextField();
	   private JPasswordField pwdInput=new JPasswordField();
	   private JButton loginButton=new JButton("�α���");
	   private JPanel panel=new JPanel();
	   
	   private String username="ID";
	   private String password="PW";
	   private static Connection dbTest;
	   
	   private JTextArea check_area = new JTextArea();
	   private JComboBox<String> check_box = new JComboBox<String>();
	   
	   private JTextField modelInput = new JTextField();
	   private JButton buyButton = new JButton("����");
	   private JComboBox<Integer> num = new JComboBox<Integer>();
	   
	   String model="";
	   int price = 0;
	   int quantity = 0;
	   
	   private void PCstore() {
	      frame.setVisible(true);
	      frame = new JFrame();
	      panel = new JPanel();
	      
	      panel.setFont(new Font("�ʱ�ü", 1, 12));
	      panel.setBorder(new TitledBorder("��ȸ"));
	      panel.setBounds(380, 80, 490, 280);
	      panel.setLayout(null);
	      
	      check_box.addItem("PC");
	      check_box.addItem("Laptop");
	      check_box.addItem("Printer");
      
	      check_area.setBorder(new LineBorder(Color.gray, 2));
	      check_area.setEditable(false);
	      
	      JScrollPane scroll=new JScrollPane();
	      scroll.setViewportView(check_area);      
	      
	      check_box.setBounds(20,40,70,30);
	      scroll.setBounds(10,80,360,170);      
	      check_box.addActionListener(this);
	      
	      modelInput.setBounds(100, 40, 70, 30);
	      num.setBounds(170, 40, 60, 30);
	      
	      for(int i=1; i<11 ;i++) {
	         num.addItem(i);
	      }
	      
	      buyButton.setBounds(300, 40, 60, 35);
	      buyButton.addActionListener(this);
	      
	      panel.add(check_box);
	      panel.add(scroll);
	      
	      panel.add(modelInput);
	      panel.add(num);
	      panel.add(buyButton);
	      
	      frame.add(panel);
	      
	      frame.setTitle("PC Store");
	      frame.setSize(400, 300);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	      
	   }
	   
	   public DB_week12() {
	      panel.setLayout(null);
	      idLabel.setBounds(20,10,60,30);
	      pwdLabel.setBounds(20,50,60,30);
	      idInput.setBounds(100,10,80,30);
	      pwdInput.setBounds(100,50,80,30);
	      loginButton.setBounds(200,25,80,35);

	      loginButton.addActionListener(this);
	      
	      
	      panel.add(idLabel);
	      panel.add(pwdLabel);
	      panel.add(idInput);
	      panel.add(pwdInput);
	      panel.add(loginButton);
	      
	      frame.add(panel);
	      
	      
	      frame.setTitle("PC Store");
	      frame.setSize(320,130);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	      
	   }
	   
	   public void actionPerformed(ActionEvent e) {
	      if (e.getSource()==loginButton) {
	         username = idInput.getText();
	         password = new String(pwdInput.getPassword());
	         connectDB();
	      }else if(e.getSource()==check_box) {
	         try {
	            showTable();
	         }catch(SQLException se) {
	            se.printStackTrace();
	         }
	      }else if(e.getSource()==buyButton) {
	         model = modelInput.getText();
	         quantity =(int)num.getSelectedIndex() + 1;
	         
	         try {
	            insertItem(model,quantity);
	         }catch(SQLException e1) {
	            e1.printStackTrace();
	         }
	      }
	   }
	   
	   private void insertItem(String model, int quantity) throws SQLException{
	      String sqlStr="SELECT model FROM " + (String)check_box.getSelectedItem();
	      
	      if(model != sqlStr) {
		      JOptionPane.showMessageDialog(null, "��������, �Է��� �𵨸� " + model + "�� �������� �ʱ� ������ ������ �� �����ϴ�", "�޼���", JOptionPane.INFORMATION_MESSAGE);
	      }
	      else {
	    	  sqlStr = "SELECT price FROM " + (String)check_box.getSelectedItem() + " WHERE model =" + model;
		      PreparedStatement stmt=dbTest.prepareStatement(sqlStr);
		      ResultSet rs = stmt.executeQuery();
		      if(rs.next()) {
		         price= rs.getInt("price");
		         sqlStr="INSERT into Transaction (tnumber, tmodel, tcount, tprice) " +
		               "VALUES (tnum_seq.NEXTVAL" + "," + model + "," + quantity + "," + price*quantity + ")";
		         stmt = dbTest.prepareStatement(sqlStr);
		         stmt.executeUpdate();
		         
		         JOptionPane.showMessageDialog(null, "�� " + model + "��/��" + quantity+"�� �����Ͽ����ϴ�.", "�޼���", JOptionPane.INFORMATION_MESSAGE);   
		      }
		      rs.close();
		      stmt.close();
	      }
	   }
	   
	   private void showTable() throws SQLException{
	      String specification = "";
	      
	      String sqlStr = "SELECT count(column_name) num from cols where table_name = '"+
	                  ((String)check_box.getSelectedItem()).toUpperCase()+"'";
	      PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
	      ResultSet rs=stmt.executeQuery();
	      
	      rs.next();
	      int number = rs.getInt("num");
	      String[] tables = new String[number];
	      
	      sqlStr = "select column_name from cols where table_name = '"+
	            ((String)check_box.getSelectedItem()).toUpperCase()+"'";
	      stmt = dbTest.prepareStatement(sqlStr);
	      rs=stmt.executeQuery();
	      
	      for(number=0; rs.next(); number++) {
	         tables[number] = rs.getString("column_name");
	         specification += tables[number] + '\t';
	      }
	      
	      for (specification += '\n'; number>0; number--) {
	         specification += "-------------------";
	      }
	      
	      specification += "\n";
	      
	      sqlStr = "SELECT * from "+
	            (String)check_box.getSelectedItem();
	      stmt = dbTest.prepareStatement(sqlStr);
	      rs=stmt.executeQuery();
	      
	      for(number=0; rs.next(); number++) {
	         for(int i=0; i<tables.length; i++) {
	            specification +=rs.getString(tables[i]) + "\t";
	         }
	         specification += '\n';
	      }
	      check_area.setText(specification);
	      
	      rs.close();
	      stmt.close();
	      }


	   private void connectDB(){
		      try { //JDBC Driver Loading
		         Class.forName("oracle.jdbc.OracleDriver");
		          dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username,password);
		          System.out.println("���������� �����ͺ��̽��� ����Ǿ����ϴ�.");
		          PCstore();
		      } catch (SQLException e) {
		    	  e.printStackTrace();
		          System.out.println("������ ���̽� ���ῡ �����Ͽ����ϴ�.");
		          System.out.println("SQLException:" + e);
		       } catch (Exception e) {
		          System.out.println("Exception:" + e);
		       }
	   }   
	   
	   public static void main(String[] args) {
	      // TODO Auto-generated method stub
	      new DB_week12();
	   }

	}

