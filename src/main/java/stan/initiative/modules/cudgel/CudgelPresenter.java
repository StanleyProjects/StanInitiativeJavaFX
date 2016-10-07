package stan.initiative.modules.cudgel;

import stan.initiative.contracts.CudgelContract;

public class CudgelPresenter
    implements CudgelContract.Presenter
{
    private boolean dragged = false;

    private CudgelContract.View view;

    public CudgelPresenter(CudgelContract.View v)
    {
        this.view = v;
    }

    @Override
    public void beginMove(double x, double y)
    {
    	dragged = false;
    }
    @Override
    public void moveTo(double x, double y)
    {
    	dragged = true;
    	view.moveTo(x, y);
    }
}