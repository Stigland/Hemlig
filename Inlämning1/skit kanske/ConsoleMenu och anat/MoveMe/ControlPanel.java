package com.sandklef.edu.MoveMe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JPanel implements ActionListener{

    JButton left;
    JButton right;
    JButton up;
    JButton down;

    Field f;

    public ControlPanel(Field f) {
	this.f = f;
	setMinimumSize(new Dimension(2, 20));
	setVisible(true);
	setLayout(new GridLayout(3,1));

	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new GridBagLayout());
	GridBagLayout layout = (GridBagLayout)buttonPanel.getLayout();
	GridBagConstraints c = (GridBagConstraints) layout.getConstraints(this);

	left  = new JButton(" Left ");
	right = new JButton(" Right");
	up    = new JButton("  Up  ");
	down  = new JButton(" Down ");
	
	left.addActionListener(this);
	right.addActionListener(this);
	up.addActionListener(this);
	down.addActionListener(this);

	c.gridx = 0;
	c.gridy = 1;	
	buttonPanel.add(left, c);
	c.gridx = 3;
	c.gridy = 1;	
	buttonPanel.add(right, c);
	c.gridx = 2;
	c.gridy = 0;	
	buttonPanel.add(up, c);
	c.gridx = 2;
	c.gridy = 3;	
	buttonPanel.add(down, c);

	add(buttonPanel);
    }

    public void actionPerformed(ActionEvent e) {
	Object o = e.getSource();
	if (o==left) {
	    System.out.println("Left");
	    f.moveLeft();
	} else if (o==right) {
	    System.out.println("Right");
	    f.moveRight();
	} else if (o==up) {
	    System.out.println("Up");
	    f.moveUp();
	} else if (o==down) {
	    System.out.println("Down");
	    f.moveDown();
	}
	if (f.isStop()) {
	    System.out.println("Goal!!!");
	}
    }
}