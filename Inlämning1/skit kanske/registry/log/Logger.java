
package tig058.handin01.log;
/**
 * Logger provides logging functionality. It comes with three levels
 * of logging. The level is easily set with e method.
 *
 * Logger uses stderr for printing, to make sure outpur on stdout is
 * not interferred with. 
 * 
 * You don't need to create an instance of the Logger class, the
 * methods are static using a single instance. Logger is designed to
 * be used as a singleton. This means you can not instantiate a Logger
 * object yourself. Instead you have to ask for the single available
 * instance (hence the name singletong) by calling getInstance().
 *
 * The three levels of logging are:
 *  <li>DEBUG - all messages are printed
 *  <li>INFO  - INFO and WARN messages are printed (not DEBUG)
 *  <li>WARN  - only WARN messages are printed (not INFO and DEBUG)
 *
 * Example use:
 *
 * <pre>
 *  private myMethod() {
 *	Logger.debugEnterMethod();
 *      .... do something ...
 *      Logger.info("Database is synced");
 *      .... do something ...
 *      Logger.setWarningLevel();
 *      .... do something ...
 *	Logger.debugLeaveMethod();
 *  }
 * </pre>
 * 
 *
 */
public class Logger {


    /**
     *
     * Enum for the different levels of logging
     *
     */ 
    public enum LogLevel {
	DEBUG,
	INFO,
	WARN
    }

    private static Logger ll=null;

    private static LogLevel loglevel ;

    /**
     * Logger is designed as a singleton class.  To get the (only)
     * instance of Logger, you call this method to get a reference to
     * the object.
     *
     * @return the only instance of Logger
     *
     */ 
    private static Logger getInstance() {
	if (ll==null) {
	    ll = new Logger();
	}
	return ll;
    }
    

    private Logger() {
	loglevel=LogLevel.WARN;
    }
    
    /**
     * 
     * Sets the log level to WARN 
     *
     */ 
    public static void setWarningLevel() {
	setLevel(LogLevel.WARN);
    }

    /**
     * 
     * Sets the log level to DEBUG 
     *
     */ 
    public static void setDebugLevel() {
	setLevel(LogLevel.DEBUG);
    }

    /**
     * 
     * Sets the log level to INFO 
     *
     */ 
    public static void setInfoLevel() {
	setLevel(LogLevel.INFO);
    }

    /**
     * 
     * Set the log level
     *
     * @param LogLevel - the loglevel to set
     */ 
    public static void setLevel(LogLevel l) {
	getInstance().loglevel=l;
    }

    /**
     * 
     * Log a message. Printed if the loglevel is correct, ignored otherwise.
     *
     * @param String - the messasge to print
     * @param LogLevel - the loglevel for the message
     *
     */ 
    public static void log(String s, LogLevel l) {
	if (l.compareTo(getInstance().loglevel)>=0) { 
	    System.err.printf("%s\n", s);
	}
    }

    /**
     * 
     * Log a message. Printed if the loglevel is correct, ignored
     * otherwise. Before the actual message the calling method name is
     * printed.
     *
     * @param String - the messasge to print
     * @param LogLevel - the loglevel for the message
     *
     */ 
    private static void logM(String s, LogLevel l) {
	int index=3;
	StackTraceElement[] se = Thread.currentThread().getStackTrace();
	if (l.compareTo(getInstance().loglevel)>=0) { 
	    String pre = null;
	    if (se!=null && 
		se.length >= index && 
		se[index]!=null) {
		System.err.printf("%s %s\n",
				  s, 
				  se[index].getMethodName());
	    } else {
		System.err.printf(s);
	    }
	}
    }

    /**
     * 
     * Log a message using DEBUG level. 
     *
     * @param String - the messasge to print
     *
     */ 
    public static void debug(String s) {
	log(s, LogLevel.DEBUG);
    }

    /**
     * 
     * Log a message using WARN level. 
     *
     * @param String - the messasge to print
     *
     */ 
    public static void warning(String s) {
	log(s, LogLevel.WARN);
    }

    /**
     * 
     * Log a message using INFO level. 
     *
     * @param String - the messasge to print
     *
     */ 
    public static void info(String s) {
	log(s, LogLevel.INFO);
    }

    /**
     * 
     * Log a message using DEBUG level, with the calling method name
     * prepended.
     *
     * @param String - the messasge to print
     *
     */ 
    public static void debugM(String s) {
	logM(s, LogLevel.DEBUG);
    }

    /**
     * 
     * Log a message using WARN level, with the calling method name
     * prepended.
     *
     * @param String - the messasge to print
     *
     */ 
    public static void warningM(String s) {
	logM(s, LogLevel.WARN);
    }

    /**
     * 
     * Log a message using INFO level, with the calling method name
     * prepended.
     *
     * @param String - the messasge to print
     *
     */ 
    public static void infoM(String s) {
	logM(s, LogLevel.INFO);
    }

    /**
     * 
     * Log the calling method name with DEBUG level.
     *
     */ 
    public static void debugM() {
	logM("", LogLevel.DEBUG);
    }

    /**
     * 
     * Log the calling method name with WARN level.
     *
     */ 
    public static void warningM() {
	logM("", LogLevel.WARN);
    }

    /**
     * 
     * Log the calling method name with INFO level.
     *
     */ 
    public static void infoM() {
	logM("", LogLevel.INFO);
    }

    /**
     * 
     * Log the calling method name with a String version of right
     * pointing arrow, using DEBUG level.
     *
     */ 
    public static void debugEnterMethod() {
	logM("---->", LogLevel.DEBUG);
    }

    /**
     * 
     * Log the calling method name with a String version of right
     * pointing arrow, using WARN level.
     *
     */ 
    public static void warningEnterMethod() {
	logM("---->", LogLevel.WARN);
    }

    /**
     * 
     * Log the calling method name with a String version of right
     * pointing arrow, using INFO level.
     *
     */ 
    public static void infoEnterMethod() {
	logM("---->", LogLevel.INFO);
    }


    /**
     * 
     * Log the calling method name with a String version of left
     * pointing arrow, using DEBUG level.
     *
     */ 
    public static void debugLeaveMethod() {
	logM("<----", LogLevel.DEBUG);
    }

    /**
     * 
     * Log the calling method name with a String version of left
     * pointing arrow, using WARN level.
     *
     */ 
    public static void warningLeaveMethod() {
	logM("<----", LogLevel.WARN);
    }

    /**
     * 
     * Log the calling method name with a String version of left
     * pointing arrow, using INFO level.
     *
     */ 
    public static void infoLeaveMethod() {
	logM("<----", LogLevel.INFO);
    }

}
