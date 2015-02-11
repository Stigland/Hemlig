package tig058.handin01.member;

import java.util.Calendar;
import java.util.ArrayList;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 *
 * This class keeps information about a Member as used by the entire
 * Club Management system.
 *
 *
 *
 */
public class Member implements Serializable {

    public static final int ADULT_THRESHOLD = 18 ;

    private int       id;
    private String    givenName;
    private String    familyName;
    private String    email;

    private boolean   player;
    private boolean   coach;
    private boolean   parent;

    private ArrayList<Member>   children;
    private ArrayList<Member>   parents;
    private ArrayList<String>   teamsCoached;
    private String    team;

    private int       gender; //0-man, 1-woman

    private Calendar  birth;
    private Calendar  memberSince;
    
    private boolean   active;

    /**
     *
     * Constructor to create a new Member.
     *
     * Member is not responsible for setting the id. This is left to
     * the caller.
     *
     * @param id - the id of the member. 
     * @param givenName - the given name.
     * @param familyName - the given name.
     * @param email - the email address.
     * @param birth - when the member was born
     * @param memberSince - when the member became a member
     * @param memberSince - gender of the Member
     *
     */ 
    public Member(int      id,
		  String   givenName,
		  String   familyName,
		  String   email,
		  Calendar birth,
		  Calendar memberSince, 
		  int      gender) {
	this.id          = id;
	this.givenName   = givenName;
	this.familyName  = familyName;
	this.email       = email;
	this.birth       = birth;
	this.memberSince = memberSince;
	this.gender      = gender;
	parents          = new ArrayList<Member>();
	
	coach            = false;
	player           = false;
	parent           = false;
	active           = true;
    }

    /**
     *
     * Constructor to create a new Member.
     *
     * Member is not responsible for setting the id. This is left to
     * the caller.
     * 
     * This method sets the date when the person become a member to now.
     *
     * @param id - the id of the member. 
     * @param givenName - the given name.
     * @param familyName - the given name.
     * @param email - the email address.
     * @param birth - when the member was born
     *
     */ 
    public Member(int      id,
		  String   givenName,
		  String   familyName,
		  String   email,
		  Calendar birth,
		  int      gender) {
	this(id, givenName, familyName, email, birth, Calendar.getInstance(), gender);

    }


    /**
     * Check if the Member is a player
     * 
     * @return true if the Member is a player
     *
     */
    public boolean isPlayer() {
	return player;
    }

    /**
     * Check if the Member is a coach
     * 
     * @return true if the Member is a coach
     *
     */
    public boolean isCoach() {
	return coach;
    }

    /**
     * Check if the Member is a parent
     * 
     * @return true if the Member is a parent
     *
     */
    public boolean isParent() {
	return parent;
    }

    /**
     * Returns the email address of the user.
     * 
     * @return email address of the user, null if not existing.
     *
     */
    public String getEmail() {
	return email;
    }

    /**
     * Returns the id of the user.
     * 
     * @return id of the user
     *
     */
    public int getId() {
	return id;
    }

    /**
     * Returns the children of a Member. If no children null.
     * 
     */
    public ArrayList<Member> getChildren() {
	return children;
    }

    /**
     * Returns the parents of a Member. If no parents null.
     * 
     */
    public ArrayList<Member> getParents() {
	return parents;
    }

    /**
     * Returns the age of the Member. 
     *
     * This method does not take into concern what month the member
     * was born. Only the year is of interest.
     * 
     */
    public int getAgeThisYear() {
	return Calendar.getInstance().get(Calendar.YEAR) -
	    birth.get(Calendar.YEAR) ;
    }

    /**
     *
     * Turn the Member into a player (or not)
     *
     * @param boolean If true the Member is marked as a player, if
     * false it is marked as not being a player
     * 
     */
    public void setPlayer(boolean is) {
	this.player = is;
    }

    /**
     *
     * Turn the Member into a coach (or not)
     *
     * @param boolean If true the Member is marked as a coach, if
     * false it is marked as not being a coach
     * 
     */
    public void setCoach(boolean is) {
	this.coach = is;
    }

    /**
     *
     * Turn the Member into a parent (or not)
     *
     * @param boolean If true the Member is marked as a parent, if
     * false it is marked as not being a parent
     * 
     */
    public void setParent(boolean is) {
	this.parent = is;
    }

    /**
     *
     * Set the team of a Member.
     *
     * @param String - the name of the team 
     * 
     */
    public void setTeam(String team) {
	this.team = team;
    }

    /**
     *
     * Get the team of a Member.
     *
     * @return String - the name of the team 
     * 
     */
    public String getTeam() {
	return team;
    }

    /**
     *
     * Set the team(s) coached by a Member.
     *
     * @param teamsCoached, an ArrayList containing the teams the member coaches
     * 
     */
    public void setTeamsCoached(ArrayList<String> teamsCoached) {
	this.teamsCoached = new ArrayList<String>(teamsCoached);
    }

    private void addParent(Member m) {
	if (m!=null) {
	    parents.add(m);
	}
    }

    /**
     *
     * Set the children of a member
     *
     * @param children, an ArrayList containing the children of the member
     * 
     */
    public void setChildren(ArrayList<Member> children) {

	for (Member m: children) {
	    m.addParent(this);
	}

	this.children = new ArrayList<Member>(children);
    }
    
    /**
     *
     * Returns the name of the member, both the given name and the
     * family name.
     *
     * Format:
     * 
     *   <pre>givenName + " " + familyName</pre>
     *
     * E.g 
     * 
     *   "Joe Smith" 
     *
     * @return String - the name of the Member. 
     *
     */
    public String getName() {
	return givenName + " " + familyName;
    }


    /**
     *
     * Returns the family name of the member, not the given name
     *
     * If the member is called "Joe Smith" only "Smith" is returned
     *
     * @return String - the family name of the Member. 
     *
     */
    public String getFamilyName() {
	return familyName;
    }


    /**
     *
     * Returns the given name of the member, not the family name
     *
     * If the member is called "Joni Smith" only "Joni" is returned
     *
     * @return String - the given name of the Member. 
     *
     */
    public String getGivenName() {
	return givenName;
    }

    /**
     *
     * Returns the a String representation of the Member instance
     *
     * For the children and parents, only the ids are printed.
     *
     * The following attributes are printed:
     * <pre>
     * id
     * givenName
     * familyName
     * email
     * team
     * teamsCoached
     * birthS
     * memberSince
     * gender
     * player
     * coach
     * parent
     * childrenString
     * parentString
     * </pre>
     *
     * @return String - a String version of the Member
     *
     */
    @Override
    public String toString() {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	String birthS = sdf.format(birth.getTime());
	String memberSinceS = sdf.format(memberSince.getTime());

	String childrenString = "";
	if (children!=null && children.size() > 0) {
	    for (int i=0; i<children.size(); i++) {
		//System.out.println("Creating children string" + m.getId());
		childrenString += children.get(i).getId();
		if (i<children.size()-1) {
		    childrenString += ",";
		}
	    }
	}
	String parentString = "";
	if (parents!=null && parents.size() > 0) {
	    for (int i=0; i<parents.size(); i++) {
		//System.out.println("Creating parent string" + m.getId());
		parentString += parents.get(i).getId();
		if (i<parents.size()-1) {
		    parentString += ",";
		}
	    }
	}

	String ret = String.format("%d: %s %s <%s>;(%s/%s);(%s,%s);%d;%b;%b;%b;[%s];[%s]",
				   id, givenName, familyName, email, team, teamsCoached, birthS, 
				   memberSinceS, 
				   gender, 
				   player, coach, parent, 
				   childrenString, parentString);
	//	System.out.println(ret);
	return ret;
    }

}

