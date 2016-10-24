package stan.initiative.modules.cudgel;

import java.util.HashMap;

import stan.initiative.contracts.ModelsContract.GoogleModel;
import stan.initiative.contracts.ModelsContract.SpeechApiModel;

public class Google
    implements GoogleModel
{
    private SpeechApiModel speechApiModel;

    public Google(HashMap google)
    {
        Object s = google.get("speechapi");
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
        speechApiModel = new SpeechApiModel()
        {
            public String getApiKey()
            {
                return (String)a;
            }
        };
    }

    public SpeechApiModel getSpeechApi()
    {
        return speechApiModel;
    }
}