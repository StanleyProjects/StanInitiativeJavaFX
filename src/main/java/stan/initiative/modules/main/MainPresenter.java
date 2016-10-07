package stan.initiative.modules.main;

import stan.initiative.contracts.MainContract;

public class MainPresenter
    implements MainContract.Presenter
{
    private MainContract.View view;

    public MainPresenter(MainContract.View v)
    {
        this.view = v;
    }
}