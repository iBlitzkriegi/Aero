package Home;

/**
 * Created by Blitz on 5/14/2016.
 */
public class Settings {
    private static String commandStart = "-";
    private static String msgStart = "Ӝ ";
    private static String game = "With Baefell.";
    private static String version = "Alpha 2.2";
    private static String name = "Dank Gasai";
    private static String adminMsg = "You are not one of my admins! Sorry!";

    public static String getGame()
    {
        return game;
    }

    public static String getCommandStart()
    {
        return commandStart;
    }

    public static String getMsgStart(){

        return msgStart;
    }

    public static String getVersion(){

        return version;
    }
    public static String getName(){
        return name;
    }

    public static String getAdminMsg(){
        return adminMsg;
    }

}


