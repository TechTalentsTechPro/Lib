package main;


import net.tpdl.lib.content.Content;
import net.tpdl.lib.frame.Window;
import net.tpdl.lib.img.Img;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	Window w = new Window();
	w.enableThis(true);
	Content c = new Content(w);
	w.addContent(c);
	Img img1 = new Img("C:\\Users\\Alumno\\Pictures\\Dados.png",1000, 1000);
	Img img2 = new Img("C:\\Users\\Alumno\\Pictures\\Dados.png",1000, 1000);
	c.drawImg(img1,1000,1000);
	c.drawImg(img2, 1000, 1000);
	c.moveImg(img1, 100, 100, 100);
	c.moveImg(img2, 1300, 100, 100);


	}

}
