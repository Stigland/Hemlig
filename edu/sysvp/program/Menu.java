package edu.sysvp.program;

import com.sandklef.edu.ConsoleMenu.*;

//class MenuItemZero implements MenuItem{
		//public void menuItemSelected(ConsoleMenuEvent e) { 
		    //System.out.println("Egen menuitem");  
	    //}
//}
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
	    },"List all members by family name" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersAlphaFirstName(); } 
	    },"List all members by first name" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersById(); } 
	    },"List all members by ID" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printMembersByTeam(); } 
	    },"List all members by team" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printSpecificTeam(); } 
	    },"List all members of a specific team" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printSpecificTeamParents(); } 
	    },"List all parents of a specific team" );

	menu.addMenuItem(new MenuItem(){
		public void menuItemSelected(ConsoleMenuEvent e) { 
		    ch.printEmail(); } 
	    },"List all e-mail adresses of a member" );



	menu.run();
    }

}
