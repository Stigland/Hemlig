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
	System.out.println(" -----===< Club Manager >===-----");
	printProp("  User:       ", "user.name");
	printProp("  OS arch:    ", "os.arch");
	printProp("  OS name:    ", "os.name");
	printProp("  OS version: ", "os.version");
	System.out.println(" -----=====================-----");

	//	Logger.setDebugLevel();
	ClubHelper ch = new ClubHelper();
	//ch.menuChoice();

	Menu menu = new Menu(ch);
	menu.run();

    }

}
