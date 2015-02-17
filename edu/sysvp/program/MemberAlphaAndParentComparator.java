package edu.sysvp.program;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import java.util.*;
/**
 * Comparator to sort members lexically by age, for use in the class ClubHelper.
 *
 * @return int - comparative value of s2 in comparison to s1.
 */
public class MemberAlphaAndParentComparator implements Comparator<Member>{

    public int compare(Member s1, Member s2){
    	return s2.getAgeThisYear() - (s1.getAgeThisYear());
    	}
    }

