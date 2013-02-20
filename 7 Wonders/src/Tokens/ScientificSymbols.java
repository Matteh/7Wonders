package Tokens;

public class ScientificSymbols {

	private int gears, compass, tablets;
	
	public ScientificSymbols()
	{
		gears = 0;
		compass = 0;
		tablets = 0;
	}
	
	//this constructor should not be used other than for testing
	public ScientificSymbols(int g, int c, int t)
	{
		gears = g;
		compass = c;
		tablets = t;
	}
	
	public int GetGears()
	{
		return gears;
	}
	
	public int GetCompass()
	{
		return compass;
	}
	
	public int GetTablets()
	{
		return tablets;
	}
	
	public int GetTotalSymbols()
	{
		return (gears + compass + tablets);
	}
	
	public int VictoryPointsValue()
	{
		int min;
		if ( gears < compass ) min = Math.min(gears, tablets);
		else min = Math.min(compass, tablets);

		if ( min != 0 )
			return ((int)Math.pow(gears, 2) + (int)Math.pow(compass, 2) + (int)Math.pow(tablets,  2) + 7*min);
		return ((int)Math.pow(gears, 2) + (int)Math.pow(compass, 2) + (int)Math.pow(tablets,  2));
	}
	
	public void AddGears(int amount)
	{
		gears += amount;
	}
	
	public void AddCompass(int amount)
	{
		compass += amount;
	}
	
	public void AddTablets(int amount)
	{
		tablets += amount;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScientificSymbols sym = new ScientificSymbols(3, 4, 2);
		System.out.println(sym.VictoryPointsValue());
	}

}
