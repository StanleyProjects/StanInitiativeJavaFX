package stan.initiative.modules.main;

import stan.initiative.contracts.MainContract;

public class MainPresenter
    implements MainContract.Presenter
{
    private MainContract.View view;
    private boolean showMusicPlayer;

    public MainPresenter(MainContract.View v)
    {
        view = v;
        showMusicPlayer = false;
    }

    @Override
    public void showMusicPlayer()
    {
    	if(!showMusicPlayer)
    	{
    		showMusicPlayer = true;
    		view.showMusicPlayer();
    	}
    }
    @Override
    public void hideMusicPlayer()
    {
    	if(showMusicPlayer)
    	{
    		showMusicPlayer = false;
    		view.hideMusicPlayer();
    	}
    }
    @Override
    public void showSettings()
    {
        view.hideCudgel();
        view.showSettings();
    }
    @Override
    public void hideSettings()
    {
        view.showCudgel();
        view.hideSettings();
    }
    @Override
    public void exit()
    {
    	System.exit(0);
    }
}