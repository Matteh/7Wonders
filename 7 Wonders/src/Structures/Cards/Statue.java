<<<<<<< ef5494ce7084e41394288e7084e3ea4669f8181b
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Statue extends Structure {

	public static final int StatueID = 0x25;
	
	public Statue()
	{
		super(new Resources(2, 0, 1, 0, 0, 0, 0, 0), StatueID, "Statue", BLUE_CARD, 2);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Theater.TheaterID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
=======
package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Statue extends Structure {

	public static final int StatueID = 0x25;
	
	public Statue()
	{
		super(new Resources(2, 0, 1, 0, 0, 0, 0, 0), StatueID, "Statue", BLUE_CARD, 2);
	}
	
	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(Theater.TheaterID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
>>>>>>> local
