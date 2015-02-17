package edu.sysvp.program;

import java.util.ArrayList;
import java.util.Collections;
import java.io.Console;
import java.util.Scanner;
import java.util.Scanner;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import com.sandklef.edu.ConsoleMenu.*;

public class ClubHelper {

    private ClubRegistry cr;
	Scanner sc = new Scanner(System.in);
	
    public ClubHelper() {
		Logger.debug("Init system");
		cr = ClubRegistry.getInstance();
    }

    public String askUser(String s) {
		Console console = System.console();
		String input = console.readLine(s);
		return input;
    }

    public void printTeams() {
		Logger.debugM();
		ArrayList<String> teams = cr.getTeams();
	
		System.out.print("Teams: ");
		for (String team: teams) {
			System.out.print(team + " ");
		}
		System.out.println("");
    }

    public void printMembersByTeam() {
		Logger.debugM();
		ArrayList<Member> members = 
	    cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator2());

		System.out.println("Members:");
		for (Member m: members) {
			System.out.println(m);
		}
    }

    public void printMembers() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		System.out.println("Members:");
		for (Member m: members) {
			System.out.println(m);
		}
    }

    public void printMembersAlpha() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator());

		System.out.println("Members:");
		for (Member m: members) {
			System.out.println(m);
		}
    }

 /**
  * Sorts and prints members. This time it's by the ID.
  */
    public void printMembersById() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new IDcomparator());

		System.out.println("Members:");
		for (Member m: members) {
			System.out.println(m);
		}
    }


    public void printMembersAlphaFirstName() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaFirstnameComparator());

		System.out.println("Members:");
		for (Member m: members) {
			System.out.println(m);
		}
    }

    public void printMembers(String s) {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers(s);
		Collections.sort(members, new MemberAlphaComparator());

		System.out.println("Members:");
		for (Member m: members) {
			System.out.println(m);
		}
    }

    public void printTeamMembers(String team) {
		Logger.debugM();
		ArrayList<Member> members = cr.getTeamMembers(team);
		Collections.sort(members, new MemberAlphaComparator());

		System.out.println("Members in " + team + ":");
		for (Member m: members) {
			System.out.println(m);
		}
    }


    public void emailMember(int id) {
		Member m = cr.getMember(id);
		if (m==null) { return ; }
	
		emailMember(m);
    }
	
    public void emailMember(Member m) {
		Logger.debugM();

		ArrayList<Member> parents = cr.getAdults(m);

		System.out.print("Email to " + m.getName() + "");
		if (parents==null || parents.size()==0) { 
			System.out.println("<" + m.getEmail() + ">"); 
		}else{
			System.out.println(" via parents:");
			for (Member p: parents) {
			System.out.println(" * " + p.getName() + "<" + p.getEmail() + ">");
			}
		}
    }

    public void printParents(int id) {
		Member m = cr.getMember(id);
		if (m==null) { return ; }
	
		printParents(m);
    }

    public void printParents(Member m) {
		Logger.debugM();

		ArrayList<Member> parents = cr.getParents(m);
		System.out.println("Parents to " + m.getName() + ":");
		if (parents==null || parents.size()==0) { System.out.println(" * <none>"); return ; }
	
		for (Member p: parents) {
			System.out.println(" * " + p);
		}
    }
	
 /** 
  * Prints out all members of a specific team, chosen by input from the Scanner sc. It does this 
  * by searching the member-list in a for-each-loop and excluding members not belonging to the
  * specified team. 
  */
    public void printSpecificTeam(){

    	
    	System.out.println("Which of the following available teams would you like to print out?");
    	System.out.println("Girls: D, F94, F97, F01, F03, F04, F07");
    	System.out.println("Boys: H, P94, P95, P97, P98, P00, P01, P04, P05, P06, P07");

    	String answer = sc.nextLine();

    	Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator());
		System.out.println("Players in the team: " + answer);
		System.out.print("Name:                \n");
    	for (Member m: members){
    		String memberTeam = m.getTeam();
    		if (memberTeam.equalsIgnoreCase(answer)){
    			System.out.printf("%-20s \n", m.getName());
    		}
    	}

    }

    
	
 /** 
  * Prints e-mail of a specific player, searched for by name according to input to the Scanner sc. 
  * The search may display several players if several players match the input.
  * 
     * If the member is not 18 years old the e-mails and names of the parents are printed instead.
     * The method first tests the age of the member and should it be under 18 the method gathers
     * the parents IDs from the Member-object. It then declares an ArrayList of the parents and 
     * prints their names and e-mails without discrimination. 
  */

	public void printSpecificTeamParents(){

    	
    	System.out.println("Which of the following available teams would you like to print out the parents of?");
    	System.out.println("Girls: D, F94, F97, F01, F03, F04, F07");
    	System.out.println("Boys: H, P94, P95, P97, P98, P00, P01, P04, P05, P06, P07");

    	String answer = sc.nextLine();

    	Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator());

    	for (Member m: members){
    		String memberTeam = m.getTeam();
    		boolean memberIsParent = m.isParent();
    		if (memberTeam.equalsIgnoreCase(answer) && memberIsParent){
    			System.out.println(m.getName() + " - is the parent of: ");
    			ArrayList<Member> children = m.getChildren();
    			for (Member c: children){
    				System.out.println(c.getName() + " - " + c.getTeam());
    			}
    			System.out.println();
    		}
    	}

    }


 /** 
  * Checks if there are any arguments to start the program with.
  * If the instruction is "--members" or "--m", all members will be printed and the program exits.
  * If the instruction is "--teams" or "--t", it will instead print all the teams. The option to send both
  * arguments will do both actions and then terminate the program. 
  *  
  * @param s - the argument determining what instruction the program will take
  * 
  */
    public void printEmail(){

		System.out.println("Enter the name of the member");
    	System.out.println();

    	String answer = sc.nextLine();
    	System.out.println();

    	Logger.debugM();
		ArrayList<Member> members = cr.getMembers(answer);
		ArrayList<Member> parentList;
		Collections.sort(members, new MemberAlphaComparator());
		Collections.sort(members, new MemberAlphaAndParentComparator());
	
    	for (Member m: members){
			if (m.getAgeThisYear() < 18){
				int ID = m.getId();
				parentList = cr.getParents(ID);
				System.out.println(m.getName() + " - parents: ");
				for (Member p : parentList){
					System.out.println(p.getName() + " - e-mail: " + p.getEmail());
				}
				System.out.println();
			}else{
    			System.out.println(m.getName() + " - e-mail: " + m.getEmail());
    			System.out.println();
			}
    	}
    }



	/**	Checks if there are any arguments to start the program with
	If the instruction is "--members" or "--m", all members will be printed and the program exits.
	If the istruction is "--teams" or "--t", it will instead print all the teams. */


	public void startProgram(String s){
  		if (s.equalsIgnoreCase("--members") || s.equalsIgnoreCase("--m")){   
   			printMembers();               
   			System.exit(1);                
  		}else if(s.equalsIgnoreCase("--teams") || s.equalsIgnoreCase("--t")){   
   			printTeams(); 
   			System.exit(1);  
  		}
 	}

	/** 
  	* 
  	* Pretty much the same as above, but with two parameters for multiple actions, quite possibly obsolete :) 
  	* 
  	* @param s - the argument determining what instruction the program will take
  	* @param t - the argument determining what instruction the program will take
  	* 
  	*/
	
	public void startProgram(String s, String t){
  		if(s.equalsIgnoreCase("--teams") && t.equalsIgnoreCase("--members")){
   			printTeams();
   			printMembers();
   			System.exit(1);
  		}else if(s.equalsIgnoreCase("--t") && t.equalsIgnoreCase("--m")){
   			printTeams();
   			printMembers();
   			System.exit(1);
  		}else if(t.equalsIgnoreCase("--teams") && s.equalsIgnoreCase("--members")){
   			printMembers();
   			printTeams();
   			System.exit(1);
  		}else if(t.equalsIgnoreCase("--t") && s.equalsIgnoreCase("--m")){
   			printMembers();
   			printTeams();
   			System.exit(1);
  		}
 	}

}



