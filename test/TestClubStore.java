import java.util.ArrayList;
import tig058.handin01.member.Member;
import tig058.handin01.clubstore.ClubStore;
import tig058.handin01.clubstore.ClubStoreFactory;


public class TestClubStore {

    public static void main(String[] args) {
	int size1;
	int size2;
	int size3;

	ArrayList<Member> newMembers = GenerateMembers.generateClub(100);
	ArrayList<Member> members;

	ClubStore cs = ClubStoreFactory.getClubStore("bin");
	cs.clearMembers();

	// Make sure member size is 0 - should be since we store null
	cs.clearMembers();
	members = cs.getMembers();
	size3 = members.size();
	if (size3!=0) {
	    System.out.println("size:         FAIL  (size=" + size3 + ")");
	    System.exit(1);
	}
	System.out.println("setMember:    OK");
	System.out.println("size:         OK (" + size3 + ")" );

	// Make sure member size is 100
	cs.setMembers(newMembers);
	members = cs.getMembers();
	size3 = members.size();
	if (size3<10) {
	    System.out.println("size:         FAIL  (size=" + size3 + ")");
	    System.exit(1);
	}
	System.out.println("setMember:    OK");
	System.out.println("size:         OK (" + size3 + ")" );

	
	members = cs.getMembers();
	size1 = members.size();
	System.out.println("getMember:    OK");

	cs.setMembers(members);
	size2 = members.size();
	System.out.println("setMember:    OK");
	
	// Make sure member sizes are the same - not really a great test
	if (size1!=size2) {
	    System.out.println("size:         FAIL");
	    System.exit(1);
	} 
	System.out.println("size:         OK (" + size1 + ")" );

	// Make sure member size is 0 - should be since we store null
	cs.clearMembers();
	members = cs.getMembers();
	size3 = members.size();
	if (size3!=0) {
	    System.out.println("size:         FAIL  (size=" + size3 + ")");
	    System.exit(1);
	}
	System.out.println("setMember:    OK");
	System.out.println("size:         OK (" + size3 + ")" );
	System.out.println("");
	System.out.println("Suceess");

    }

}
