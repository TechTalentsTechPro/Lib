package net.lib.frame.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Content extends JPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = -706790674959951203L;
	String s = "";
	ArrayList<String> strings = new ArrayList<String>();
	ArrayList<Img> Imgs = new ArrayList<Img>();

	int x = 0;
	int Imgx = 0;
	int Imgy = 0;
	net.lib.frame.window.Img Img = null;
	int y = 0;
	Font f = null;
	Color c = null;
	public Content(Window w){
		setSize(w.getWidth(), w.getHeight());
		setVisible(true);
	}





	////////////////////////Text Methods/////////////////////
	public void drawText(String s, int x, int y){
		this.x = x;
		this.y = y;
		this.s = s;
		strings.add(s);
		repaint();



	}
	/*
	public void getVectorText(String text){
		if(strings.contains(text)){



		}


	}
	*/
	public void moveText(final String s, final int x2, final int y2, final int speed){
		if(strings.contains(s)){
			Thread t = new Thread(new Runnable() {



				@Override
				public void run() {
					int newX = 0;
					if(x2 > x){
						newX = x2-x;
					}
					else if(x2 < x){
						newX = x-x2;

					}
					int newY = 0;
					if(y2 > y){
						newY = y2-y;
					}
					else if(y2 < y){
						newY = y-y2;


					}


					for(int x3 = x; x3 < newX; x3++){
						for(int y3 = y; y3 < newY; y3++){
							repaint();
							x++;
							y++;
							try {
								Thread.sleep(speed);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}


						}


					}


				}
			});
			t.start();
		}

	}
	public int getXText(String s){
		if(strings.contains(s)){
			return x;
		}
		return 0;

	}
	public int getYText(String s){
		if(strings.contains(s)){
			return y;
		}
		return 0;

	}
	public void setFont(Font f){
		this.f = f;
	}

	public void setColor(Color c){
		this.c = c;
	}






//////////////Img Methods////////////////////
	public int getXImg(Img Img){
		if(Imgs.contains(Img)){
			return x;
		}
		return 0;

	}
	public int getYImg(Img Img){
		if(Imgs.contains(Img)){
			return y;
		}
		return 0;

	}
	public void drawImg(Img Img, int x, int y){
		this.Img = Img;
		this.Imgx = x;
		this.Imgy = y;
		Imgs.add(Img);
		repaint();

	}
	public void moveImg(final Img Img, final int x2, final int y2, final int speed){
		if(Imgs.contains(Img)){
			Thread t = new Thread(new Runnable() {



				@Override
				public void run() {
					int newX = 0;
					if(x2 > Imgx){
						newX = x2-Imgx;
					}
					else if(x2 < Imgx){
						newX = Imgx-x2;

					}
					int newY = 0;
					if(y2 > Imgy){
						newY = y2-Imgy;
					}
					else if(y2 < Imgy){
						newY = Imgy-y2;


					}


					for(int x3 = x; x3 < newX; x3++){
						for(int y3 = y; y3 < newY; y3++){
							repaint();
							Imgx++;
							Imgy++;
							try {
								Thread.sleep(speed);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}


						}


					}


				}
			});
			t.start();
		}
///////////////////////Key Methods///////////////////
		
		
	}
public boolean keyPress(final int keycode){
	setFocusable(true);
	requestFocus();
	boolean isTrue = false;
	addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			boolean isTrue2 = false;
		    int key = e.getKeyCode();

		    if(key == keycode){
		    	isTrue2 = true;
		    }
		    isTrue2 = false;
		    isTrue = isTrue2;
		}
		});
	
	
	return false;
}





///////////////////////Paint////////////////////////
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(f != null){
			g.setFont(f);
		}
		if(c != null){
			g.setColor(c);
		}
		g.drawString(s, x, y);
		if(Img != null){
			g.drawImage(Img.getImage(), Imgx, Imgy, null);
		}
	}




}
