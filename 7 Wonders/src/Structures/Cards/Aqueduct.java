
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Aqueduct extends Structure {

	public static final int AqueductID = 32;
	
	public Aqueduct()
	{
		super(new Resources(0, 3, 0, 0, 0, 0, 0, 0), AqueductID, "Aqueduct", BLUE_CARD, 2);
		effects.add(new VictoryPointBonus(5));
	}
	
	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(Baths.BathsID);
	}
	


}
