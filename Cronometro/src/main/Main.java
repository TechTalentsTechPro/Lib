package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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

	public static void main(String[] args) {

		w = new Window();
		w.enableThis(true);
		c = new Contenido(w);
		
		w.addContent(c);
		BufferedImage imgMarco= procesar();
		Img fondo2 = new Img("imgs/wrologo.png",w.getWidth()/2, w.getHeight()/2);
		fondo2.setHeight(w.getHeight());
		fondo2.setWidth(w.getWidth());
		fondo2.setY(w.getHeight()/2);
		fondo2.setX(w.getWidth()/2);
		Img fondo = new Img(imgMarco,w.getWidth()/2-380,w.getHeight()/2-525);
		fondo.setWidth(1200);
		fondo.setHeight(1200);
		c.drawImg(fondo2, 0, 0);
		c.drawImg(fondo, 0,0);
		
		te = new Text("0:00.00",w.getWidth()/2-50,w.getHeight()/2+400);
		te30secs = new Text("00.00",w.getWidth()/2-50,w.getHeight()/2+350);
		img2 = new Img("imgs/ManecillaCorta.png",w.getWidth()/2,w.getHeight()/2-175);
		img = new Img("imgs/ManecillaLarga.png",w.getWidth()/2,w.getHeight()/2-150);
		img.rotate(-90);
		img2.rotate(-90);
		c.drawText(te);
		c.drawText(te30secs);
		c.drawImg(img2, 0, 0);
		c.drawImg(img , 100, 100);
		c.setFont(new Font("MyFont",Font.BOLD,50));
		c.setColor(Color.BLUE);
		img.setCenter(41, 42);
		img2.setCenter(41, 69);
		c.setBackground(Color.WHITE);



	}

	private static BufferedImage procesar() {
		try {
			BufferedImage img = ImageIO.read(new File("imgs/LogoTechTalents.png"));
			
			int ancho = img.getWidth();
			int alto = img.getHeight();
			for(int x = 0; x < ancho; x++){
				for(int y = 0; y < alto; y++){
					if(img.getRGB(x, y) == Color.WHITE.getRGB()){
						img.setRGB(x, y, Color.TRANSLUCENT);
					}
				}
			}
			
			
			
			return img;
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
					te.setText("0:"+cuentax/100f);

					img2.rotate(cuenta*3-90);
					img.rotate(cuenta30*12-90);
					if(cuentax == 60.0){
						cuentax = 0;
						te.setText("1:"+cuentax/100f);


					}
					if(cuentaz/100f == 30.0){
						c.setColor(Color.RED);

					}
					if(cuentax/100f == 120.0){
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
