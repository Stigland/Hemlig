package com.sandklef.edu.MoveMe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ControlPanel extends JPanel {

    public ControlPanel() {
	setMinimumSize(new Dimension(2, 20));
	setVisible(true);
	setLayout(new GridLayout(3,1));

	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new GridBagLayout());
	GridBagLayout layout = (GridBagLayout)buttonPanel.getLayout();
	GridBagConstraints c = (GridBagConstraints) layout.getConstraints(this);

	c.gridx = 0;
	c.gridy = 1;	
	buttonPanel.add(new JButton("Left "), c);
	c.gridx = 3;
	c.gridy = 1;	
	buttonPanel.add(new JButton("Right"), c);
	c.gridx = 2;
	c.gridy = 0;	
	buttonPanel.add(new JButton(" Up  "), c);
	c.gridx = 2;
	c.gridy = 3;	
	buttonPanel.add(new JButton("Down "), c);

	add(buttonPanel);
    }

}