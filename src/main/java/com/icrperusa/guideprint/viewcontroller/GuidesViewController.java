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
import com.icrperusa.guideprint.utils.Reports;

import javafx.application.Platform;
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

    @FXML
    private void initialize() {
        /*
         * Initialize ComboBox Company
         * */
        cbocompany.setConverter(new CompanyController().LabelCombo());
        cbocompany.setItems(new CompanyController().LoadCombo());
        cbocompany.valueProperty().addListener((obs, oldval, newval) -> {cboComboBox_changeSelected(obs, oldval, newval);});
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
            Platform.runLater(() -> numberguide.requestFocus());
        } else{
            Notifications.create().title("Alerta").text("Seleccione una empresa!").showWarning();
            log.info("combo no selected or empty");
        }
    }


    private void cboComboBox_changeSelected(ObservableValue<?> observable, Object oldValue, Object newValue) {
        if (newValue != null){
            Company _com = (Company)newValue;
            ivcompany.setImage(new Image(getClass().getResourceAsStream(String.format("/images/%s.png", _com.getCompanyid()))));
            Platform.runLater(() -> numberguide.requestFocus());
        }
    }

    @FXML
    private void numberguideKeypress(KeyEvent e) throws SQLException{
        if (KeyCode.ENTER == e.getCode()) {
            System.out.println("YEAH if only ENTER");
            Company item = cbocompany.getSelectionModel().getSelectedItem();
            if (item != null) {
                // get parameters
                String path = getClass().getResource("/").getPath();
                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter.put("GUIDEID", numberguide.getText());
                parameter.put("SOURCEPATH", path.concat("reports/"));
                // guidematerialsrpt
                log.info("Name file: ".concat(path));
                JasperPrint dprint = new Reports(item.getCompanyid()).getReportcn(path.concat("reports/guideremision.jasper"), parameter);
                JasperViewer view =  new JasperViewer(dprint, false);
                view.setAlwaysOnTop(true);
                view.setVisible(true);
                ImageIcon icon = new ImageIcon(String.format("%simages/%s.png", path, numberguide.getText()));
                view.setIconImage( icon.getImage() );
                view.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                view.setTitle("GUIA DE REMINISION ".concat(numberguide.getText()));
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
