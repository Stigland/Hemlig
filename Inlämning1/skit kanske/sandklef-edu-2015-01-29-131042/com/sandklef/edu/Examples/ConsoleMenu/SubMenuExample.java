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

package com.sandklef.edu.Examples.ConsoleMenu;

import com.sandklef.edu.ConsoleMenu.*;

public class SubMenuExample extends ConsoleMenu implements MenuItem {

    private final String LIST_MENU_ITEM   = "List Members";
    private final String PLAYER_MENU_ITEM = "Player";
    private final String PARENT_MENU_ITEM = "Parent";
    private final String COACH_MENU_ITEM  = "Coach";

    public void buildMenu() {
	/* 
	 * Top menu
	 *
	 */
	setMenuTitle("Member management");

	/*
	 * Sub menu: List Members
	 *
	*/
	ConsoleMenu listMenu = new ConsoleMenu(LIST_MENU_ITEM);

	listMenu.addMenuItem(this, PLAYER_MENU_ITEM);
	listMenu.addMenuItem(this, PARENT_MENU_ITEM );
	listMenu.addMenuItem(this, COACH_MENU_ITEM );

	// Adding listMenu to it self
	// A bit stupid, but good for layout test and for making sure
	// sub menues are created as they should
	listMenu.addMenuItem(listMenu, LIST_MENU_ITEM );


	// Add listMenu to top menu
	addMenuItem(listMenu, LIST_MENU_ITEM);

	// Add remove menu item to top menu
	addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) {
		    String itemTitle = e.getMenuItemTitle();
		    String menuTitle = e.getMenuTitle();
		    System.out.println(" removeMember() code"); } 
	    },"Remove member" );

	// Display the menu
	run();
    }
   

    public void menuItemSelected(ConsoleMenuEvent e) {
	String itemTitle = e.getMenuItemTitle();
	String menuTitle = e.getMenuTitle();

	System.out.println("menuItemSelected(\"" + itemTitle + "\", \"" + menuTitle + "\") ");

	if (menuTitle.equals(LIST_MENU_ITEM)) { 
	    if (itemTitle.equals(PLAYER_MENU_ITEM)) {
		System.out.println(" Will list players...");
	    } else if (itemTitle.equals(PARENT_MENU_ITEM)) {
		System.out.println(" Will list parents...");
	    } else if (itemTitle.equals(COACH_MENU_ITEM)) {
		System.out.println(" Will list coaches...");
	    }
	} 
    }

    public static void main(String args[]) {
	SubMenuExample sme = new SubMenuExample();
	sme.buildMenu();
    }


}