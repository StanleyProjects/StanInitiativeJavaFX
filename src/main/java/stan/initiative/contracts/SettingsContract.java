package stan.initiative.contracts;

public interface SettingsContract
{
    interface GoogleResult
    {
        void apiKey(String apiKey);
    }

    interface View
    {
    	void moveTo(double x, double y);
    }
    interface Presenter
    {
    	void beginMove(double xOffset, double yOffset);
    	void moveTo(double x, double y);
    	void close();
    }
    interface Behaviour
    {
        void close();
    }
}