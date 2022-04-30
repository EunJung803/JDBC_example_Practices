import java.sql.ResultSet;

public class PC_DB_CheckPanel {
   public void Search(String modelname) {
      try {
	         String test = "";
	         String sqlStr = "select * from " + modelname;
	         PC_GUI.stmt = PC_GUI.conn.prepareStatement(sqlStr);
	         ResultSet rs = PC_GUI.stmt.executeQuery(sqlStr);
	         if(modelname == "PC") {
	        	 for (int i = 0; rs.next(); i++) {
		               test += rs.getString("model") + "        ";
		               test += rs.getInt("speed") + "          ";
		               test += rs.getInt("ram") + "        ";
		               test += rs.getFloat("hd") + "        ";
		               test += rs.getString("cd") + "      ";
		               test += "$" + rs.getInt("price") + "      ";
		               test += "\n";
	        	 }
         
            PC_GUI.TabCheckTextArea
            		.setText("model      "
			                  + "speed      "
			                  + "ram         "
			                  + "hd         "
			                  + "cd                        "
			                  + "price"
			                  + "\n"
			                  + "----------------------------------------------------------------\n"
			                  + test);
            }
            
	        else if(modelname == "Laptop") {
	           for (int i = 0; rs.next(); i++) 
	           {
	               test += rs.getString("model") + "        ";
	               test += rs.getInt("speed") + "          ";
	               test += rs.getInt("ram") + "        ";
	               test += rs.getFloat("hd") + "        ";
	               test += rs.getFloat("screen") + "                        ";
	               test += "$" + rs.getInt("price") + "      ";
	               test += "\n";
	            }
	           
	           PC_GUI.TabCheckTextArea.setText("model      "
	                     + "speed      "
	                     + "ram         "
	                     + "hd         "
	                     + "screen                     "
	                     + "price"
	                     + "\n"
	                     + "----------------------------------------------------------------\n"
	                     + test);
	         }
	         
	        else if(modelname == "Printer") {
	           for (int i = 0; rs.next(); i++) 
	           {
	              test += rs.getString("model") + "        ";
	               test += rs.getString("color") + "          ";
	               test += rs.getString("type") + "        ";
	               test += "$" + rs.getInt("price") + "      ";
	               test += "\n";
	            }
	           
	           PC_GUI.TabCheckTextArea.setText("model      "
	                     + "color                          "
	                     + "type                           "
	                     + "price"
	                     + "\n"
	                     + "----------------------------------------------------------------\n"
	                     + test);
	         }   
       } catch(Exception e) {
      	 e.printStackTrace();
       }
   }
}