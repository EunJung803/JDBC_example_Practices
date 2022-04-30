import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_week9 {
	
	private String username = "database";
	private String passward = "1234";
	private static Connection dbTest;
	
	DB_week9() {
		connectDB();
	}
	
	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, passward);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException:" + e);
		} catch(Exception e) {
			System.out.println("SQLException:" + e.toString());
		}
	}
	
	public void execute_query() throws SQLException{
		String sqlStr = " SELECT price FROM pc WHERE price-100 >= 2000";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		
		System.out.println("PRICE");
		System.out.println("-------------------");
		
		while(rs.next()) {
			System.out.println(rs.getInt("price")-100);
		}
		rs.close();
		stmt.close();
	}
	
	public static void main(String[] args) {
		DB_week9 t1 = new DB_week9();
		try {
			t1.execute_query();
			dbTest.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException:" + e);
		}
	}
}
