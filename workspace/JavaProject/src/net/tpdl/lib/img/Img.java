package net.tpdl.lib.img;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.tpdl.lib.point.Point;

public class Img{
	private Image img = null;
	private float x;
	private float y;
	private List<Point> points = new ArrayList<Point>();
	private boolean isMoving;
	private AffineTransform at;
	private double degrees;
	private int centrox;
	private int centroy;
	private double sx;
	private double sy;
	public Img(String path, float x, float y){
		at = AffineTransform.getTranslateInstance(x, y);

		this.x = x;
		this.y = y;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException("Error de lectura: " + path);
		}



	}
	public Img(BufferedImage img, float x, float y){
		at = AffineTransform.getTranslateInstance(x, y);

		this.x = x;
		this.y = y;

			this.img = img;



	}
	


	public void setHeight(int newHeight){
		img = img.getScaledInstance(img.getWidth(null), newHeight, Image.SCALE_SMOOTH);



	}
	
	public void setWidth(int newWidth){
		img = img.getScaledInstance(newWidth, img.getHeight(null), Image.SCALE_SMOOTH);

	}
	public int getHeight(){
		return img.getHeight(null);
		


	}
	
	public int getWidth(){
		return img.getWidth(null);

	}
	public Image getImage(){
		return img;
		
		
	}
	
	public void rotate(float degrees){
		 this.degrees = degrees;
	}
	
	public Point getNextPoint(){
		if(points.isEmpty())return null;
		Point p = points.get(0);
		points.remove(p);
		return p;
	}
	
	public void addPoint(Point p){
		points.add(p);
	}
	

	public float getY() {
		return y;
	}



	public void setY(float y) {
		this.y = y;
	}



	public float getX() {
		return x;
	}



	public void setX(float x) {
		this.x = x;
	}
	
	public void paint(Graphics g){
		at.setToIdentity();
		
		at.translate(x+centrox, y+centroy);
		at.rotate(Math.toRadians(degrees));
		at.scale(sx, sy);
		at.translate(-centrox, -centroy);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, at, null);
		
	}



	public void setImage(Image image) {
		this.img = image;
	}




	public boolean isMoving() {
		return isMoving;
	}



	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	
	public void setCenter(int x , int y){
		this.centrox = x;
		this.centroy = y;
	}
	public double getSx() {
		return sx;
	}
	public void setSx(double sx) {
		this.sx = sx;
	}
	public double getSy() {
		return sy;
	}
	public void setSy(double sy) {
		this.sy = sy;
	}
	
}
