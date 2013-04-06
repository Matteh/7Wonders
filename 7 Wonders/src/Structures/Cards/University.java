
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class University extends Structure {

	public static final int UniversityID = 73;
	
	public University()
	{
		super(new Resources(0, 0, 2, 0, 1, 0, 1, 0), UniversityID, "University", GREEN_CARD, 3);
		effects.add(new ScientificSymbolBonus(3, 2, false));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Library.LibraryID);
	}



}

