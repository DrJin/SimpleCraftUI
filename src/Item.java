
public abstract class Item {
	public static final int TREE = 0;
	public static final int STONE = 1;
	public static final int IRON = 2;
	public static final int HAND = 3;
	protected int itemCode;//아이템코드
	protected int itemNum;//갯수
	protected String itemName;//이름
	protected boolean canBurn;//탈 수 있는지
	public String toString(){return " : " + itemName + "(" + itemNum + "개)";} 
}

/*
 * 아이템 코드
 * 0 - 없음(손)
 * 1 - 나무조각
 * 2 - 돌조각
 * 3 - 철조각
 * 4 - 나무 막대기
 * 5 - 나무 덩이
 * 6 - 돌덩이
 * 7 - 철괴
 * 8 - 나무도끼
 * 9 - 나무곡괭이
 * 10 - 돌도끼
 * 11 - 돌곡괭이
 * 12 - 철도끼
 * 13 - 철곡괭이
 * 14 - 부싯돌
 * 15 - 라이터(부싯돌 + 철괴)
 * 16 - 사과
 *  
 */
