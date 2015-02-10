import tig058.handin01.log.Logger;

public class TestLogger {

    public static void main(String[] args) {
	Logger.setDebugLevel();
	Logger.debugEnterMethod();
	System.err.println("---------------");

	Logger.debug("Debug");
	Logger.info("Info");
	Logger.warning("Warning");
	System.err.println("---------------");

	Logger.setInfoLevel();
	Logger.debug("Debug");
	Logger.info("Info");
	Logger.warning("Warning");
	System.err.println("---------------");

	Logger.setWarningLevel();
	Logger.debug("Debug");
	Logger.info("Info");
	Logger.warning("Warning");
	System.err.println("---------------");


	Logger.setDebugLevel();
	Logger.debugLeaveMethod();
    }
}
	
