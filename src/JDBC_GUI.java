/*
 * �볪 ���� ���� �ڵ�
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
   
   JPanel MainPanel; //���� �г� 
   JTabbedPane TabbedInfoPanel; //�� �г�
   
   //----------------------Product �г�-----------------------//
   JLabel TabProductLabel; //'��' �� 
   JComboBox<String> TabProductmodelNumberCombobox; //�޺� �ڽ� 
   static JTextArea TabProductTextArea; //�ؽ�Ʈ �����ֱ�
   static JTextArea TabProductTextArea1 ;
   static JTextArea TabProductTextArea2;
   static JTextArea TabProductTextArea3;
  
      //product�гο� �߰� (model�Է� ��, ���� ĭ, Ȯ�� ��ư
   JLabel getmodelInputLabel;
   JTextField modelInput = new JTextField(); //�� ��ȣ �Է��� �� 
   JButton okayButton = new JButton("Ȯ��"); //Ȯ�ι�ư
   JTextField modelInput1 = new JTextField();JTextField modelInput2 = new JTextField();JTextField modelInput3 = new JTextField();  
   JButton okayButton1 = new JButton("Ȯ��");JButton okayButton2 = new JButton("Ȯ��");JButton okayButton3 = new JButton("Ȯ��");
 //---------------------------------------------------------//
   //PC, Laptop, Printer�г� ���� ������//
   JComboBox<String> TabPCmodelCombobox; //�޺� �ڽ� 
   JComboBox<String> TabLaptopmodelCombobox;
   JComboBox<String> TabPrintermodelCombobox;
   


   
   public JDBC_GUI() {
      UIManager.put("swing.blodMetal", Boolean.FALSE);
      setTitle("�ǽ�");
      setBounds(100, 20, 540, 380);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      MainPanel = new JPanel();  //�����г� ����
      MainPanel.setLayout(null);
      //�����гλ�����
      
      makeComponent();
      
      getContentPane().add(MainPanel, BorderLayout.CENTER);
   }
   
   public void makeComponent() {
    //---------------------------�г�-----------------------------//
      TabbedInfoPanel = new JTabbedPane();
      
      JPanel TabbedInfoPanel_Product = new JPanel(); TabbedInfoPanel_Product.setLayout(null);
      //��
      JPanel TabPanel_PC = new JPanel(); TabPanel_PC.setLayout(null);
      JPanel TabPanel_Laptop = new JPanel(); TabPanel_Laptop.setLayout(null);
      JPanel TabPanel_Printer = new JPanel(); TabPanel_Printer.setLayout(null);
      
      //����� product tab�ȿ� �������
      TabbedInfoPanel.addTab("Product",  TabbedInfoPanel_Product);
      TabbedInfoPanel.addTab("PC",  TabPanel_PC);
      TabbedInfoPanel.addTab("Laptop",  TabPanel_Laptop);
      TabbedInfoPanel.addTab("Printer",  TabPanel_Printer);
      
      TabbedInfoPanel.setBounds(10, 20, 500, 300);
      
      MainPanel.add(TabbedInfoPanel); //�����гξȿ� �������г� �߰�. �׷����� ��â ����
    //------------------------------------------------------------//
      
    //---------------------Product �г�-----------------------------//
      
      //���г� �ȿ� �� (model�̶� ���� �κ�)
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
  
      
      //���г� �ȿ� �� �޺��ڽ�, �ؽ�Ʈarea,,
      //�޺��ڽ�
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
      
      //�ư�����
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
      TabProductTextArea.setFont(new Font("����", 0, 12));TabProductTextArea1.setFont(new Font("����", 0, 12));TabProductTextArea2.setFont(new Font("����", 0, 12));TabProductTextArea3.setFont(new Font("����", 0, 12));
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
      
      //�߰� �κ�//
      //���г� �ȿ� �� (model�Է��̶� ���� ��)
      getmodelInputLabel = new JLabel();
      JLabel getmodelInputLabel1 = new JLabel();
      JLabel getmodelInputLabel2 = new JLabel();
      JLabel getmodelInputLabel3 = new JLabel();
      getmodelInputLabel.setText("model �Է� ");
      getmodelInputLabel1.setText("model �Է� ");
      getmodelInputLabel2.setText("model �Է� ");
      getmodelInputLabel3.setText("model �Է� ");
      getmodelInputLabel.setIcon(new ImageIcon("")); getmodelInputLabel1.setIcon(new ImageIcon(""));getmodelInputLabel2.setIcon(new ImageIcon(""));getmodelInputLabel3.setIcon(new ImageIcon(""));
      getmodelInputLabel.setBounds(225, 0, 100, 80);getmodelInputLabel1.setBounds(225, 0, 100, 80);getmodelInputLabel2.setBounds(225, 0, 100, 80);getmodelInputLabel3.setBounds(225, 0, 100, 80);
      TabbedInfoPanel_Product.add(getmodelInputLabel);
      
      modelInput.setBounds(300, 25, 70, 30);modelInput1.setBounds(300, 25, 70, 30);modelInput2.setBounds(300, 25, 70, 30);modelInput3.setBounds(300, 25, 70, 30);
      okayButton.setBounds(387, 23, 60, 35);okayButton1.setBounds(387, 23, 60, 35);okayButton2.setBounds(387, 23, 60, 35);okayButton3.setBounds(387, 23, 60, 35);
      TabbedInfoPanel_Product.add(modelInput);
      TabbedInfoPanel_Product.add(okayButton);
      okayButton.addActionListener(this); //�̺�Ʈ ó���� Ŭ���� this . �̺�Ʈ ó���� ����
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
      String model = ""; //�߰� 
      
      if(buttonAction == TabProductmodelNumberCombobox) { //�޺��ڽ� ������
         //���� ���� modelnumber�� �����
         String modelnumber = (String) TabProductmodelNumberCombobox.getSelectedItem();
         JDBC_Connect_Product JCP = new JDBC_Connect_Product(); //Ŭ���� �ҷ���
         try {
         JCP.productSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
         System.out.println(modelnumber); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      //�߰�///
      else if(buttonAction == okayButton) {
         model = modelInput.getText();
          JDBC_Connect_Product JCP = new JDBC_Connect_Product(); //Ŭ���� �ҷ���
          try {
         JCP.productSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
          System.out.println(model); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      
      //-----------------------------------PC------------------------------------///
      if(buttonAction == TabPCmodelCombobox) { //�޺��ڽ� ������
         //���� ���� modelnumber�� �����
         String modelnumber = (String) TabPCmodelCombobox.getSelectedItem();
         JDBC_Connect_PC JCP = new JDBC_Connect_PC(); //Ŭ���� �ҷ���
         try {
         JCP.pcSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
         System.out.println(modelnumber); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      //�߰�///
      else if(buttonAction == okayButton1) {
         model = modelInput1.getText();
         JDBC_Connect_PC JCP = new JDBC_Connect_PC(); //Ŭ���� �ҷ���
          try {
         JCP.pcSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
          System.out.println(model); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      
    //-----------------------------------Laptop------------------------------------///
      if(buttonAction == TabLaptopmodelCombobox) { //�޺��ڽ� ������
         //���� ���� modelnumber�� �����
         String modelnumber = (String) TabLaptopmodelCombobox.getSelectedItem();
         JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop(); //Ŭ���� �ҷ���
         try {
         JCP.laptopSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
         System.out.println(modelnumber); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      //�߰�///
      else if(buttonAction == okayButton2) {
         model = modelInput2.getText();
         JDBC_Connect_Laptop JCP = new JDBC_Connect_Laptop(); //Ŭ���� �ҷ���
          try {
         JCP.laptopSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
          System.out.println(model); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      
    //-----------------------------------Printer------------------------------------///
      if(buttonAction == TabPrintermodelCombobox) { //�޺��ڽ� ������
         //���� ���� modelnumber�� �����
         String modelnumber = (String) TabPrintermodelCombobox.getSelectedItem();
         JDBC_Connect_Printer JCP = new JDBC_Connect_Printer(); //Ŭ���� �ҷ���
         try {
         JCP.printerSearch(modelnumber);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
         System.out.println(modelnumber); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      //�߰�///
      else if(buttonAction == okayButton3) {
         model = modelInput3.getText();
         JDBC_Connect_Printer JCP = new JDBC_Connect_Printer(); //Ŭ���� �ҷ���
          try {
         JCP.printerSearch(model);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } //productSearch�޼ҵ忡 �Ѱ��ش�.
          System.out.println(model); //�� �ƴ��� Consoleâ���� Ȯ��
      }
      
      
      
   }
   
}