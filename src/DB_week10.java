import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DB_week10 implements ActionListener{	//ActionListener�� ����ϱ� ���ؼ��� ����� �޾ƾ���
	//new �����ڸ� ���� ��ü ���� �� �޸� �Ҵ�
	private JFrame frame = new JFrame();
	private JLabel idLabel = new JLabel("���̵�");
	private JLabel pwdLabel = new JLabel("��й�ȣ");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("�α���");
	private JPanel panel = new JPanel();
	
	//oracle DB�� �����ϱ� ���� ������
	private String username = "ID";
	private String password = "PW";
	private static Connection dbTest;	//�����ͺ��̽� ������ ���� ����
	
	public DB_week10() {	//class�Լ��� class��� �����ؾ��Ѵ�
		panel.setLayout(null);	//�����ڰ� ���� ũ�⸦ �����ϴ� null
		//Component���� ��ġ ����
		idLabel.setBounds(20, 10, 60, 30);
		pwdLabel.setBounds(20, 50, 60, 30);
		idInput.setBounds(100, 10, 80, 30);
		pwdInput.setBounds(100, 50, 80, 30);
		loginButton.setBounds(200, 25, 80, 35);
		
		//Button�� ActionListener ����
		loginButton.addActionListener(this);
		
		//Component���� Panel�� �߰�
		panel.add(idLabel);
		panel.add(pwdLabel);
		panel.add(idInput);
		panel.add(pwdInput);
		panel.add(loginButton);
		
		//panel�� Frame�� �߰�
		frame.add(panel);
		
		//frame�� �ʿ��� �ΰ����� ��ҵ�
		frame.setTitle("JDBC Practice 1");	//â�� �̸�
		frame.setSize(320, 130);	//â�� ũ��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//��� ��ư
		frame.setVisible(true);		//ȭ�� ��� ����
		
		//panel�� ��ġ�� �˾Ƴ� �� �ִ� ��ɾ� 
		//(�г��� ���ο� ������ ������ ���� component���� ��ġ ��ǥ ����)
		System.out.println(frame.getContentPane().getSize());
	}
	
	//�α��� ��ư�� ������ �� �����ϴ� �Լ�
	public void actionPerformed(ActionEvent e) {	//�α��� ��ư���� �׼��� �߻��ϸ� �Ѿ���� e��� �����ȿ��� �̶� �߻��� ��ǵ� ���� ������ ����.
		if(e.getSource() == loginButton) {
			username = idInput.getText();
			password = new String(pwdInput.getPassword());
			
			connectDB();
		}
	}
	
	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, password);
			System.out.println("�����ͺ��̽��� ���� �Ǿ����ϴ�.");
		} catch(SQLException e) {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.out.println("SQLException: " + e);
		} catch(Exception e) {
			System.out.println("SQLException: " + e);
		}
	}
	
	public static void main(String[] args) {
		new DB_week10();
	}
}
