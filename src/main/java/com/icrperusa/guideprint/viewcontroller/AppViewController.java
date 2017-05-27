/**
 *
 */
package com.icrperusa.guideprint.viewcontroller;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author christian
 *
 */
public class AppViewController {


    @FXML
    private void handCloseApp(){
        Platform.exit();
    }

}
