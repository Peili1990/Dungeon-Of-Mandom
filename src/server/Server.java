package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Exchanger;

public class Server {
	
	public Server(int port) throws IOException
	{
		@SuppressWarnings("resource")
		ServerSocket server=new ServerSocket(port);
		System.out.println("server starts work");
		while(true){
			Socket conn=server.accept();
			DataInputStream in=new DataInputStream(conn.getInputStream());
			String who=in.readUTF();
			System.out.println("Client(IP:"+conn.getInetAddress()+")"+who+" enter!");
			ServerHander cn=new ServerHander(who,conn);
			cn.start();
		}
	}
	
	public static void main(String args[]) throws IOException{
		new Server(34251);
	}
}
	
class ServerHander extends Thread{
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	String who;
	Exchanger<String> exchanger = new Exchanger<String>();
	protected static Vector<ServerHander> clientlist = new Vector<ServerHander>();
	protected static int readynum;

	public ServerHander(String name, Socket socket) throws IOException {
		this.who = name;
		this.socket = socket;
		in = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		out = new DataOutputStream(new BufferedOutputStream(
				socket.getOutputStream()));
	}

	public void run() {
		try {
			clientlist.addElement(this);
			sendAllClient("Welcome " + who + " entered!");
			while (true) {
				String msg = in.readUTF();
				if(msg.equals(Clientcode.READY_SIGNAL)){
					sendAllClient(who + " is ready for the game!");
					readynum++;
					if(readynum>1&&readynum==clientlist.size()){
						sendAllClient("Game Start!");
						Gameprocess gm=new Gameprocess(clientlist,clientlist);
						gm.start();
						readynum=0;
					}
				}
				else if(msg.equals("")) continue; 
				else if(msg.length()>=4&&msg.substring(0, 4).equals("3173"))
					exchanger.exchange(msg);
				else sendAllClient(who + " said: " + msg);

			}
		} catch (IOException e) {
			System.out.println("Client exit or error.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			clientlist.removeElement(this);
			sendAllClient(who + " left!");
			try {
				socket.close();
			} catch (IOException ex) {
				System.out.println("Connection has been closed.");
			}
		}
	}

	public void sendAllClient(String msg){
		synchronized(clientlist){
			Enumeration<ServerHander> allclients=clientlist.elements();
			while(allclients.hasMoreElements()){
				ServerHander serh=(ServerHander)allclients.nextElement();
				send(serh,msg,0);
			}
		}
	}
	
	public void send(ServerHander target,String msg,int code){
		try{
			target.out.writeUTF(msg);
			target.out.writeInt(code);
			target.out.flush();
		}
		catch(IOException e){
			target.interrupt();
		}
	}
	
	
	
	

}
