package Resources;

//create packets to communicate from the server to anyone connected to the server
//EACH packet represents a different type of communication
//Packet4Object is the most complex and should be used for sending playing moves to the 
//server

public class Packet {
	public static class Packet0LoginRequest{}
	public static class Packet1LoginAnswer{
		private boolean accepted = false;
		private long idvalue;
		private Object o;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
		public boolean getAccepted(){return accepted;}
		public void setAccepted(boolean b){accepted = b;}
		public void setIDValue(long id){idvalue = id;}
		public long getIDValue(){return idvalue;}		
	}
	public static class Packet2MatchListRequest{
		private Object o;
		private Object o2;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
		public Object getObject2(){ return o2;}
		public void setObject2(Object ob){ o2 = ob;}
	}
	public static class Packet3Connection{
		private boolean accepted = false;
		private long idvalue;
		public boolean getAccepted(){return accepted;}
		public void setAccepted(boolean b){accepted = b;}
		public void setIDValue(long id){idvalue = id;}
		public long getIDValue(){return idvalue;}

	}
	public static class Packet4Object{
		private Object o;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
	}
	public static class Packet5Disconnect{
		//if client sends one of these
		//eliminate from any current game
		private long matchID;
		private long cID;
		private String username;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}		
		public long getCID(){ return cID;}
		public void setCID(long i){cID= i;}
		public String getUName(){return username;}
		public void setUName(String s){username = s;}
	}
	public static class Packet6ChatMsg{
		private String msg;
		private long matchID;
		private long clientID;
		private String uName;
		public String getMsg(){ return msg;}
		public void setMsg(String s){ msg = s;}
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public long getCID(){return clientID;}
		public void setCID(long id){clientID=id;}
		public String getuName(){return uName;}
		public void setuName(String n){uName = n;}
	}
	public static class Packet7MatchFunction{
		private Object o;
		private int id;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
		public int getID(){return id;}
		public void setID(int i){id = i;}
	}
	public static class Packet8ClientResponse{
		private long matchID;
		private long clientID;
		private Object o;
		private boolean last;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public long getCID(){return clientID;}
		public void setCID(long id){clientID=id;}
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
		public boolean getBoolean(){return last;}
		public void setBoolean(boolean b){ last = b;}
	}
	public static class Packet9StartMatch{}
	public static class Packet10EndMatch{}
	public static class Packet11ImmediateStart{
		//if client sends one of these
		//eliminate from any current game
		private long matchID;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}		
		
	}
	public static class Packet12CreateMatch{
		private int numHuman,numAI;
		private long clientID;
		private String username;
		private int aiDifficulty;
		public void setHuman(int x){numHuman = x;}
		public int getHuman(){return numHuman;}
		public void setAI(int x){numAI = x;}
		public int getAI(){return numAI;}
		public long getCID(){return clientID;}
		public void setCID(long id){clientID=id;}
		public String getUName(){return username;}
		public void setUName(String s){username = s;}
		public int getAIDiff(){return aiDifficulty;}
		public void setAIDiff(int i){aiDifficulty = i;}		
	}
	public static class Packet13MatchJoinRequest{
		private long matchID;
		private long cID;
		private String username;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public long getCID(){ return cID;}
		public void setCID(long i){cID= i;}
		public String getUName(){return username;}
		public void setUName(String s){username = s;}
	}
	public static class Packet14HostCreateMatch{
		private long matchID;
		private int numPlayer;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public void setnPlayer(int i){numPlayer = i;}
		public int  getnPlayer(){return numPlayer;}
	}
	public static class Packet15MatchDisconnect{}
	public static class Packet16ForcefulDisconnect{}
	public static class Packet17ConnectionCount{
		private int connectedCount = 0;
		private int total = 0;
		public int getConCount(){return connectedCount;}
		public void setConCount(int n){connectedCount = n;}
		public int getMaxCount(){return total;}
		public void setMaxCount(int n){total = n;}
	}

}

