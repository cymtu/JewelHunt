package org.jewelhunt.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.jewelhunt.controllers.Controller;

public class MenuApp extends MenuBar {

    private final Controller controller;
    public MenuApp(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init(){
        KeyCombination keyCombination;

        Menu menuGame = new Menu(controller.getMessage("MenuApp.Game"));

        MenuItem menuItemNewGame = new MenuItem(controller.getMessage("MenuApp.NewGame"));
        menuItemNewGame.setOnAction(e -> controller.newGame());
        keyCombination = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        menuItemNewGame.setAccelerator(keyCombination);

        MenuItem menuItemStatistics = new MenuItem(controller.getMessage("MenuApp.Statistics"));

        MenuItem menuItemParameters = new MenuItem(controller.getMessage("MenuApp.Parameters"));
        menuItemParameters.setOnAction(e -> controller.showParameters());
        keyCombination = new KeyCodeCombination(KeyCode.F2);
        menuItemParameters.setAccelerator(keyCombination);

        MenuItem menuItemExit = new MenuItem(controller.getMessage("MenuApp.Exit"));
        menuItemExit.setOnAction(e -> controller.exit());

        menuGame.getItems().addAll(menuItemNewGame, menuItemStatistics, menuItemParameters, menuItemExit);

        Menu menuAbout = new Menu(controller.getMessage("MenuApp.About"));

        MenuItem menuItemAbout = new MenuItem(controller.getMessage("MenuApp.ItemAbout"));
        menuItemAbout.setOnAction(e -> controller.showAbout());
        keyCombination = new KeyCodeCombination(KeyCode.F1);
        menuItemAbout.setAccelerator(keyCombination);

        MenuItem menuItemHelp = new MenuItem(controller.getMessage("MenuApp.Help"));

        menuAbout.getItems().addAll(menuItemAbout, menuItemHelp);

        getMenus().addAll(menuGame, menuAbout);
    }
}
