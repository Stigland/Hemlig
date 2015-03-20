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
 * Interface used in ConsoleMenu. Classes implementing this interface
 * can be added as items to consolemenu.
 *
 */
package com.sandklef.edu.ConsoleMenu;

public interface MenuItem {

    /**
     *
     * This method is invoked by ConsoleMenu when the item is selected
     *
     */
    public void menuItemSelected(ConsoleMenuEvent ce);

}