/**
 *
 */
package com.icrperusa.guideprint.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public GuideRemission bedsideReport(String nguide) throws SQLException{
        GuideRemission object = null;
        String xquery = "SELECT * FROM almacen_guiaremision g INNER JOIN home_transportista t ON  WHERE guia_id = ? LIMIT 1 OFFSET 1";
        ResultSet rs = new Connect(getEnterprise()).ExecuteQuery(xquery, new Object[]{ nguide });
        while (rs.next()) {
            object = new GuideRemission();
            object.setDotout(rs.getString(""));
        }
        return object;
    }



}
