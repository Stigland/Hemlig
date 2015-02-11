package tig058.handin01.member;

import java.util.*;
/**
 * Simple compator class intented to be used when wanting to Sort
 * lists of members alphabetically. Sorts primarily on family name and
 * secondaily on given name.
 *
 */
public class MemberAlphaComparator implements Comparator<Member>{

    public int compare(Member s1, Member s2){
	if(s1.getFamilyName().equals(s2.getFamilyName())){
	    return s1.getGivenName().compareTo(s2.getGivenName());
	}else{
	    return s1.getFamilyName().compareTo(s2.getFamilyName());
	}
    }
}
