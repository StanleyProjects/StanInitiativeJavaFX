package stan.initiative.contracts;

public interface MainContract
{
    interface View
    {
        void showCudgel();
        void hideCudgel();
        void showMusicPlayer();
        void hideMusicPlayer();
        void showSettings();
        void hideSettings();
    }

    interface Presenter
    {
        void showMusicPlayer();
        void hideMusicPlayer();
        void showSettings();
        void hideSettings();
        void exit();
    }
}