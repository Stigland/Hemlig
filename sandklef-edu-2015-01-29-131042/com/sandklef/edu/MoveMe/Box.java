package com.sandklef.edu.MoveMe;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Box extends JButton implements ActionListener {

    private int width;
    private int height;

    private boolean barrier;
    private boolean stop;
    private boolean start;
    private boolean foot;
    private Point   p;

    public Box (int width, int height, int xPos, int yPos) {
	super("");

	this.width = width;
	this.height = height;
	barrier=false;
	foot=false;
	start=false;
	stop=false;

	p = new Point(xPos, yPos);
	setOpaque(true);
	setVisible(true);
	setBorder(BorderFactory.createLineBorder(Color.black));

	setBackground(Color.LIGHT_GRAY);

	this.addActionListener(this);
    }

    // public Box (int size, int xPos, int yPos) {
    // 	this(size, size, xPos, yPos);
    // }

    public void setBarrier() {
	if (start||stop) return;
	setBackground(Color.RED);
	setText("");
	barrier=true;
    }

    public void unSetBarrier() {
	if (start||stop) return;
	setBackground(Color.WHITE);
	barrier=false;
    }

    public boolean isBarrier() {
	return barrier;
    }

    public void setStart() {
	setBackground(Color.GREEN);
	barrier=false;
	start=true;
	setText("Start");
    }

    public boolean isStop() {
	return stop;
    }

    public boolean isStart() {
	return start;
    }

    public void setStop() {
	setBackground(Color.YELLOW);
	setText("Goal");
	barrier=false;
	stop=true;
    }

    public void setBeenHere(String s) {
	setBackground(Color.YELLOW);
	setText(getText()+ ", " + s);
	foot=true;
    }

    public void setPath(String s) {
	setBackground(Color.GREEN);
	setText(getText()+ ", " + s);
	foot=true;
    }

    public void unSetPath() {
	setBackground(Color.LIGHT_GRAY);
	setText("");
	foot=false;
    }

    public boolean isPath() {
	return foot;
    }

    public int getCol() {
	return  p.getCol();
    }
			     
    public int getRow() {
	return  p.getRow();
    }


    public void actionPerformed(ActionEvent e) {
	if (isBarrier()) {
	    unSetBarrier();
	} else {
	    setBarrier();
	}
    }
	

}