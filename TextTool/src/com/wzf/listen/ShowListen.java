package com.wzf.listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;

import tool.text.interf.ToolTextInterface;

import com.wzf.show.ShowPanel;

public class ShowListen implements ActionListener {

	private JComboBox jComboBox;
	private JTextArea textarea;
	
	public JComboBox getjComboBox() {
		return jComboBox;
	}

	public void setjComboBox(JComboBox jComboBox) {
		this.jComboBox = jComboBox;
	}

	public JTextArea getTextarea() {
		return textarea;
	}

	public void setTextarea(JTextArea textarea) {
		this.textarea = textarea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text=textarea.getText();
		if(StringUtils.isBlank(text))
			JOptionPane.showMessageDialog(null,"没有输入内容");
		
		int index=jComboBox.getSelectedIndex();
		String className=ShowPanel.funList.getFunXmlModelList().get(index).getPath();
		try {
			Class cla=Class.forName(className);
				Object obj=cla.newInstance();
				ToolTextInterface ttt=(ToolTextInterface) obj;
				ttt.doWork(text);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,e1);
		}

	}

}
