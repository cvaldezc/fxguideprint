/**
 *
 */
package com.icrperusa.guideprint.viewcontroller;

import com.icrperusa.guideprint.MainApp;
import com.icrperusa.guideprint.controller.CompanyController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author christian
 *
 */
public class GuidesViewControlller {

    // define FX Controls
    @FXML
    private TextField numberguide;

    @FXML
    private ComboBox<CompanyController> company = new ComboBox<CompanyController>();

    private MainApp mainApp;

    private void initialize() {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        company.getItems().addAll(new CompanyController().load());
        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }

}
