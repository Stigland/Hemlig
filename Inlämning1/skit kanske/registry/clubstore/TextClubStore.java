package tig058.handin01.clubstore;

import tig058.handin01.member.Member;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;

/**
 * 
 * A ClubStore which implements the ClubStore interface. This class
 * uses Java's serialization API to store and read the data used.
 *
 */
public class TextClubStore implements ClubStore {

    private static ArrayList<Member> members ;
    private static final String FILESTORE_NAME = "members.db";

    private static TextClubStore tcs = null;

    private TextClubStore() {
	members = new ArrayList<Member>();
    }

    /**
     * Initiates the ClubStore
     *
     */
    public void init() { 
	readMembers();
    }
   
    /**
     * Returnes true if this backend supports the requested
     * backend. So if the supplied String equals "bin" this method
     * returns true, otherwise false.
     *
     * @param String the requested backend
     * @return true if the String is "bin", otherwise false
     *
     */
    public boolean supports(String s) {
	if(s!=null && s.equals("bin")) { 
	    return true ;
	}
	return false;
    }


    /**
     * In order to prevent multiple instances of this class it can not
     * be instantiated using constructors. Instead you have to get a
     * reference to the single instance of the class using this
     * method.
     *
     * @return ClubStore - the single instance
     *
     */
    public static ClubStore getInstance() {
	if (tcs==null) {
	    tcs = new TextClubStore();
	}
	return tcs;
    }

    /*    public void printMembers() {
	for (int i=0;i<members.size();i++) {
	    System.out.println(members.get(i));
	}
    }
    */

    /**
     *
     * Read the data from the file. The data is stored internally.
     * 
     */
    private void readMembers() {
	ObjectInputStream objectinputstream = null;
	ArrayList<Member> m = null;
	try {
	    FileInputStream streamIn = new FileInputStream(FILESTORE_NAME);
	    objectinputstream = new ObjectInputStream(streamIn);
	    //	    @SuppresWarning ()
	    m = (ArrayList<Member>) objectinputstream.readObject();
	    
	    if(objectinputstream != null){
		objectinputstream .close();
	    } 
	} catch (Exception e) {
	    e.printStackTrace();
	    m = null;
	}

	members = m;
    }

    /**
     *
     * Read the data from the file. The data is stored internally.
     * 
     */
    public ArrayList<Member> getMembers() {
	return members;
    }

    /**
     *
     * Replace current internal storage with the one passed via the parameter.
     * 
     * @param The new list of Members to replace the old one.
     */
    public void setMembers(ArrayList<Member> m) {
	if (m!=null) {
	    members = m;
	    storeMembers();
	}
    }

    /**
     *
     * Clear the internal storage.
     * 
     */
    public void clearMembers() {
	members.clear();
	storeMembers();
    }

    /**
     *
     * Store the data to file. The data is stored from internal
     * memory.
     * 
     */
    public void storeMembers() {

	try {
	    FileOutputStream fileOut =
		new FileOutputStream(FILESTORE_NAME);
	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    out.writeObject(members);
	    out.close();
	    fileOut.close();
	    System.err.println("Serialized data is saved in " + FILESTORE_NAME);
	} catch(IOException i) {
	    i.printStackTrace();
	}
	
    }

    /* ONLY FOR TEST 
    public static void main(String args[]) {
	TextClubStore cs = new TextClubStore();
	
	if ( (args.length > 0 ) && args[0].equals("--write") ) {
	    cs.storeMembers();
	} else {
	    cs.readMembers();
	}

	cs.printMembers();
    }
    */

}
