package stan.initiative.contracts;

public interface CudgelContract
{
    interface View
    {
    	void moveTo(double x, double y);
    }

    interface Presenter
    {
    	void beginMove(double x, double y);
    	void moveTo(double x, double y);
    }
}