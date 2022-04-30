import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JDBC_Connect_Printer {

	   public void printerSearch(String modelnumber) throws SQLException{ //�� ��ȣ�� ��ȸ 
		      //�𵨿� ���� ���� ������ ����
		      String model = "����";
		      String color = "����";
		      String type = "����";
		      String price = "0";
		      
		      try {
		         //��� ����
		         Class.forName("oracle.jdbc.OracleDriver");
		         JDBC_GUI.conn = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "database" ,"1234"); 
		          
		         String sqlStr = "select * from printer where model = \'"+ modelnumber + "\'";
		         JDBC_GUI.stmt = JDBC_GUI.conn.prepareStatement(sqlStr);
		         ResultSet rs = JDBC_GUI.stmt.executeQuery(sqlStr);
		         
		         if (rs.next()) { //������ ��� ������ ����
		            model = rs.getString("model");
		            color = rs.getNString("color");
		            type = rs.getNString("type");
		            price = rs.getString("price");
		            
		            System.out.println(model);
		         }
		         else {
		            JDBC_GUI.TabProductTextArea3.setText('\n' + "model : " + model + '\n' + '\n'
		                  + "color : " + color + '\n'
		                  + "type : " + type + '\n'
		                  + "price : " + price + '\n');
		            throw new SQLException();
		         }

		         JDBC_GUI.TabProductTextArea3.setText('\n' + "model : " + model + '\n' + '\n'
		               + "color : " + color + '\n'
		               + "type : " + type + '\n'
		               + "price : " + price + '\n');
		         rs.close();
		         JDBC_GUI.stmt.close();
		      }

		      catch(Exception e) {
		         JOptionPane.showMessageDialog(null, "��������, �Է��� �𵨸��� �������� �ʱ� ������ ������ �� �����ϴ�.", "�޼���", JOptionPane.INFORMATION_MESSAGE);
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
