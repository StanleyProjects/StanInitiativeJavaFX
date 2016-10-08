package stan.initiative.modules.cudgel;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.util.Duration;

public class RecognitionLabel
    extends Label
{
    static private final int DURATION = 1000;

    public RecognitionLabel()
    {
        super();
        init();
    }
    private void init()
    {
        setId("recognition_label");
    }

    public void addText(String t)
    {
        super.setText(t);
        FadeTransition ft = new FadeTransition(Duration.millis(DURATION), this);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setAutoReverse(true);
        Path path = new Path();
        path.getElements().add(new MoveTo(0,0));
        path.getElements().add(new CubicCurveTo(36, 0, 128, 36, 256, 72));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(DURATION));
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(true);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                ft,
                pathTransition
        );
        parallelTransition.setCycleCount(0);
        parallelTransition.play();
    }
}