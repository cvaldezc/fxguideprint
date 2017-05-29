/**
 *
 */
package com.icrperusa.guideprint.controller;

import com.icrperusa.guideprint.entity.GuideRemission;
import com.icrperusa.guideprint.model.Connect;

/**
 * @author Christian
 *
 */
public class GuideRemissionController extends GuideRemission {

    /*
     * Established company id by default
     */
    public GuideRemissionController(String enterprise) {
        setEnterprise(enterprise);
    }

    /*
     * Get last number of guide remissions register in db
     * */
    public String lastGudeRemission() {
        String _number = "";
        String xquery = "SELECT g.guia_id FROM almacen_guiaremision g ORDER BY g.registrado DESC LIMIT 1 OFFSET 0;";
        _number = (String) new Connect(getEnterprise()).ExecuteQuery(xquery, new Object[0], "guia_id");
        return _number;
    }



}
