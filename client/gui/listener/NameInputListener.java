package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.Client;
import client.gui.NameInputGUI;

public class NameInputListener extends MouseAdapter{
	Client client;
	NameInputGUI nameinput;
	public NameInputListener(Client c,NameInputGUI nameinput){
		client=c;
		this.nameinput=nameinput;
	}
	
	public void mouseClicked(MouseEvent e){
		client.sf.setVisible(true);
		nameinput.setVisible(false);
		client.send(nameinput.nameinput.getText().trim());
	}
}
