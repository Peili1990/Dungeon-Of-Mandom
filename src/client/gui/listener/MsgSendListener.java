package client.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.Client;
import client.gui.ClientGUI;

public class MsgSendListener extends MouseAdapter{
	
	Client client;
	ClientGUI sframe;
	public MsgSendListener(Client c,ClientGUI sf){
		client=c;
		sframe=sf;
	}
	
	public void mouseClicked(MouseEvent e){
		client.send(sframe.sendFD.getText());
		sframe.sendFD.setText("");
	}

}
