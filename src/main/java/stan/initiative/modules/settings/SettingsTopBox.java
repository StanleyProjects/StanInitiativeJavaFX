package stan.initiative.modules.settings;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SettingsTopBox
    extends HBox
{
    private Button close = new Button();
    private Label label = new Label();

    private SettingsTopBoxBehaviour behaviour;

    public SettingsTopBox(SettingsTopBoxBehaviour b)
    {
        super();
        setStyle("-fx-background-color: red");
        setMinHeight(36);
        behaviour = b;
        initViews();
        Platform.runLater(()->
        {
            init();
        });
    }
    private void initViews()
    {
        close.setMinWidth(36);
        close.prefHeightProperty().bind(heightProperty());
        label.prefHeightProperty().bind(this.heightProperty());
        HBox.setHgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        this.getChildren().addAll(label, close);
    }
    private void init()
    {
        label.setText("settingstest");
        close.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                behaviour.close();
            }
        });
    }

    public interface SettingsTopBoxBehaviour
    {
        void close();
    }
}