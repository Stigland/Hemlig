package com.sandklef.edu.MoveMe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



public class MoveMeControl extends JFrame  {
    
    ControlPanel control;
    FindControl  finder;
    Field f;


    /**
     *
     * Creates a MoveMeControl (size: 400x400)
     *
     * @param cols number of columns
     * @param rows number of rows
     *
     */
    public MoveMeControl (int cols, int rows) {
	this(400,400,cols,rows);
    }

	
    public MoveMeControl (int xSize, int ySize, int cols, int rows) {
	f = new Field(xSize-100, ySize, cols, rows);
	setLayout(new GridLayout(1,2));
	control = new ControlPanel (f);
	finder  = new FindControl (f);

	setMinimumSize(new Dimension(xSize,ySize));
	add(f);
	add(finder);
	add(control);
	pack();
	setVisible(true);
 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Field getField() {
	return f;
    }
    

}