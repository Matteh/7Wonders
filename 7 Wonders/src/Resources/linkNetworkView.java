package Resources;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.MClient;
import view.menu.CreateMatchPanel;
import view.menu.LobbyPanel;
import view.menu.StartPanel;
import view.menu.WaitingPanel;

public class linkNetworkView {
	LobbyPanel lobby;
	Chat chat;
	CreateMatchPanel createMenu;
	MClient mclient;
	StartPanel startMenu;
	WaitingPanel waitingPanel;
	
	public linkNetworkView(MClient c){
		mclient = c;
		lobby = new LobbyPanel(mclient);
		chat = new Chat(mclient);
		createMenu = new CreateMatchPanel(mclient);
		waitingPanel = new WaitingPanel();
	}
	
	public WaitingPanel launchWaiting() {
		waitingPanel.showGUI();
		return waitingPanel;
	}
	
	public void updateWaiting(int c, int t) {
		waitingPanel.update(c, t);
	}
	
	public LobbyPanel launchLobby() {
		lobby.showGUI();
		lobby.update(mclient.getMatchList());
		return lobby;
	}
	
	public void updateLobby(ArrayList<String> list) {
		lobby.update(list);
	}
	
	public void failedMatchLobby() {
		lobby.failedJoin();
		lobby.showGUI();
	}
	
	public void launchChatFrame() {
		chat.launchChatFrame(mclient.getHost());
	}
	
	public void killChatFrame() {
		chat.kill();
	}
	
	public CreateMatchPanel launchCreateMenu() {
		createMenu.showGUI();
		return createMenu;
	}
	
	public Chat getChat() {
		return chat;
	}
	
	public void failConnect() {
		
		int failJoin = JOptionPane.showConfirmDialog(null, "Failed to connect to the server.\nDo you wish to retry?");
		if(failJoin == JOptionPane.OK_OPTION){
			//startMenu.run();
		}
		else{ 
			System.exit(0);
		}		
	}


	public void otherClientDisconnect() {
		JOptionPane.showMessageDialog(null, "A player has left the match :(\nBeing moved to lobby");
	}
	
}
