
public interface Tree {
	int tree_durability=30;
	int tree_effectiveness=5;
	boolean canBurn=true;
}
class WoodenAx extends Ax implements Tree{//8.��������
	public WoodenAx(){
		super.durability=tree_durability;
		super.effectiveness=tree_effectiveness;
		super.ingredient = TREE;
		super.canBurn=Tree.canBurn;
		super.itemCode=8;
		super.itemNum=0;
		super.itemName="��������";
	}
}
class WoodenPickax extends Pickax implements Tree{//9.�������
	public WoodenPickax(){
		super.durability=tree_durability;
		super.effectiveness=tree_effectiveness;
		super.ingredient = TREE;
		super.canBurn=Tree.canBurn;
		super.itemCode=9;
		super.itemNum=0;
		super.itemName="�������";
	}
}