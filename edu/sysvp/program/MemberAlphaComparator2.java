package edu.sysvp.program;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import java.util.*;
/**
 * Simple compator class intented to be used when wanting to Sort
 * lists of members alphabetically. Sorts primarily on family name and
 * secondaily on given name.
 *
 */
public class MemberAlphaComparator2 implements Comparator<Member>{

    public int compare(Member s1, Member s2){
	    return s1.getTeam().compareTo(s2.getTeam());
    }
}
