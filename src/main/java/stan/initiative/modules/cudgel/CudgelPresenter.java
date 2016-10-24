package stan.initiative.modules.cudgel;

import java.util.ArrayList;
import java.util.HashMap;

import stan.initiative.contracts.CudgelContract;
import stan.initiative.contracts.SettingsContract.GoogleResult;
import stan.initiative.managers.SettingsManager;
import stan.initiative.modules.settings.Google;
import stan.initiative.helpers.FileHelper;
import stan.initiative.helpers.json.JSONParser;

import stan.voice.recognition.Voice;
import stan.voice.recognition.GoogleResponse;

public class CudgelPresenter
    implements CudgelContract.Presenter
{
    private boolean dragged = false;
    private double xOffset = 0;
    private double yOffset = 0;

    private Voice voice;
    private boolean initVoiceRecognition = false;
    private boolean voiceRecognitionOn = false;

    private CudgelContract.View view;

    public CudgelPresenter(CudgelContract.View v)
    {
        view = v;
        initFromConfig();
    }

    private ArrayList getAlternatives(HashMap responseObject)
    {
        return (ArrayList)((HashMap)((ArrayList)responseObject.get("result")).get(0)).get("alternative");
    }
    private void parseAlternatives(ArrayList alternatives)
    {
        String transcript = (String)((HashMap)alternatives.get(0)).get("transcript");
        parseTranscript(transcript);
    }
    private void parseTranscript(String transcript)
    {
        System.out.println(getClass().getName() + " response - " + transcript);
        view.showRecognition(transcript);
    }

    @Override
    public void exit()
    {
        if(voice != null)
        {
            voice.stopRecognize();
        }
    }

    @Override
    public void initFromFile(String filename)
    {
        String result = FileHelper.readFile(filename);
        try
        {
            initFromHashMap((HashMap)new JSONParser().parse(result));
        }
        catch(Exception e)
        {
            System.out.println(getClass().getName() + " Read file error - " + e.getMessage());
        }
    }
    private void initFromConfig()
    {
        SettingsManager.getInstanse().getGoogle(new GoogleResult()
        {
            public void apiKey(String apiKey)
            {
                initVoiceRecognition(apiKey);
            }
        });
    }
    private void initFromHashMap(HashMap main)
    {
        Object g = main.get("google");
        if(g==null)
        {
            return;
        }
        SettingsManager.getInstanse().setGoogle(new Google((HashMap)g));
        initFromConfig();
    }
    public void initVoiceRecognition(String googleSpeechApiKey)
    {
        voice = new Voice(new Voice.IResponseListener()
        {
            @Override
            public void getSpeech(GoogleResponse deserialized)
            {
                ArrayList alternatives = getAlternatives((HashMap)deserialized.responseObject);
                if(alternatives.size() > 0)
                {
                    parseAlternatives(alternatives);
                }
            }
            @Override
            public void audioLevel(int al)
            {
                view.showAudioLevel(al);
            }
        }, googleSpeechApiKey)
        {
            @Override
            public GoogleResponse deSerialize(String response)
            {
                response = response.replace("{\"result\":[]}", "");
                if(response.length() == 0)
                {
                    response = "{\"result\":[{\"alternative\":[]}]}";
                }
                HashMap obj = null;
                try
                {
                    obj = (HashMap) new JSONParser().parse(response);
                }
                catch(Exception e)
                {
                    System.out.println("parse response error - " + e.getMessage());
                }
                return new GoogleResponse<HashMap>(obj, response);
            }
        };
        initVoiceRecognition = true;
        voice.stopRecognize();
        view.voiceRecognitionOn(false);
    }
    @Override
    public void beginMove(double x, double y)
    {
        System.out.println(getClass().getName() + " beginMove x " + x + " y " + y);
    	dragged = false;
        xOffset = x;
        yOffset = y;
    }
    @Override
    public void moveTo(double x, double y)
    {
    	dragged = true;
    	view.moveTo(x + xOffset, y + yOffset);
    }
    @Override
    public void voiceRecognitionSwitch()
    {
        if(dragged || !initVoiceRecognition)
        {
            return;
        }
        voiceRecognitionOn = !voiceRecognitionOn;
        if(voiceRecognitionOn)
        {
            try
            {
                voice.startRecognize();
            }
            catch(Exception e)
            {
                System.out.println(getClass().getName() + " Voice start Recognize error - " + e.getMessage());
            }
            view.voiceRecognitionOn(true);
        }
        else
        {
            voice.stopRecognize();
            view.voiceRecognitionOn(false);
        }
        System.out.println(getClass().getName() + " voiceRecognitionOn " + voiceRecognitionOn);
    }
}