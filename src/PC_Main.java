import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class PC_Main {
	
	public static void main(String[] args) {
		
		//���̵� ��� �Է�â
		String userid = JOptionPane.showInputDialog("Oracle ���̵� �Է����ּ���.");
		String userpw = JOptionPane.showInputDialog("Oracle ��й�ȣ�� �Է����ּ���.");
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
	        PC_GUI.conn = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", userid ,userpw); 
	        
	        //�α����� �Ǹ� �޼���â �����ֱ�
	        JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
	        System.out.println("�����ͺ��̽� ����");
	        
	        //�Է�â���� �Է��� ���̵�� ��й�ȣ�� PC_GUI�� ������
	        PC_GUI gui = new PC_GUI(userid, userpw);
	        gui.setVisible(true);
		}catch(Exception x) {
			x.printStackTrace();
			JOptionPane.showMessageDialog(null, "�α��� �� ���� �����ϴ�. ���̵�� ��й�ȣ�� �ٽ� �Է����ּ���,");
		}
	}
}
