/**
 *
 */
package com.icrperusa.guideprint.controller;

import java.util.ArrayList;
import java.util.List;

import com.icrperusa.guideprint.Interfaces.LoadInitilize;
import com.icrperusa.guideprint.entity.Company;

/**
 * @author christian
 *
 */
public class CompanyController extends Company implements LoadInitilize {

    public CompanyController(){
        super("", "");
    }

    public CompanyController(String companyid, String companyname) {
        super(companyid, companyname);
    }

    public List<Company> load() {
        List<Company> _list =  new ArrayList<Company>();
        _list.add(new Company("20428776110", "ICR PERU"));
        _list.add(new Company("20555029277", "ICR INSTALACIONES"));
        return _list;
    }

}
