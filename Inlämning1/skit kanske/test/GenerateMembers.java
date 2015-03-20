import tig058.handin01.member.Member;
import tig058.handin01.clubstore.ClubStore;
import tig058.handin01.clubstore.ClubStoreFactory;

import java.util.Random;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.AbstractSet;

import java.io.*;


public class GenerateMembers {

    private static Random rand = null; 
    private static String separator = ";";

    private static int id = 1000 ;

    private final static int YOUNGEST_MEMBER_YEAR = 2008;
    private final static int YOUNGEST_PARENT_YEAR = 1990;

    private static AbstractSet<String> teams = new TreeSet<String>();

    public static String givenNamesWoman[] =
    { 
	"Maria",
	"Anna",
	"Margareta",
	"Elisabeth",
	"Eva",
	"Birgitta",
	"Kristina",
	"Karin",
	"Elisabet",
	"Marie",
	"Ingrid",
	"Christina",
	"Linnéa",
	"Marianne",
	"Sofia",
	"Kerstin",
	"Lena",
	"Helena",
	"Inger",
	"Johanna",
	"Emma",
	"Linnea",
	"Cecilia",
	"Sara",
	"Ulla",
	"Anita",
	"Elin",
	"Viola",
	"Gunilla",
	"Louise",
	"Linda",
	"Susanne",
	"Ida",
	"Katarina",
	"Malin",
	"Hanna",
	"Jenny",
	"Irene",
	"Carina",
	"Barbro",
	"Elsa",
	"Monica",
	"Ulrika",
	"Viktoria",
	"Astrid",
	"Siv",
	"Ingegerd",
	"Britt",
	"Åsa",
	"Matilda",
	"Yvonne",
	"Therese",
	"Camilla",
	"Julia",
	"Amanda",
	"Agneta",
	"Alice",
	"Ann",
        "Caroline",
	"Lisa",
	"Lovisa",
	"Berit",
	"Inga",
	"Anette",
	"Ingeborg",
	"Charlotte",
	"Sandra",
	"Frida",
	"Sofie",
	"Margaretha",
	"Charlotta",
	"Birgit",
	"Emelie" };

    public static String givenNamesMan[] =
    { 
	"Erik", 
	"Lars", 
	"Karl", 
	"Anders", 
	"Johan", 
	"Per", 
	"Nils", 
	"Jan", 
	"Carl", 
	"Mikael", 
	"Lennart", 
	"Hans", 
	"Olof", 
	"Gunnar", 
	"Peter", 
	"Sven", 
	"Bengt", 
	"Fredrik", 
	"Bo", 
	"Åke", 
	"Göran", 
	"Daniel", 
	"Gustav", 
	"Magnus", 
	"Alexander", 
	"Martin", 
	"Stefan", 
	"Andreas", 
	"Leif", 
	"John", 
	"Bertil", 
	"Mats", 
	"Ulf", 
	"Arne", 
	"Björn", 
	"Ingemar", 
	"Henrik", 
	"Thomas", 
	"Christer", 
	"Jonas", 
	"Stig", 
	"Axel", 
	"Robert", 
	"Kjell", 
	"Rolf", 
	"Håkan", 
	"David", 
	"Emil", 
	"Mattias", 
	"Roland", 
	"Oskar", 
	"Tommy", 
	"Ingvar", 
	"Gustaf", 
	"Patrik", 
	"Michael", 
	"Roger", 
	"Joakim", 
	"William", 
	"Christian", 
	"Simon", 
	"Marcus", 
	"Olov", 
	"Sebastian", 
	"Anton", 
	"Tomas", 
	"Ove", 
	"Eric", 
	"Rune", 
	"Oscar", 
	"Kent", 
	"Johannes", 
	"Tobias", 
	"Viktor", 
	"Niklas"     };

	
    public static String familyNames[] =
    { 
	"Johansson",
	"Andersson",
	"Karlsson",
	"Nilsson",
	"Eriksson",
	"Larsson",
	"Olsson",
	"Persson",
	"Svensson",
	"Gustafsson",
	"Pettersson",
	"Jonsson",
	"Jansson",
	"Hansson",
	"Bengtsson",
	"Jönsson",
	"Carlsson",
	"Petersson",
	"Lindberg",
	"Magnusson",
	"Lindström",
	"Gustavsson",
	"Olofsson",
	"Lindgren",
	"Axelsson",
	"Lundberg",
	"Bergström",
	"Jakobsson",
	"Lundgren",
	"Berg",
	"Berglund",
	"Fredriksson",
	"Mattsson",
	"Sandberg",
	"Henriksson",
	"Sjöberg",
	"Forsberg",
	"Lindqvist",
	"Lind",
	"Engström",
	"Håkansson",
	"Danielsson",
	"Eklund",
	"Lundin",
	"Gunnarsson",
	"Holm",
	"Samuelsson",
	"Bergman",
	"Fransson",
	"Johnsson",
	"Nyström",
	"Lundqvist",
	"Holmberg",
	"Arvidsson",
	"Björk",
	"Isaksson",
	"Nyberg",
	"Söderberg",
	"Wallin",
	"Mårtensson",
	"Pettsson"
    } ;

    static Member genMemberWoman() {
	return  genMember(true);
    }

    static Member genMemberMan() {
	return  genMember(false);
    }

    static Member genMember(boolean isWoman) {

	// We need to make sure we have more players than coaches
	int year   = 1960 + rand.nextInt(2008-1960);
	int month  = rand.nextInt(12);
	int day    = rand.nextInt(28);
	return  genMember(year, month, day, isWoman);
    }

    static Member genPlayer(boolean isWoman, int year) {
	int month  = rand.nextInt(12);
	int day    = rand.nextInt(28);
	Member m = genMember(year, month, day, isWoman);
	//m.setTeam(team);
	//	m.setTeamsCoached(teamsCoached);

	return m;
    }

    static Member genCoach(boolean isWoman, int year, ArrayList<String> teamsCoached) {
	int month  = rand.nextInt(12);
	int day    = rand.nextInt(28);
	Member m = genMember(year, month, day, isWoman);
	m.setCoach(true);
	//	m.setTeamsCoached(teamsCoached);

	return m;
    }

    static Member genParent(boolean isWoman, int year, ArrayList<Member> children ) {
	int month  = rand.nextInt(12);
	int day    = rand.nextInt(28);
	Member m = genMember(year, month, day, isWoman);
	m.setParent(true);
	m.setChildren(children);

	return m;
    }

    static Member genMemberWoman(int year, int month, int day) {
	return genMember(year, month, day, true);
    }

    static Member genMemberMain(int year, int month, int day) {
	return genMember(year, month, day, false);
    }


    static Member genMember(int year, int month, int day, boolean isWoman) {
	
	String given = null;
	String family = null;

	boolean player = false ;
	boolean parent = false ;
	boolean coach  = false ;

	id++;
	//	System.out.println(" new id: " + id);

	if (isWoman) {
	    given  = givenNamesWoman[rand.nextInt(givenNamesWoman.length)];
	} else {
	    given  = givenNamesMan[rand.nextInt(givenNamesMan.length)];
	}
	family = familyNames[rand.nextInt(familyNames.length)];

	SimpleDateFormat shortYearDF = new SimpleDateFormat("yy");

	GregorianCalendar birthC       = new GregorianCalendar(year, month, day);
	GregorianCalendar memberSinceC = new GregorianCalendar(year+10, month, day);

	String shortYear = shortYearDF.format(birthC.getTime());
	
	String teamSeniorPrefix = null;
	String teamJuniorPrefix = null;
	if (isWoman) {
	    teamSeniorPrefix = "D";
	    teamJuniorPrefix = "F";
	} else {
	    teamSeniorPrefix = "H";
	    teamJuniorPrefix = "P";
	}	    

	player = true;;
	String team = "";
	if (year<1985) {
	    parent = true;
	    player = false;;
	    team = "";
	} else 	if (year<1989) {
	    player = false;;
	    coach = true ;
	    team = teamSeniorPrefix ;
	} else if (year<1994) {
	    team = teamSeniorPrefix ;
	} else {
	    team = teamJuniorPrefix + shortYear;
	}
	
	int gender = 1;
	if (isWoman) {
	    gender = 2;
	}
	
	if (!team.equals("")) {
	    teams.add(team);
	}
	
	Member m = new Member(id, given, family, 
			      given+"."+family+"-"+id+"@myclub.se",
			      (Calendar)birthC, (Calendar)memberSinceC,
			      gender);
	m.setPlayer(player);
	m.setCoach(coach);
	m.setParent(parent);

	m.setTeam(team);
	
	return m;
    }

    public static int randYearPlayer(int offset) {
	return offset + rand.nextInt(YOUNGEST_MEMBER_YEAR - offset);
    }

    public static int randYearParent(int offset) {
	return offset + rand.nextInt(YOUNGEST_PARENT_YEAR - offset);
    }

    public static ArrayList<Member> generateClub(int memberCount) {
	ArrayList<Member> members = new ArrayList<Member>();
	ArrayList<Member> kids = new ArrayList<Member>();

	int nrOfMembers =  memberCount;
	int nrOfPlayers =  nrOfMembers * 4 / 10;
	int nrOfCoaches =  nrOfMembers / 10;
	// init random generator
	Date d = new Date();
	rand   = new Random(d.getTime());
	
	//	System.out.println("Creating: " + nrOfPlayers + " players");

	for (int i=0;i<nrOfPlayers;i++) {
	    
	    int year = randYearPlayer(1987);

	    Member m = genPlayer(i%2==0,  
				 year);
	    kids.add(m);
	    members.add(m);

	    //	    System.out.println("  Player: " + m.getId() + " "+ m.getName() + " " 
	    //		       + m.getAgeThisYear());
	    if (m.getAgeThisYear()<Member.ADULT_THRESHOLD) {

		Member p1 = genParent(true, 
				      randYearParent(1987),
				      kids);

		Member p2 = genParent(false, 
				      randYearParent(1987),
				      kids);

		/*
		for (Member k: kids) {
		    k.addParent(p1);
		    k.addParent(p2);
		}
		*/

		// System.out.println("PARENTS --->" + p1.getId() + "  " 
		// 		   + p2.getId()  + "<---");
		// System.out.println("      :" + kids.size());
		  
		members.add(p1);
		members.add(p2);
	    }

	    // Every tenth kid has a sibbling
	    if (i%10 != 0) {
		//		System.out.println("clear: " + kids);
		kids.clear();
	    // } else {
	    // 	System.out.println("NOT clear: " + kids);
	    }


	}
	//	System.out.println(teams);
	return members;
    }


    public static void main(String args[]) {
	int nrOfMembers = 100;
	if (args.length>0) {
	    nrOfMembers = Integer.parseInt(args[0]);
	} 

	ArrayList<Member> members = generateClub(nrOfMembers);

	try {
	    FileOutputStream fileOut =
		new FileOutputStream("members.db");
	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    out.writeObject(members);
	    out.close();
	    fileOut.close();
	    System.out.printf("Serialized data is saved");
	} catch(IOException i) {
	    i.printStackTrace();
	}
	

    }


}
