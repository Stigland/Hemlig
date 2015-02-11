package tig058.handin01.clubstore;

import java.util.ArrayList;
import tig058.handin01.member.Member;

/**
 *
 * The ClubStore provides a Storage backend for the Club management
 * system. Since we might want to change the backend and keep the
 * upper layers intact we provide an Interface that backend providers
 * must implement. Upper layers should instead use the ClubStore Interface, as
 * returned by the ClubStorefactory.
 *
 *
 */
public interface ClubStore {

    // Init methods

    /**
     * Initiates the ClubStore
     *
     */
    public void     init();

    /**
     * Returnes true if this backend supports the requested
     * backend. So if the supplied String equals "bin" this method
     * returns true, otherwise false.
     *
     * @param String the requested backend
     * @return true if the String is "bin", otherwise false
     *
     */
    public boolean supports(String s);

    // Functional methods
    
    /**
     *
     * Read the data from the backend as used by the classes
     * implmenting this Interface. The data is stored internally.
     * 
     */
    public ArrayList<Member> getMembers();

    /**
     *
     * Replace current internal storage with the one passed via the parameter.
     * 
     * @param The new list of Members to replace the old one.
     */
    public void setMembers(ArrayList<Member> m);

    /**
     *
     * Store the data. The data is stored from internal
     * memory.
     * 
     */
    public void storeMembers();

    /**
     *
     * Clear the internal storage.
     * 
     */
    public void clearMembers();    

}
