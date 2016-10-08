package stan.initiative.modules.main;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import stan.initiative.modules.cudgel.CudgelPane;
import stan.initiative.contracts.MainContract;

public class MainScene
    extends Scene
    implements MainContract.View
{
    private CudgelPane cudgelPane;

    private MainContract.Presenter presenter;

    public MainScene(double width, double height)
    {
        super(new Pane(), width, height, Color.TRANSPARENT);
        getStylesheets().add("css/cudgel.css");
        presenter = new MainPresenter(this);
        initViews((Pane)getRoot());
        init();
    }
    private void initViews(Pane root)
    {
        cudgelPane = new CudgelPane();
        root.getChildren().add(cudgelPane);
        configRoot(root);
    }
    public void init()
    {
        cudgelPane.setLayoutX(getWidth()/2 - cudgelPane.getWidth()/2);
        cudgelPane.setLayoutY(getHeight()/2 - cudgelPane.getHeight()/2);
        System.out.println(getClass().getName() + " w " + getWidth() + " h " + getHeight());
    }

    private void configRoot(Pane root)
    {
        root.setStyle("-fx-background-color: null");
        //root.requestFocus();
        //root.requestLayout();
        root.layout();
    }

    @Override
    public void showCudgel()
    {
    }
}