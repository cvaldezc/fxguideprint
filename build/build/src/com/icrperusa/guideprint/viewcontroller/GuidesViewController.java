/**
 *
 */
package com.icrperusa.guideprint.viewcontroller;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.controlsfx.control.Notifications;

// import com.icrperusa.guideprint.MainApp;
import com.icrperusa.guideprint.controller.CompanyController;
import com.icrperusa.guideprint.controller.GuideRemissionController;
import com.icrperusa.guideprint.entity.Company;
import com.icrperusa.guideprint.entity.GuideRemission;
import com.icrperusa.guideprint.utils.Reports;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author christian
 *
 */
public class GuidesViewController {

    final Logger log = Logger.getLogger(getClass().getName());

    @SuppressWarnings("unchecked")
    @FXML
    private void initialize() {
        /*
         * Initialize ComboBox Company
         * */
        cbocompany.setConverter(new CompanyController().LabelCombo());
        cbocompany.setItems(new CompanyController().LoadCombo());
        cbocompany.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue obs, Object oldval, Object newval) {
                cboComboBox_changeSelected(obs, oldval, newval);
            }
        });
        // (obs, oldval, newval) -> {});
    }

    /*
     * ADD ALL EVENTS FROM CONTROLS
     * */

    @FXML
    private void handleEventSearchLatest (){
        System.out.println("This event is OK!");
        Company item = cbocompany.getSelectionModel().getSelectedItem();
        if (item != null) {
            String guide = "";
            guide = new GuideRemissionController(item.getCompanyid()).lastGudeRemission();
            numberguide.setText(guide);
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    numberguide.requestFocus();
                }
            });
        } else{
            Notifications.create().title("Alerta").text("Seleccione una empresa!").showWarning();
            log.info("combo no selected or empty");
        }
    }


    private void cboComboBox_changeSelected(ObservableValue<?> observable, Object oldValue, Object newValue) {
        if (newValue != null){
            Company _com = (Company)newValue;
            ivcompany.setImage(new Image(getClass().getResourceAsStream(String.format("/images/%s.png", _com.getCompanyid()))));
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    numberguide.requestFocus();
                }
            } );
        }
    }

    @FXML
    private void numberguideKeypress(KeyEvent e) throws SQLException{
        if (KeyCode.ENTER == e.getCode()) {
            System.out.println("YEAH if only ENTER");
            Company item = cbocompany.getSelectionModel().getSelectedItem();
            if (item != null) {
                GuideRemission getbedside = new GuideRemissionController(item.getCompanyid()).bedsideReport(numberguide.getText());
                if (getbedside != null)
                    try{
                        String[] months = new String[]{ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre",};
                        // get parameters
                        log.info("guide ".concat(getbedside.toString()));
                        String path = getClass().getResource("/").getPath();
                        Map<String, Object> parameter = new HashMap<String, Object>();
                        parameter.put("GUIDEID", numberguide.getText());
                        parameter.put("SOURCEPATH", path.concat("reports/"));
                        parameter.put("DOTSTART", "Jr San Martin Mz E Lt 6 Urb Los Huertos de Huachipa, Lurigancho Chosica, Lima");
                        parameter.put("DOTARRIVAL", getbedside.getDotarrival());
                        parameter.put("SUPPLIERNAME", getbedside.getSuppliername());
                        parameter.put("SUPPLIERID", getbedside.getSupplierid());
                        parameter.put("DAY", getbedside.getDay());
                        parameter.put("MONTH", months[Integer.parseInt(getbedside.getMonth()) - 1]);
                        parameter.put("YEAR", getbedside.getYear());
                        parameter.put("BRAND", getbedside.getBrand());
                        parameter.put("PLATE", getbedside.getPlate());
                        parameter.put("INSCRIPTION", getbedside.getInscription() == null?"":getbedside.getInscription());
                        parameter.put("LISENCE", getbedside.getLisence());
                        parameter.put("TRASUPPLIERNAME", getbedside.getTrasuppliername());
                        parameter.put("TRASUPPLIERID", getbedside.getTrasupplierid());
                        parameter.put("PROJECT", String.format("%s - %s", getbedside.getProjectid(), getbedside.getProjectname()));
                        log.info("Name file: ".concat(path));
                        parameter.put("ORDERS", new GuideRemissionController(item.getCompanyid()).getOrders(numberguide.getText()));
                        // parameter.forEach((k, v) -> System.out.println("Key: ".concat(k).concat(" Value: ").concat((String) v)));
                        //
                        JasperPrint dprint = new Reports(item.getCompanyid()).getReportcn(path.concat("reports/guideremision.jasper"), parameter);
                        JasperViewer view =  new JasperViewer(dprint, false);
                        //view.setAlwaysOnTop(true);
                        view.setVisible(true);
                        ImageIcon icon = new ImageIcon(String.format("%simages/%s.png", path, numberguide.getText()));
                        view.setIconImage( icon.getImage() );
                        view.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                        view.setTitle("GUIA DE REMINISION ".concat(numberguide.getText()));
                    }catch(Exception ex){
                        log.info("Error in view controller " + ex.getLocalizedMessage());
                    }
            }
        }
    }

    // define FX Controls
    // private MainApp mainapp;
    @FXML
    private TextField numberguide;

    @FXML
    private ComboBox<Company> cbocompany;

    @FXML
    private ImageView ivcompany;

}
