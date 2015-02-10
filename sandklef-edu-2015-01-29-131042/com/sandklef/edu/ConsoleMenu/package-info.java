/**
 * Simple package to ease up console menus
 *
 * The main purpose is, rather than easing up writing a console menu,
 * to show the use of Interfaces. In the examples we show how to
 * create and add MenuItem classes and also how to add anonymous
 * classes/objects.
 * 
 * ConsoleMenu supports sub menu. Simply add a ConsoleMenu to another
 * ConsoleMenu using the addMenuItem method
 *
 * You can either extend the ConsoleMenu class or create an object
 * from it, just as you can with JPanel.
 * 
 *
 * <br>
 * <br>
 * <br>
 * <h2>Example console menu, from first example below</h2>  
 * <br>
 * <h3>Menu level 1</h3>  
 * <pre>
 * <code>
 * Member management
 * =============
 * 0.  List Members
 * 1.  Remove member
 * 2.  Exit menu
 * > 
 * </code>
 * </pre>
 *
 * <h3>Menu level 2</h3>  
 * <pre>
 * <code>
 *[ Member management] ==> 
 *  List Members
 *  =============
 *  0.  Player
 *  1.  Parent
 *  2.  Coach
 *  3.  List Members
 *  4.  Exit menu
 *> 
 * </code>
 * </pre>
 *
 * 
 * <br> 
 * <hr>
 * <br>
 * <h2>Extending your class to use the menu (suggested)</h2>  
 * <br>
 * <br>
 * <pre>
 * <code>
 * import com.sandklef.edu.ConsoleMenu.*;
 *
 * public class MenuExtendedExample extends ConsoleMenu {
 *
 *   private final String LIST_MENU_ITEM   = "List Members";
 *   private final String PLAYER_MENU_ITEM = "Player";
 *   private final String PARENT_MENU_ITEM = "Parent";
 *   private final String COACH_MENU_ITEM  = "Coach";
 *
 *   public void buildMenu() {
 *	setMenuTitle("Member management");
 *
 *	ConsoleMenu listMenu = new ConsoleMenu(LIST_MENU_ITEM);
 *
 *	listMenu.addMenuItem(this, PLAYER_MENU_ITEM);
 *	listMenu.addMenuItem(this, PARENT_MENU_ITEM );
 *	listMenu.addMenuItem(this, COACH_MENU_ITEM );
 *
 *	addMenuItem(listMenu, LIST_MENU_ITEM);
 *
 *	run();
 *   }
 *
 *   public void menuItemSelected(ConsoleMenuEvent e) {
 *	String itemTitle = e.getMenuItemTitle();
 *	String menuTitle = e.getMenuTitle();
 *
 *	System.out.println("menuItemSelected(\"" + itemTitle + "\", \"" + menuTitle + "\") ");
 *
 *	if (menuTitle.equals(LIST_MENU_ITEM)) { 
 *	    if (itemTitle.equals(PLAYER_MENU_ITEM)) {
 *		System.out.println(" Will list players...");
 *	    } else if (itemTitle.equals(PARENT_MENU_ITEM)) {
 *		System.out.println(" Will list parents...");
 *	    } else if (itemTitle.equals(COACH_MENU_ITEM)) {
 *		System.out.println(" Will list coaches...");
 *	    }
 *	} 
 *   }
 *
 *   public static void main(String args[]) {
 *	MenuExtendedExample me = new MenuExtendedExample();
 *	me.createMenu();
 *   }
 *}
 *    
 * </code>
 * </pre>
 *
 * 
 * 
 * <h2>Creating ConsoleMenu object to use as a menu</h2>  
 * <br>
 * <h3>Example with anonymous class:</h3>  
 * <pre>
 * <code>
 * 
 *   ConsoleMenu menu = new ConsoleMenu("-= MenuExample =-");
 *    public void createMenu() {
 *	setMenuTitle ("-= MenuExample =-");
 *	addMenuItem(new MenuItem(){
 *		public void menuItemSelected(ConsoleMenuEvent e) 
 *		{ System.out.println("Buy Mudhoney records") ; } },
 *		    "Mudhoney" );
 *	addMenuItem(new MenuItem(){
 *		public void menuItemSelected(ConsoleMenuEvent e) 
 *		{ System.out.println("Buy Mule records") ; } },
 *	    "Mule" );
 *	addMenuItem(new MenuItem(){
 *		public void menuItemSelected(ConsoleMenuEvent e) 
 *		{ System.out.println("Buy Will Oldham records") ; } },
 *	    "Will" );
 *
 *   menu.run();
 *
 * </code>
 * </pre>
 *
 *
 * <h3>Example with separate class:</h3>  
 * <pre>
 * <code>
 * import com.sandklef.edu.ConsoleMenu.MenuItem;
 * 
 * public class RemoveMenu implements MenuItem {
 *
 *   public void menuItemSelected(ConsoleMenuEvent e) {
 *	System.out.println("remove selected");
 *   }
 * }
 * 
 * </code>
 * </pre>
 * 
 * <h3>.... and the coresponding code to create and run the menu. </h3>
 *
 * <pre>
 * <code>
 *   ConsoleMenu menu = new ConsoleMenu("-= MenuExample =-");
 *   RemoveMenu remove = new RemoveMenu();
 *   menu.addMenuItem(remove, "Remove member" );
 *   menu.run();
 * </code>
 * </pre>
 *
 *
 * <br>
 * <br>
 *
 * See package com.sandklef.edu.examples for examples on how to use it.
 */

package com.sandklef.edu.ConsoleMenu;
