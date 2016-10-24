package stan.initiative.modules.media.musicplayer;

import java.util.ArrayList;
import java.util.HashMap;

import stan.initiative.contracts.media.MusicPlayerContract;
import stan.initiative.helpers.FileHelper;
import stan.initiative.helpers.json.JSONParser;

import stan.voice.recognition.Voice;
import stan.voice.recognition.GoogleResponse;

public class MusicPlayerPresenter
    implements MusicPlayerContract.Presenter
{
    private boolean dragged = false;
    private double xOffset = 0;
    private double yOffset = 0;

    private int state;

    private MusicPlayerContract.View view;

    public MusicPlayerPresenter(MusicPlayerContract.View v)
    {
        this.view = v;
        state = States.NOT_INIT;
    }

    @Override
    public void beginMove(double x, double y)
    {
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
    public void stop()
    {
        state = States.STOP;
    }
    @Override
    public void playPauseSwitch()
    {
        if(dragged || state == States.NOT_INIT)
        {
            return;
        }
        if(state != States.PLAY)
        {
            state = States.PLAY;
            view.showPause();
        }
        else
        {
            state = States.PAUSE;
            view.showPlay();
        }
    }

    private interface States
    {
        int NOT_INIT = -1;
        int STOP = 1;
        int PLAY = 2;
        int PAUSE = 3;
    }
}