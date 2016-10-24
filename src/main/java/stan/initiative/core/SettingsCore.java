package stan.initiative.core;

public interface SettingsCore
{
    interface ConfigurationModel
    {
        GoogleModel getGoogleModel();
        void setGoogleModel(GoogleModel googleModel);
    }
    interface GoogleModel
    {
        SpeechApiModel getSpeechApi();
    }
    interface SpeechApiModel
    {
        String getApiKey();
        void setApiKey(String apiKey);
    }
}