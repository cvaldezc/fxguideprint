package com.icrperusa.guideprint;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icrperusa.guideprint.viewcontroller.GuidesViewControlller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    // define varible
    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Reporte - Guia Remisión (Materiales)");
        initRootLayout();
        showGuidePrint();
    }

    /*
     * Initializes the root layout
     * */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file
            String fxmlRoot = "/fxml/RootLayout.fxml";
            FXMLLoader loader = new FXMLLoader();
            rootLayout = (BorderPane) loader.load(getClass().getResourceAsStream(fxmlRoot));

            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            log.info("LAYOUT ROOT ERROR: ".concat(ex.getMessage()));
        }
    }
    /*
     * Show the Guide layout inside the root layout
     * */
    public void showGuidePrint() {
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlGuide = "/fxml/FMGuides.fxml";
            AnchorPane fmguides = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlGuide));
            // show in the parent form
            rootLayout.setCenter(fmguides);
            
            // add controller
            System.out.println("Loader controller");
            GuidesViewControlller controller = loader.getController();
            System.out.println("set controller in app");
            controller.setMainApp(this);
            
        } catch (IOException ex) {
            log.info("Error WHEN SHOW GUIDE PRINT: ".concat(ex.getMessage()));
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    //    public void start(Stage stage) throws Exception {
    //
    //        log.info("Starting Hello JavaFX and Maven demonstration application");
    //
    //        String fxmlFile = "/fxml/hello.fxml";
    //        log.debug("Loading FXML for main view from: {}", fxmlFile);
    //        FXMLLoader loader = new FXMLLoader();
    //        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
    //
    //        log.debug("Showing JFX scene");
    //        Scene scene = new Scene(rootNode, 400, 200);
    //        scene.getStylesheets().add("/styles/styles.css");
    //
    //        stage.setTitle("Hello JavaFX and Maven");
    //        stage.setScene(scene);
    //        stage.show();
    //    }
}
