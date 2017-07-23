package com.wzf.listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.wzf.show.ShowPanel;

public class MethodComListen implements ActionListener {
	private JTextArea textArea;
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index=((JComboBox)e.getSource()).getSelectedIndex();
		textArea.setText(ShowPanel.funList.getFunXmlModelList().get(index).getDescription());
	}

}
