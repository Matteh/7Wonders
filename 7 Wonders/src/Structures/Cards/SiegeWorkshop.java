
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class SiegeWorkshop extends Structure {

	public static final int SiegeWorkshopID = 51;
	
	public SiegeWorkshop()
	{
		super(new Resources(0, 0, 1, 3, 0, 0, 0, 0), SiegeWorkshopID, "Siege Workshop", RED_CARD, 3);
		effects.add(new ShieldBonus(3));
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Laboratory.LaboratoryID);
	}
	


}

