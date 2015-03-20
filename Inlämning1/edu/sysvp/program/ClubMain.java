package edu.sysvp.program;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

public class ClubMain {	
	
    public static void main(String[] args) {	
		
		ClubHelper ch = new ClubHelper();
		Menu menu = new Menu(ch);
		
		if (args.length == 1){
   			ch.startProgram(args[0]);
  		}else if(args.length == 2){
   			ch.startProgram(args[0], args[1]);
  		}else{
	
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
		String date = sdf.format(today);		
		
		System.out.println(" -----===< Club Manager >===-----");
		printProp("  User:       ", "user.name");			
		printProp("  OS arch:    ", "os.arch");
		printProp("  OS name:    ", "os.name");
		printProp("  OS version: ", "os.version");
		System.out.println(" -----=====================-----");
		System.out.println("        "+date);
		System.out.println(" -----=====================-----");
			
		Logger.setInfoLevel();
		// ch.menuChoice();
		
		menu.run();
			
		}
	}
	
    public static void printProp(String s, String prop) {
		String propValue = System.getProperty(prop);
		if (propValue!=null) {
			System.out.println(s + propValue );
		}
	}
}

