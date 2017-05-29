/**
 *
 */
package com.icrperusa.guideprint.controller;

import com.icrperusa.guideprint.Interfaces.MasterSettings;

/**
 * @author Christian
 *
 */
public class SettingsMasterController implements MasterSettings {

    private String enterprise;

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

}
