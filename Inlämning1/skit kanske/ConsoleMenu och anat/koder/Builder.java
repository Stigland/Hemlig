package com.sandklef.edu.koder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.InterruptedException;

public class Builder {

    private static String latestOut=null;
    private static String latestErr=null;

    public static String getLatestOut(){
	return latestOut;
    }

    public static String getLatestErr(){
	return latestErr;
    }

    public static int compile(String filename) {
	Process process = null;
	BufferedReader inStream = null;
	BufferedReader errStream = null;
	String line;
	int ret;

	try {

	    String cmd ="/usr/bin/javac " + filename;
	    
	    System.out.println("  COMPILE: " + cmd);

	    process = Runtime.getRuntime().exec(cmd);
	    
	    inStream = new BufferedReader(new InputStreamReader( process.getInputStream() ));  
	    errStream = new BufferedReader(new InputStreamReader( process.getErrorStream() ));  

	    latestOut="";
	    while((line=inStream.readLine())!=null){
		latestOut += line + "\n";
		System.out.println("  COMPILE:" + line);
	    }
	    
	    latestErr="";
	    while((line=errStream.readLine())!=null){
		latestErr += line + "\n";
		System.out.println("  COMPILE:" + line);
	    }
	    
	    System.out.println("  WAIT:");
	    process.waitFor();  
	    System.out.println("  EXIT VAL?:");
	    ret = process.exitValue();

	    System.out.println("  COMPILE:" + ret);
	    return ret;
	} catch (IOException e)  {
	    System.err.println(e);
	} catch (InterruptedException e)  {
	    System.err.println(e);
	}
	return 1;
    }


    public static int run(String className) {
	Process process = null;
	BufferedReader inStream = null;
	BufferedReader errStream = null;
	String line;
	int ret;

	try {

	    String cmd ="/usr/bin/java " + className;
	    
	    System.out.println("  COMPILE: " + cmd);

	    process = Runtime.getRuntime().exec(cmd);
	    
	    inStream = new BufferedReader(new InputStreamReader( process.getInputStream() ));  

	    process.waitFor();  

	    inStream = new BufferedReader(new InputStreamReader( process.getInputStream() ));  
	    errStream = new BufferedReader(new InputStreamReader( process.getErrorStream() ));  

	    latestOut="";
	    while((line=inStream.readLine())!=null){
		latestOut += line + "\n";
		System.out.println("  COMPILE:" + line);
	    }
	    
	    latestErr="";
	    while((line=errStream.readLine())!=null){
		latestErr += line + "\n";
		System.out.println("  COMPILE:" + line);
	    }
	    
	    ret = process.exitValue();

	    System.out.println("  RUN:" + ret);
	    return ret;
	} catch (IOException e)  {
	    System.err.println(e);
	} catch (InterruptedException e)  {
	    System.err.println(e);
	}
	return 1;
    }


}