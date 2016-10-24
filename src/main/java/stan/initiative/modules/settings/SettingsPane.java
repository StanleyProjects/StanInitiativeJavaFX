package stan.initiative.modules.settings;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import stan.initiative.contracts.SettingsContract;

public class SettingsPane
    extends VBox
	implements SettingsContract.View
{
    private double xMin, yMin, xMax, yMax;

    private FileChooser fileChooser;

    private SettingsTopBox topBox;

    private SettingsContract.Presenter presenter;
    private SettingsContract.Behaviour behaviour;

    public SettingsPane(SettingsContract.Behaviour b)
    {
        super();
        setStyle("-fx-background-color: blue");
        setPrefSize(364,256);
        behaviour = b;
        initViews();
        Platform.runLater(()->
        {
            init();
        });
    }
    private void initViews()
    {
        topBox = new SettingsTopBox(new SettingsTopBox.SettingsTopBoxBehaviour()
        {
            @Override
            public void close()
            {
                behaviour.close();
            }
        });
        topBox.setMinHeight(36);
        getChildren().addAll(topBox);
    }
    private void init()
    {
        presenter = new SettingsPresenter(this);
        setMaxMinXY(getParent().getBoundsInParent().getMinX(),
            getParent().getBoundsInParent().getMinY(),
            getParent().getBoundsInParent().getMaxX(),
            getParent().getBoundsInParent().getMaxY());
    }
    private void setMaxMinXY(double xmi, double ymi, double xma, double yma)
    {
        xMin = xmi;
        yMin = ymi;
        xMax = xma;
        yMax = yma;
    }

    @Override
    public void moveTo(double x, double y)
    {
        if(x < xMin)
        {
            x = xMin;
        }
        else if(x + getWidth() > xMax)
        {
            x = xMax - getWidth();
        }
        if(y < yMin)
        {
            y = yMin;
        }
        else if(y + getHeight() > yMax)
        {
            y = yMax - getHeight();
        }
        setLayoutX(x);
        setLayoutY(y);
    }
}