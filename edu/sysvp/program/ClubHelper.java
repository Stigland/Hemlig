package edu.sysvp.program;

import java.util.ArrayList;
import java.util.Collections;
import java.io.Console;
import java.util.Scanner;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

public class ClubHelper {

    private ClubRegistry cr;

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
	ArrayList<String> teams = 
	    cr.getTeams();
	
	System.out.print("Teams: ");
	for (String team: teams) {
	    System.out.print(team + " ");
	}
	System.out.println("");
    }

    public void printMembers() {
	Logger.debugM();
	ArrayList<Member> members = 
	    cr.getMembers();
	System.out.println("Members:");
	for (Member m: members) {
	    System.out.println(m);
	}
    }

    public void printMembersAlpha() {
	Logger.debugM();
	ArrayList<Member> members = 
	    cr.getMembers();
	Collections.sort(members, new MemberAlphaComparator());

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
	} else {
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

    public void menuChoice(){

	System.out.println("Choose an option");
	System.out.println("1. List all members by family name");
	System.out.println("2. List all members by first name");
	System.out.println("3. List all members by ID");
	System.out.println("4. List all members by team");
	System.out.println("5. List all members of a specific team");
	System.out.println("6. List all parents of a specific team");
	System.out.println("7. List all e-mail adresses of a member");
	System.out.println(">");

	Scanner sc = new Scanner(System.in);

	boolean validChoice = false;

	//Below will restart loop uless condition validChoice is met and set to true.
	while(!validChoice){

	int s = sc.nextInt();

    	if (s == 1){

    		validChoice = true;
    	}
    	else if (s == 2){

    		validChoice = true;
    	}
    	else if (s == 3){
    	
    		validChoice = true;
    	}
    	else if (s == 4){
    	
    		validChoice = true;
    	}
    	else if (s == 5){
    	
    		validChoice = true;
    	}
    	else if (s == 6){
    		
    		validChoice = true;
    	}
    	else if (s == 7){
    		
    		validChoice = true;
    	}
    	else{

    	}

    }
    }

    

}
