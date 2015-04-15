package client.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class NameInputGUI extends JFrame{
	
	public JTextField nameinput;
	public JButton ok;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NameInputGUI(){
		setTitle("Dungeon of Mandom");
		setLayout(null);
		setSize(300,200);
		JLabel label=new JLabel("请输入姓名：");
		label.setBounds(50, 20, 200, 50);
		add(label);
		nameinput=new JTextField();
		nameinput.setBounds(50,70,200,30);
		add(nameinput);
		ok=new JButton("确定");
		ok.setBounds(200,110,50,30);
		add(ok);
		setLocate(this.getWidth(),this.getHeight());
		setVisible(true);
		addWindowListener(new WindowAdapter(){	
			public void windowClosing(WindowEvent e){			
				System.exit(0);
			}
		});
	}
	
	
	
	public void setLocate(int WIDTH,int HEIGHT){
		Toolkit kit=Toolkit.getDefaultToolkit();

        Dimension screenSize=kit.getScreenSize();  //系统对象获取工具

        int width=screenSize.width;

        int height=screenSize.height;

        int x=(width-WIDTH)/2;

        int y=(height-HEIGHT)/2;

        setLocation(x,y);
	}
	
	public static void main(String arg[]){
		new NameInputGUI();
	}

}


