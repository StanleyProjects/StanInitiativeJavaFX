package stan.initiative.modules.cudgel;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import stan.initiative.contracts.CudgelContract;

public class CudgelPane
    extends Pane
	implements CudgelContract.View
{
    private double xMin, yMin, xMax, yMax;

    private FileChooser fileChooser;

    private CudgelButton cudgelButton;
    private Button settingsButton;
    private RecognitionLabel recognition;

    private CudgelContract.Presenter presenter;

    public CudgelPane()
    {
        super();
        setStyle("-fx-background-color: null");
        setPrefSize(144,144);
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
        settingsButton = new Button();
        settingsButton.setMinSize(36, 36);
        settingsButton.setId("settings_button");
        recognition = new RecognitionLabel();
        getChildren().addAll(cudgelButton, settingsButton, recognition);
    }
    private void init()
    {
        setMaxMinXY(getParent().getBoundsInParent().getMinX(),
            getParent().getBoundsInParent().getMinY(),
            getParent().getBoundsInParent().getMaxX(),
            getParent().getBoundsInParent().getMaxY());
        fileChooser = new FileChooser();
        cudgelButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                presenter.voiceRecognitionSwitch();
            }
        });
        cudgelButton.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if(event.getButton() == MouseButton.PRIMARY)
                {
                    presenter.beginMove(getLayoutX() - event.getScreenX(), getLayoutY() - event.getScreenY());
                }
                else if(event.getButton() == MouseButton.SECONDARY)
                {
                    presenter.exit();
                }
            }
        });
        cudgelButton.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                presenter.moveTo(event.getScreenX(), event.getScreenY());
            }
        });
        moveCudgelButton();
        settingsButton.setLayoutX(getWidth() - settingsButton.getWidth());
        settingsButton.setLayoutY(getHeight()/2 - settingsButton.getHeight()/2);
        settingsButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                openConfigurationFile();
            }
        });
        recognition.setLayoutY(getHeight() - recognition.getHeight());
    }
    private void setMaxMinXY(double xmi, double ymi, double xma, double yma)
    {
        xMin = xmi;
        yMin = ymi;
        xMax = xma;
        yMax = yma;
    }
    private void openConfigurationFile()
    {
        File file = fileChooser.showOpenDialog(null);
        if(file != null && file.exists())
        {
            presenter.initFromFile(file.getAbsolutePath());
        }
        else
        {
        }
    }

    private void moveCudgelButton()
    {
        layout();
        cudgelButton.setLayoutX(getWidth()/2 - cudgelButton.getWidth()/2);
        cudgelButton.setLayoutY(getHeight()/2 - cudgelButton.getHeight()/2);
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
    @Override
    public void showAudioLevel(int audioLevel)
    {
        audioLevel -= 1500;
        if(audioLevel > 0)
        {
            if(audioLevel > 2000)
            {
                audioLevel = 2000;  
            }
        }
        else
        {
            audioLevel = 0;
        }
        final int p = audioLevel;
        Platform.runLater(()->
        {
            cudgelButton.changeSize(p/20);
            moveCudgelButton();
        });
    }
    @Override
    public void voiceRecognitionOn(boolean on)
    {
        if(on)
        {
            cudgelButton.setEnable();
        }
        else
        {
            cudgelButton.setNone();
        }
        moveCudgelButton();
    }
    @Override
    public void showRecognition(String r)
    {
        Platform.runLater(()->
        {
            recognition.addText(r);
        });
    }
}