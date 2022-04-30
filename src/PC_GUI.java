import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.awt.Graphics;

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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JScrollPane;


public class PC_GUI extends JFrame implements ActionListener{
   private static final long serialVersionUID = 1L;
   //private JTextArea check_area = new JTextArea();
   static int TotalPrice=0;
   
   private JPanel panel = new JPanel();
   static Connection conn=null;
   static PreparedStatement stmt=null;
   
   private JTextField PCField = new JTextField();
   private JTextField LaptopField = new JTextField();
   private JTextField PrinterField = new JTextField();

   private JComboBox<Integer> PCCB=new JComboBox<Integer>();
   private JComboBox<Integer> LaptopCB =new JComboBox<Integer>();
   private JComboBox<Integer> PrinterCB= new JComboBox<Integer>();

   JPanel MainPanel;
   JTabbedPane TabbedInfoPanel;
   
   JLabel TabProductLabel;
   private JComboBox<String> TabCheckmodelNumberCombobox = new JComboBox<String>();
   static JTextArea TabCheckTextArea;
   //static JScrollPane scroll = new JScrollPane(TabCheckTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
   
   static JTextArea TabFBTextArea;
   static JTextArea TabBITextTArea;
   //
   static String PCmodel ="";
   static String Laptopmodel ="";
   static String Printermodel ="";
   static JLabel SMLabel1=new JLabel();
   static JLabel SMLabel=new JLabel();

   
   JButton BuyButton = new JButton("����");
   JButton CloseButton = new JButton("�ݱ�");
   JButton ResetButton = new JButton("����");
   JButton FBButton = new JButton("���� ����");
   JButton CheckButton = new JButton("��ȸ");
   
   JLabel modelnum= new JLabel();
   JLabel count= new JLabel();
   
   
   
   JLabel PCtitle= new JLabel();
   JLabel Laptoptitle= new JLabel();
   JLabel Printertitle= new JLabel();
   
   

   
   //JTabbedPane TabCheckmodelNumberCombobox;
   
   JLabel title1=new JLabel();
   JLabel title2=new JLabel();
   Font font=new Font("Aharoni ����",Font.BOLD, 25);
   
   //PC_GUI  
   public PC_GUI(String userid, String userpw) 
   {
      UIManager.put("swing.blodMetal", Boolean.FALSE);
      setTitle("JDBC�� �ڹ� GUI �ǽ�");
       setBounds(100, 20, 1000, 500);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
         
       MainPanel = new JPanel();
       MainPanel.setLayout(null);
       
       panel = new JPanel();
       panel.setFont(new Font("�ʱ�ü",1,12));
       panel.setBorder(new TitledBorder("����"));
       panel.setBounds(15,120,400,320);
       panel.setLayout(null);
       

       PCtitle.setBounds(40,80,70,30);
       PCtitle.setText("PC");
       panel.add(PCtitle);
       
       Laptoptitle.setBounds(40,130,70,30);
       Laptoptitle.setText("Laptop");
       panel.add(Laptoptitle);
       
       Printertitle.setBounds(40,190,70,30);
       Printertitle.setText("Printer");
       panel.add(Printertitle);
       
       modelnum.setBounds(150,30,70,30);
       modelnum.setText("�� ��ȣ");
       panel.add(modelnum);
       
       
       count.setBounds(260,30,50,30);
       count.setText("����");
       panel.add(count);
      
       PCField.setBounds(140,70,80,40);
       panel.add(PCField);
       
       
       PCCB.setBounds(240,70,70,40);
       for(int i=1;i<11 ;i++) {
         PCCB.addItem(i);
      }
       panel.add(PCCB);
       
      LaptopField.setBounds(140,125,80,40);
      
       panel.add(LaptopField);
       LaptopCB.setBounds(240,125,70,40);
       for(int i=1;i<11 ;i++) {
         LaptopCB.addItem(i);
      }
       panel.add(LaptopCB);
       
       PrinterField.setBounds(140,180,80,40);
       panel.add(PrinterField);
       PrinterCB.setBounds(240,180,70,40);
       for(int i=1;i<11 ;i++) {
         PrinterCB.addItem(i);
      }
       panel.add(PrinterCB);
       
       BuyButton.setBounds(100, 250, 100,40);
       BuyButton.addActionListener(this);
       panel.add(BuyButton);
       CloseButton.setBounds(230, 250, 100,40);
       CloseButton.addActionListener(this);
       panel.add(CloseButton);
      
       makeComponet();
       
       getContentPane().add(MainPanel, BorderLayout.CENTER);
       
   }
   
   public void paint(Graphics g)
   {
      super.paint(g);
      g.drawRect(20, 40, 400, 84);
      g.drawRect(460, 40, 500, 84);
   }
  
   public void makeComponet() 
   {   

      title1.setFont(font);
      title1.setText("KDE ��ǻ�Ͱ���");
      title1.setBounds(110,30,500,50);

      
      title2.setFont(font);
      title2.setText("���� �ð� �Ǽ���. [ 2020�� / 12�� / 20�� ]");
      title2.setBounds(470,30,500,50);
      
      
      //TabCheckmodelNumberCombobox = new JTabbedPane();
      TabbedInfoPanel = new JTabbedPane();
      JPanel TabbedInfoPanel_Search = new JPanel();
      JPanel TabbedInfoPanel_Buy = new JPanel();
      JPanel TabbedInfoPanel_Buycontent = new JPanel();
      TabbedInfoPanel_Search.setLayout(null);
      TabbedInfoPanel_Buy.setLayout(null);
      TabbedInfoPanel_Buycontent.setLayout(null);
      TabbedInfoPanel.addTab("��ȸ",  TabbedInfoPanel_Search);
      TabbedInfoPanel.addTab("��������",  TabbedInfoPanel_Buy);
      TabbedInfoPanel.addTab("���ų���",  TabbedInfoPanel_Buycontent);
      TabbedInfoPanel.setBounds(450,120,500,320);
      
      MainPanel.add(TabbedInfoPanel);
      
      //��
      TabProductLabel = new JLabel();
      TabProductLabel.setText("��ȸ�� ��ǰ ����");
      TabProductLabel.setIcon(new ImageIcon(""));
      TabProductLabel.setBounds(30,25, 160,30);

      //���¹�ư
      ResetButton.setBounds(360, 233, 100,40);
      ResetButton.addActionListener(this);
      TabbedInfoPanel_Buy.add(ResetButton);
      
      //�������Ź�ư
      FBButton.setBounds(240, 233, 100,40);
      FBButton.addActionListener(this);
      TabbedInfoPanel_Buy.add(FBButton);
       

      
      TabCheckmodelNumberCombobox.setBounds(180,20,80,40);
      TabCheckmodelNumberCombobox.addActionListener(this);
      TabCheckmodelNumberCombobox.addItem("PC");
      TabCheckmodelNumberCombobox.addItem("Laptop");
      TabCheckmodelNumberCombobox.addItem("Printer");
      
      TabCheckTextArea = new JTextArea();
      TabCheckTextArea.setFont(new Font("����", 0, 12));
      TabCheckTextArea.setForeground(Color.black);
      TabCheckTextArea.setOpaque(true);//����
      TabCheckTextArea.setBackground(Color.white);
      TabCheckTextArea.setBorder(new LineBorder(Color.black, 2));
      TabCheckTextArea.setBounds(20, 80, 450, 180);
      TabCheckTextArea.setLineWrap(true);
      TabCheckTextArea.setEditable(false);//�����Ұ�
       
      TabFBTextArea = new JTextArea();
      TabFBTextArea.setFont(new Font("����", 0, 12));
      TabFBTextArea.setForeground(Color.black);
      TabFBTextArea.setOpaque(true);//����
      TabFBTextArea.setBackground(Color.white);
      TabFBTextArea.setBorder(new LineBorder(Color.black, 2));
      TabFBTextArea.setBounds(20, 30, 450, 180);
      TabFBTextArea.setLineWrap(true);
      TabFBTextArea.setEditable(false);//�����Ұ�
       
      TabBITextTArea = new JTextArea();
      TabBITextTArea.setFont(new Font("����", 0, 12));
      TabBITextTArea.setForeground(Color.black);
      TabBITextTArea.setOpaque(true);//����
      TabBITextTArea.setBackground(Color.white);
      TabBITextTArea.setBorder(new LineBorder(Color.black, 2));
      TabBITextTArea.setBounds(20, 30, 450, 180);
      TabBITextTArea.setLineWrap(true);
      TabBITextTArea.setEditable(false);//�����Ұ�
      JScrollPane scroll=new JScrollPane();
      scroll.setViewportView(TabBITextTArea);
      scroll.setBounds(10,270,430,170);
      
       //��ȸ��ư�ֱ�
       CheckButton.setBounds(370, 233, 100,40);
       CheckButton.addActionListener(this);
       TabbedInfoPanel_Buycontent.add(CheckButton);
       
      SMLabel1=new JLabel();
      SMLabel1.setText("KDE ��ǻ�� ���� �� ���� : ");
      SMLabel1.setIcon(new ImageIcon(""));
      SMLabel1.setBounds(30,233, 160,30);
      TabbedInfoPanel_Buycontent.add(SMLabel1);
      
      SMLabel=new JLabel();
      SMLabel.setText("  ");
      SMLabel.setIcon(new ImageIcon(""));
      SMLabel.setBounds(180,233, 160,30);
      TabbedInfoPanel_Buycontent.add(SMLabel);
     
       
       
      //��ȸâ�ȿ� ����
      TabbedInfoPanel_Search.add(TabCheckTextArea);   
      //TabbedInfoPanel_Search.add(scroll);
      TabbedInfoPanel_Search.add(TabProductLabel);
      TabbedInfoPanel_Search.add(TabCheckmodelNumberCombobox);
      
      //��������â�ȿ� ����
      TabbedInfoPanel_Buy.add(TabFBTextArea);
      //���ų���â�ȿ� ����
      TabbedInfoPanel_Buycontent.add(TabBITextTArea);   
      

      
      MainPanel.add(title1);
      MainPanel.add(title2);
      MainPanel.add(panel);

      
      
   }
   
    
   public void actionPerformed(ActionEvent e) {
      Object buttonAction = e.getSource();

      //���� �г� ���� Button
      if(buttonAction == BuyButton)  
      {
          PCmodel = (String) PCField.getText();
          Laptopmodel = (String) LaptopField.getText();
          Printermodel = (String) PrinterField.getText();
          
          System.out.println("PCmodel ��: " + PCmodel );
          System.out.println("Laptopmodel ��: " + Laptopmodel );
          System.out.println("Printermodel ��: " + Printermodel );
          
          int PCcount = (Integer) PCCB.getSelectedItem();
          int Laptopcount = (Integer) LaptopCB.getSelectedItem();
          int Printercount = (Integer) PrinterCB.getSelectedItem();
          
          PC_DB_Buy PDB = new PC_DB_Buy();
          PDB.BuyHistory(PCmodel, Laptopmodel, Printermodel, PCcount, Laptopcount, Printercount);
          
          //���Ź�ư ������ �ٽ� �ʱ�ȭ ��Ŵ
          PCField.setText("");
          LaptopField.setText("");
          PrinterField.setText("");
          PCCB.setSelectedIndex(0);
          LaptopCB.setSelectedIndex(0);
          PrinterCB.setSelectedIndex(0);
      }
      
      //���� �г� �ݱ� Button 
      if(buttonAction == CloseButton)
      {
         JOptionPane.showMessageDialog(null,  "���α׷��� �����մϴ�.");
          dispose();
      }
      
    //��ȸ �� ComboBox
      if(buttonAction == TabCheckmodelNumberCombobox) {
          String modelname = (String) TabCheckmodelNumberCombobox.getSelectedItem();
          PC_DB_CheckPanel PDC = new PC_DB_CheckPanel();
          PDC.Search(modelname);
          System.out.println(modelname);
       }
      
    //���� Button
       if(buttonAction == ResetButton) {
          TotalPrice = 0;
          TabFBTextArea.setText("");
          JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.");
       }
     
     //�������� button  
       if(buttonAction == FBButton) {
           if(TotalPrice == 0) {
              JOptionPane.showMessageDialog(null, "���� ���Ÿ� ���ּ���.");
           }
           else {
              System.out.println("PCmodel ��: " + PC_DB_Buy.PCmodelStore);
              System.out.println("Laptopmodel ��: " + PC_DB_Buy.LaptopmodelStore);
              System.out.println("Printermodel ��: " + PC_DB_Buy.PrintermodelStore);
              JOptionPane.showMessageDialog(null, "���������Ͽ� [ �ѱݾ� : $" + TotalPrice + "]�� �����Ǿ����ϴ�.");
              
              PC_DB_Insert PDI = new PC_DB_Insert();
              try {
                 PDI.Insert();
              } catch (SQLException e1) {
                 e1.printStackTrace();
              }
              
              
              if(JOptionPane.showConfirmDialog(null, "��� ���Ÿ� �ϰڽ��ϱ�?", "Ȯ��â",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 
              }else {
                 JOptionPane.showMessageDialog(null, "���α׷��� �����մϴ�.");
                 System.exit(0);
              }
           }
        } 
       
       if(buttonAction == CheckButton) {
           PC_DB_BuyList PCB = new PC_DB_BuyList();
           PCB.SumPrice();
      }

   }
   
}