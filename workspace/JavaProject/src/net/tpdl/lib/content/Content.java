package net.tpdl.lib.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import net.tpdl.lib.frame.Window;
import net.tpdl.lib.img.Img;
import net.tpdl.lib.point.Point;
import net.tpdl.lib.text.Text;


public class Content extends JPanel implements KeyListener{
	/**
	 *
	 */
	private static final long serialVersionUID = -706790674959951203L;
	String s = "";
	ArrayList<Text> strings = new ArrayList<Text>();
	ArrayList<Img> Imgs = new ArrayList<Img>();

	int Imgx = 0;
	int Imgy = 0;
	net.tpdl.lib.img.Img Img = null;
	Font f = null;
	Color c = null;
	public Content(Window w){
		setSize(w.getWidth(), w.getHeight());
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}





	////////////////////////Text Methods/////////////////////
	public void drawText(String s, int x, int y){
		strings.add(new Text(s,x,y));
		repaint();



	}
	/*
	public void getVectorText(String text){
		if(strings.contains(text)){



		}


	}
	 */
	public void moveText(final String s, final int x2, final int y2, final float speed){
		for(int i = 0; i < strings.size(); i++){

			final Text te = strings.get(i);
			if(te.getText().equals(s)){
				Point p = new Point(x2,y2);
				te.addPoint(p);

				if(!te.isMoving()){
					te.setMoving(true);

					Thread t = new Thread(new Runnable() {



						@Override
						public void run() {
							Point nextPoint = te.getNextPoint();
							while(nextPoint  != null){
								double distX = nextPoint.x-te.getX();
								double distY = nextPoint.y-te.getY();
								double mod = Math.sqrt(distX*distX + distY*distY);

								distX /= mod;
								distY /= mod;
								while(mod > speed){

									te.setX((float) (te.getX() + distX*speed/100));
									te.setY((float) (te.getY() + distY*speed/100));

									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									repaint();
									double distX2 = nextPoint.x-te.getX();
									double distY2 = nextPoint.y-te.getY();
									mod = Math.sqrt(distX2*distX2 + distY2*distY2);
								}
								nextPoint = te.getNextPoint();
							}
							te.setMoving(false);
						}
					});
					t.start();

				}
			}


		}




	}

	public void setFont(Font f){
		this.f = f;
	}

	public void setColor(Color c){
		this.c = c;
	}






	//////////////Img Methods////////////////////

	public void drawImg(Img Img, int x, int y){
		this.Img = Img;
		this.Imgx = x;
		this.Imgy = y;
		Imgs.add(Img);
		repaint();

	}
	public void moveImg(final Img Img, final int x2, final int y2, final int speed){
		for(int i = 0; i < Imgs.size(); i++){
			final Img te = Imgs.get(i);
			if(te.getImage() == Img.getImage()){
				final Point p = new Point(x2,y2);
				te.addPoint(p);

				if(!te.isMoving()){
					te.setMoving(true);
				Thread t = new Thread(new Runnable() {



					@Override
					public void run() {
						Point p = te.getNextPoint();
						while(p != null){
						double distX = p.x-te.getX();
						double distY = p.y-te.getY();
						double mod = Math.sqrt(distX*distX + distY*distY);

						distX /= mod;
						distY /= mod;
						while(mod > speed){

							te.setX((float) (te.getX() + distX*speed/100));
							te.setY((float) (te.getY() + distY*speed/100));

							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							repaint();
							double distX2 = p.x-te.getX();
							double distY2 = p.y-te.getY();
							mod = Math.sqrt(distX2*distX2 + distY2*distY2);
						}
						p = te.getNextPoint();
						}
						te.setMoving(false);
					}
					

				});
				t.start();


			}
			}

		}

	}










	///////////////////////Key Methods///////////////////









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
		for(int i = 0; i < strings.size(); i++){
			Text s = strings.get(i);
			s.paint(g);
		}
		for(int i = 0; i < Imgs.size(); i++){
			Img s = Imgs.get(i);
			s.paint(g);
		}


	}





	@Override
	public void keyPressed(KeyEvent e) {
		                
	}





	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}





	public void drawText(Text te) {
		strings.add(te);
	}




}
