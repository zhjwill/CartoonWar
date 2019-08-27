package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import com.action.ActionAble;
import com.constant.Constant;
import com.util.GetImageUtil;

/**
* @ClassName: ������
* @Description: TODO
* @author jin
* @date 2019��8��20�� ����3:20:46
*
*/
public class Prop extends Gameobj implements ActionAble {
	private double theta = Math.PI/4;
	private int speed;
    public Prop() {
		// TODO Auto-generated constructor stub
	}
    public Prop(int x,int y,String imgName) {
    	this.x = x;
    	this.y = y;
    	this.img = GetImageUtil.getImg(imgName);
    	this.speed = 5;
    }
	@Override
	
	public void move() {
	    x+=speed*Math.cos(theta);
	    y+=speed*Math.sin(theta);
	    if(x<0||x>Constant.GAMEWIDTH-img.getWidth(null)) {
	    	theta = Math.PI-theta;
	    }
	    if(y<35||y>Constant.GAMEHEIGHT-img.getHeight(null)) {
	    	theta = -theta;
	    }
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);
		move();
	}
    //�õ���ǰ���ߵľ���
	public Rectangle getRect() {
  		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
  	}
}
