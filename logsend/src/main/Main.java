package main;
/* �ڴ������ô�����
 * 
 * 
 * 
 * */
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main  extends javax.swing.JDialog{
    	 private JPanel outer;
    	 private JLabel one,two,three,four,five,six;
    	 private JTextField pw1,pw2,pw3,pw4,pw5,pw6;
    	 private JButton jb0,jb1;
    	static{
    		System.out.println("111");
    	}
    	 public static void main(String[] args) {
    	  SwingUtilities.invokeLater(new Runnable() {
    	   public void run() {
    		   Main inst = new Main(null);
    	    inst.setResizable(false);
    	    inst.setLocationRelativeTo(null);
    	    inst.setVisible(true);
    	    inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	   }
    	  });
    	 }
    	 
    	 public Main(JFrame frame) {
    	  super(frame);
    	  initGUI();
    	 }
    	 
    	 private void initGUI() {
    	  try {
    	   setTitle("log�������ã�");
    	   getContentPane().setLayout(null);
    	   {
    	    outer = new JPanel();
    		   ActionListener l=new Do(outer);
    	    getContentPane().add(outer);
    	    
    	    {
    	     one = new JLabel();
    	     outer.add(one);
    	     one.setText("�ļ�·��:");
    	     one.setBounds(44, 56, 69, 15);
    	    }
    	    {
    	     two = new JLabel();
    	     outer.add(two);
    	     two.setText("�ؼ���a:");
    	     two.setBounds(44, 90, 69, 15);
    	    }
    	    {
    	     three = new JLabel();
    	     outer.add(three);
    	     three.setText("�ؼ���b:");
    	     three.setBounds(34, 126, 100, 15);
    	    }
    	    {
    		     four = new JLabel();
    		     outer.add(four);
    		     four.setText("�����ȣ�1~9ʮ�ַ�������Ϊ���й�����:");
    		     four.setBounds(44, 160, 69, 15);
    		    }
    	    {
    		     five = new JLabel();
    		     outer.add(five);
    		     five.setText("��ʼʱ�䣨s��:");
    		     five.setBounds(44, 196, 69, 15);
    		    }
    	    {
    		     six = new JLabel();
    		     outer.add(six);
    		     six.setText("���ʱ�䣨s��:");
    		     six.setBounds(44, 226, 69, 15);
    		    }
    	    {
    	     pw1 = new JTextField();
    	     outer.add(pw1);
    	     pw1.setText("");
    	     pw1.setBounds(119, 52, 133, 22);
    	    }
    	    {
    	     pw2 = new JTextField();
    	     outer.add(pw2);
    	     pw2.setText("����ǰʱ�䣩");
    	     pw2.setBounds(119, 86, 133, 22);
    	    }
    	    {
    	     pw3 = new JTextField();
    	     outer.add(pw3);
    	     pw3.setText("");
    	     pw3.setBounds(119, 122, 133, 22);
    	    }
    	    {
    		     pw4 = new JTextField();
    		     outer.add(pw4);
    		     pw4.setText("");
    		     pw4.setBounds(119, 157, 133, 22);
    		    }
    	    {
    		     pw5 = new JTextField();
    		     outer.add(pw5);
    		     pw5.setText("");
    		     pw5.setBounds(119, 190, 133, 22);
    		    }
    	    {
    		     pw6 = new JTextField();
    		     outer.add(pw6);
    		     pw6.setText("");
    		     pw6.setBounds(119, 227, 133, 22);
    		    }
    	    
    	    
    	    {
    		     jb0 = new JButton();
    		     jb0.addActionListener(l);
    		     outer.add(jb0);
    		     jb0.setText("���");
    		     jb0.setBounds(60, 260, 80, 22);
    		    }{
    			     jb1 = new JButton();
    			     jb1.addActionListener(l);
    			     outer.add(jb1);
    			     jb1.setText("ִ��");
    			     jb1.setBounds(200, 260, 80, 22);
    			    }
    		   
    	    
    	   }
    	   setSize(400, 400);
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }
    	 }

    	}
