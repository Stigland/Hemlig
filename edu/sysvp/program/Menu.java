package edu.sysvp.program;

import com.sandklef.edu.ConsoleMenu.*;


public class Menu {
    
    ClubHelper ch ;
    
    public Menu(ClubHelper c) {
	ch = c;
    }

    public void run() {

   	ConsoleMenu menu = new ConsoleMenu("-----===< Club Manager >===-----");

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersAlpha(); } 
	    },"List members by family name" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersAlphaFirstName(); } 
	    },"List members by first name" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersById(); } 
	    },"List members by ID" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersByTeam(); } 
	    },"List members by team" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printSpecificTeam(); } 
	    },"List members of a specific team" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printSpecificTeamParents(); } 
	    },"List parents of a specific team" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printEmail(); } 
	    },"List e-mail addresses of a member" );

	menu.run();
    }

}
