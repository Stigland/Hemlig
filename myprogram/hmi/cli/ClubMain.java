package myprogram.hmi.cli;

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
	Scanner sc = new Scanner;
	ClubHelper ch = new ClubHelper();
	System.out.println("Choose an option");
	System.out.println("1. List all members by family name");
	System.out.println("2. List all members by first name");
	System.out.println("3. List all members by ID");
	System.out.println("4. List all members by team");
	System.out.println("5. List all members of a specific team");
	System.out.println("6. List all parents of a specific team");
	System.out.println("7. List all e-mail adresses of a member");
	
	  // System.out.println(" ... let's assume user wanted to print teams");
	  // ch.printTeams();
	  //   System.out.println(" ... let's assume user wanted to print members");
	  // ch.printMembers();
	  /*
	    System.out.println(" ... let's assume user wanted to print members in alpha order");
	    ch.printMembersAlpha();
	  System.out.println(" ... let's assume user wanted to print members in alpha order on given name");
	  ch.printMembersAlphaFirstName();
	*/
	
	
	/*
	ArrayList<Member> members = cm.cr.getMembers();
	System.out.println(" ... size: " + members.size());
	members.remove(10);
	System.out.println(" ... size: " + members.size());
	cm.cr.storeMembers();
	*/

	ch.printMembers("%Karl%");
	ch.printTeams();

	ch.printTeamMembers("F01");
	ch.printTeamMembers("D");
	ch.printParents(1016);
	ch.printParents(1017);

	ch.emailMember(1010);
	ch.emailMember(1016);
	ch.emailMember(1017);
	ch.emailMember(1018);
    }

}
