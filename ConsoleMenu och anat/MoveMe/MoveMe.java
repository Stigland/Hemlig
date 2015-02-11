package com.sandklef.edu.MoveMe;

import java.util.ArrayList;


public class MoveMe  {

    
    public static void createGui() {
	MoveMeControl m = new MoveMeControl(600,500, 10, 10);
    }

    public static void main(String args[]) {
	
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createGui();
		}
	    });
    }

}