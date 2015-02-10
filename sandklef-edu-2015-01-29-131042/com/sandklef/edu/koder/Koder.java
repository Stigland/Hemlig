package com.sandklef.edu.koder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;//   constraints;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.util.GregorianCalendar;

public class Koder extends JFrame implements ActionListener{

    private GridBagConstraints   constraints;
    private JTabbedPane tabbedPane;
    private JButton run;
    private JButton newTab;
    private int     codeTabs ;
    private int     width;
    private int     height;


    public void addTabbedPane(int type, 
			      String title) {

	CodeTab ct = new CodeTab(height/15, width/20, title, type, tabbedPane);
	
    }

    public Koder(int  x, int y) {
	height     = y;
	width      = x;
	tabbedPane = new JTabbedPane();
	JPanel control = new JPanel();
	run      = new JButton("Run");
	newTab   = new JButton("New Tab");
	codeTabs = 0;

	getContentPane().setLayout(new BoxLayout(getContentPane(), 
						 BoxLayout.Y_AXIS));
	


	control.add(newTab);
	control.add(run);
	add(control);
	add(tabbedPane);
	// constraints = new GridBagConstraints();
	// constraints.weightx = 1.0;
	// constraints.weighty = 1.0;
	addTabbedPane(CodeTab.RESULT_TEXT_TYPE, "Result");
	addTabbedPane(CodeTab.MAIN_TEXT_TYPE,   "Execute");

	pack();
	setMinimumSize(new Dimension(width, height));
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	run.addActionListener(this);
	newTab.addActionListener(this);

    }
    
    public void actionPerformed(ActionEvent e)
    {
	Object o = e.getSource();
	if (o==run) {
	    handleRun();
	} else if (o==newTab) {
	    handleNewTab();
	}
    }
    
    public void handleRun() {
	int totalTabs = tabbedPane.getTabCount();

	/* 
	 *
	 *  Build / Compile
	 *
	 */ 
	String res="";
	for(int i = 0; i < totalTabs; i++)
	    {
		Component c = tabbedPane.getComponentAt(i);
		CodeTab ct  = (CodeTab) c;

		if (ct.getType() == CodeTab.CODE_TEXT_TYPE ) {
		    String fName = ct.getFileName();
		    System.out.println("You clicked the button, file: " + fName);
		    String output=null;
		    int ret = Builder.compile(fName);
		} else if (ct.getType() == CodeTab.MAIN_TEXT_TYPE ) {
		    String fName = "Main.java";
		    ct.createFile(fName);
		    String output=null;
		    int ret = Builder.compile(fName);
		    if (ret!=0) {
			res += "Build " + fName + " output [ " + new GregorianCalendar().getTime() + " ]\n";
			res += "--------------------------------------\n";
			res += Builder.getLatestErr() +"\n";
			res += "--------------------------------------\n";
			res += "\n";
		    } else {
			res += "Build " + fName + " output [ " + new GregorianCalendar().getTime() + " ]\n";
			res += "--------------------------------------\n";
			res += " OK \n";
			res += "--------------------------------------\n";
			res += "\n";
		    }
		}
		
	    }

	

	/* 
	 *
	 *  Execute / Run
	 *
	 */ 
	int ret = Builder.run("Main");
	
	for(int i = 0; i < totalTabs; i++)
	    {
		Component c = tabbedPane.getComponentAt(i);
		CodeTab ct = (CodeTab) c;
		if (ct.getType() == CodeTab.RESULT_TEXT_TYPE ) {
	
		    /* build output */
		    ct.append(res);
		    
		    ct.append("Execution output [ " + new GregorianCalendar().getTime() + " ]\n");
		    ct.append("--------------------------------------\n");
		    if (ret==0) {
			ct.append(Builder.getLatestOut() +"\n");
		    } else {
			ct.append(Builder.getLatestErr() +"\n");
		    }
		    ct.append("--------------------------------------\n");
		    ct.append("\n");
		}
	    }
	
    }
    
    public void handleNewTab() {
	codeTabs++;
	String nr = String.format("%02d", codeTabs);
	addTabbedPane(CodeTab.CODE_TEXT_TYPE,   "Code #" + nr );
    }
    
    public static void main(String args[]) {
	final Koder k = new Koder(1024,768);
	
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    k.setVisible(true);
		}
	    });
	
	
    }
    
}
