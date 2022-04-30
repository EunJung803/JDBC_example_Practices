
import java.sql.ResultSet;
import java.sql.SQLException;

public class PC_DB_Insert {
   public void Insert() throws SQLException{
      if(PC_DB_Buy.PCmodelStore != "없음") {
         String sqlStr = ("insert into transaction"
               +"(tnumber, tmodel, tcount, tprice)"
               +"values (tnum_seq.nextval," + PC_GUI.PCmodel + ","
               +PC_DB_Buy.pcNum + ",\'" + PC_DB_Buy.TotalPCPrice + "\')");
         PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
         ResultSet rs = PC_GUI.stmt.executeQuery(sqlStr);
         rs.close();
      }
      
      if(PC_DB_Buy.LaptopmodelStore != "없음") {
          String sqlStr = ("insert into transaction"
                +"(tnumber, tmodel, tcount, tprice)"
                +"values (tnum_seq.nextval," + PC_GUI.Laptopmodel + ","
                +PC_DB_Buy.laptopNum + ",\'" + PC_DB_Buy.TotalLaptopPrice + "\')");
          PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
          ResultSet rs = PC_GUI.stmt.executeQuery(sqlStr);
          rs.close();
       }
      
      
      if(PC_DB_Buy.PrintermodelStore != "없음") {
          String sqlStr = ("insert into transaction"
                +"(tnumber, tmodel, tcount, tprice)"
                +"values (tnum_seq.nextval," + PC_GUI.Printermodel + ","
                +PC_DB_Buy.printerNum + ",\'" + PC_DB_Buy.TotalPrinterPrice + "\')");
          PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
          ResultSet rs = PC_GUI.stmt.executeQuery(sqlStr);
          rs.close();
       }
   }
}