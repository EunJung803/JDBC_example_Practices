/*
 * 내가 짰는데 GUI가 이상하게 나오는 코드
 */





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JDBC_GUII extends JFrame implements ActionListener {
      private static final long serialVersionUID = 1L;
      
      static Connection conn = null;
      static PreparedStatement stmt = null; 
      
      JPanel MainPanel;   //메인 패널 정의
      JTabbedPane TabbedInfoPanel;   //탭 패널 정의
      
      JLabel TabProductLabel;
      JLabel TabPCLabel;
      JLabel TabLaptopLabel;
      JLabel TabPrinterLabel;
      
      JComboBox<String> TabProductmodelNumberCombobox;
      JComboBox<String> TabPCmodelNumberCombobox;
      JComboBox<String> TabLaptopmodelNumberCombobox;
      JComboBox<String> TabPrintermodelNumberCombobox;
      
      static JTextArea TabProductTextArea;
      static JTextArea TabPCTextArea;
      static JTextArea TabLaptopTextArea;
      static JTextArea TabPrinterTextArea;
      
      JLabel modelInputLabel_1 = new JLabel();
      JTextField modelInput_1 = new JTextField();
      private JButton okButton_1 = new JButton("확인");
      
      JLabel modelInputLabel_2 = new JLabel();
      JTextField modelInput_2 = new JTextField();
      private JButton okButton_2 = new JButton("확인");
      
      JLabel modelInputLabel_3 = new JLabel();
      JTextField modelInput_3 = new JTextField();
      private JButton okButton_3 = new JButton("확인");
      
      JLabel modelInputLabel_4 = new JLabel();
      JTextField modelInput_4 = new JTextField();
      private JButton okButton_4 = new JButton("확인");
      
      String model = "";
      
      public JDBC_GUII() {   //GUI를 실행할때 나오는 메인 화면
         UIManager.put("swing.blodMetal", Boolean.FALSE);
         setTitle("JDBC와 자바 GUI 실습");
         setBounds(100, 20, 540, 380);   //메인 프레임의 크기 정의
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         MainPanel = new JPanel();
         MainPanel.setLayout(null);
         //메인 패널 생성됨
         
         makeComponent();
         
         getContentPane().add(MainPanel, BorderLayout.CENTER);
      }
      
      public void makeComponent() {
         TabbedInfoPanel = new JTabbedPane();   //탭 패널 정의
         JPanel TabbedInfoPanel_Product = new JPanel();
         JPanel TabbedInfoPanel_PC = new JPanel();
         JPanel TabbedInfoPanel_Laptop = new JPanel();
         JPanel TabbedInfoPanel_Printer = new JPanel();
         TabbedInfoPanel_Product.setLayout(null);

         TabbedInfoPanel.addTab("Product",  TabbedInfoPanel_Product);   //탭의 이름, 만든걸 product tab안에 집어넣음
         TabbedInfoPanel.addTab("PC",  TabbedInfoPanel_PC);
         TabbedInfoPanel.addTab("Laptop",  TabbedInfoPanel_Laptop);
         TabbedInfoPanel.addTab("Printer",  TabbedInfoPanel_Printer);
         
         TabbedInfoPanel.setBounds(10, 20, 500, 300);   //탭 패널 크기
         MainPanel.add(TabbedInfoPanel); //메인 패널 안에 탭 인포 패널 추가. 추가해야 메인 패널 안에 탭창이 나온다
         
         //product
         TabProductLabel = new JLabel();
         TabProductLabel.setText("model");   //탭 패널 안에 라벨
         TabProductLabel.setIcon(new ImageIcon(""));
         TabProductLabel.setBounds(20, 0, 80, 80);   //라벨에 대한 크기
         TabbedInfoPanel_Product.add(TabProductLabel);
         
         modelInputLabel_1 = new JLabel();
         modelInputLabel_1.setText("model 입력");
         modelInputLabel_1.setIcon(new ImageIcon(""));
         modelInputLabel_1.setBounds(225, 0, 100, 80);
         TabbedInfoPanel_Product.add(modelInputLabel_1);
         
         modelInput_1 = new JTextField();
         modelInput_1.setBounds(300, 23, 90, 40);
         TabbedInfoPanel_Product.add(modelInput_1);
         
         okButton_1 = new JButton("확인");
         okButton_1.setBounds(400, 23, 80, 40);
         okButton_1.addActionListener(this);
         TabbedInfoPanel_Product.add(okButton_1);

         
         //PC
         TabPCLabel = new JLabel();
         TabPCLabel.setText("model");   //탭 패널 안에 라벨
         TabPCLabel.setIcon(new ImageIcon(""));
         TabPCLabel.setBounds(20, 0, 80, 80);   //라벨에 대한 크기
         TabbedInfoPanel_PC.add(TabPCLabel);
         
         modelInputLabel_2 = new JLabel();
         modelInputLabel_2.setText("model 입력");
         modelInputLabel_2.setIcon(new ImageIcon(""));
         modelInputLabel_2.setBounds(225, 0, 100, 80);
         TabbedInfoPanel_PC.add(modelInputLabel_2);
         
         modelInput_2 = new JTextField();
         modelInput_2.setBounds(300, 23, 90, 40);
         TabbedInfoPanel_PC.add(modelInput_2);
         
         okButton_2 = new JButton("확인");
         okButton_2.setBounds(400, 23, 80, 40);
         okButton_2.addActionListener(this);
         TabbedInfoPanel_PC.add(okButton_2);
         
         //Laptop
         TabLaptopLabel = new JLabel();
         TabLaptopLabel.setText("model");   //탭 패널 안에 라벨
         TabLaptopLabel.setIcon(new ImageIcon(""));
         TabLaptopLabel.setBounds(20, 0, 80, 80);   //라벨에 대한 크기
         TabbedInfoPanel_Laptop.add(TabLaptopLabel);
         
         modelInputLabel_3 = new JLabel();
         modelInputLabel_3.setText("model 입력");
         modelInputLabel_3.setIcon(new ImageIcon(""));
         modelInputLabel_3.setBounds(225, 0, 100, 80);
         TabbedInfoPanel_Laptop.add(modelInputLabel_3);
         
         modelInput_3 = new JTextField();
         modelInput_3.setBounds(300, 23, 90, 40);
         TabbedInfoPanel_Laptop.add(modelInput_3);
         
         okButton_3 = new JButton("확인");
         okButton_3.setBounds(400, 23, 80, 40);
         okButton_3.addActionListener(this);
         TabbedInfoPanel_Laptop.add(okButton_3);
         
         //Printer
         TabPrinterLabel = new JLabel();
         TabPrinterLabel.setText("model");   //탭 패널 안에 라벨
         TabPrinterLabel.setIcon(new ImageIcon(""));
         TabPrinterLabel.setBounds(20, 0, 80, 80);   //라벨에 대한 크기
         TabbedInfoPanel_Printer.add(TabPrinterLabel);
         
         modelInputLabel_4 = new JLabel();
         modelInputLabel_4.setText("model 입력");
         modelInputLabel_4.setIcon(new ImageIcon(""));
         modelInputLabel_4.setBounds(225, 0, 100, 80);
         TabbedInfoPanel_Printer.add(modelInputLabel_4);
         
         modelInput_4 = new JTextField();
         modelInput_4.setBounds(300, 23, 90, 40);
         TabbedInfoPanel_Printer.add(modelInput_4);
         
         okButton_4 = new JButton("확인");
         okButton_4.setBounds(400, 23, 80, 40);
         okButton_4.addActionListener(this);
         TabbedInfoPanel_Printer.add(okButton_4);
         
         //탭 패널 안에 들어갈 콤보박스, 텍스트area,,
         //콤보박스
         TabProductmodelNumberCombobox = new JComboBox<String>();
         TabPCmodelNumberCombobox = new JComboBox<String>();
         TabLaptopmodelNumberCombobox = new JComboBox<String>();
         TabPrintermodelNumberCombobox = new JComboBox<String>();
               
         for (int i = 1; i<10; i++) {
            TabProductmodelNumberCombobox.addItem("100" + i);
            TabPCmodelNumberCombobox.addItem("100" + i);
         }
         TabProductmodelNumberCombobox.addItem("1010");
         TabPCmodelNumberCombobox.addItem("1010");
         for (int i = 1; i<8; i++) {
            TabProductmodelNumberCombobox.addItem("200" + i);
            TabLaptopmodelNumberCombobox.addItem("200" + i);
         }
         for (int i = 1; i<10; i++) {
            TabProductmodelNumberCombobox.addItem("300" + i);
            TabPrintermodelNumberCombobox.addItem("300" + i);
         }
         
         TabProductmodelNumberCombobox.setBounds(80, 20, 130, 40);
         TabbedInfoPanel_Product.add(TabProductmodelNumberCombobox);
         TabProductmodelNumberCombobox.addActionListener(this);
         
         TabPCmodelNumberCombobox.setBounds(80, 20, 130, 40);
         TabbedInfoPanel_PC.add(TabPCmodelNumberCombobox);
         TabPCmodelNumberCombobox.addActionListener(this);
         
         TabLaptopmodelNumberCombobox.setBounds(80, 20, 130, 40);
         TabbedInfoPanel_Laptop.add(TabLaptopmodelNumberCombobox);
         TabLaptopmodelNumberCombobox.addActionListener(this);
         
         TabPrintermodelNumberCombobox.setBounds(80, 20, 130, 40);
         TabbedInfoPanel_Printer.add(TabPrintermodelNumberCombobox);
         TabPrintermodelNumberCombobox.addActionListener(this);
         
         //Text area
         TabProductTextArea = new JTextArea();
         TabPCTextArea = new JTextArea();
         TabLaptopTextArea = new JTextArea();
         TabPrinterTextArea = new JTextArea();
         
         TabProductTextArea.setFont(new Font("굴림", 0, 12));
         TabProductTextArea.setForeground(Color.black);
         TabProductTextArea.setOpaque(true);//투명도
         TabProductTextArea.setBackground(Color.white);
         TabProductTextArea.setBounds(20, 80, 450, 180);
         TabProductTextArea.setBorder(null);
         TabProductTextArea.setLineWrap(true);
         TabProductTextArea.setEditable(false);//수정불가
         TabbedInfoPanel_Product.add(TabProductTextArea);
         
         TabPCTextArea.setFont(new Font("굴림", 0, 12));
         TabPCTextArea.setForeground(Color.black);
         TabPCTextArea.setOpaque(true);//투명도
         TabPCTextArea.setBackground(Color.white);
         TabPCTextArea.setBounds(20, 80, 450, 180);
         TabPCTextArea.setBorder(null);
         TabPCTextArea.setLineWrap(true);
         TabPCTextArea.setEditable(false);//수정불가
         TabbedInfoPanel_PC.add(TabPCTextArea);
         
         TabLaptopTextArea.setFont(new Font("굴림", 0, 12));
         TabLaptopTextArea.setForeground(Color.black);
         TabLaptopTextArea.setOpaque(true);//투명도
         TabLaptopTextArea.setBackground(Color.white);
         TabLaptopTextArea.setBounds(20, 80, 450, 180);
         TabLaptopTextArea.setBorder(null);
         TabLaptopTextArea.setLineWrap(true);
         TabLaptopTextArea.setEditable(false);//수정불가
         TabbedInfoPanel_Laptop.add(TabLaptopTextArea);
         
         TabPrinterTextArea.setFont(new Font("굴림", 0, 12));
         TabPrinterTextArea.setForeground(Color.black);
         TabPrinterTextArea.setOpaque(true);//투명도
         TabPrinterTextArea.setBackground(Color.white);
         TabPrinterTextArea.setBounds(20, 80, 450, 180);
         TabPrinterTextArea.setBorder(null);
         TabPrinterTextArea.setLineWrap(true);
         TabPrinterTextArea.setEditable(false);//수정불가
         TabbedInfoPanel_Printer.add(TabPrinterTextArea);
      }   
      
      public void actionPerformed(ActionEvent e) {
         Object buttonAction = e.getSource();
         
         if(buttonAction == TabProductmodelNumberCombobox) {
            String modelnumber = (String) TabProductmodelNumberCombobox.getSelectedItem();
            JDBC_Connect_Product JCP = new JDBC_Connect_Product();
            //JCP.productSearch(modelnumber);
            System.out.println(modelnumber);
         }else if(e.getSource()==okButton_1) {
               String sqlStr="SELECT model FROM " + (String)TabProductmodelNumberCombobox.getSelectedItem();
               if(model != sqlStr) {
                  JOptionPane.showMessageDialog(null, "김은정님, 입력한 모델명은 존재하지 않기 때문에 구매할 수 없습니다", "메세지", JOptionPane.INFORMATION_MESSAGE);
               }
               model = modelInput_1.getText();
               JDBC_Connect_Product JCP = new JDBC_Connect_Product();
               //JCP.productSearch(model);
               System.out.println(model);
         }
         
         if(buttonAction == TabPCmodelNumberCombobox) {
               String modelnumber = (String) TabPCmodelNumberCombobox.getSelectedItem();
               JDBC_Connect_PC JCP = new JDBC_Connect_PC();
               //JCP.pcSearch(modelnumber);
               System.out.println(modelnumber);
            }else if(e.getSource()==okButton_2) {
                  String sqlStr="SELECT model FROM " + (String)TabProductmodelNumberCombobox.getSelectedItem();
                  if(model != sqlStr) {
                     JOptionPane.showMessageDialog(null, "김은정님, 입력한 모델명은 존재하지 않기 때문에 구매할 수 없습니다", "메세지", JOptionPane.INFORMATION_MESSAGE);
                  }
                  model = modelInput_2.getText();
                  JDBC_Connect_PC JCP = new JDBC_Connect_PC();
                  //JCP.pcSearch(model);
                  System.out.println(model);
            }
         
         
         if(buttonAction == TabLaptopmodelNumberCombobox) {
               String modelnumber = (String) TabLaptopmodelNumberCombobox.getSelectedItem();
               JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop();
               //JCP.laptopSearch(modelnumber);
               System.out.println(modelnumber);
            }else if(e.getSource()==okButton_3) {                     
                  String sqlStr="SELECT model FROM " + (String)TabProductmodelNumberCombobox.getSelectedItem();
                  if(model != sqlStr) {
                     JOptionPane.showMessageDialog(null, "김은정님, 입력한 모델명은 존재하지 않기 때문에 구매할 수 없습니다", "메세지", JOptionPane.INFORMATION_MESSAGE);
                  }
                  model = modelInput_3.getText();
                  JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop();
                  //JCP.laptopSearch(model);
                  System.out.println(model);
            }
         
         if(buttonAction == TabPrintermodelNumberCombobox) {
               String modelnumber = (String) TabPrintermodelNumberCombobox.getSelectedItem();
               JDBC_Connect_Printer JCP = new JDBC_Connect_Printer();
               //JCP.printerSearch(modelnumber);
               System.out.println(modelnumber);
            }else if(e.getSource()==okButton_4) {
                  String sqlStr="SELECT model FROM " + (String)TabProductmodelNumberCombobox.getSelectedItem();
                  if(model != sqlStr) {
                     JOptionPane.showMessageDialog(null, "김은정님, 입력한 모델명은 존재하지 않기 때문에 구매할 수 없습니다", "메세지", JOptionPane.INFORMATION_MESSAGE);
                  }
                  model = modelInput_4.getText();
                  JDBC_Connect_Printer JCP = new JDBC_Connect_Printer();
                  //JCP.printerSearch(model);
                  System.out.println(model);
            }
      }

}