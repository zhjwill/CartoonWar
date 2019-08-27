package com.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.action.ActionAble;
import com.client.GameClient;
import com.constant.Constant;
import com.util.GetImageUtil;

public class Boos extends Mouse implements ActionAble {
	private GameClient gc;
	private int speed = 2;
	private boolean up;
	public Boos() {
		
	}
	public Boos(int x,int y,GameClient gc,boolean IsGood) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.IsGood = IsGood;
		this.blood = 100;
	}
    //����һ������ͼƬ
	private static Image[] images = new Image[7];
	static
	{
		for(int i = 0;i<images.length;i++) {
			images[i] = GetImageUtil.getImg("com/img/boos/lvshuiling0"+(i+1)+".png"); 
		}
	}
	@Override
	public void move() {
	    x -= speed;
	    if(x<1150) {
	    	x = 1150;
	    	if(up) {
	    		y -= speed;//������
	    	}
	    	if(!up) {
	    		y += speed;
	    	}
	    	//��֤�ɻ����ܷɳ�ȥ�߿���
	    	if(y>=Constant.GAMEHEIGHT-images[0].getHeight(null)) {
	    		up = true;
	    	}
	    	if(y<30) {
	    	    up = false;	
	    	}
	    }
	    if(random.nextInt(500)>450) {
	    	//����������һ�������
	    fire();
	    }
	}
	Random random = new Random();
    int count = 0; 
    public void draw(Graphics g) {
        if(count>6) {
        	count = 0;
        }
        g.drawImage(images[count++],x,y,null);
        move();
        g.drawString("��ǰѪ��Ϊ��"+blood, x, y);
    }
  //��ȡ��boos�ľ���
  	public Rectangle getRec() {
  		return new Rectangle(x,y,images[1].getWidth(null),images[1].getHeight(null));
//  		System.out.println("ewgewrgeg");
  	}
  	//����
  	public void fire() {
  		play.play("com/sound/SOUND_BOOSTER_COIN.mp3");
    	Bullet bullet = new Bullet(x-50,y+images[1].getHeight(null)/2,"com/img/bullet/BULLET_CHAR_ANNA_01.png",gc,false);
    	gc.bullets.add(bullet);
  	}
}
