package stan.initiative.managers;

import java.io.File;

import stan.initiative.Main;

public class PreferenceManager
{
    static private PreferenceManager instanse;
    static public PreferenceManager getInstanse()
    {
        if(instanse == null)
        {
            instanse = new PreferenceManager();
        }
        return instanse;
    }

    private String preferencePath;

    private PreferenceManager()
    {
    }

    public void setPreferencePath(String pck)
    {
        preferencePath = System.getProperty("user.home");
        preferencePath += "/"+pck;
        checkRoot();
        //System.out.println(getClass().getName() + " p " + preferencePath);
    }
    private boolean checkRoot()
    {
        File images = new File(getImagesDirectory());
        images.mkdirs();
        File config = new File(getConfigDirectory());
        config.mkdirs();
        if(images.exists() && config.exists())
        {
            System.out.println(getClass().getName()
            + "\nImagesDirectory - " + getImagesDirectory()
            + "\nConfigDirectory - " + getConfigDirectory());
            return true;
        }
        return false;
    }

    public String getPreferencePath()
    {
        return preferencePath;
    }
    public String getImagesDirectory()
    {
        return getPreferencePath() + "/images";
    }
    public String getConfigDirectory()
    {
        return getPreferencePath() + "/config";
    }
}