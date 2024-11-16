package org.jewelhunt.ui;

import javafx.scene.layout.BorderPane;
import org.jewelhunt.controllers.Controller;

public class WindowApp extends BorderPane {
    private final ViewApp viewApp;
    private final BottomApp bottomApp;

    public WindowApp(Controller controller) {

        MenuApp menuApp = new MenuApp(controller);
        setTop(menuApp);

        viewApp = new ViewApp(controller);
        setCenter(viewApp);

        bottomApp = new BottomApp();
        setBottom(bottomApp);

    }

    public ViewApp getViewApp() {
        return viewApp;
    }

    public void setBottomText(String s) {
        bottomApp.setText(s);
    }
}
