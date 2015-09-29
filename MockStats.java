/*
 * Copyright (C) 2015 captainbowtie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.allenbarr.MockStats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author captainbowtie
 */
public class MockStats extends Application {

    private Database db;
    private final Stage stage = new Stage();
    private Scene scene;
    private VBox pane = new VBox();
    private MenuBar menuBar = new MenuBar();
    

    @Override
    public void start(Stage primaryStage) {
        setupMenuBar();
        pane.getChildren().addAll(menuBar, new StartupHBox());
        scene = new Scene(pane);
        scene.getStylesheets().add(MockStats.class.getResource("MockStatsCSS.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private class StartupHBox extends HBox {

        private final Button loadButton = new Button("Load Database");
        private final Button newButton = new Button("New Database");

        StartupHBox() {
            loadButton.setOnAction((e) -> {
                if (loadFile()) {
                    System.out.println("Load");
                    pane.getChildren().clear();
                    pane.getChildren().add(new DataEntryPane());
                }
            });
            newButton.setOnAction((e) -> {
                pane.getChildren().clear();
                pane.getChildren().add(new DataEntryPane());
            });
            this.getChildren().add(menuBar);
            this.getChildren().add(loadButton);
            this.getChildren().add(newButton);
            this.getStyleClass().add("hbox");
           
        }
    }

    private class DataEntryPane extends SplitPane {

        DataEntryPane() {
            
        }
    }
    private class programManagementPane extends SplitPane{
        private final SplitPane memberWitnessDisplay = new SplitPane();
        private final VBox memberDisplay = new VBox();
        private final HBox memberControls = new HBox();
        private final TextField memberName = new TextField("firstName LastName");
        private final Button addMember = new Button("Add Member");
        private final Button deleteMember = new Button("Delete Member");
        private final ListView memberList = new ListView();
        private final VBox witnessDisplay = new VBox();
        private final HBox witnessControls = new HBox();
        private final TextField witnessName = new TextField("lastName");
        private final Button addWitness = new Button("Add Witness");
        private final Button deleteWitness = new Button("Delete Witness");
        private final ListView witnessList = new ListView();
        programManagementPane(){
            this.setOrientation(Orientation.HORIZONTAL);
            memberWitnessDisplay.setOrientation(Orientation.VERTICAL);
            memberControls.getChildren().addAll(memberName,addMember,deleteMember);
            memberDisplay.getChildren().addAll(memberControls,memberList);
            witnessControls.getChildren().addAll(witnessName,addWitness,deleteWitness);
            witnessDisplay.getChildren().addAll(witnessControls,witnessList);
            
        }
    }
    private boolean loadFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select a MockStats Database");
        File dbFile = fc.showOpenDialog(stage);
        if (null != dbFile && dbFile.exists() && dbFile.isFile()) {
            try {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(dbFile))) {
                    db = (Database) input.readObject();
                    return true;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MockStats.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(MockStats.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    private void setupMenuBar(){
        Menu viewMenu = new Menu("View");
        MenuItem dataEntry = new MenuItem("Tournament Data Entry");
        MenuItem programManagement = new MenuItem("Program Management");
        Menu fileMenu = new Menu("File");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As...");
        MenuItem open = new MenuItem("Open...");
        viewMenu.getItems().add(dataEntry);
        viewMenu.getItems().add(programManagement);
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(viewMenu);
    }
}
