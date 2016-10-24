package stan.initiative.modules.settings;

import stan.initiative.contracts.SettingsContract;
import stan.initiative.managers.SettingsManager;

public class SettingsPresenter
    implements SettingsContract.Presenter
{
    private boolean dragged = false;
    private double xOffset = 0;
    private double yOffset = 0;

    private SettingsContract.View view;

    public SettingsPresenter(SettingsContract.View v)
    {
        view = v;
    }

    @Override
    public void close()
    {
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
}