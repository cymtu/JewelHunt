package org.jewelhunt.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomApp extends HBox {

    private Label label;

    public BottomApp() {
        init();
    }

    private void init(){
        label = new Label();
        getChildren().add(label);
    }

    public void setText(String s) {
        label.setText(s);
    }
}
