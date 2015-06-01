package main;


import java.awt.Font;
import java.awt.event.KeyEvent;

import net.tpdl.lib.content.Content;
import net.tpdl.lib.frame.Window;
import net.tpdl.lib.img.Img;
import net.tpdl.lib.text.Text;



public class Main {
	public static float cuenta;
	public static float cuenta30;
	private static long tiempoanterior;


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
		te = new Text("0:00.00",w.getWidth()/2-50,w.getHeight()/2+200);
		te30secs = new Text("00.00",w.getWidth()/2-50,w.getHeight()/2+150);

		c.drawText(te);
		c.drawText(te30secs);

		c.setFont(new Font("MyFont",Font.BOLD,50));




	}
	
	public static void emepezar() {
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
						if(cuentax == 60.0){
							cuentax = 0;
							te.setText("1:"+cuentax/100f);

						
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
