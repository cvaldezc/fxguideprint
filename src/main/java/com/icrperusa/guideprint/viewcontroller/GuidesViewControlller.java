/**
 *
 */
package com.icrperusa.guideprint.viewcontroller;

import com.icrperusa.guideprint.MainApp;
import com.icrperusa.guideprint.controller.CompanyController;
import com.icrperusa.guideprint.entity.Company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * @author christian
 *
 */
public class GuidesViewControlller {

    // define FX Controls
    @FXML
    private TextField numberguide;

    @FXML
    private ComboBox<Company> company; // = new ComboBox<CompanyController>();
    
    @FXML
    private void handleEventSearchLatest (){
        System.out.println("This event is OK!");
    }

    private MainApp mainApp;

    @FXML
    private void initialize() {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    private final ObservableList<Company> other =
            FXCollections.observableArrayList(
                    new Company("Azamat", "2200.15"),
                    new Company("Veli", "1400.0"),
                    new Company("Nurbek", "900.5"));
    public void setMainApp(MainApp mainapp) {
        System.out.println("here its set items cbo");
        // ObservableList<List<Company>> data = FXCollections.observableList(new
        // CompanyController().load());
        company.setConverter(new StringConverter<Company>() {
            @Override
            public String toString(Company object) {
                return object.getCompanyname();
            }

            @Override
            public Company fromString(String string) {
                return null;
            }
        });

        company.setItems(FXCollections.observableArrayList(new Company("Azamat", "2200.15"),
                new Company("Veli", "1400.0"),
                new Company("Nurbek", "900.5")));

        company.valueProperty().addListener((obs, oldVal, newVal) -> {
            String selectionText = "Price of the " + newVal.getCompanyid() + " is : " + newVal.getCompanyname();

            System.out.println(selectionText);
            //textNamePrice.setText(selectionText);
        });
        mainApp = mainapp;
        company = new ComboBox<Company>(other);
        System.out.println("finish set items cbo");
//        company.setItems();
        // company.getItems().addAll(new CompanyController().load());
        // Add observable list data to the table
        // personTable.setItems(mainApp.getPersonData());
    }

}
