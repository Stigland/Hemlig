package tig058.handin01.member;

import java.util.*;
/**
 * Simple compator class intented to be used when wanting to Sort
 * lists of members alphabetically with the given name.
 *
 */

public class MemberAlphaFirstnameComparator implements Comparator<Member>{

    public int compare(Member s1, Member s2){
	if(s1.getGivenName().equals(s2.getGivenName())){
	    return s1.getFamilyName().compareTo(s2.getFamilyName());
	}else{
	    return s1.getGivenName().compareTo(s2.getGivenName());
	}
    }
}
