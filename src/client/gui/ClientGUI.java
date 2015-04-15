package client.gui;

import java.awt.*;

import javax.swing.*;

public class ClientGUI extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextArea showTA;
	public TextField sendFD;
	public Button gamestart;
	public JScrollBar bar;
	public ClientGUI(String winnm){
		super(winnm);
		setLayout(new BorderLayout(5,5));
		OutputPanel output=new OutputPanel();
		showTA=output.showTA;
		bar=output.bar;
		add("North",output);
		showTA.setEditable(false);
		InputPanel input=new InputPanel();
		add("South",input);
		pack();
		setLocate(this.getWidth(),this.getHeight());
		setResizable(false);
		sendFD=input.sendFD;
		gamestart=input.gamestart;
		sendFD.requestFocus();	
		
		
	}
	
	public void setLocate(int WIDTH,int HEIGHT){
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int width=screenSize.width;

        int height=screenSize.height;

        int x=(width-WIDTH)/3;

        int y=(height-HEIGHT)/3;

        setLocation(x,y);
	}
}





