package stan.initiative.modules.cudgel;

import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import stan.initiative.contracts.CudgelContract;

public class CudgelPane
    extends Pane
	implements CudgelContract.View
{
    private CudgelButton cudgelButton;

    private double xOffset = 0;
    private double yOffset = 0;
    private CudgelContract.Presenter presenter;

    public CudgelPane()
    {
        super();
        setStyle("-fx-background-color: blue");
        setPrefSize(200,200);
    	presenter = new CudgelPresenter(this);
        initViews();
        Platform.runLater(()->
        {
            init();
        });
    }
    private void initViews()
    {
        cudgelButton = new CudgelButton();
        getChildren().add(cudgelButton);
    }
    private void init()
    {
        cudgelButton.setLayoutX(getWidth()/2 - cudgelButton.getWidth()/2);
        cudgelButton.setLayoutY(getHeight()/2 - cudgelButton.getHeight()/2);
        System.out.println(getClass().getName() + " w " + getWidth() + " h " + getHeight());
        System.out.println(getClass().getName() + " getMaxX " + getParent().getBoundsInParent().getMaxX());
        cudgelButton.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = getLayoutX() - event.getScreenX();
                yOffset = getLayoutY() - event.getScreenY();
                presenter.beginMove(event.getScreenX(), event.getScreenY());
            }
        });
        cudgelButton.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                presenter.moveTo(event.getScreenX() + xOffset, event.getScreenY() + yOffset);
            }
        });
    }

    @Override
    public void moveTo(double x, double y)
    {
        if(x < getParent().getBoundsInParent().getMinX())
        {
            x = getParent().getBoundsInParent().getMinX();
        }
        else if(x + getWidth() > getParent().getBoundsInParent().getMaxX())
        {
            x = getParent().getBoundsInParent().getMaxX() - getWidth();
        }
        if(y < getParent().getBoundsInParent().getMinY())
        {
            y = getParent().getBoundsInParent().getMinY();
        }
        else if(y + getHeight() > getParent().getBoundsInParent().getMaxY())
        {
            y = getParent().getBoundsInParent().getMaxY() - getHeight();
        }
        setLayoutX(x);
        setLayoutY(y);
    }
}