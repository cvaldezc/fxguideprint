/**
 *
 */
package com.icrperusa.guideprint.Interfaces;

import javafx.collections.ObservableList;
import javafx.util.StringConverter;

/**
 * @author christian
 *
 */
public interface LoadInitilize {

    /*
     * method for load initialize data combobox
     * */
    public ObservableList<?> LoadCombo();

    /*
     * method for parse object to string
     * */
    public StringConverter<?> LabelCombo();

}
