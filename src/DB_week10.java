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

public class DB_week10 implements ActionListener{	//ActionListener를 사용하기 위해서는 상속을 받아야함
	//new 생성자를 통해 객체 생성 및 메모리 할당
	private JFrame frame = new JFrame();
	private JLabel idLabel = new JLabel("아이디");
	private JLabel pwdLabel = new JLabel("비밀번호");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("로그인");
	private JPanel panel = new JPanel();
	
	//oracle DB에 접속하기 위한 변수들
	private String username = "ID";
	private String password = "PW";
	private static Connection dbTest;	//데이터베이스 연결을 위한 변수
	
	public DB_week10() {	//class함수는 class명과 동일해야한다
		panel.setLayout(null);	//개발자가 직접 크기를 조정하는 null
		//Component들의 위치 지정
		idLabel.setBounds(20, 10, 60, 30);
		pwdLabel.setBounds(20, 50, 60, 30);
		idInput.setBounds(100, 10, 80, 30);
		pwdInput.setBounds(100, 50, 80, 30);
		loginButton.setBounds(200, 25, 80, 35);
		
		//Button에 ActionListener 연결
		loginButton.addActionListener(this);
		
		//Component들을 Panel에 추가
		panel.add(idLabel);
		panel.add(pwdLabel);
		panel.add(idInput);
		panel.add(pwdInput);
		panel.add(loginButton);
		
		//panel을 Frame에 추가
		frame.add(panel);
		
		//frame에 필요한 부가적인 요소들
		frame.setTitle("JDBC Practice 1");	//창의 이름
		frame.setSize(320, 130);	//창의 크기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//취소 버튼
		frame.setVisible(true);		//화면 출력 여부
		
		//panel의 수치를 알아낼 수 있는 명령어 
		//(패널의 가로와 세로의 정보를 토대로 component들의 위치 좌표 설정)
		System.out.println(frame.getContentPane().getSize());
	}
	
	//로그인 버튼을 눌렀을 때 동작하는 함수
	public void actionPerformed(ActionEvent e) {	//로그인 버튼에서 액션이 발생하면 넘어오고 e라는 변수안에는 이때 발생한 사건데 대한 정보가 담긴다.
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
			System.out.println("데이터베이스에 연결 되었습니다.");
		} catch(SQLException e) {
			System.out.println("데이터베이스 연결에 실패하였습니다.");
			System.out.println("SQLException: " + e);
		} catch(Exception e) {
			System.out.println("SQLException: " + e);
		}
	}
	
	public static void main(String[] args) {
		new DB_week10();
	}
}
