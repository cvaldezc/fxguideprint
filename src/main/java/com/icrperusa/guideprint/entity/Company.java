/**
 *
 */
package com.icrperusa.guideprint.entity;

/**
 * @author christian
 *
 */
public class Company {

    private String companyid;

    private String companyname;



    public Company(String companyid, String companyname) {
        this.companyid = companyid;
        this.companyname = companyname;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

}
