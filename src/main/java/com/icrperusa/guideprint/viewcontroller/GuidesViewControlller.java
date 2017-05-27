/**
 *
 */
package com.icrperusa.guideprint.viewcontroller;

import com.icrperusa.guideprint.MainApp;
import com.icrperusa.guideprint.controller.CompanyController;
import com.icrperusa.guideprint.entity.Company;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author christian
 *
 */
public class GuidesViewControlller {

    // define FX Controls
    private MainApp mainapp;
    @FXML
    private TextField numberguide;

    @FXML
    private ComboBox<Company> cbocompany;



    @FXML
    private void handleEventSearchLatest (){
        System.out.println("This event is OK!");
        cbocompany.getSelectionModel().selectFirst();
    }



    @FXML
    private void initialize() {
        /*
         * Initialize ComboBox Company
         * */
        cbocompany.setConverter(new CompanyController().LabelCombo());
        cbocompany.setItems(new CompanyController().LoadCombo());
        cbocompany.valueProperty().addListener((obs, oldval, newval) -> {cboComboBox_changeSelected(obs, oldval, newval);});
    }

    /*
     * ADD ALL EVENTS FROM CONTROLS
     * */

    private void cboComboBox_changeSelected(ObservableValue<?> observable, Object oldValue, Object newValue) {
        Company val = (Company) newValue;
        String selectionText = "Price of the " + val.getCompanyid() + " is : " + val.getCompanyname();
        System.out.println(selectionText);
    }
}
