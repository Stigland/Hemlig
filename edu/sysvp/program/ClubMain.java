package edu.sysvp.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

public class ClubMain {

    public static void printProp(String s, String prop) {
		String propValue = System.getProperty(prop);
		if (propValue!=null) {
			System.out.println(s + propValue );
		}
    }

    public static void main(String[] args) {
		
		ClubHelper ch = new ClubHelper();
		Menu menu = new Menu(ch);
		
		if (args.length > 0){																// 	Checks if there are any arguments to start the program with		
			if (args[0].equalsIgnoreCase("--members") == true || 							//	If the instruction is "--members" or "--m", 
									args[0].equalsIgnoreCase("--members") == true){			//	all members will be printed
				ch.printMembers();															//	and the program exits.
				System.exit(1);																//	If the istruction is "--teams" or "--t", it will instead print all the teams.
			}else if(args[0].equalsIgnoreCase("--teams") == true || 							
											args[0].equalsIgnoreCase("--t") == true){			
				ch.printTeams();	
				System.exit(1);																
			}else{																			

			System.out.println(" -----===< Club Manager >===-----");
			printProp("  User:       ", "user.name");
			printProp("  OS arch:    ", "os.arch");
			printProp("  OS name:    ", "os.name");
			printProp("  OS version: ", "os.version");
			System.out.println(" -----=====================-----");

			// Logger.setDebugLevel();
			// ch.menuChoice();
		
			menu.run();
			}
		}
	}
}
