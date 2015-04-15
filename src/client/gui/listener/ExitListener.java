package client.gui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.Client;

public class ExitListener extends WindowAdapter{
	Client client;
	public ExitListener(Client c){
		client=c;
	}
	public void windowClosing(WindowEvent e){
		client.close();
		System.exit(0);
	}
}