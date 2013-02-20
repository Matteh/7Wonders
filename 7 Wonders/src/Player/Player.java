package Player;

import WonderBoards.WonderBoard;
import Structures.Structure;
import Resources.Resources;
import java.util.ArrayList;
import Tokens.MilitaryVictoryTokens;

public class Player {

	private String username;
	private int ID;
	private WonderBoard wonderBoard;
	private ArrayList<Structure> cards;
	private Structure chosenCard;
	private Resources resources;
	private int shields;
	private MilitaryVictoryTokens militaryVictoryPoints;
	
	//constructors
	public Player()
	{
		username = "noob";
		ID = 0;
		wonderBoard = new WonderBoard();
		cards = new ArrayList<Structure>();
		chosenCard = null;
		resources = new Resources(0, 0, 0, 0, 0, 0, 0, 3);
		militaryVictoryPoints = new MilitaryVictoryTokens();
		shields = 0;
	}
	
	public Player(String uname, int id)
	{
		username = uname;
		ID = id;
		wonderBoard = new WonderBoard();
		cards = new ArrayList<Structure>();
		chosenCard = null;
		resources = new Resources();
		militaryVictoryPoints = new MilitaryVictoryTokens();
		shields = 0;
	}

	//getters
	public String GetUsername()
	{
		return username;
	}
	
	public int GetID()
	{
		return ID;
	}
	
	public ArrayList<Structure> GetCards()
	{
		return cards;
	}
	
	public WonderBoard GetWonderBoard()
	{
		return wonderBoard;
	}
	
	public Structure GetChosenCard()
	{
		return chosenCard;
	}
	
	public int GetShields()
	{
		return shields;
	}
	
	public MilitaryVictoryTokens GetMilitaryVictoryTokens()
	{
		return militaryVictoryPoints;
	}
	
	
	//actions
	public void AssignWonderBoard(WonderBoard wb)
	{
		wonderBoard = wb;
	}
		
	public void AssignCard(Structure s)
	{
		if ( cards.size() < 7 ) cards.add(s);
	}
	
	public void AssignCards(ArrayList<Structure> newCards)
	{
		cards = newCards;
	}
	
	public void ChooseCard(int index)
	{
		chosenCard = cards.remove(index);
	}
	
	public void AddShields(int s)
	{
		shields += s;
	}
	
	public boolean ActionPhase(int choice)
	{
		switch ( choice )
		{
		case 1://build the structure
			if ( chosenCard.GetResourceCost().CanAfford(resources) && !wonderBoard.ContainsCard(chosenCard.GetID()) )
			{
				wonderBoard.BuildStructure(chosenCard);
				chosenCard = null;
				return true;
			}
			break;
			
		case 2://Build wonderboard stage
			
			break;
			
		case 3://discard the card
			resources.AddCoins(3);
			chosenCard = null;
			return true;
		}
		return false;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
