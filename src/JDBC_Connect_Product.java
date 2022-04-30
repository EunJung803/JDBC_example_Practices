import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JDBC_Connect_Product {

   public void productSearch(String modelnumber) throws SQLException{ //모델 번호로 조회 
      //모델에 대한 정보 저장할 변수
      String maker = "없음";
      String model = modelnumber;
      String type = "없음";
      
      try {
         //디비 연결
         Class.forName("oracle.jdbc.OracleDriver");
         JDBC_GUI.conn = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "database" ,"1234"); 
          
         String sqlStr = "select * from product where model = \'"+ modelnumber + "\'";
         JDBC_GUI.stmt = JDBC_GUI.conn.prepareStatement(sqlStr);
         ResultSet rs = JDBC_GUI.stmt.executeQuery(sqlStr);
         
         if (rs.next()) { //쿼리문 결과 변수에 저장
            model = rs.getString("model");
            maker = rs.getNString("maker");
            type = rs.getNString("type");
            
            System.out.println(model);
         }
         else {
            JDBC_GUI.TabProductTextArea.setText('\n' + "model : " + model + '\n' + '\n'
                  + "maker : " + maker + '\n'
                  + "type : " + type + '\n');
            throw new SQLException();
         }

         
         JDBC_GUI.TabProductTextArea.setText('\n' + "model : " + model + '\n' + '\n'
               + "maker : " + maker + '\n'
               + "type : " + type + '\n');
         rs.close();
         JDBC_GUI.stmt.close();
         
      }

      catch(Exception e) {
         JOptionPane.showMessageDialog(null, "김은정님, 입력한 모델명은 존재하지 않기 때문에 구매할 수 없습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
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
