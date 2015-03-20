package edu.sysvp.program;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import java.util.*;

public class MemberAlphaComparator2 implements Comparator<Member>{
	/**
	 * Comparator to sort members lexically by team, for use in the class ClubHelper.
	 *
	 * @return int - comparative value of s1 in comparison to s2.
	 */
    public int compare(Member s1, Member s2){
	    return s1.getTeam().compareTo(s2.getTeam());
    }
}
