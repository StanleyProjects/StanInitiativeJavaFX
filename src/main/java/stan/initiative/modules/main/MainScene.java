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
        this.presenter = new MainPresenter(this);
        initViews((Pane)this.getRoot());
        init();
    }
    private void initViews(Pane root)
    {
        this.cudgelPane = new CudgelPane();
        root.getChildren().add(cudgelPane);
        configRoot(root);
    }
    public void init()
    {
        this.cudgelPane.setLayoutX(this.getWidth()/2 - this.cudgelPane.getWidth()/2);
        this.cudgelPane.setLayoutY(this.getHeight()/2 - this.cudgelPane.getHeight()/2);
        System.out.println(this.getClass().getName() + " w " + this.getWidth() + " h " + this.getHeight());
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