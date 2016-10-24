package stan.initiative.contracts;

public interface CudgelContract
{
    interface View
    {
    	void moveTo(double x, double y);
    	void showAudioLevel(int audioLevel);
    	void showRecognition(String recognition);
    	void voiceRecognitionOn(boolean on);
    }

    interface Presenter
    {
        void exit();
    	void initFromFile(String filename);
    	void beginMove(double xOffset, double yOffset);
    	void moveTo(double x, double y);
    	void voiceRecognitionSwitch();
    }

    interface Behaviour
    {
        void openMusicPlayer();
        void openSettings();
        void exit();
    }

    interface Callback
    {
        void showMusicPlayerButton(boolean show);
    }
}