package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.Client;
import client.gui.FirstAnnounceGUI;

public class SelfDefineListener extends MouseAdapter{
	Client client;
	FirstAnnounceGUI gui;
	String code;
	
	public SelfDefineListener(Client c,FirstAnnounceGUI gui,String code){
		this.client=c;
		this.gui=gui;
		this.code=code;
	}
	
	public void mouseClicked(MouseEvent e){
		client.send(code);
		gui.setVisible(false);
	}
}
