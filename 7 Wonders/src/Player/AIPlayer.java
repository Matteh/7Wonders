package Player;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Structures.Effects.SpecialEffect;
import Tokens.Resources;
import WonderBoards.WonderBoardStage;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

	public static final int SCIENCE = 0;
	public static final int MILITARY = 1;
	public static final int VPOINTS = 2;
	private int favor;						//The structure cards favored over others. Structure based strategy.
	private Strategy strategy;				//The strategy/difficulty of the ai.
	private Random rand = new Random();
	
	//Used to generate a AI with a random difficulty
	public AIPlayer()
	{
		super();
		isAI = true;
		favor = rand.nextInt(3);
		setRandomStrategy();
	}
	
	//Used to generate AI with difficulty corresponding to strat passed
	public AIPlayer(Strategy strat)
	{
		super();
		isAI = true;
		strategy = strat;
		favor = rand.nextInt(3);
	}
	
	public AIPlayer(long id, String name, Strategy strat)
	{
		super(name, id);
		isAI = true;
		favor = rand.nextInt(3);
		setRandomStrategy();
		strategy = strat;
	}
	
	public void setRandomStrategy()
	{
		int randomStrat = rand.nextInt(3);
		if (randomStrat == 0)
			strategy = new Simple();
		else if (randomStrat == 1)
			strategy = new Moderate();
		else 
			strategy = new Intermediate();	
	}
	
	public void pickCard(ArrayList<Structure> discarded, Player leftNeighbor, Player rightNeighbor)
	{
		strategy.strategicPick(this, discarded, leftNeighbor, rightNeighbor);
	}
	
	//Looks for cards that could be used strategically by the next player
	public ArrayList<Integer> freeIndexes(Player p)
	{
		ArrayList<Integer> freeIndexes = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if (cards.get(index).canBuildForFree(p.getWonderBoard()))
			{
				freeIndexes.add(index);
			}
		}
		return freeIndexes;
	}
	
	//Returns a list of indexes that refer to science cards in the hands
	public ArrayList<Integer> getScienceIndexes()
	{
		ArrayList<Integer> science = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( cards.get(index).getColor() == Structure.GREEN_CARD )
			{
				science.add(index);
			}
		}
		return science;
	}
	
	//Returns a list of indexes that refer to military cards in the hand
	public ArrayList<Integer> getMilitaryIndexes()
	{
		ArrayList<Integer> military = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( cards.get(index).getColor() == Structure.RED_CARD )
			{
				military.add(index);
			}
		}
		return military;
	}
	
	//Returns a list of indexes that refer to military cards in the hand
	public ArrayList<Integer> getVictoryIndexes()
	{
		ArrayList<Integer> vPoint = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( cards.get(index).getColor() == Structure.BLUE_CARD )
			{
				vPoint.add(index);
			}
		}
		return vPoint;
	}	
	
	//Returns a list of indexes that refer to resource cards in the hand
	public ArrayList<Integer> getResourceIndexes()
	{
		ArrayList<Integer> resource = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if ( (cards.get(index).getColor() == Structure.GREY_CARD) || (cards.get(index).getColor() == Structure.BROWN_CARD))
			{
				resource.add(index);
			}
		} 
		return resource;
	}
	
	public ArrayList<Integer> getGreyIndexes()
	{
		ArrayList<Integer> resource = new ArrayList<Integer>();
		
		for ( int index = 0; index < cards.size(); ++index )
		{
			if (cards.get(index).getColor() == Structure.GREY_CARD)
			{
				resource.add(index);
			}
		} 
		return resource;
	}
	
	//Try to detect the next players basic strategy
	public int nextPlayerStrat(Player p)
	{
		int military = p.getWonderBoard().getRedCardAmount();
		int science = p.getWonderBoard().getGreenCardAmount();
		int vPoint = p.getWonderBoard().getBlueCardAmount();
		int totalStrat = military + science + vPoint;
		
		if ( totalStrat != 0 )
		{
			if ((((float)military / totalStrat) > ((float)science / totalStrat)) && (((float)military / totalStrat) > ((float)science / totalStrat)))
			{
				return MILITARY;
			}
			else if (((float)science / totalStrat) > ((float)vPoint / totalStrat))
			{
				return SCIENCE;
			}
			else
			{
				return VPOINTS;
			}
		}
		return -1;
	}
	
	//Returns the player that the hand is being passed to next turn
	public Player getNextPlayer(Player leftNeighbor, Player rightNeighbor)
	{
		if (getAge() == 2)
			return rightNeighbor;
		else
			return leftNeighbor;
	}
	
	public float militaryCoef(Player leftNeighbor, Player rightNeighbor){
		float militaryCoef = (((float)leftNeighbor.getShields()  / (float)this.getShields()) + ((float)rightNeighbor.getShields() / (float)this.getShields())) / 2;
		
		return militaryCoef;
	}
	
	//Computes a value for each card in the players hand and adds them to a list linked by the index to the card in the hand.
	//The higher the number, the better a card is to play.
	public ArrayList<Integer> cardSelectionNumbers(Player leftNeighbor, Player rightNeighbor, float choiceCoef)
	{
		ArrayList<Integer> selectionNumbers = new ArrayList<Integer>();
		
		for(int i = 0; i < cards.size(); i++)
		{
			selectionNumbers.add(0);
		}
		
		if (this.strategy instanceof Intermediate)
		{
			for(Integer i : getMilitaryIndexes())
			{
				selectionNumbers.set(i, (int) (selectionNumbers.get(i) + (Math.floor(this.militaryCoef(leftNeighbor, rightNeighbor)))));
			}
		}
		
		if (favor == SCIENCE)
		{
			for(Integer i : getGreyIndexes())
			{
				selectionNumbers.set(i, (selectionNumbers.get(i) + 2));
			}
		}
	
		for(Integer i : getStrategicIndexes(this.favor))
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + 2));
		}
		
		for(Integer i : freeIndexes(this))
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + 1));
		}
		
		if (currentAge == 1)
		{
			for(Integer i : getResourceIndexes())
			{
				selectionNumbers.set(i, (selectionNumbers.get(i) + 1));
			}
		}
		
		for(Integer i : getResourceIndexes())
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + 1));
		}
		
		for(Integer i : getStrategicIndexes(nextPlayerStrat(getNextPlayer(leftNeighbor, rightNeighbor))))
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + (Math.round(1/choiceCoef))));
		}
		
		for(Integer i : freeIndexes(getNextPlayer(leftNeighbor, rightNeighbor)))
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + (Math.round(1/choiceCoef))));
		}
		
		return selectionNumbers;
	}
	
	public ArrayList<Integer> blockSelectionNumbers(Player leftNeighbor, Player rightNeighbor)
	{
		ArrayList<Integer> selectionNumbers = new ArrayList<Integer>();
		
		for(int i = 0; i < cards.size(); i++)
		{
			selectionNumbers.add(0);
		}
		
		for(Integer i : getStrategicIndexes(nextPlayerStrat(getNextPlayer(leftNeighbor, rightNeighbor))))
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + 1));
		}
		
		for(Integer i : freeIndexes(getNextPlayer(leftNeighbor, rightNeighbor)))
		{
			selectionNumbers.set(i, (selectionNumbers.get(i) + 1));
		}
		
		return selectionNumbers;
	}
	
	public ArrayList<Integer> getStrategicIndexes(int strat)
	{
		if (strat == MILITARY)
			return getMilitaryIndexes();
		if (strat == SCIENCE)
			return getScienceIndexes();
		else
			return getVictoryIndexes();
	}
	
	//Handle Resource choice cards
	public void resourceChoice()
	{
		
		Random r = new Random();
		for ( Structure s: this.getWonderBoard().getYellowCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ResourceChoice.ResourceChoiceID )
				{
					ArrayList<Integer> list = new ArrayList<Integer>();
					if ( ((ResourceChoice) se).getPossibilities().getOre() > 0 ) list.add(Resources.ORE_ID);
					if ( ((ResourceChoice) se).getPossibilities().getStone() > 0 ) list.add(Resources.STONE_ID);
					if ( ((ResourceChoice) se).getPossibilities().getWood() > 0 ) list.add(Resources.WOOD_ID);
					if ( ((ResourceChoice) se).getPossibilities().getClay() > 0 ) list.add(Resources.CLAY_ID);
					if ( ((ResourceChoice) se).getPossibilities().getGlass() > 0 ) list.add(Resources.GLASS_ID);
					if ( ((ResourceChoice) se).getPossibilities().getLoom() > 0 ) list.add(Resources.LOOM_ID);
					if ( ((ResourceChoice) se).getPossibilities().getPapyrus() > 0 ) list.add(Resources.PAPYRUS_ID);
					((ResourceChoice) se).chooseResource(list.get(r.nextInt(list.size())), this);
				}
			}
		}
		
		for ( Structure s: this.getWonderBoard().getBrownGreyCards() )
		{
			for ( SpecialEffect se: s.getEffects() )
			{
				if ( se.getID() == ResourceChoice.ResourceChoiceID )
				{
					ArrayList<Integer> list = new ArrayList<Integer>();
					if ( ((ResourceChoice) se).getPossibilities().getOre() > 0 ) list.add(Resources.ORE_ID);
					if ( ((ResourceChoice) se).getPossibilities().getStone() > 0 ) list.add(Resources.STONE_ID);
					if ( ((ResourceChoice) se).getPossibilities().getWood() > 0 ) list.add(Resources.WOOD_ID);
					if ( ((ResourceChoice) se).getPossibilities().getClay() > 0 ) list.add(Resources.CLAY_ID);
					if ( ((ResourceChoice) se).getPossibilities().getGlass() > 0 ) list.add(Resources.GLASS_ID);
					if ( ((ResourceChoice) se).getPossibilities().getLoom() > 0 ) list.add(Resources.LOOM_ID);
					if ( ((ResourceChoice) se).getPossibilities().getPapyrus() > 0 ) list.add(Resources.PAPYRUS_ID);
					((ResourceChoice) se).chooseResource(list.get(r.nextInt(list.size())), this);
				}
			}
		}
		for ( WonderBoardStage stg: this.getWonderBoard().getStages() )
		{
			if ( stg.isBuilt() )
			{
				for ( SpecialEffect se: stg.getEffects() )
				{
					if ( se.getID() == ResourceChoice.ResourceChoiceID )
					{
						ArrayList<Integer> list = new ArrayList<Integer>();
						if ( ((ResourceChoice) se).getPossibilities().getOre() > 0 ) list.add(Resources.ORE_ID);
						if ( ((ResourceChoice) se).getPossibilities().getStone() > 0 ) list.add(Resources.STONE_ID);
						if ( ((ResourceChoice) se).getPossibilities().getWood() > 0 ) list.add(Resources.WOOD_ID);
						if ( ((ResourceChoice) se).getPossibilities().getClay() > 0 ) list.add(Resources.CLAY_ID);
						if ( ((ResourceChoice) se).getPossibilities().getGlass() > 0 ) list.add(Resources.GLASS_ID);
						if ( ((ResourceChoice) se).getPossibilities().getLoom() > 0 ) list.add(Resources.LOOM_ID);
						if ( ((ResourceChoice) se).getPossibilities().getPapyrus() > 0 ) list.add(Resources.PAPYRUS_ID);
						((ResourceChoice) se).chooseResource(list.get(r.nextInt(list.size())), this);
					}
				}
			}
		}
	}

}
