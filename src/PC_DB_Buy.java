import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class PC_DB_Buy {

   static int PCPrice,pcNum,LaptopPrice,laptopNum,PrinterPrice,printerNum, TotalPCPrice,TotalLaptopPrice,TotalPrinterPrice;
   
   static String PCmodelStore;
   static String LaptopmodelStore;
   static String PrintermodelStore;
   
   public void BuyHistory(String PCmodel, String Laptopmodel, String Printermodel,
         int PCcount, int Laptopcount, int Printercount)
   {
      try {
         if (PCmodel.isEmpty() && Laptopmodel.isEmpty() && Printermodel.isEmpty())
         {
            JOptionPane.showMessageDialog(null, "세개 중에 한개는 입력해 주세요.");
         } else {
                       
            PCPrice = 0;
            pcNum = 0;  
            LaptopPrice = 0;
            laptopNum = 0;
            PrinterPrice = 0;
            printerNum = 0;
            TotalPCPrice = 0;
            TotalLaptopPrice = 0;
            TotalPrinterPrice = 0;
            PC_GUI.TotalPrice = 0;
            if(PCmodel.compareTo("") == 0) {
               PCmodel = "없음";
               PCPrice = 0;
               pcNum = 0;
               PCcount = 0;
            } else {
               String sqlStr = "select price from pc where model = \'" + PCmodel + "\'";
               PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
               ResultSet rs = PC_GUI.stmt.executeQuery(sqlStr);
               rs.next();
               PCPrice = rs.getInt("price");
               pcNum = PCcount;
               rs.close();
            }
            
            if(Laptopmodel.isEmpty()) {
               Laptopmodel = "없음";
               LaptopPrice = 0;
               laptopNum = 0;
               Laptopcount = 0;
            } else {
               String sqlStr = "select price from laptop where model = \'" + Laptopmodel + "\'";
               PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
               ResultSet rs1 = PC_GUI.stmt.executeQuery(sqlStr);
               rs1.next();
               LaptopPrice = rs1.getInt("price");
               laptopNum = Laptopcount;
               rs1.close();
            }
            
            if(Printermodel.isEmpty()) {
                Printermodel = "없음";
                PrinterPrice = 0;
                printerNum = 0;
                Printercount = 0;
             } else {
                String sqlStr = "select price from printer where model = \'" + Printermodel + "\'";
                PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
                ResultSet rs2 = PC_GUI.stmt.executeQuery(sqlStr);
                rs2.next();
                PrinterPrice = rs2.getInt("price");
                printerNum = Printercount;
                rs2.close();
             }
            
            
            TotalPCPrice = PCPrice * pcNum;
            TotalLaptopPrice = LaptopPrice * laptopNum;
            TotalPrinterPrice = PrinterPrice * printerNum;
            
            PC_GUI.TotalPrice = TotalPCPrice + TotalLaptopPrice + TotalPrinterPrice;
            
            PC_GUI.TabFBTextArea.setText(" -PC- \n PCmodel : " + PCmodel 
                  + "    \t개수 : " + PCcount + "\t가격 : $" + TotalPCPrice
                  + "\n\n" + " -Laptop- \n Laptopmodel : " + Laptopmodel
                  + "\t개수 : " + Laptopcount + "\t가격 : $" + TotalLaptopPrice
                  + "\n\n" + " -Printer- \n Printermodel : " + Printermodel
                  + "\t개수 : " + Printercount + "\t가격 : $" + TotalPrinterPrice
                  + "\n\n" + " 총가격 : $" + PC_GUI.TotalPrice + "\n");
            JOptionPane.showMessageDialog(null,  "정상적으로 추가되었습니다.");
            
            PCmodelStore = PCmodel;
            LaptopmodelStore = Laptopmodel;
            PrintermodelStore = Printermodel;
            
         }
      }catch (Exception e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(null, "입력하신 모델이 존재하지 않습니다.");
      }
   }
   
}