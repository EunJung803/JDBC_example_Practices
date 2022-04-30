import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JDBC_Connect_PC {

	public void pcSearch(String modelnumber) throws SQLException{ //�� ��ȣ�� ��ȸ 
	      //�𵨿� ���� ���� ������ ����
	      String model = "����";
	      String speed = "0";
	      String ram = "0";
	      String hd = "0.0";
	      String cd = "0.0";
	      String price = "0";
	      
	      try {
	         //��� ����
	         Class.forName("oracle.jdbc.OracleDriver");
	         JDBC_GUI.conn = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "database" ,"1234"); 
	          
	         String sqlStr = "select * from pc where model = \'"+ modelnumber + "\'";
	         JDBC_GUI.stmt = JDBC_GUI.conn.prepareStatement(sqlStr);
	         ResultSet rs = JDBC_GUI.stmt.executeQuery(sqlStr);
	         
	         if (rs.next()) { //������ ��� ������ ����
	            model = rs.getString("model");
	            speed = rs.getString("speed");
	            ram = rs.getString("ram");
	            hd = rs.getString("hd");
	            cd = rs.getNString("cd");
	            price = rs.getString("price");
	            
	            System.out.println(model);
	         }
	         else {
	            JDBC_GUI.TabProductTextArea1.setText('\n' + "model : " + model + '\n' + '\n'
	                  + "speed : " + speed + '\n'
	                  + "ram : " + ram + '\n'
	                  + "hd : " + hd + '\n'
	                  + "cd : " + cd + '\n'
	                  + "price : " + price + '\n');
	            throw new SQLException();
	         }

	         JDBC_GUI.TabProductTextArea1.setText('\n' + "model : " + model + '\n' + '\n'
	               + "speed : " + speed + '\n'
	               + "ram : " + ram + '\n'
	               + "hd : " + hd + '\n'
	               + "cd : " + cd + '\n'
	               + "price : " + price + '\n');
	         rs.close();
	         JDBC_GUI.stmt.close();
	      }
	      catch(SQLException e) {JOptionPane.showMessageDialog(null, "��������, �Է��� �𵨸��� �������� �ʱ� ������ ������ �� �����ϴ�.", "�޼���", JOptionPane.INFORMATION_MESSAGE);}
	      catch(Exception e) {
	         //JOptionPane.showMessageDialog(null, "�볪����, �Է��� �𵨸��� �������� �ʱ� ������ ������ �� �����ϴ�.", "�޼���", JOptionPane.INFORMATION_MESSAGE);
	      }finally {
	         try {
	            if(JDBC_GUI.stmt != null) 
	               JDBC_GUI.stmt.close();
	            if(JDBC_GUI.conn != null) 
	               JDBC_GUI.conn.close();
	         } catch(SQLException e) {   
	         }
	      }
	   }
}