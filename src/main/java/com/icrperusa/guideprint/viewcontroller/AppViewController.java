/**
 *
 */
package com.icrperusa.guideprint.viewcontroller;

import org.apache.log4j.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author christian
 *
 */
public class AppViewController {

    final Logger log = Logger.getLogger(AppViewController.class.getName());

    @FXML
    private void initialize() {
        // initialize images in menu bar
        ivclear.setImage(new Image(getClass().getResourceAsStream("/images/clean.png")));
        
        ivclose.setImage(new Image(getClass().getResourceAsStream("/images/close.png")));
        
        ivlast.setImage(new Image(getClass().getResourceAsStream("/images/scoreboard.png")));
        
        ivreport.setImage(new Image(getClass().getResourceAsStream("/images/report.png")));
    }

    @FXML
    private void handCloseApp() {
        Platform.exit();
    }
    /*
     * Initialize variable in view
     * */
    @FXML
    private ImageView ivclear;
    @FXML
    private ImageView ivclose;
    @FXML
    private ImageView ivlast;
    @FXML
    private ImageView ivreport;

}
