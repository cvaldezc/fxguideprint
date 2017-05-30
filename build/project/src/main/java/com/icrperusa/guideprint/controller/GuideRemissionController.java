/**
 *
 */
package com.icrperusa.guideprint.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.util.Log;

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
        try {
            String xquery = "SELECT " +
                    "g.guia_id, " +
                    "ct.razonsocial," +
                    "ct.ruccliente_id," +
                    "g.puntollegada, " +
                    "g.dotoutput, " +
                    "g.traslado, " +
                    "EXTRACT(YEAR FROM g.traslado::DATE) AS anio," +
                    "EXTRACT(MONTH FROM g.traslado::DATE) AS mes, " +
                    "EXTRACT(DAY FROM g.traslado::DATE) AS dia, " +
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
                    " FROM almacen_guiaremision g " +
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
                object.setSupplierid(rs.getString("ruccliente_id"));
                object.setDotarrival(rs.getString("puntollegada"));
                object.setDotout(rs.getString("dotoutput"));
                object.setTraslate(rs.getString("traslado"));
                object.setYear(rs.getString("anio"));
                object.setMonth(rs.getString("mes"));
                object.setDay(rs.getString("dia"));
                object.setTrasuppliername(rs.getString("tranom"));
                object.setTrasupplierid(rs.getString("traruc_id"));
                object.setLisence(rs.getString("conlic"));
                object.setPlate(rs.getString("nropla_id"));
                object.setBrand(rs.getString("marca"));
                object.setStatus(rs.getString("status"));
                object.setInscription(rs.getString("coninscription"));
                object.setObservation(rs.getString("observation"));
                object.setProjectid(rs.getString("proyecto_id"));
                object.setProjectname(rs.getString("nompro"));
            }
            // object.setOrders(orders);
        } catch (Exception e) {
            Log.info("ERROR BEDSIDE ".concat(e.getMessage()));
        }
        return object;
    }

    public String getOrders(String nguide){
        String orders = "";
        try {
            /*
             * GET ALL ORDERS FROM GUIDE REMISSION
             * */
            String xquery = "SELECT DISTINCT order_id FROM almacen_detguiaremision WHERE guia_id = ?;";
            ResultSet ors = new Connect(getEnterprise()).ExecuteQuery(xquery, new Object[] { nguide });
            while (ors.next())
                orders = orders.concat(ors.getString("order_id")).concat(" - ");
        } catch (Exception e) {
            Log.info("ERROR IN ORDERS ".concat(e.getMessage()));
        }
        return orders;
    }



}
