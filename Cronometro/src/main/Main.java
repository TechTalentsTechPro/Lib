package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.tpdl.lib.content.Content;
import net.tpdl.lib.frame.Window;
import net.tpdl.lib.img.Img;
import net.tpdl.lib.text.Text;



public class Main {
	public static float cuenta;
	public static float cuenta30;
	private static long tiempoanterior;
	static Img img;
	static Img img2;


	/**
	 * @param args
	 */
	static Window w;
	static Content c;
	static Text te;
	static Text te30secs;
	public static boolean working;

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static void main(String[] args) {

		w = new Window();
		w.enableThis(true);
		c = new Contenido(w);
		
		w.addContent(c);
		BufferedImage imgMarco = procesar();
		Img fondo2 = new Img("imgs/wrologo.png",w.getWidth()/2, w.getHeight()/2);
		
		fondo2.setWidth((int)(fondo2.getWidth()*1.4));
		fondo2.setHeight((int)(fondo2.getHeight()*1.4));
		fondo2.setY(w.getHeight()/2-fondo2.getHeight()/2);
		fondo2.setX(w.getWidth()/2-fondo2.getWidth()/2);
		fondo2.setSx(1);
		fondo2.setSy(1);
		Img fondo = new Img(imgMarco,w.getWidth()/2,w.getHeight()/2);
		fondo.setWidth(1000);
		fondo.setHeight(1000);
		fondo.setX(w.getWidth()/2-fondo.getWidth()/2);
		fondo.setY(w.getHeight()/2-fondo.getHeight()/2);
		fondo.setSx(1);
		fondo.setSy(1);
		c.drawImg(fondo2, 0, 0);
		c.drawImg(fondo, 0,0);
		
		te = new Text("0:00.00",w.getWidth()/2-50,w.getHeight()/2+400);
		te30secs = new Text("00.00",w.getWidth()/2-50,w.getHeight()/2+350);
		img2 = new Img("imgs/ManecillaCorta.png",w.getWidth()/2-40,w.getHeight()/2-63);
		img = new Img("imgs/ManecillaLarga.png",w.getWidth()/2-40,w.getHeight()/2-38);
		img.rotate(-90);
		img2.rotate(-90);
		c.drawText(te);
		c.drawText(te30secs);
		c.drawImg(img2, 0, 0);
		c.drawImg(img , 100, 100);
		c.setFont(new Font("MyFont",Font.BOLD,50));
		c.setColor(Color.BLUE);
		img.setCenter(41, 42);
		img.setSx(0.7);
		img.setSy(0.7);
		img2.setCenter(41, 69);
		img2.setSx(0.7);
		img2.setSy(0.7);
		c.setBackground(Color.WHITE);



	}

	private static BufferedImage procesar() {
		try {
			BufferedImage img = ImageIO.read(new File("imgs/LogoTechTalents.png"));
			
			
			BufferedImage resizedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = resizedImage.createGraphics();
		    g2d.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
		    g2d.dispose();
			
			
			int ancho = resizedImage.getWidth();
			int alto = resizedImage.getHeight();
			for(int x = 0; x < ancho; x++){
				for(int y = 0; y < alto; y++){
					int co = resizedImage.getRGB(x, y);
					Color color = new Color(co);
					float r = color.getRed()/255f;
					float g = color.getGreen()/255f;
					float b = color.getBlue()/255f;
					Color c = new Color(r,g,b,0.5f);
					if(r >0){
						resizedImage.setRGB(x, y, c.getRGB());
					}
					
				}
			}
			
			
			
			return resizedImage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void emepezar() {
		c.setColor(Color.BLUE);
		working = true;
		cuenta30 = 0;
		cuenta = 0;
		tiempoanterior = 0;

		te.setText("0:00.00");
		te30secs.setText("00.00");

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while(cuenta30 <= 30 && cuenta < 120){


					long tiempoactual = System.currentTimeMillis();
					long tiempoTranscurrido;
					if(tiempoanterior == 0){
						tiempoTranscurrido = 0;
					}else{
						tiempoTranscurrido = tiempoactual - tiempoanterior;
					}
					tiempoanterior = tiempoactual;

					cuenta30 += tiempoTranscurrido/1000f;
					cuenta += tiempoTranscurrido/1000f;
					int cuentax = (int) (cuenta * 100.0);
					int cuentaz = (int)(cuenta30 * 100.0);

					te30secs.setText(""+cuentaz/100f);
					

					img2.rotate(cuenta*3-90);
					img.rotate(cuenta30*12-90);
					
					if(cuentax/100 >= 60.0){
						te.setText("01:"+((cuentax-6000)/100f));
					}else{
						te.setText("00:"+cuentax/100f);
					}
					if(cuentaz/100f == 30.0){
						c.setColor(Color.RED);

					}
					if(cuentax/100f >= 120.0){
						te30secs.setText("");
						te.setText("Tiempo Finalizado");
					}
					c.repaint();
					if(!working){break;}

				}
				



			}
		});

		t.start();
	}
	public static void terminado(){
		working = false;
	}

	public static void respirar(){
		cuenta30 = 0;

	}
	public static void parar() {
		working = false;
		cuenta30 = 0;
		cuenta = 0;
		te.setText("0:00.00");
		te30secs.setText("00.00");
	}

}
