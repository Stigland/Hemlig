package tig058.handin01.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import tig058.handin01.clubstore.ClubStore;
import tig058.handin01.clubstore.ClubStoreFactory;
import tig058.handin01.member.Member;
import tig058.handin01.log.Logger;

/**
 *
 * A class abstracting the the ClubStore to make it easier to write
 * more user logical functionality for developers.
 *
 * This class uses the ClubStore interface and not any instance
 * implementing the Interface.
 *
 */
public class ClubRegistry {

    private static ClubRegistry reg=null;
    private static ArrayList<Member> members;
    private static ClubStore cs;

    private ClubRegistry() {
	Logger.debugEnterMethod();
	cs = ClubStoreFactory.getClubStore("bin");
	cs.init();
	members = cs.getMembers();
	Logger.debugLeaveMethod();
    }

    /**
     * We prevent multiple instances of this class by setting the
     * constructor to private. In order to get hold of the only
     * instance this method can be used.
     *
     * @return ClubRegistry the single instance of the ClubRegistry class
     */
    public static ClubRegistry getInstance() {
	if (reg==null) {
	    reg=new ClubRegistry();
	}
	return reg;
    }



    /**
     *
     * Returns a list of all the teams managed by the system
     * 
     * @return ArrayList of Strings containing the names of the teams
     */
    public ArrayList<String> getTeams() {
	Logger.debugEnterMethod();
        Set<String> s = new HashSet<String>();

	for (int i=0;i<members.size();i++) {
	    //	    Member m = ;
	    String team = members.get(i).getTeam();
	    if ( (team!=null) && (!team.equals("")) ) {
		s.add(team);
	    }
	}
	ArrayList<String> list = new ArrayList<String>(s);
	Logger.debugLeaveMethod();
	return list;
    }

    /**
     *
     * Force the system to store the internal data to the requested
     * backend system (ClubStore).
     *
     */ 
    public void storeMembers() {
	cs.storeMembers();
    }

    /**
     *
     * Returns a list of all the Members managed by the system
     * 
     * @return ArrayList of members 
     */
    public ArrayList<Member> getMembers() {
	return new ArrayList<Member>(members);
    }

    /**
     *
     * Returns a list of all the Members that matches the expression
     * specified by the caller. The expression is matched against the
     * name (given and family) of the Members.
     * 
     * The syntax of the expression was originally aimed at being SQL
     * compliant, but is now only supporting the following:
     *
     * <pre>    % - a wildcard
     *
     * Exmaples of  search strings:
     *
     * "Joni" will return all members with either the given
     * name or the family name equal to "Joni"
     * 
     * "Joni%" will return all members with either the given name or
     * the family name equal to "Joni" followed by any character or
     * characters so "Jonin" will also match.
     * 
     * "%oni" will return all members with either the given name or
     * the family name beginning with any character or charcters
     * followed by "oni" so "Joni" and "Toni" and many more will
     * match.
     * 
     * "%oni%" will return all members with either the given name or
     * the family name beginning with any character or charcters
     * followed by "oni" so "Joni" and "Toni" in turned followed by
     * any character or characters so "Joni", "Jonin", "Thoniloli"
     * will also match, as will will many more.
     * 
     * </pre>
     * 
     * @param String - the expression 
     * @return ArrayList of members 
     */
    public ArrayList<Member> getMembers(String needle) {
	ArrayList<Member> hits = new ArrayList<Member>();

	String regexp ;
	regexp = needle.replace(".","\\.");
	regexp = regexp.replace("%","(.)*");

	Logger.debugM("Looking for members with '" + needle + "' '" + regexp + "'");
	
	Pattern p = Pattern.compile(regexp);
	for (Member m: members) {
	    String  gName = m.getGivenName();
	    Matcher gMatch = p.matcher(gName);
	    String  fName = m.getFamilyName();
	    Matcher fMatch = p.matcher(fName);
	    //	    System.out.println("match?: " + gName + "  " + regexp); 
	    if (gMatch.matches() || fMatch.matches()) {
		//System.out.println("yes"); 
		hits.add(m);
	    }
	}
	return hits;
    }

    /**
     *
     * Returns a list of all the Members playing in the team specified
     * by the caller.
     * 
     * @param String - name of the team
     * @return ArrayList of members 
     */
    public ArrayList<Member> getTeamMembers(String team) {
	Logger.debugEnterMethod();
	ArrayList<Member> mem = new ArrayList<Member>();


	for (int i=0;i<members.size();i++) {
	    Member m     = members.get(i) ;
	    String mTeam = m.getTeam();
	    if ( (mTeam!=null) && (mTeam.equals(team)) ) {
		mem.add(m);
	    }
	}
	Logger.debugLeaveMethod();
	return mem;
    }

    /**
     *
     * Returns the a Member given an id, if no Member has been
     * assigned the id null is returned.
     *
     * @param int - the id 
     * @return Member - the Member matching id
     */
    public Member getMember(int id) {
	for (Member member: members) {
	    if (member.getId()==id) {
		return member;
	    }
	}
	return null;
    }

    /**
     *
     * Returns a list Member being the parents of the Member.
     *
     * @param int - the id 
     * @return ArrayList of parents (Member)
     */
    public ArrayList<Member> getParents(int id) {
	Member m = getMember(id);
	return getParents(m);
    }


    /**
     *
     * Returns a list Member being the parents of the Member.
     *
     * @param Member to get the parents to
     * @return ArrayList of parents (Member)
     */
    public ArrayList<Member> getParents(Member child) {
	Logger.debugEnterMethod();
	Logger.debugLeaveMethod();
	return child.getParents();
    }
    
    /**
     *
     * Finds and returns the adults of a given Member. If the Member
     * is adult himself/herself null is returned.
     *
     * @param  Member - Member to find adults to
     * @return Null is returned if the Member is an adult. Otherwise a
     * list (may be of zero size) with parents is returned.
     */ 
    public ArrayList<Member> getAdults(Member child) {
	Logger.debugEnterMethod();

	if (child.getAgeThisYear() >= Member.ADULT_THRESHOLD ) {
	    Logger.debugLeaveMethod();
	    return null;
	} else {
	    Logger.debugLeaveMethod();
	    return child.getParents();
	}

    }

    

}
