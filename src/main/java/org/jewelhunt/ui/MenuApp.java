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
        Menu menuGame = new Menu(controller.getMessage("MenuApp.Game"));
        menuGame.getItems().addAll(menuItemNewGame(), menuItemStatistics(), menuItemParameters(), menuItemExit());

        Menu menuAbout = new Menu(controller.getMessage("MenuApp.About"));
        menuAbout.getItems().addAll(menuItemAbout(), menuItemHelp());

        getMenus().addAll(menuGame, menuAbout);
    }

    private MenuItem menuItemNewGame() {
        MenuItem menuItemNewGame = new MenuItem(controller.getMessage("MenuApp.NewGame"));
        menuItemNewGame.setOnAction(e -> controller.newGame());
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        menuItemNewGame.setAccelerator(keyCombination);
        return menuItemNewGame;
    }

    private MenuItem menuItemStatistics() {
        return new MenuItem(controller.getMessage("MenuApp.Statistics"));
    }

    private MenuItem menuItemParameters() {
        MenuItem menuItemParameters = new MenuItem(controller.getMessage("MenuApp.Parameters"));
        menuItemParameters.setOnAction(e -> controller.showParameters());
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.F2);
        menuItemParameters.setAccelerator(keyCombination);
        return menuItemParameters;
    }

    private MenuItem menuItemExit() {
        MenuItem menuItemExit = new MenuItem(controller.getMessage("MenuApp.Exit"));
        menuItemExit.setOnAction(e -> controller.exit());
        return menuItemExit;
    }

    private MenuItem menuItemAbout() {
        MenuItem menuItemAbout = new MenuItem(controller.getMessage("MenuApp.ItemAbout"));
        menuItemAbout.setOnAction(e -> controller.showAbout());
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.F1);
        menuItemAbout.setAccelerator(keyCombination);
        return menuItemAbout;
    }

    private MenuItem menuItemHelp() {
        return new MenuItem(controller.getMessage("MenuApp.Help"));
    }
}
