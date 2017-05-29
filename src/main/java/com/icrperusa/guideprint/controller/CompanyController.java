/**
 *
 */
package com.icrperusa.guideprint.controller;

import com.icrperusa.guideprint.Interfaces.LoadInitilize;
import com.icrperusa.guideprint.entity.Company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

/**
 * @author christian
 *
 */
public class CompanyController extends Company implements LoadInitilize {

    public CompanyController(){
        super();
    }

    public CompanyController(String companyid, String companyname) {
        super(companyid, companyname);
    }

    /*
     * this method perform load data for control ComboBox
     * */
    @Override
    public ObservableList<Company> LoadCombo() {
        ObservableList<Company> _list = FXCollections.observableArrayList();
        _list.add(new Company("20428776110", "ICR PERU"));
        _list.add(new Company("20555029277", "ICR INSTALACIONES"));
        return _list;
    }

    /*
     * this method perform parse object to string for control ComboBox
     * */
    @Override
    public StringConverter<Company> LabelCombo (){
        return new StringConverter<Company>() {
            @Override
            public String toString(Company object){
                return object.getCompanyname();
            }

            @Override
            public Company fromString(String string) {
                return null;
            }
        };
    }


}
