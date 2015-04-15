package client.gui;

import java.awt.*;

import javax.swing.*;

public class InputPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TextField sendFD;
	protected Button sendmeg;
	protected Button gamestart;
	
	public InputPanel(){
		setLayout(new FlowLayout());
		add(sendFD=new TextField(30));
		add(sendmeg=new Button("发送"));
		add(gamestart=new Button("准备"));
	}
	

}
