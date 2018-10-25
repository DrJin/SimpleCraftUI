
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

//actionsometing 1. ����\t2. ä��\t3. ������ ����\t4. ����

public class Main extends JFrame {
	static ArrayList<Item> item_list;
	static Ax my_ax;
	static Pickax my_pickax;
	static ImageIcon[] images;
	static String making_array[] = {"1.���� �����(�������� 2��)","2.��������(�������� 2��)","3.������(������ 2��)\n",
		"4.ö��(ö���� 2��)","5.��������(��������� 2�� �������� 3��)","6.�������(��������� 2�� �������� 4��)",
		"7.������(��������� 2�� ������ 3��)","8.�����(��������� 2�� ������ 4��)","9.ö����(��������� 2�� ö�� 3��)",
		"10.ö���(��������� 2�� ö��4��)","11.������(ö�� 1�� �ν˵� 1��)"};
	
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
		//arraylist�� ���
		switch(select){
		case 1://����
			if(my_ax.itemCode==0)
				my_ax.effectiveness=3;
			boolean isSuccess1 = my_ax.function(new WoodPiece());

			if(isSuccess1){
				boolean isInList=false;
				for(int i=0; i<item_list.size(); i++){
					if(item_list.get(i).itemName.equals("��������")){//���࿡ ����Ʈ ���ο� ���������� �̹� ������
						item_list.get(i).itemNum++;
						isInList=true;
					}
				}
				if(!isInList){//�ƴϸ�
					WoodPiece wp = new WoodPiece();//���� ����
					item_list.add(wp);
					wp.itemNum++;
				}
			}

			return isSuccess1;
		case 2://ä��
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
			//�������� ���� �踦 ��:�� 7:3
			if(isSuccess2){
				if(isStone){
					int rate2 = (int) ((Math.random() * 10) + 1);
					if(rate2>5) isFlint=true;
					else isFlint=false;
				}
				boolean isInList1=false;
				boolean isInList2=false;
				for(int i=0; i<item_list.size(); i++){
					if(item_list.get(i).itemName.equals((isStone?"������":"ö����"))){//���࿡ ����Ʈ ���ο� �������̳� ö���� �̹� ������
						item_list.get(i).itemNum++;
						isInList1=true;
					}
					if(item_list.get(i).itemName.equals("�ν˵�") && isFlint){//���࿡ ����Ʈ ���ο� �������̳� ö���� �̹� ������
						item_list.get(i).itemNum++;
						new JOptionPane().showMessageDialog(null,"�ν˵��� Ƣ��Խ��ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						isInList2=true;
					}
				}

				if(!isInList1){//�ƴϸ�
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
					new JOptionPane().showMessageDialog(null,"�ν˵��� Ƣ��Խ��ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
					isFlint=false;

				}
			}

			return isSuccess2;
		case 3://�����ۺ���
			try{
				int index = item_list.indexOf(JOptionPane.showInputDialog(null, "��� �����Ͻðڽ��ϱ�?(���Ͻô� ����� ��ȣ�� �Է��ϼ���)",
						"��� ����",
						JOptionPane.QUESTION_MESSAGE, null,
						item_list.toArray(),"�����ϼ���"))+1;				
				
				if(!(item_list.get(index-1) instanceof Instrument)){//��� �ƴϸ�
					JOptionPane.showMessageDialog(null, "��� �ƴմϴ�.","����",JOptionPane.ERROR_MESSAGE);
				}
				else if(item_list.get(index-1) instanceof Ax){//����
					my_ax = (Ax) item_list.get(index-1);//��� ����
				}
				else if(item_list.get(index-1) instanceof Pickax){//���
					my_pickax = (Pickax) item_list.get(index-1);
				}
				else{//������
					int select_burn = item_list.indexOf(JOptionPane.showInputDialog(null, "�¿� ������ ��ȣ��?",
							"��� ����",
							JOptionPane.QUESTION_MESSAGE, null,
							item_list.toArray(),"�����ϼ���"))+1;
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
		case 4://����������



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
			JOptionPane.showMessageDialog(null, j,"������ ����", JOptionPane.QUESTION_MESSAGE);
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
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			return true;
		}

	}
	static void Making(int itemCode1,int Num1,int itemCode2,int Num2,Item createItem){
		boolean check1 = false;
		boolean check2 = false;
		for(int i=0; i<item_list.size(); i++){
			if(item_list.get(i).itemCode==itemCode1){//���ϴ� ������ ��ȣ�� �°�
				if(item_list.get(i).itemNum>=Num1){//���ϴ� �������� ������
					check1=true;
					item_list.get(i).itemNum-=Num1;
				}
				else
					JOptionPane.showMessageDialog(null, "������ �����մϴ�.","���� ����!",JOptionPane.ERROR_MESSAGE);

			}
			if(item_list.get(i).itemCode==itemCode2){//���ϴ� ������ ��ȣ�� �°�
				if(item_list.get(i).itemNum>=Num2){//���ϴ� �������� ������
					check2=true;
					item_list.get(i).itemNum-=Num2;
				}
				else
					JOptionPane.showMessageDialog(null, "������ �����մϴ�.","���� ����!",JOptionPane.ERROR_MESSAGE);
			}
		}

		for(int i=0; i<item_list.size(); i++)
			if(item_list.get(i).itemNum==0){//0���� �Ǹ�
				item_list.remove(i);//�����
				i--;
			}

		if(check1 && check2){
			JOptionPane.showMessageDialog(null,createItem.itemName+"�Ѱ��� �ϼ��Ǿ����ϴ�","���� ����!",JOptionPane.INFORMATION_MESSAGE);
			createItem.itemNum++;
			item_list.add(createItem);
		}
		else{//���ϴ� ��ȣ�� ��������
			JOptionPane.showMessageDialog(null, "�ʿ��� �������� �����ϴ�.","���� ����!",JOptionPane.ERROR_MESSAGE);
		}
	}
	static void Making(int itemCode1,int Num1,Item createItem){
		boolean check = false;
		for(int i=0; i<item_list.size(); i++){
			if(item_list.get(i).itemCode==itemCode1){//���ϴ� ������ ��ȣ�� �°�
				if(item_list.get(i).itemNum>=Num1)//���ϴ� �������� ������
					item_list.get(i).itemNum-=Num1;
				else{
					JOptionPane.showMessageDialog(null, "������ �����մϴ�.","���� ����!",JOptionPane.ERROR_MESSAGE);
					check=false;
					break;
				}
				if(item_list.get(i).itemNum==0)//0���� �Ǹ�
					item_list.remove(i);//�����
				check=true;
			}

		}
		if(!check){//���ϴ� ��ȣ�� ��������
			JOptionPane.showMessageDialog(null, "�ʿ��� �������� �����ϴ�.","���� ����!",JOptionPane.ERROR_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,createItem.itemName+"�Ѱ��� �ϼ��Ǿ����ϴ�","���� ����!",JOptionPane.INFORMATION_MESSAGE);
			boolean isInList=false;
			for(int i=0; i<item_list.size(); i++){
				if(item_list.get(i).itemName.equals(createItem.itemName)){//���࿡ ����Ʈ ���ο� �������� �̹� ������
					item_list.get(i).itemNum++;
					isInList=true;
				}
			}

			if(!isInList){//�ƴϸ�
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
