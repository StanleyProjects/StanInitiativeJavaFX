package stan.initiative.modules.main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import stan.initiative.managers.PreferenceManager;
import stan.initiative.modules.cudgel.CudgelPane;
import stan.initiative.modules.media.musicplayer.MusicPlayerPane;
import stan.initiative.modules.settings.SettingsPane;
import stan.initiative.contracts.CudgelContract;
import stan.initiative.contracts.SettingsContract;
import stan.initiative.contracts.media.MusicPlayerContract;
import stan.initiative.contracts.MainContract;
import stan.initiative.units.CallbackConnector;

public class MainScene
    extends Scene
    implements MainContract.View
{
    private Pane cudgelPane;
    private Pane musicPlayerPane;
    private Pane settingsPane;

    private MainContract.Presenter presenter;
    private CudgelContract.Callback cudgelCallback;

    public MainScene(double width, double height)
    {
        super(new Pane(), width, height, Color.TRANSPARENT);
        getStylesheets().addAll(
            "css/cudgel.css",
            "css/media/musicplayer.css");
        presenter = new MainPresenter(this);
        initViews((Pane)getRoot());
        init();
    }
    private void initViews(Pane root)
    {
        cudgelPane = new CudgelPane(new CudgelContract.Behaviour()
        {
            @Override
            public void openMusicPlayer()
            {
                presenter.showMusicPlayer();
            }
            @Override
            public void openSettings()
            {
                presenter.showSettings();
            }
            @Override
            public void exit()
            {
                presenter.exit();
            }
        }, new CallbackConnector<CudgelContract.Callback>()
        {
            @Override
            public void setCallback(CudgelContract.Callback c)
            {
                cudgelCallback = c;
            }
        });
        musicPlayerPane = new MusicPlayerPane(new MusicPlayerContract.Behaviour()
        {
            @Override
            public void close()
            {
                presenter.hideMusicPlayer();
            }
        });
        settingsPane = new SettingsPane(new SettingsContract.Behaviour()
        {
            @Override
            public void close()
            {
                presenter.hideSettings();
            }
        });
        root.getChildren().addAll(cudgelPane, musicPlayerPane, settingsPane);
        configRoot(root);
    }
    public void init()
    {
        cudgelPane.setLayoutX(getWidth()/2 - cudgelPane.getWidth()/2);
        cudgelPane.setLayoutY(getHeight()/2 - cudgelPane.getHeight()/2);
        System.out.println(getClass().getName() + " w " + getWidth() + " h " + getHeight());
        musicPlayerPane.setVisible(false);
        settingsPane.setLayoutX(getWidth()/2 - settingsPane.getWidth()/2);
        settingsPane.setLayoutY(getHeight()/2 - settingsPane.getHeight()/2);
        settingsPane.setVisible(false);
    }

    private void configRoot(Pane root)
    {
        root.setStyle("-fx-background-color: null");
        root.layout();
    }

    @Override
    public void showCudgel()
    {
        cudgelPane.setVisible(true);
    }
    @Override
    public void hideCudgel()
    {
        cudgelPane.setVisible(false);
    }
    @Override
    public void showMusicPlayer()
    {
        musicPlayerPane.setVisible(true);
        cudgelCallback.showMusicPlayerButton(false);
    }
    @Override
    public void hideMusicPlayer()
    {
        musicPlayerPane.setVisible(false);
        cudgelCallback.showMusicPlayerButton(true);
    }
    @Override
    public void showSettings()
    {
        settingsPane.setVisible(true);
    }
    @Override
    public void hideSettings()
    {
        settingsPane.setVisible(false);
    }
}