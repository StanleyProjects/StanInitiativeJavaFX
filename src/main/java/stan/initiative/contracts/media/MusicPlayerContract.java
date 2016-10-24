package stan.initiative.contracts.media;

public interface MusicPlayerContract
{
    interface View
    {
        void moveTo(double x, double y);
        void showStop();
        void showPlay();
        void showPause();
    }

    interface Presenter
    {
    	void beginMove(double xOffset, double yOffset);
        void moveTo(double x, double y);
        void stop();
        void playPauseSwitch();
    }

    interface Behaviour
    {
        void close();
    }
}