package com.sandklef.edu.koder;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;//   constraints;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import java.awt.Font;

public class CodeTab extends JPanel implements ActionListener {

    public static final int CODE_TEXT_TYPE   = 0;
    public static final int RESULT_TEXT_TYPE = 1;
    public static final int MAIN_TEXT_TYPE   = 2;

    private static JTextArea resultTextArea;

    private JTextArea textArea ;
    private GridBagConstraints   constraints;
    private int type;
    private JButton close;
    private JTabbedPane pane;
    private JScrollPane jsp;
    JPanel panelTab ;
    JPanel contentTab ;

    public CodeTab(int x, int y, String title, int type, JTabbedPane pane) {
	super();

	panelTab = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	contentTab = this;
	//	panelTab=this;

	panelTab.setOpaque(false);

	textArea = new JTextArea(x*2/3, y*2/3);
	jsp = new JScrollPane(textArea);
	contentTab.add(jsp);

	if (type==MAIN_TEXT_TYPE) {
	    System.out.println("MAIN MAIN MAIN");
	    resultTextArea = new JTextArea(x/3,y/3);
	    contentTab.add(new JScrollPane(resultTextArea));
	}
	
	
	Font font = textArea.getFont();
	float size = font.getSize() + 10.0f;
	textArea.setFont( font.deriveFont(size) );

	pane.addTab(null, contentTab);
	int pos = pane.indexOfComponent(contentTab);

	JLabel lblTitle = new JLabel(title);
	//	lblTitle.setIcon(icon);

	this.pane = pane;
	close = new JButton("x");
	close.addActionListener(this);

	panelTab.add(lblTitle);
	if (pos>1) {
	    panelTab.add(close);
	}
	panelTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	pane.setTabComponentAt(pos, panelTab);

	close.setPreferredSize(new Dimension(15, 15));
	close.setToolTipText("close this tab");

	this.type=type;
	if (type==CODE_TEXT_TYPE) {
	    textArea.setText("public class YourClassname {\n\n  //int myInteger;\n  //String myString;\n\n\n\n\n\n\n\n}");
	} else if (type==RESULT_TEXT_TYPE) {
	    textArea.setEditable(false);
	} else if (type==MAIN_TEXT_TYPE) {
	    textArea.setText("\n\n\n\n\n\n\n\n\n");
	}

	setVisible(true);
    }

    public String getText() {
	return textArea.getText();
    }
    
    public void setText(String str) {
	textArea.setText(str);
    }
    
    public void append(String str) {
	textArea.append(str);
    }
    
    private String getFirstLines(String className) {
	String ret = " /* Generated class section */\n" ;
	ret       += " /* DO NOT EDIT */\n" ;
	ret       += "\n" ;
	ret       += "\n" ;
	ret       += "public class " + className + " {\n" ;
	ret       += "\n" ;
	ret       += "\n" ;
	ret       += "    public static void main (String args[])  {\n" ;
	ret       += "\n" ;
	return ret;
    }


    private String getLastLines(String className) {
	String ret = "    } \n" ;
	ret       += "}\n" ;
	return ret;
    }


    public void createFile(String fileName) {
	
	try {
	    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
	    String[] lines = textArea.getText().split("\\n");
	    
 	    if (type==MAIN_TEXT_TYPE) {
		writer.println(getFirstLines(fileName.replace(".java","")));
	    }
	    
	    for (int i=0;i<lines.length;i++) {
		writer.println(lines[i]);
	    }

 	    if (type==MAIN_TEXT_TYPE) {
		writer.println(getLastLines(fileName));
	    }

	    writer.close();
	} catch (FileNotFoundException e) {
	    System.err.println(e);
	    return;
	} catch (UnsupportedEncodingException e) {
	    System.err.println(e);
	    return;
	}
    }

    public String getFileName() {
	String[] lines = textArea.getText().split("\\n");
	String myClassName = null;
	for (int i=0;i<lines.length;i++) {
	    Scanner s = new Scanner(lines[i]);
	    //System.out.println("Scan line: " + lines[i]);
	    while (s.hasNext()) {
		String token = s.next();
		if ( "class".equals(token) && s.hasNext() ) {
		    myClassName = s.next();

		    createFile(myClassName+".java");
		    return myClassName+".java";
		}
	    }
	}
	return myClassName;
    }

    public void actionPerformed(ActionEvent e)    {
	// int i = this.indexOfTabComponent(.this);
	// if (i != -1) {
	//     pane.remove(i);
	// }
	int i = pane.indexOfComponent(this);

	System.out.println("AP: " + i + " " + pane.getTitleAt(i));
	
	if (i>1) {
	    pane.remove(i);
	}
    }


    public int getType() {
	return type;
    }

}

