/**
 *
 */
package com.icrperusa.guideprint.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.icrperusa.guideprint.controller.SettingsMasterController;
import com.icrperusa.guideprint.model.Connect;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author christian
 *
 */
public class Reports extends SettingsMasterController {

    public Reports(String RUC){
        this.setEnterprise(RUC);
    }

    public JasperPrint getReportcn(String jasper, Map<String, Object> parameter) throws SQLException{
        JasperPrint vprint = null;
        Connection xcon = new Connect(this.getEnterprise()).Open();
        try {
            System.out.println("PATH COMPLETE JRXML " + jasper);
            if (new File(jasper).exists()){
                System.out.println("JRXML EXIST");
                JasperReport master = (JasperReport) JRLoader.loadObjectFromFile(jasper);
                System.out.println("Object process");
                vprint = JasperFillManager.fillReport(master, parameter, xcon);

            }
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            if (!xcon.isClosed())
                xcon.close();
        }
        return vprint;
    }

    public byte[] getReportEmptyData(String jrxml, Map<String, Object> param){
        byte[] bytes = null;
        try {
            JasperReport master = (JasperReport) JRLoader.loadObjectFromFile(jrxml);
            bytes = JasperRunManager.runReportToPdf(master, param,  new JREmptyDataSource());
        } catch (Exception e) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, e);
        }
        return bytes;
    }

}
