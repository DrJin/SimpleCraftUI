import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.Random;



class GameMain extends JFrame implements Runnable,
KeyListener{
	private final static int INTERVAL = 50;
	private final static int OBJECT_MAX = 30;//나무나 돌 최대개수
	private final static int X = 16;
	private final static int Y = 12;
	private int object_num = 0;//나무나 돌 수
	private int[][] int_map = new int[16][12];
	
	//0 : 땅 1 : 나무 2 : 돌 3 : 집
	Main m;
	ImageIcon ic = new ImageIcon("./image/char.png");
	Image buffimg;
	Graphics gc;

	Thread th;

	int x, y; 
	int cnt; 
	int moveStatus; 
	GameMain(){
		m = new Main();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SimpleCraft GUI ver 1.3");

		x = 2;
		y = 2;
		//초기위치
		int_map[0][0]=3;
		int_map[1][0]=4;
		int_map[0][1]=5;
		int_map[1][1]=6;//4자리
		moveStatus = 2;//down
		// 0 : up 1 : right 2 : down 3 : left

		while(object_num<OBJECT_MAX){//조형물 추가해주기
			addObject();
			object_num++;
		}

		this.addKeyListener(this);
		th = new Thread(this);
		th.start();

		setSize(810, 630);setLocation(50,50);//16,12 - 10과 30은 예비구역
		setVisible(true);
	}

	public void run(){ 
		while(true){
			try{
				repaint();
				Thread.sleep(20);
				cnt++;

			}catch(Exception e){}
		}
	}

	public void paint(Graphics g){ 
		buffimg = createImage(810, 630);
		gc = buffimg.getGraphics();
		update(g);
	}
	public void update(Graphics g){
		DrawImage(ic.getImage(), x*INTERVAL, y*INTERVAL, 50, 50);
		g.drawImage(buffimg, 0, 0, this);

	}
	public void addObject(){
		Random r = new Random();
		int rx,ry;
		rx = r.nextInt(X-1)+1;
		ry = r.nextInt(Y);

		while((rx==x && ry==y) || int_map[rx][ry]!=0){
			rx = r.nextInt(X-1)+1;
			ry = r.nextInt(Y);
		}
		if(r.nextBoolean())//true - 나무
			int_map[rx][ry] = 1;
		else//false - 돌
			int_map[rx][ry] = 2;
	}


	public void DrawImage(Image img, int x, int y, int width, 
			int height){
		for(int i=0; i<X; i++){//배경 출력
			for(int j=0; j<Y; j++){
				switch(int_map[i][j]){
				case 0:
					gc.drawImage(new ImageIcon("./image/field.png").getImage(),10+50*i,30+50*j,this);
					break;
				case 1:
					gc.drawImage(new ImageIcon("./image/tree.png").getImage(),10+50*i,30+50*j,this);
					break;
				case 2:
					gc.drawImage(new ImageIcon("./image/stone.png").getImage(),10+50*i,30+50*j,this);
					break;
				default :
					gc.drawImage(new ImageIcon("./image/tent_0"+(int_map[i][j]-2)+".png").getImage(),10+50*i,30+50*j,this);
					break;
				}

			}
		}
		gc.setClip(x+10 , y+30, width, height);

		switch(cnt / 10 % 4){
		case 0:
			gc.drawImage(img, x-(width*0)+10, y-(height*moveStatus)+30, this);

			break;
		case 1:
			gc.drawImage(img, x-(width*1)+10, y-(height*moveStatus)+30, this);
			break;
		case 2:
			gc.drawImage(img, x-(width*2)+10, y-(height*moveStatus)+30, this);
			break;
		case 3:
			gc.drawImage(img, x-(width*1)+10, y-(height*moveStatus)+30, this);
			break;
		}


	}

	public boolean checkObject(){//물체 확인 후 행동 & 제거 & 추가생성
		switch(int_map[x][y]){
		case 1://나무
			if(m.ActionSomthing(1)){//행동
				int_map[x][y]=0;//제거
				object_num--;//제거
				addObject();//생성
			}
			return false;

		case 2://돌
			if(m.ActionSomthing(2)){//행동
				int_map[x][y]=0;//제거
				object_num--;//제거
				addObject();//생성
			}
			return false;
		case 3:
		case 4:
		case 6:
			return false;
		case 5:
			String[] buttons = {"아이템 장착","아이템 제작","취소"};
			int check = JOptionPane.showOptionDialog(this, "항목을 선택해 주세요","아이템 창고",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,buttons,"아이템 장착");
			if(check==JOptionPane.OK_OPTION){
				m.ActionSomthing(3);
			}
			else if(check==JOptionPane.NO_OPTION){
				m.ActionSomthing(4);
			}
			else{
				
			}
		default:
			return true;
		}
	}
	public void keyPressed(KeyEvent e) {
		int px = x*INTERVAL+(ic.getIconWidth()/4/2);//실제 화면 좌표
		int py = y*INTERVAL+(ic.getIconHeight()/4/2);
		if(!Thread.currentThread().interrupted()){//쓰레드 종료할때까지
			switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				x++;
				if(px>=(X-1)*INTERVAL)//실제 화면좌표로 계산하고
					x--;//값은 위치값으로 준다
				else if(checkObject())
					moveStatus=1;
				else {x--; moveStatus=1;}
				break;
			case KeyEvent.VK_LEFT:
				x--;
				if(px<=1*INTERVAL)
					x++;
				else if(checkObject())
					moveStatus=3;
				else {x++; moveStatus=3;}


				break;

			case KeyEvent.VK_UP:

				y--;
				if(py<=1*INTERVAL)
					y++;
				else if(checkObject())
					moveStatus=0;
				else {y++; moveStatus=0;}

				break;
			case KeyEvent.VK_DOWN:

				y++;
				if(py>=(Y-1)*INTERVAL)
					y--;
				else if(checkObject())
					moveStatus=2;
				else {y--; moveStatus=2;}

				break;
			case KeyEvent.VK_A://돌도끼&돌곡괭이
				StoneLump sl = new StoneLump();
				m.item_list.add(sl);
				sl.itemNum+=7;
				WoodStick st =new WoodStick();
				m.item_list.add(st);
				st.itemNum+=4;
				break;
			case KeyEvent.VK_S:
				IronLump il = new IronLump();
				m.item_list.add(il);
				il.itemNum++;
				Flint f =new Flint();
				m.item_list.add(f);
				f.itemNum++;
				break;
			case KeyEvent.VK_I:
				JOptionPane.showMessageDialog(null, new JScrollPane(new JList(m.item_list.toArray())),
						"아이템 목록",JOptionPane.INFORMATION_MESSAGE);
				break;
			case KeyEvent.VK_E:
				JOptionPane.showMessageDialog(null, "장비(내구도)\n\n도끼 : "+m.my_ax.itemName+"("+m.my_ax.durability+")"+
			"\n곡괭이 : "+m.my_pickax.itemName+"("+m.my_pickax.durability+")",
			"장비",JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}

	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args){
		new GameMain();
	}
}
