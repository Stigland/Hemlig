package edu.sysvp.program;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import java.util.*;

public class IDcomparator implements Comparator<Member>{
	/**
	 * Comparator to sort members by ID, for use in the class ClubHelper.
	 *
	 * @return int - comparative value of s1 in comparison to s2.
	 */
    public int compare(Member s1, Member s2){
	    return Integer.compare(s1.getId(), s2.getId());
    }
}
