package net.tpdl.lib.frame;

import javax.swing.JFrame;

import net.tpdl.lib.content.Content;



public class Window extends JFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 7367449566300032427L;
	public void enableThis(boolean enabled){
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(enabled){
			setVisible(true);
			setEnabled(true);
		}
		else{
			setVisible(false);
			setEnabled(false);
		}


	}


	public void addContent(Content c){
		setContentPane(c);

	
	
		
	}

}
