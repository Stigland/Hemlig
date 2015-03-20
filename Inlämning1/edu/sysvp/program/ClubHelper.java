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
	/**
	 * Changes to the println-statement for a nicer print! Prints using printf instead!
	 */
    public void printMembersByTeam() {
		Logger.debugM();
		ArrayList<Member> members = 
	    cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator2());

		System.out.println("Listing all members by team:");
		System.out.println("Name:               ");
		System.out.println("Team:   ");
		for (Member m: members) {
			System.out.printf("%-20s %-5s\n", m.getName(), m.getTeam());
		}
    }
	/**
	 * Changes to the println-statement for a nicer print! Prints using printf instead!
	 */
    public void printMembers() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		System.out.println("Listing all members:");
		System.out.print("\nName:               ");
		System.out.print("Team:");
		System.out.print("ID:  ");
		System.out.println("E-mail:\n");
		for (Member m: members) {
			System.out.printf("%5d %-12s %-10s %5s %s\n", m.getId(), m.getFamilyName(), 
										   m.getGivenName(), m.getTeam(), m.getEmail());
		}
    }
	/**
	 * Changes to the println-statement for a nicer print!
	 */
	public void printMembersAlpha() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator());

		System.out.println("Listing all members by family name:\n");
		for (Member m: members) {
			System.out.println(m.getName());
		}
    }

	/**
	 * Sorts and prints members. This time it's by the ID.
	 */
    public void printMembersById() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new IDcomparator());

		System.out.println("Listing all members by ID:\n");
		System.out.print("Name:                ");
		System.out.print("ID:     ");
		for (Member m: members) {
			System.out.printf("%-20s %-5d\n", m.getName(), m.getId());
		}
    }
	/**
	 * Changes to the println-statement for a nicer print! Prints using printf instead!
	 */
    public void printMembersAlphaFirstName() {
		Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaFirstnameComparator());

		System.out.println("Listing all members by first name:\n");
		for (Member m: members) {
			System.out.println(m.getName());
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
    	
		for (Member m: members){																   
    		String memberTeam = m.getTeam();									  //	Finds out what team the player is in 
    		if (memberTeam.equalsIgnoreCase(answer)){		  				      //	If it is the same as the scanner input,
    			System.out.println(m.getName());								  //	it will be printed.
    		}
    	}

    }
    
	
	/** 
	 * Prints out all parents of a specific team, chosen by input from the Scanner sc. It does this 
	 * by searching the member-list in a for-each-loop and excluding members not belonging to the
	 * specified team. 
	 */

	public void printSpecificTeamParents(){

    	
    	System.out.println("Which of the following available teams would you like to print out the parents of?");
    	System.out.println("Girls: D, F94, F97, F01, F03, F04, F07");
    	System.out.println("Boys: H, P94, P95, P97, P98, P00, P01, P04, P05, P06, P07");

    	String answer = sc.nextLine();

    	Logger.debugM();
		ArrayList<Member> members = cr.getMembers();
		Collections.sort(members, new MemberAlphaComparator());

		System.out.println("Parents in the team: " + answer);
		System.out.println();

    	for (Member m: members){
    		String memberTeam = m.getTeam();									  	
    		boolean memberIsParent = m.isParent();								  //	Finds out what team the member is in and if they are a parent
    		if (memberTeam.equalsIgnoreCase(answer) && memberIsParent){			  //	
    			System.out.println(m.getName() + " - is the parent of: ");		  // 	If they are, prints their name, their respective children's name and team. 
    			ArrayList<Member> children = m.getChildren();
    			for (Member c: children){
    				System.out.println(c.getName() + " - " + c.getTeam());
    			}
    			System.out.println();
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
    public void printEmail(){

		System.out.println("Enter the name of the member");
    	System.out.println();

    	String answer = sc.nextLine();
    	System.out.println();

    	Logger.debugM();
		ArrayList<Member> members = cr.getMembers(answer);
		ArrayList<Member> parentList;
		Collections.sort(members, new MemberAlphaAndParentComparator());
		
		System.out.println("\nListing the e-mail addresses of all members:");
		System.out.print("\nName:                ");
		System.out.println("E-mail:\n");
		
    	for (Member m: members){																   
			if (m.getAgeThisYear() < 18){														   	
				int ID = m.getId();																   	 	
				parentList = cr.getParents(ID);													   	
				System.out.printf("%s is underage, their parents are:\n", m.getName());			   						
				for (Member p : parentList){													   						
					System.out.printf("%-20s %s \n", p.getName(), p.getEmail());				   					
				}																								
				System.out.println();																				
			}else{
    			System.out.printf("%-20s %s\n", m.getName(), m.getEmail());
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
	 * @param command - the argument determining what instruction the program will take, taken from args
	 * 
	 */
	public void startProgram(String command){
  		if (command.equalsIgnoreCase("--members") || command.equalsIgnoreCase("--m")){   
   			printMembers();               
   			System.exit(0);                
  		}else if(command.equalsIgnoreCase("--teams") || command.equalsIgnoreCase("--t")){   
   			printTeams(); 
   			System.exit(0);  
  		}
 	}

	/** 
  	 * 
  	 * Pretty much the same as above, but with two parameters for multiple actions, quite possibly obsolete :) 
  	 * 
  	 * @param 	command1	 the argument determining what instruction the program will take, taken from args
  	 * @param 	command2	 the eventual other argument determining what instruction the program will take, taken from args
  	 * 
  	 */
	public void startProgram(String command1, String command2){
  		if(command1.equalsIgnoreCase("--teams") && command2.equalsIgnoreCase("--members")){
   			printTeams();
   			printMembers();
   			System.exit(0);
  		}else if(command1.equalsIgnoreCase("--t") && command2.equalsIgnoreCase("--m")){
   			printTeams();
   			printMembers();
   			System.exit(0);
  		}else if(command2.equalsIgnoreCase("--teams") && command1.equalsIgnoreCase("--members")){
   			printMembers();
   			printTeams();
   			System.exit(0);
  		}else if(command2.equalsIgnoreCase("--t") && command1.equalsIgnoreCase("--m")){
   			printMembers();
   			printTeams();
   			System.exit(0);
  		}
 	}
}