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
        String xquery = "SELECT " +
                         "g.guia_id, " +
                         "ct.razonsocial," +
                         "ct.ruccliente_id," +
                         "g.puntollegada, " +
                         "g.dotoutput, " +
                         "g.traslado, " +
                         "EXTRAXT(YEAR FROM g.traslado::DATE) AS anio," +
                         "EXTRAXT(MONTH FROM g.traslado::DATE) AS mes, " +
                         "EXTRAXT(YEAR FROM g.traslado::DATE) AS dia, " +
                         "tr.tranom, " +
                         "tr.traruc_id, " +
                         "cd.condni_id, " +
                         "cd.connom, " +
                         "cd.conlic, " +
                         "cd.coninscription, " +
                         "tp.nropla_id, " +
                         "tp.marca, " +
                         "g.status, " + 
                         "g.observation, " +
                         "pr.proyecto_id, " +
                         "pr.nompro " +
                        "FROM almacen_guiaremision g " +
                        "INNER JOIN home_cliente ct " +
                        "ON ct.ruccliente_id = g.ruccliente_id " +
                        "INNER JOIN almacen_pedido pe " +
                        "ON pe.pedido_id = g.pedido_id " +
                        "INNER JOIN ventas_proyecto pr " +
                        "ON pr.proyecto_id = pe.proyecto_id " +
                        "INNER JOIN home_transportista tr " +
                        "ON g.traruc_id = tr.traruc_id " +
                        "INNER JOIN home_transporte tp " +
                        "ON tr.traruc_id = tp.traruc_id AND tp.nropla_id = g.nropla_id " +
                        "INNER JOIN home_conductore cd " +
                        "ON tr.traruc_id = cd.traruc_id AND cd.condni_id = g.condni_id "+
                        "WHERE g.guia_id = ? LIMIT 1 OFFSET 0;";
        ResultSet rs = new Connect(getEnterprise()).ExecuteQuery(xquery, new Object[]{ nguide });
        while (rs.next()) {
            object = new GuideRemission();
            object.setGuideid(rs.getString("guia_id"));
            object.setSuppliername(rs.getString("razonsocial"));
            object.setSupplierid(rs.getString("ruccliente"));
            object.setDotarrival(rs.getString("punollegada"));
            object.setDotout(rs.getString("dotoutput"));
            object.setTraslate(rs.getString("traslado"));
            object.setYear(rs.getString("anio"));
            object.setMonth(rs.getString("mes"));
            object.setDay(rs.getString("dia"));
            object.setTrasuppliername(rs.getString("tranom"));
            object.setTrasupplierid(rs.getString("trruc_id"));
            object.setLisence(rs.getString("conlic"));
            object.setPlate(rs.getString("nropla_id"));
            object.setBrand(rs.getString("marca"));
            object.setStatus(rs.getString("status"));
            object.setInscription(rs.getString("coninscription"));
            object.setObservation(rs.getString("observation"));
            object.setProjectid(rs.getString("proyecto_id"));
            object.setProjectname(rs.getString("nompro"));
        }
        
        /*
         * GET ALL ORDERS FROM GUIDE REMISSION
         * */
        xquery = "SELECT DISTINCT order_id FROM almacen_detguiaremision WHERE guia_id = ?";
        rs = new Connect(getEnterprise()).ExecuteQuery(xquery, new Object[] { nguide} );
        String orders = "";
        while (rs.next()){
            orders=orders.concat(rs.getString("order_id")).concat(" - ");
        }
        object.setOrders(orders);
        return object;
    }



}
