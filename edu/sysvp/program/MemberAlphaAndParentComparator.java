package edu.sysvp.program;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import java.util.*;
/**
 *Comparator to sort members lexically by team, for use in the class ClubHelper.
 *
 *@return comparative value of s1 in comparison to s2.
 */
public class MemberAlphaAndParentComparator implements Comparator<Member>{

    public int compare(Member s1, Member s2){
    	if (s1.getParents() != null){
    		return 100;
    	}
    	else{
    		return -100;
    	}
    }
}
