/*
 *                                                                   
 *         Educational software
 *                      
 *   Copyright (C) 2014 Henrik Sandklef 
 *                                                                   
 * This program is free software; you can redistribute it and/or     
 * modify it under the terms of the GNU General Public License       
 * as published by the Free Software Foundation; either version 3    
 * of the License, or any later version.                             
 *                                                                   
 *                                                                   
 * This program is distributed in the hope that it will be useful,   
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the     
 * GNU General Public License for more details.                      
 *                                                                   
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software       
 * Foundation, Inc., 51 Franklin Street, Boston,            
 * MA  02110-1301, USA.                                              
 ****/

/**
 *
 * Simple class to ease up console menus
 *
 */
package com.sandklef.edu.ConsoleMenu;

import java.util.ArrayList;
import java.util.Scanner; 

public class ConsoleMenu implements MenuItem {

    private int    levels;
    private final String levelMark = " ";
    private String preMenu="";
    private String title;
    private ArrayList<InternalMenuItem> menuItems;
    boolean debugMode = false;

    class InternalMenuItem {
	MenuItem m;
	String title;

	InternalMenuItem(String title, MenuItem m) {
	    this.m     = m;
	    this.title = title;
	}

	public String getTitle() {
	    return this.title;
	}

	public MenuItem getMenuItem() {
	    return this.m;
	}
    }


    /* We can (hrmpfsfs?) assume that is CM itself who made this call */
    public void menuItemSelected(ConsoleMenuEvent e) {
	String itemTitle = e.getMenuItemTitle();
	String menuTitle = e.getMenuTitle();
	//	System.out.println("INTERNAL PRESS" + itemTitle + " " + menuTitle);
	String savedPreMenu = preMenu;
	if (preMenu.equals("")) {
	    preMenu = menuTitle;
	} else {
	    preMenu = preMenu + "->" + menuTitle;
	}
	levels++;
	run();
	levels--;
	preMenu = savedPreMenu;
    }
    

    /**
     *
     * Constructs a ConsoleMenu with default Menu title: "ConsoleMenu"
     *
     */
    public ConsoleMenu() {
	title = "ConsoleMenu";
	menuItems = new ArrayList<InternalMenuItem>();
	debugMode = false;
    }

    /**
     *
     * Constructs a ConsoleMenu with title as given by user
     *
     * @param s Title for ConsoleMenu
     */
    public ConsoleMenu(String s) {
	this();
	setMenuTitle(s);
    }

    /**
     *
     * Sets the title of the ConsoleMenu
     *
     * @param s Title for ConsoleMenu
     */
    public void setMenuTitle(String s) {
	title = s;
    }

    /**
     *
     * Gets the title of the ConsoleMenu
     *
     * @return the title for ConsoleMenu
     */
    public String getMenuTitle() {
	return title ;
    }

    private void debug(String s) {
	if (debugMode) {
	    System.out.println(s);
	}
    }

    public void setDebug() {
	debugMode = true;
    }

    public void unSetDebug() {
	debugMode = false;
    }


    public void printNewLines(int times) {
	for (int k=0;k<times;k++) {
	    System.out.println("\n");
	}
    }

    public void printLevelMarks() {
	for (int k=0;k<levels;k++) {
	    System.out.print(levelMark);
	}
	System.out.print(" ");
    }

    /**
     *
     * Displays the menu containing all menu items added by user.  An
     * extra menu item is added at the end which provides a way to
     * exit the ConsoleMenu
     *
     * The menu loops intil exit is chosen.
     *
     */
    public void run() {
	while (true) {
	    printNewLines(1);
	    if (!preMenu.equals("")) {
		System.out.println("[ " + preMenu + "] ==> ");
	    }
	    printLevelMarks();	    
	    System.out.println(title);
	    printLevelMarks();	    
	    System.out.println("=============");
	    int i=0;
	    for (i=0; i<menuItems.size(); i++) {
		printLevelMarks();
		System.out.println(i + ".  " + 
				   menuItems.get(i).getTitle());
	    }
	    printLevelMarks();	    
	    System.out.println(i + ".  Exit menu");
	    
	    System.out.print("> ");
	    Scanner userInput = new Scanner(System.in);
	    String choice = userInput.nextLine();
	    
	    /* setting the index to max value (last in menu)......
	     * 
	     */
	    int index = menuItems.size();
	    try {
		// Try to parse the user input tp an integer
		index = Integer.parseInt(choice);
	    } catch (java.lang.NumberFormatException e) {
		;
	    }
	    if ( index >= 0 && index < menuItems.size() ) {
		InternalMenuItem mi = menuItems.get(index);
		ConsoleMenuEvent ce = new ConsoleMenuEvent(mi.getTitle(),
							   getMenuTitle());
		mi.getMenuItem().menuItemSelected(ce);
	    } else if ( index == menuItems.size() ) {
		return;
	    } else {
		System.out.println("Invalid input (\"" +  choice +"\")");
	    }
	    printNewLines(4);
	}
    }

    /**
     *
     *@param m - menuitem containing the code to perform when chosen
     *@param s - title for the menu item
     *
     */
    public void addMenuItem(MenuItem m, String s) {
	debug("Adding MenuItem: \"" + " \"" + s + "\" ");
	menuItems.add(new InternalMenuItem(s, m));
    }

    /**
     *
     *@param c - menuitem containing the code to perform when chosen
     *@param s - title for the menu item
     *
     */
    public void addMenuItem(ConsoleMenu m) {
	debug("Adding consolemenu: \"" + " \"" + m + "\" ");
	menuItems.add(new InternalMenuItem(m.getMenuTitle(),m));
    }

}