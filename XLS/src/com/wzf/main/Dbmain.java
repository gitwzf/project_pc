package com.wzf.main;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.wzf.dbconn.Dbcon;

public class Dbmain extends javax.swing.JDialog {
	 private JPanel outer;
	 private JLabel two,three,four,five,six;
	 private JTextField pw2,pw3,pw4,pw5,pw6;
	 private JButton jb0,jb1,jb2;
	
	 public static void main(String[] args) {
	  SwingUtilities.invokeLater(new Runnable() {
	   public void run() {
		   Dbmain inst = new Dbmain(null);
	    inst.setResizable(false);
	    inst.setLocationRelativeTo(null);
	    inst.setVisible(true);
	   }
	  });
	 }
	 
	 public Dbmain(JFrame frame) {
	  super(frame);
	  initGUI();
	 }
	 
	 private void initGUI() {
	  try {
	   setTitle("数据库操作(mysql)：");
	   getContentPane().setLayout(null);
	   {
	    outer = new JPanel();
		   ActionListener l=new TableListen(outer);
	    getContentPane().add(outer);
	    outer.setBounds(41, 34, 313, 310);
	    outer.setBorder(BorderFactory.createTitledBorder("填入信息:"));
	    outer.setLayout(null);
	    
	    {
	     two = new JLabel();
	     outer.add(two);
	     two.setText("机子端口号:");
	     two.setBounds(44, 90, 69, 15);
	    }
	    {
	     three = new JLabel();
	     outer.add(three);
	     three.setText("数据库用户名:");
	     three.setBounds(34, 126, 100, 15);
	    }
	    {
		     four = new JLabel();
		     outer.add(four);
		     four.setText("数据库密码:");
		     four.setBounds(44, 160, 69, 15);
		    }
	    {
		     five = new JLabel();
		     outer.add(five);
		     five.setText("数据库名:");
		     five.setBounds(44, 196, 69, 15);
		    }
	    {
		     six = new JLabel();
		     outer.add(six);
		     six.setText("sql/method:");
		     six.setBounds(44, 232, 69, 15);
		    }
	   
	    {
	     pw2 = new JTextField();
	     outer.add(pw2);
	     pw2.setText("3306");
	     pw2.setBounds(119, 86, 133, 22);
	    }
	    {
	     pw3 = new JTextField();
	     outer.add(pw3);
	     pw3.setText("root");
	     pw3.setBounds(119, 122, 133, 22);
	    }
	    {
		     pw4 = new JTextField();
		     outer.add(pw4);
		     pw4.setText("12345678");
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
		     pw6.setBounds(119, 223, 133, 22);
		    }
	    
	    {
		     jb0 = new JButton();
		     jb0.addActionListener(l);
		     outer.add(jb0);
		     jb0.setText("清空");
		     jb0.setBounds(28, 260, 78, 22);
		    }{
			     jb1 = new JButton();
			     jb1.addActionListener(l);
			     outer.add(jb1);
			     jb1.setText("sql执行");
			     jb1.setBounds(120, 260, 78, 22);
			    }
		    {
			     jb2 = new JButton();
			     jb2.addActionListener(l);
			     outer.add(jb2);
			     jb2.setText("method执行");
			     jb2.setBounds(212, 260, 78, 22);
			    }
	    
	   }
	   setSize(400, 400);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }

	}
