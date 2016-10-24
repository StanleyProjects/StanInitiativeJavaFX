package stan.initiative.modules.settings;

import java.util.HashMap;

import stan.initiative.core.SettingsCore;

public class Google
    implements SettingsCore.GoogleModel
{
    private SettingsCore.SpeechApiModel speechApiModel;

    public Google(HashMap g)
    {
        Object s = g.get("speechapi");
        if(s==null)
        {
            return;
        }
        initSpeechApi((HashMap)s);
    }
    private void initSpeechApi(HashMap speechapi)
    {
        Object a = speechapi.get("apikey");
        if(a==null)
        {
            return;
        }
        speechApiModel = new SettingsCore.SpeechApiModel()
        {
            public String getApiKey()
            {
                return (String)a;
            }
            public void setApiKey(String apiKey)
            {

            }
        };
    }

    public SettingsCore.SpeechApiModel getSpeechApi()
    {
        return speechApiModel;
    }
}