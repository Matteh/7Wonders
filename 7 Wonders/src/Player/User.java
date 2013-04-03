package Player;

import client.MClient;
import Controls.CommandMessage;
import Controls.Match1;
import Controls.Match2;

public class User {

	protected long ID;
	protected String username;
	private Match2 currentMatch;
	private CommandMessage msg;
	private MClient client;
	
	public User(String name, long id)
	{
		ID = id;
		username = name;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public long getID()
	{
		return ID;
	}
	
	public MClient getClient()
	{
		return client;
	}
	
	public void setUsername(String name)
	{
		username = name;
	}
	
	public void setID(long id)
	{
		ID = id;
	}
	
	public void setClient(MClient cl)
	{
		client = cl;
	}
	
//	public void sendCommandMessage()
//	{
//		client.sendCommandMessage(msg);
//	}
	
	public void updateMatch(Match2 match)
	{
		
	}
	
	public void returnToLobby()
	{
		client.returnToLobby();
	}
	
	public void startMatch(Match2 match)
	{
		currentMatch = match;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
