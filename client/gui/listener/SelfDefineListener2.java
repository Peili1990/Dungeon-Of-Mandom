package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.Client;
import client.gui.SecondAnnounceGUI;

public class SelfDefineListener2 extends MouseAdapter{
	Client client;
	SecondAnnounceGUI gui;
	String code;
	
	public SelfDefineListener2(Client c,SecondAnnounceGUI gui,String code){
		this.client=c;
		this.gui=gui;
		this.code=code;
	}
	
	public void mouseClicked(MouseEvent e){
		client.send(code);
		gui.setVisible(false);
	}
}



