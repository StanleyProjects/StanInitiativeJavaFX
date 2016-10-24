package stan.initiative.modules.media.musicplayer;

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

import stan.initiative.contracts.media.MusicPlayerContract;

public class MusicPlayerPane
    extends Pane
	implements MusicPlayerContract.View
{
    private double xMin, yMin, xMax, yMax;

    private Button playButton;

    private MusicPlayerContract.Presenter presenter;
    private MusicPlayerContract.Behaviour behaviour;

    public MusicPlayerPane(MusicPlayerContract.Behaviour b)
    {
        super();
        setStyle("-fx-background-color: null");
        setPrefSize(144,144);
    	presenter = new MusicPlayerPresenter(this);
        behaviour = b;
        initViews();
        Platform.runLater(()->
        {
            init();
        });
    }
    private void initViews()
    {
        playButton = new Button();
        playButton.setMinSize(56, 56);
        getChildren().addAll(playButton);
    }
    private void init()
    {
        setMaxMinXY(getParent().getBoundsInParent().getMinX(),
            getParent().getBoundsInParent().getMinY(),
            getParent().getBoundsInParent().getMaxX(),
            getParent().getBoundsInParent().getMaxY());
        hoverProperty().addListener((observable, oldValue, newValue)->
        {
            System.out.println(getClass().getName() + " hover " + newValue);
        });
        playButton.setOnAction((event)->
        {
            presenter.playPauseSwitch();
        });
        playButton.setOnMousePressed((event)->
        {
            if(event.getButton() == MouseButton.PRIMARY)
            {
                presenter.beginMove(getLayoutX() - event.getScreenX(), getLayoutY() - event.getScreenY());
            }
            else if(event.getButton() == MouseButton.SECONDARY)
            {
                behaviour.close();
            }
        });
        playButton.setOnMouseDragged((event)->
        {
            presenter.moveTo(event.getScreenX(), event.getScreenY());
        });
        movePlayButton();
        showPlay();
    }
    private void setMaxMinXY(double xmi, double ymi, double xma, double yma)
    {
        xMin = xmi;
        yMin = ymi;
        xMax = xma;
        yMax = yma;
    }

    private void movePlayButton()
    {
        layout();
        playButton.setLayoutX(getWidth()/2 - playButton.getWidth()/2);
        playButton.setLayoutY(getHeight()/2 - playButton.getHeight()/2);
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
    public void showStop()
    {
    }
    @Override
    public void showPlay()
    {
        playButton.setId("play_button");
    }
    @Override
    public void showPause()
    {
        playButton.setId("pause_button");
    }
}