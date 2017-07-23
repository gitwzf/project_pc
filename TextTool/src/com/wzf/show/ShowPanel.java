package com.wzf.show;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.wzf.listen.MethodComListen;
import com.wzf.listen.ShowListen;
import com.wzf.model.FunXmlModelList;
//TODO 以后 可根据配置自动显示参数项（可点击增加？），现在只是按描述格式输入
public class ShowPanel extends javax.swing.JDialog {
	
	private JPanel jpanel;
	private JTextArea befJtext=new JTextArea();
	private JTextArea aftJtext=new JTextArea();
	private JComboBox jComboBox=new JComboBox();
	private JTextArea jtextDes=new JTextArea();
	private JLabel label1=new JLabel("输入");
	private JLabel label2=new JLabel("输出");
	private JButton jbutton=new JButton("执行");
	private static Serializer serializer = new Persister();;
	public static  FunXmlModelList funList;
	public ShowPanel(JFrame frame){
		super(frame);
		try {
			funList=serializer.read(FunXmlModelList.class, this.getClass().getResourceAsStream("/function.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(funList==null||funList.getFunXmlModelList()==null
				||funList.getFunXmlModelList().size()==0){
			JOptionPane.showMessageDialog(null,"配置文件里没有可用的规则");
			return;
		}
		initGui();
	}
	/**
	 * 初始化
	 */
	protected void initGui(){
		//TODO 反射的方式调用
		getContentPane().setLayout(null);
		jpanel=new JPanel();
		ShowListen al=new ShowListen();
		getContentPane().add(jpanel);
		jpanel.setBorder(BorderFactory.createTitledBorder("文本处理"));
		jpanel.setLayout(null);
		jComboBox.setBounds(30, 20, 150, 20);
		MethodComListen methodListen=new MethodComListen();
		methodListen.setTextArea(jtextDes);
		jComboBox.addActionListener(methodListen);
		jpanel.add(jComboBox);
		al.setjComboBox(jComboBox);
		al.setTextarea(befJtext);
		jtextDes.setBackground(Color.LIGHT_GRAY);
		jpanel.add(addScoll(jtextDes,30, 50,450,50));
		int len=funList.getFunXmlModelList().size();
		for(int i=0;i<len;i++){
			jComboBox.addItem(funList.getFunXmlModelList().get(i).getName());
		}
		//文本描述
		label1.setBounds(70, 100, 100, 30);
		jpanel.add(label1);
		label2.setBounds(320, 100, 100, 30);
		jpanel.add(label2);
		
		jpanel.add(addScoll(befJtext,30, 150,200,200));
		jpanel.add(addScoll(aftJtext,270, 150,200,200));
		
		jbutton.setBounds(330, 360, 100, 30);
		jpanel.add(jbutton);
		
		jpanel.setBounds(0, 0,530, 420);
		setSize(520, 430);
		
		jbutton.addActionListener(al);
	}

	private JScrollPane addScoll(JTextArea text,int x,int y, int width, int height){
		JScrollPane scroll = new JScrollPane(text); 
		//把定义的JTextArea放到JScrollPane里面去 
		scroll.setBounds(x,y, width, height);
		//分别设置水平和垂直滚动条自动出现 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		return scroll;
	}
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ShowPanel sp=new ShowPanel(null);
				sp.setResizable(false);
				sp.setLocationRelativeTo(null);
				sp.setVisible(true);
				sp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
			}
		});
	}
}
