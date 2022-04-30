import java.sql.ResultSet;

public class PC_DB_BuyList {
   
   public void SumPrice() {
      try {
         String test = "";
         String sqlStr="select * from transaction order by tnumber desc";
         PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
         ResultSet rs = PC_GUI.stmt.executeQuery(sqlStr);
            for(int i=0; rs.next(); i++) {
               test += "  ";
               test += rs.getString("tnumber")+"      \t";
               test += rs.getInt("tmodel") + "      \t";
               test += rs.getInt("tcount") + "      \t";
               test += "$" + rs.getInt("tprice");
               test += "\n";
               
            PC_GUI.TabBITextTArea.setText("  "+"tnumber\t"
                                 + "tmodel  \t"
                                 + "tcount  \t"
                                 + "tprice"
                                 + "\n"
                                 + "-------------------------------------------------------------\n"
                                 +test);
         int STP=0;
         String TotalPrice = "";
         String sqlStr2 = "select sum(tprice) from transaction";
         PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr2);
         ResultSet rs2 = PC_GUI.stmt.executeQuery(sqlStr2);
         rs2.next();
         STP += rs2.getInt("sum(tprice)");
         TotalPrice += STP;
         PC_GUI.SMLabel.setText("$" + TotalPrice);
         rs2.close();
         }
      rs.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

}