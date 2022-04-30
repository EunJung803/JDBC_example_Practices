/*
 * 노나 도움 받은 코드
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class JDBC_GUI extends JFrame implements ActionListener{
   private static final long serialVersionUID = 1L;
   
   static Connection conn = null;
   static PreparedStatement stmt = null; 
   
   JPanel MainPanel; //메인 패널 
   JTabbedPane TabbedInfoPanel; //탭 패널
   
   //----------------------Product 패널-----------------------//
   JLabel TabProductLabel; //'모델' 라벨 
   JComboBox<String> TabProductmodelNumberCombobox; //콤보 박스 
   static JTextArea TabProductTextArea; //텍스트 보여주기
   static JTextArea TabProductTextArea1 ;
   static JTextArea TabProductTextArea2;
   static JTextArea TabProductTextArea3;
  
      //product패널에 추가 (model입력 라벨, 적는 칸, 확인 버튼
   JLabel getmodelInputLabel;
   JTextField modelInput = new JTextField(); //모델 번호 입력할 것 
   JButton okayButton = new JButton("확인"); //확인버튼
   JTextField modelInput1 = new JTextField();JTextField modelInput2 = new JTextField();JTextField modelInput3 = new JTextField();  
   JButton okayButton1 = new JButton("확인");JButton okayButton2 = new JButton("확인");JButton okayButton3 = new JButton("확인");
 //---------------------------------------------------------//
   //PC, Laptop, Printer패널 위한 변수들//
   JComboBox<String> TabPCmodelCombobox; //콤보 박스 
   JComboBox<String> TabLaptopmodelCombobox;
   JComboBox<String> TabPrintermodelCombobox;
   


   
   public JDBC_GUI() {
      UIManager.put("swing.blodMetal", Boolean.FALSE);
      setTitle("실습");
      setBounds(100, 20, 540, 380);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      MainPanel = new JPanel();  //메인패널 생성
      MainPanel.setLayout(null);
      //메인패널생성됨
      
      makeComponent();
      
      getContentPane().add(MainPanel, BorderLayout.CENTER);
   }
   
   public void makeComponent() {
    //---------------------------패널-----------------------------//
      TabbedInfoPanel = new JTabbedPane();
      
      JPanel TabbedInfoPanel_Product = new JPanel(); TabbedInfoPanel_Product.setLayout(null);
      //추
      JPanel TabPanel_PC = new JPanel(); TabPanel_PC.setLayout(null);
      JPanel TabPanel_Laptop = new JPanel(); TabPanel_Laptop.setLayout(null);
      JPanel TabPanel_Printer = new JPanel(); TabPanel_Printer.setLayout(null);
      
      //만든거 product tab안에 집어넣음
      TabbedInfoPanel.addTab("Product",  TabbedInfoPanel_Product);
      TabbedInfoPanel.addTab("PC",  TabPanel_PC);
      TabbedInfoPanel.addTab("Laptop",  TabPanel_Laptop);
      TabbedInfoPanel.addTab("Printer",  TabPanel_Printer);
      
      TabbedInfoPanel.setBounds(10, 20, 500, 300);
      
      MainPanel.add(TabbedInfoPanel); //메인패널안에 탭인포패널 추가. 그래야지 탭창 나옴
    //------------------------------------------------------------//
      
    //---------------------Product 패널-----------------------------//
      
      //탭패널 안에 라벨 (model이라 적힌 부분)
      TabProductLabel = new JLabel();
      TabProductLabel.setText("model");
      TabProductLabel.setIcon(new ImageIcon(""));
      TabProductLabel.setBounds(20, 0, 80, 80);
      TabbedInfoPanel_Product.add(TabProductLabel);
      JLabel TabProductLabel1 = new JLabel();
      TabProductLabel1.setText("model");
      TabProductLabel1.setIcon(new ImageIcon(""));
      TabProductLabel1.setBounds(20, 0, 80, 80);
      TabPanel_PC.add(TabProductLabel1);
      JLabel TabProductLabel2 = new JLabel();
      TabProductLabel2.setText("model");
      TabProductLabel2.setIcon(new ImageIcon(""));
      TabProductLabel2.setBounds(20, 0, 80, 80);
      TabPanel_Laptop.add(TabProductLabel2);
      JLabel TabProductLabel3 = new JLabel();
      TabProductLabel3.setText("model");
      TabProductLabel3.setIcon(new ImageIcon(""));
      TabProductLabel3.setBounds(20, 0, 80, 80);
      TabPanel_Printer.add(TabProductLabel3);
  
      
      //탭패널 안에 들어갈 콤보박스, 텍스트area,,
      //콤보박스
      TabProductmodelNumberCombobox = new JComboBox<String>();
            
      for (int i = 1; i<10; i++) {
         TabProductmodelNumberCombobox.addItem("100" + i);
      }
      TabProductmodelNumberCombobox.addItem("1010");
      for (int i = 1; i<9; i++) {
         TabProductmodelNumberCombobox.addItem("200" + i);
      }
      for (int i = 1; i<7; i++) {
         TabProductmodelNumberCombobox.addItem("300" + i);
      }
      
      TabProductmodelNumberCombobox.setBounds(80, 20, 130, 40);
      TabbedInfoPanel_Product.add(TabProductmodelNumberCombobox);
      TabProductmodelNumberCombobox.addActionListener(this);
      
      //아개빡쳐
      TabPCmodelCombobox = new JComboBox<String>();
      TabLaptopmodelCombobox = new JComboBox<String>();
      TabPrintermodelCombobox = new JComboBox<String>();
      for (int i = 1; i<10; i++) {
         TabPCmodelCombobox.addItem("100" + i);
      }
      TabPCmodelCombobox.addItem("1010");
      for (int i = 1; i<9; i++) {
         TabLaptopmodelCombobox.addItem("200" + i);
      }
      for (int i = 1; i<7; i++) {
         TabPrintermodelCombobox.addItem("300" + i);
      }
      TabPCmodelCombobox.setBounds(80, 20, 130, 40);
      TabLaptopmodelCombobox.setBounds(80, 20, 130, 40);
      TabPrintermodelCombobox.setBounds(80, 20, 130, 40);
      
      TabPanel_PC.add(TabPCmodelCombobox);
      TabPCmodelCombobox.addActionListener(this);
      TabPanel_Laptop.add(TabLaptopmodelCombobox);
      TabLaptopmodelCombobox.addActionListener(this);
      TabPanel_Printer.add(TabPrintermodelCombobox);
      TabPrintermodelCombobox.addActionListener(this);
      /////////////////////
      
      
      //Text area
      TabProductTextArea = new JTextArea();
      TabProductTextArea1 = new JTextArea();
      TabProductTextArea2 = new JTextArea();
      TabProductTextArea3 = new JTextArea();
      TabProductTextArea.setFont(new Font("굴림", 0, 12));TabProductTextArea1.setFont(new Font("굴림", 0, 12));TabProductTextArea2.setFont(new Font("굴림", 0, 12));TabProductTextArea3.setFont(new Font("굴림", 0, 12));
      TabProductTextArea.setForeground(Color.black);TabProductTextArea1.setForeground(Color.black);TabProductTextArea2.setForeground(Color.black);TabProductTextArea3.setForeground(Color.black);
      TabProductTextArea.setOpaque(true);TabProductTextArea1.setOpaque(true);TabProductTextArea3.setOpaque(true);TabProductTextArea2.setOpaque(true);
      TabProductTextArea.setBackground(Color.white);TabProductTextArea1.setBackground(Color.white);TabProductTextArea2.setBackground(Color.white);TabProductTextArea3.setBackground(Color.white);
      TabProductTextArea.setBounds(20, 80, 450, 180);TabProductTextArea1.setBounds(20, 80, 450, 180);TabProductTextArea2.setBounds(20, 80, 450, 180);TabProductTextArea3.setBounds(20, 80, 450, 180);
      TabProductTextArea.setBorder(null);TabProductTextArea1.setBorder(null);TabProductTextArea3.setBorder(null);TabProductTextArea2.setBorder(null);
      TabProductTextArea.setLineWrap(true);TabProductTextArea1.setLineWrap(true);TabProductTextArea3.setLineWrap(true);TabProductTextArea2.setLineWrap(true);
      TabProductTextArea.setEditable(false);TabProductTextArea1.setEditable(false);TabProductTextArea3.setEditable(false);TabProductTextArea2.setEditable(false);
      
      TabbedInfoPanel_Product.add(TabProductTextArea);
      TabPanel_PC.add(TabProductTextArea1);
      TabPanel_Laptop.add(TabProductTextArea2);
      TabPanel_Printer.add(TabProductTextArea3);
      
      //추가 부분//
      //탭패널 안에 라벨 (model입력이라 적힌 라벨)
      getmodelInputLabel = new JLabel();
      JLabel getmodelInputLabel1 = new JLabel();
      JLabel getmodelInputLabel2 = new JLabel();
      JLabel getmodelInputLabel3 = new JLabel();
      getmodelInputLabel.setText("model 입력 ");
      getmodelInputLabel1.setText("model 입력 ");
      getmodelInputLabel2.setText("model 입력 ");
      getmodelInputLabel3.setText("model 입력 ");
      getmodelInputLabel.setIcon(new ImageIcon("")); getmodelInputLabel1.setIcon(new ImageIcon(""));getmodelInputLabel2.setIcon(new ImageIcon(""));getmodelInputLabel3.setIcon(new ImageIcon(""));
      getmodelInputLabel.setBounds(225, 0, 100, 80);getmodelInputLabel1.setBounds(225, 0, 100, 80);getmodelInputLabel2.setBounds(225, 0, 100, 80);getmodelInputLabel3.setBounds(225, 0, 100, 80);
      TabbedInfoPanel_Product.add(getmodelInputLabel);
      
      modelInput.setBounds(300, 25, 70, 30);modelInput1.setBounds(300, 25, 70, 30);modelInput2.setBounds(300, 25, 70, 30);modelInput3.setBounds(300, 25, 70, 30);
      okayButton.setBounds(387, 23, 60, 35);okayButton1.setBounds(387, 23, 60, 35);okayButton2.setBounds(387, 23, 60, 35);okayButton3.setBounds(387, 23, 60, 35);
      TabbedInfoPanel_Product.add(modelInput);
      TabbedInfoPanel_Product.add(okayButton);
      okayButton.addActionListener(this); //이벤트 처리할 클래스 this . 이벤트 처리를 위해
      okayButton1.addActionListener(this);okayButton2.addActionListener(this);okayButton3.addActionListener(this);
      
      TabPanel_PC.add(getmodelInputLabel1);
      TabPanel_Laptop.add(getmodelInputLabel2);
      TabPanel_Printer.add(getmodelInputLabel3);
      TabPanel_PC.add(modelInput1);
      TabPanel_Laptop.add(modelInput2);
      TabPanel_Printer.add(modelInput3);
      TabPanel_PC.add(okayButton1);
      TabPanel_Laptop.add(okayButton2);
      TabPanel_Printer.add(okayButton3);
      
   }   
   
   public void actionPerformed(ActionEvent e) {
      Object buttonAction = e.getSource();
      String model = ""; //추가 
      
      if(buttonAction == TabProductmodelNumberCombobox) { //콤보박스 누르면
         //누른 값은 modelnumber에 저장됨
         String modelnumber = (String) TabProductmodelNumberCombobox.getSelectedItem();
         JDBC_Connect_Product JCP = new JDBC_Connect_Product(); //클래스 불러옴
         try {
         JCP.productSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
         System.out.println(modelnumber); //잘 됐는지 Console창에서 확인
      }
      //추가///
      else if(buttonAction == okayButton) {
         model = modelInput.getText();
          JDBC_Connect_Product JCP = new JDBC_Connect_Product(); //클래스 불러옴
          try {
         JCP.productSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
          System.out.println(model); //잘 됐는지 Console창에서 확인
      }
      
      //-----------------------------------PC------------------------------------///
      if(buttonAction == TabPCmodelCombobox) { //콤보박스 누르면
         //누른 값은 modelnumber에 저장됨
         String modelnumber = (String) TabPCmodelCombobox.getSelectedItem();
         JDBC_Connect_PC JCP = new JDBC_Connect_PC(); //클래스 불러옴
         try {
         JCP.pcSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
         System.out.println(modelnumber); //잘 됐는지 Console창에서 확인
      }
      //추가///
      else if(buttonAction == okayButton1) {
         model = modelInput1.getText();
         JDBC_Connect_PC JCP = new JDBC_Connect_PC(); //클래스 불러옴
          try {
         JCP.pcSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
          System.out.println(model); //잘 됐는지 Console창에서 확인
      }
      
    //-----------------------------------Laptop------------------------------------///
      if(buttonAction == TabLaptopmodelCombobox) { //콤보박스 누르면
         //누른 값은 modelnumber에 저장됨
         String modelnumber = (String) TabLaptopmodelCombobox.getSelectedItem();
         JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop(); //클래스 불러옴
         try {
         JCP.laptopSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
         System.out.println(modelnumber); //잘 됐는지 Console창에서 확인
      }
      //추가///
      else if(buttonAction == okayButton2) {
         model = modelInput2.getText();
         JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop(); //클래스 불러옴
          try {
         JCP.laptopSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
          System.out.println(model); //잘 됐는지 Console창에서 확인
      }
      
    //-----------------------------------Printer------------------------------------///
      if(buttonAction == TabPrintermodelCombobox) { //콤보박스 누르면
         //누른 값은 modelnumber에 저장됨
         String modelnumber = (String) TabPrintermodelCombobox.getSelectedItem();
         JDBC_Connect_Printer JCP = new JDBC_Connect_Printer(); //클래스 불러옴
         try {
         JCP.printerSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
         System.out.println(modelnumber); //잘 됐는지 Console창에서 확인
      }
      //추가///
      else if(buttonAction == okayButton3) {
         model = modelInput3.getText();
         JDBC_Connect_Printer JCP = new JDBC_Connect_Printer(); //클래스 불러옴
          try {
         JCP.printerSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch메소드에 넘겨준다.
          System.out.println(model); //잘 됐는지 Console창에서 확인
      }
      
      
      
   }
   
}