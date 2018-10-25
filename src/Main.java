
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

//actionsometing 1. 벌목\t2. 채광\t3. 아이템 보기\t4. 제작

public class Main extends JFrame {
	static ArrayList<Item> item_list;
	static Ax my_ax;
	static Pickax my_pickax;
	static ImageIcon[] images;
	static String making_array[] = {"1.나무 막대기(나무조각 2개)","2.나무덩이(나무조각 2개)","3.돌덩이(돌조각 2개)\n",
		"4.철괴(철조각 2개)","5.나무도끼(나무막대기 2개 나무덩이 3개)","6.나무곡괭이(나무막대기 2개 나무덩이 4개)",
		"7.돌도끼(나무막대기 2개 돌덩이 3개)","8.돌곡괭이(나무막대기 2개 돌덩이 4개)","9.철도끼(나무막대기 2개 철괴 3개)",
		"10.철곡괭이(나무막대기 2개 철괴4개)","11.라이터(철괴 1개 부싯돌 1개)"};
	
	Main(){
		item_list = new ArrayList<Item>();
		my_ax = new HandAx();
		my_pickax = new HandPickax();
		my_ax.itemCode=0;
		my_pickax.itemCode=0;
		images = new ImageIcon[15];
		for (int i = 0; i < 15; i++) 
			images[i] = new ImageIcon("./image/"+i+".png");
		
	}
	public static boolean ActionSomthing(int select){
		//arraylist를 사용
		switch(select){
		case 1://벌목
			if(my_ax.itemCode==0)
				my_ax.effectiveness=3;
			boolean isSuccess1 = my_ax.function(new WoodPiece());

			if(isSuccess1){
				boolean isInList=false;
				for(int i=0; i<item_list.size(); i++){
					if(item_list.get(i).itemName.equals("나무조각")){//만약에 리스트 내부에 나무조각이 이미 있으면
						item_list.get(i).itemNum++;
						isInList=true;
					}
				}
				if(!isInList){//아니면
					WoodPiece wp = new WoodPiece();//새로 생성
					item_list.add(wp);
					wp.itemNum++;
				}
			}

			return isSuccess1;
		case 2://채광
			if(my_pickax.itemCode==0)
				my_pickax.effectiveness=3;
			int rate = (int) ((Math.random() * 10) + 1);
			boolean isSuccess2;
			boolean isStone=true;
			boolean isFlint=false;
			if(rate>3 && rate<=10){
				isSuccess2= my_pickax.function(new StonePiece());
				isStone=true;
			}
			else{
				isSuccess2= my_pickax.function(new IronPiece());
				isStone=false;
			}
			//랜덤으로 돌과 쇠를 돌:쇠 7:3
			if(isSuccess2){
				if(isStone){
					int rate2 = (int) ((Math.random() * 10) + 1);
					if(rate2>5) isFlint=true;
					else isFlint=false;
				}
				boolean isInList1=false;
				boolean isInList2=false;
				for(int i=0; i<item_list.size(); i++){
					if(item_list.get(i).itemName.equals((isStone?"돌조각":"철조각"))){//만약에 리스트 내부에 돌조각이나 철조각 이미 있으면
						item_list.get(i).itemNum++;
						isInList1=true;
					}
					if(item_list.get(i).itemName.equals("부싯돌") && isFlint){//만약에 리스트 내부에 돌조각이나 철조각 이미 있으면
						item_list.get(i).itemNum++;
						new JOptionPane().showMessageDialog(null,"부싯돌이 튀어나왔습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
						isInList2=true;
					}
				}

				if(!isInList1){//아니면
					if(isStone){
						StonePiece sp = new StonePiece();
						item_list.add(sp);
						sp.itemNum++;
					}
					else{
						IronPiece ip = new IronPiece();
						item_list.add(ip);
						ip.itemNum++;
					}
				}
				if(!isInList2 && isStone && isFlint){

					Flint f = new Flint();
					item_list.add(f);
					f.itemNum++;
					new JOptionPane().showMessageDialog(null,"부싯돌이 튀어나왔습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
					isFlint=false;

				}
			}

			return isSuccess2;
		case 3://아이템보기
			try{
				int index = item_list.indexOf(JOptionPane.showInputDialog(null, "장비를 장착하시겠습니까?(원하시는 장비의 번호를 입력하세요)",
						"장비 장착",
						JOptionPane.QUESTION_MESSAGE, null,
						item_list.toArray(),"선택하세요"))+1;				
				
				if(!(item_list.get(index-1) instanceof Instrument)){//장비가 아니면
					JOptionPane.showMessageDialog(null, "장비가 아닙니다.","에러",JOptionPane.ERROR_MESSAGE);
				}
				else if(item_list.get(index-1) instanceof Ax){//도끼
					my_ax = (Ax) item_list.get(index-1);//장비 장착
				}
				else if(item_list.get(index-1) instanceof Pickax){//곡괭이
					my_pickax = (Pickax) item_list.get(index-1);
				}
				else{//라이터
					int select_burn = item_list.indexOf(JOptionPane.showInputDialog(null, "태울 물건의 번호는?",
							"장비 장착",
							JOptionPane.QUESTION_MESSAGE, null,
							item_list.toArray(),"선택하세요"))+1;
					Lighter l = (Lighter)item_list.get(index-1);
					if(l.function(item_list.get(select_burn-1))){
						if(item_list.get(select_burn-1).itemNum>1){
							item_list.get(select_burn-1).itemNum--;
						}
						else if(item_list.get(select_burn-1).itemNum==1){
							item_list.remove(select_burn-1);
						}
					}
				}
			}catch(ArrayIndexOutOfBoundsException e){}


			return true;
		case 4://아이템제작



			images = new ImageIcon[11];
			Integer[] intArray = new Integer[11];
			for (int i = 0; i < 11; i++) {
				intArray[i] = new Integer(i);
				images[i] = new ImageIcon("./image/"+(i+4)+".png");
				if(i==10)
					images[i] = new ImageIcon("./image/15.png");
				if (images[i] != null) {
					images[i].setDescription(making_array[i]);
				}
			}

			JComboBox j = new JComboBox(intArray);
			ComboBoxRenderer renderer= new ComboBoxRenderer();
			renderer.setPreferredSize(new Dimension(300, 130));
			j.setRenderer(renderer);
			j.setMaximumRowCount(3);
			JOptionPane.showMessageDialog(null, j,"아이템 제작", JOptionPane.QUESTION_MESSAGE);
			int index = j.getSelectedIndex()+1;

			switch(index){
			case 0:
				break;
			case 1:
				Making(1,2,new WoodStick());
				break;
			case 2:
				Making(1,2,new WoodLump());
				break;
			case 3:
				Making(2,2,new StoneLump());
				break;
			case 4:
				Making(3,2,new IronLump());
				break;
			case 5:
				Making(4,2,5,3,new WoodenAx());
				break;
			case 6:
				Making(4,2,5,4,new WoodenPickax());
				break;
			case 7:
				Making(4,2,6,3,new StoneAx());
				break;
			case 8:
				Making(4,2,6,4,new StonePickax());
				break;
			case 9:
				Making(4,2,7,3,new IronAx());
				break;
			case 10:
				Making(4,2,7,4,new IronPickax());
				break;
			case 11:
				Making(7,1,14,1,new Lighter());
				break;
			default:
			}
			return true;
		case 6:
			item_list.add(new Flint());
			item_list.add(new IronLump());
			item_list.add(new WoodenAx());
			item_list.get(0).itemNum++;
			item_list.get(1).itemNum++;
			item_list.get(2).itemNum++;
			return true;
		default:
			System.out.println("잘못 입력하셨습니다.");
			return true;
		}

	}
	static void Making(int itemCode1,int Num1,int itemCode2,int Num2,Item createItem){
		boolean check1 = false;
		boolean check2 = false;
		for(int i=0; i<item_list.size(); i++){
			if(item_list.get(i).itemCode==itemCode1){//원하는 아이템 번호가 맞고
				if(item_list.get(i).itemNum>=Num1){//원하는 개수보다 많으면
					check1=true;
					item_list.get(i).itemNum-=Num1;
				}
				else
					JOptionPane.showMessageDialog(null, "개수가 부족합니다.","제작 실패!",JOptionPane.ERROR_MESSAGE);

			}
			if(item_list.get(i).itemCode==itemCode2){//원하는 아이템 번호가 맞고
				if(item_list.get(i).itemNum>=Num2){//원하는 개수보다 많으면
					check2=true;
					item_list.get(i).itemNum-=Num2;
				}
				else
					JOptionPane.showMessageDialog(null, "개수가 부족합니다.","제작 실패!",JOptionPane.ERROR_MESSAGE);
			}
		}

		for(int i=0; i<item_list.size(); i++)
			if(item_list.get(i).itemNum==0){//0개가 되면
				item_list.remove(i);//지운다
				i--;
			}

		if(check1 && check2){
			JOptionPane.showMessageDialog(null,createItem.itemName+"한개가 완성되었습니다","제작 성공!",JOptionPane.INFORMATION_MESSAGE);
			createItem.itemNum++;
			item_list.add(createItem);
		}
		else{//원하는 번호가 없었으면
			JOptionPane.showMessageDialog(null, "필요한 아이템이 없습니다.","제작 실패!",JOptionPane.ERROR_MESSAGE);
		}
	}
	static void Making(int itemCode1,int Num1,Item createItem){
		boolean check = false;
		for(int i=0; i<item_list.size(); i++){
			if(item_list.get(i).itemCode==itemCode1){//원하는 아이템 번호가 맞고
				if(item_list.get(i).itemNum>=Num1)//원하는 개수보다 많으면
					item_list.get(i).itemNum-=Num1;
				else{
					JOptionPane.showMessageDialog(null, "개수가 부족합니다.","제작 실패!",JOptionPane.ERROR_MESSAGE);
					check=false;
					break;
				}
				if(item_list.get(i).itemNum==0)//0개가 되면
					item_list.remove(i);//지운다
				check=true;
			}

		}
		if(!check){//원하는 번호가 없었으면
			JOptionPane.showMessageDialog(null, "필요한 아이템이 없습니다.","제작 실패!",JOptionPane.ERROR_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,createItem.itemName+"한개가 완성되었습니다","제작 성공!",JOptionPane.INFORMATION_MESSAGE);
			boolean isInList=false;
			for(int i=0; i<item_list.size(); i++){
				if(item_list.get(i).itemName.equals(createItem.itemName)){//만약에 리스트 내부에 아이템이 이미 있으면
					item_list.get(i).itemNum++;
					isInList=true;
				}
			}

			if(!isInList){//아니면
				item_list.add(createItem);
				createItem.itemNum++;
			}



		}
	}
	static class ComboBoxRenderer extends JLabel
	implements ListCellRenderer {
		private Font uhOhFont;

		public ComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		/*
		 * This method finds the image and text corresponding
		 * to the selected value and returns the label, set up
		 * to display the text and image.
		 */
		public Component getListCellRendererComponent(
				JList list,
				Object value,
				int index,
				boolean isSelected,
				boolean cellHasFocus) {
			//Get the selected index. (The index param isn't
			//always valid, so just use the value.)
			int selectedIndex = ((Integer)value).intValue();

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			ImageIcon icon = images[selectedIndex];
			String making = making_array[selectedIndex];
			setIcon(icon);
			if (icon != null) {
				setText(making);
				setFont(list.getFont());
			}

			return this;
		}

		
	}
	

}
