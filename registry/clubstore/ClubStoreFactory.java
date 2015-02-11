package tig058.handin01.clubstore;

import java.util.ArrayList;
import tig058.handin01.member.Member;
/**
 *
 * To find a suiting ClubStore for your needs and to prevent multiple
 * ClubSore instances we have developed a factory class that
 * instantiates an appriopriate ClubStore.
 *
 *
 */
public class ClubStoreFactory{

    /* 
     * Really should be more of a plugin arch, 
     * but this will do fine for now
     */
    private static ArrayList<ClubStore> clubstores ;

    static {
	clubstores   = new ArrayList<ClubStore>();

	// Hrmpf, not really nice looking :(
	clubstores.add(TextClubStore.getInstance());
    }

    /**
     * 
     * Returnes a ClubStore supporting the requested backend, or null
     * if none could be found.
     *
     * @param String - what backend type you want to use. 
     * @return A Clubstore supporting the requested backend or null if
     * no ClubStore was found.
     *
     */
    public static ClubStore getClubStore(String s) {
	// Loop through the clubstores 
	for (ClubStore c: clubstores) {
	    // does this clubstore support the requested backend?
	    if (c.supports(s)) {
		return c;
	    }
	}
	return null;
    }


}
