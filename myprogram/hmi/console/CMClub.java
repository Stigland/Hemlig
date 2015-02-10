package myprogram.hmi.console;

import java.util.ArrayList;
import java.util.Collections;

import tig058.handin01.registry.ClubRegistry;
import tig058.handin01.member.Member;
import tig058.handin01.member.MemberAlphaComparator;
import tig058.handin01.member.MemberAlphaFirstnameComparator;
import tig058.handin01.log.Logger;

import myprogram.hmi.cli.ClubHelper;


public class CMClub {

    public static void printProp(String s, String prop) {
	String propValue = System.getProperty(prop);
	if (propValue!=null) {
	    System.out.println(s + propValue );
	}
    }

    public static void main(String[] args) {
	final ClubHelper ch = new ClubHelper();
	Menu m = new Menu(ch);
	m.run();

    }

}
