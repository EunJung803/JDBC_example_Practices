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
	          System.out.println("���������� �����ͺ��̽��� ���� �Ǿ����ϴ�.");
	       } catch (SQLException e) {
	          e.printStackTrace();
	          System.out.println("���������� ������ ���̽� ���ῡ �����Ͽ����ϴ�.");
	          System.out.println("SQLException:" + e);
	       } catch (Exception e) {
	          System.out.println("Exception:" + e);
	       }
	}
	
	//���� MODEL 2005 ��ǰ�� ������ �ִµ�, �̺��� �� �ӵ��� ���� PC�� LAPTOP�� ����� �Ѵ�. � ��ǰ���� �ִ��� ���Ͻÿ�.
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
