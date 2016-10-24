package stan.initiative.managers;

import java.io.File;
import java.util.HashMap;

import stan.initiative.core.SettingsCore;
import stan.initiative.contracts.SettingsContract.GoogleResult;
import stan.initiative.modules.settings.Google;
import stan.initiative.helpers.FileHelper;
import stan.initiative.helpers.json.JSONParser;
import stan.initiative.helpers.json.JSONWriter;

public class SettingsManager
{
    static private final String CONFIG_NAME = "config.stin";
    static private SettingsManager instanse;
    static public SettingsManager getInstanse()
    {
        if(instanse == null)
        {
            instanse = new SettingsManager();
        }
        return instanse;
    }

    private HashMap configuration;

    private SettingsManager()
    {
        File config = new File(PreferenceManager.getInstanse().getConfigDirectory() + "/" + CONFIG_NAME);
        if(!config.exists())
        {
            try
            {
                config.createNewFile();
            }
            catch(Exception e)
            {
                System.out.println(getClass().getName() + " createNewFile - " + e);
                return;
            }
            configuration = new HashMap();
        }
        String result = FileHelper.readFile(config.getPath());
        try
        {
            configuration = (HashMap)new JSONParser().parse(result);
        }
        catch(Exception e)
        {
            System.out.println(getClass().getName() + " read file error - " + e.getMessage());
            configuration = new HashMap();
        }
        initGoogle();
        updateConfig();
    }
    private void initGoogle()
    {
        Object google = configuration.get("google");
        if(google == null)
        {
            google = new HashMap();
            configuration.put("google", google);
        }
        Object speechapi = ((HashMap)google).get("speechapi");
        if(speechapi == null)
        {
            speechapi = new HashMap();
            ((HashMap)google).put("speechapi", speechapi);
        }
    }

    private void updateConfig()
    {
        String data = null;
        try
        {
            data = JSONWriter.mapToJSONString(configuration);
        }
        catch(Exception e)
        {
            System.out.println(getClass().getName() + " mapToJSONString - " + e.getMessage());
            return;
        }
        FileHelper.writeFile(data, PreferenceManager.getInstanse().getConfigDirectory() + "/" + CONFIG_NAME);
    }

    public void setGoogle(SettingsCore.GoogleModel googleModel)
    {
        SettingsCore.SpeechApiModel speechapi = googleModel.getSpeechApi();
        if(speechapi == null)
        {
            return;
        }
        String apikey = speechapi.getApiKey();
        if(apikey == null)
        {
            return;
        }
        System.out.println(getClass().getName() + " google speechapi apiKey exists");
        getSpeechapi().put("apikey", apikey);
        updateConfig();
    }
    public void getGoogle(GoogleResult result)
    {
        Object apikey = getSpeechapi().get("apikey");
        if(apikey == null)
        {
            return;
        }
        result.apiKey((String)apikey);
    }

    private HashMap getGoogle()
    {
        return (HashMap)configuration.get("google");
    }
    private HashMap getSpeechapi()
    {
        return (HashMap)getGoogle().get("speechapi");
    }
}