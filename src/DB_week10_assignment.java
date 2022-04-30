import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_week10_assignment {
	
	private String username = "database";
	private String passward = "1234";
	private static Connection dbTest;
	
	DB_week10_assignment() {
		connectDB();
	}
	
	private void connectDB() {
	      try {
	          //JDBC Driver Loading
	          Class.forName("oracle.jdbc.OracleDriver");
	          dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "database","1234");
	          System.out.println("김은정님이 데이터베이스에 연결 되었습니다.");
	       } catch (SQLException e) {
	          e.printStackTrace();
	          System.out.println("김은정님이 데이터 베이스 연결에 실패하였습니다.");
	          System.out.println("SQLException:" + e);
	       } catch (Exception e) {
	          System.out.println("Exception:" + e);
	       }
	}
	
	//지금 MODEL 2005 제품을 가지고 있는데, 이보다 더 속도가 빠른 PC나 LAPTOP을 사려고 한다. 어떤 제품들이 있는지 구하시오.
	public void execute_query() throws SQLException{
		String sqlStr = " SELECT model FROM laptop WHERE model != 2005"
				+ " and speed > ANY(SELECT speed FROM laptop WHERE model = 2005)"
				+ " UNION"
				+ " SELECT model FROM pc WHERE model != 2005"
				+ " and speed > ANY(SELECT speed FROM laptop WHERE model = 2005)";

		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			System.out.println("model : " + rs.getString("model"));
		}
		rs.close();
		stmt.close();
	}

	public static void main(String[] args) {
		DB_week10_assignment t1 = new DB_week10_assignment();
		try {
			t1.execute_query();
			dbTest.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException:" + e);
		}
	}
}
