package WonderBoards.Boards;

import Structures.Structure;
import Structures.Effects.CoinBonus;
import Structures.Effects.ResourcesBonus;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;

public class TheTempleOfArtemisInEphesus extends WonderBoard {

	
	public TheTempleOfArtemisInEphesus()
	{
		super(3, 1);
		WonderBoardStage stage1 = new WonderBoardStage(new Resources(0, 2, 0, 0, 0, 0, 0, 0));
		WonderBoardStage stage2 = new WonderBoardStage(new Resources(0, 0, 2, 0, 0, 0, 0, 0));
		WonderBoardStage stage3 = new WonderBoardStage(new Resources(0, 0, 0, 0, 0, 0, 0, 0));
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
		startResource = new ResourcesBonus(0, 0, 0, 0, 0, 0, 1);
	}
	
	public TheTempleOfArtemisInEphesus(int side)
	{
		super(3, side);
		WonderBoardStage stage1 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 2, 0, 0, 0, 0, 0, 0) : new Resources(0, 2, 0, 0, 0, 0, 0, 0)));
		WonderBoardStage stage2 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 2, 0, 0, 0, 0, 0) : new Resources(0, 0, 2, 0, 0, 0, 0, 0)));
		WonderBoardStage stage3 = new WonderBoardStage((side == SIDE_A ? new Resources(0, 0, 0, 0, 0, 0, 0, 0) : new Resources(0, 0, 0, 0, 1, 1, 0, 0)));

		startResource = new ResourcesBonus(0, 0, 0, 0, 0, 0, 1);
		
		if ( side == SIDE_A )
		{
			stage1.getEffects().add(new VictoryPointBonus(3));
			stage2.getEffects().add(new CoinBonus(9));
			stage3.getEffects().add(new VictoryPointBonus(7));
			
		}
		else
		{
			stage1.getEffects().add(new VictoryPointBonus(2));
			stage1.getEffects().add(new CoinBonus(4));
			stage2.getEffects().add(new VictoryPointBonus(3));
			stage2.getEffects().add(new CoinBonus(4));
			stage3.getEffects().add(new VictoryPointBonus(5));
			stage3.getEffects().add(new CoinBonus(4));
		}
		
		stages.add(stage1);
		stages.add(stage2);
		stages.add(stage3);
	}
	
	public boolean isBuilt()
	{
		return (stages.get(1).isBuilt() & stages.get(2).isBuilt() & stages.get(3).isBuilt());
	}
	
	public boolean buildStage(Structure card, Resources funds)
	{
		for (WonderBoardStage s : stages)
		{
			if (!s.isBuilt())
			{
				return s.build(card, funds);
			}
		}
		return false;
	}
	


}
