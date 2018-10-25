


public interface Iron {	
	int iron_durability=70;
	int iron_effectiveness=10;
	boolean canBurn=false;
}





class IronAx extends Ax implements Iron{//12.Ã¶µµ³¢
	public IronAx(){
		super.durability=iron_durability;
		super.effectiveness=iron_effectiveness;
		super.ingredient = IRON;
		super.canBurn=Iron.canBurn;
		super.itemCode=12;
		super.itemNum=0;
		super.itemName="Ã¶µµ³¢";
	}
	

}
class IronPickax extends Pickax implements Iron{//13.Ã¶°î±ªÀÌ
	public IronPickax(){
		super.durability=iron_durability;
		super.effectiveness=iron_effectiveness;
		super.ingredient = IRON;
		super.canBurn=Iron.canBurn;
		super.itemCode=13;
		super.itemNum=0;
		super.itemName="ÅÖ°î±ªÀÌ";
	}

}