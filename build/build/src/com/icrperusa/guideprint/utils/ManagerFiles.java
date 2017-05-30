/**
 *
 */
package com.icrperusa.guideprint.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author christian
 *
 */
public class ManagerFiles {

    final Logger log = Logger.getLogger(getClass().getName());

    public Map<String, Object> readConfig(String enterprise) {
        Map<String, Object> _config = new HashMap<String, Object>();
        System.out.println("INSTANCE VARIABLE CONFIG BEFORE OF JSON PARSE");
        JSONParser parser = new JSONParser();
        try
        {
            String path = getClass().getResource("/").getPath().concat("settings.json");
            log.info("PATH FOR READER CONFIG ".concat(path));
            log.info("PATH OF RESOURCE: ".concat(path));
            BufferedReader reader =  new BufferedReader(new FileReader(path));
            String all = "", line = "";
            try {
                while((line = reader.readLine()) != null)
                    all = all.concat(line);
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                reader.close();
            }
            Object obj = parser.parse(all);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("OBJECT JSON " + jsonObject);
            JSONObject enterpriseObj = (JSONObject) jsonObject.get("enterprise");
            System.out.println("OBJECT ENTERPRISE " + enterprise);
            enterpriseObj = (JSONObject) enterpriseObj.get(enterprise.toString());
            System.out.println("BLOCK IFs");
            if (enterpriseObj.containsKey("port")){
                System.out.println("IN THE PORT");
                _config.put("port", enterpriseObj.get("port").toString());
            }
            if (enterpriseObj.containsKey("host")){
                System.out.println("IN THE HOST");
                _config.put("host", enterpriseObj.get("host").toString());
            }
            if (enterpriseObj.containsKey("db")){
                System.out.println("IN THE DB");
                _config.put("db", enterpriseObj.get("db").toString());
            }
            if (enterpriseObj.containsKey("passwd")){
                System.out.println("IN THE PWD");
                _config.put("passwd", enterpriseObj.get("passwd").toString());
            }
            if (enterpriseObj.containsKey("user")){
                System.out.println("IN THE USER");
                _config.put("user", enterpriseObj.get("user").toString());
            }
            System.out.println("READ CONFIG " + enterpriseObj);
            System.out.println("CONFIG " + _config);
        } catch (FileNotFoundException e) {
            Logger.getLogger(ManagerFiles.class.getName()).info(e.getMessage());
        } catch (IOException e) {
            Logger.getLogger(ManagerFiles.class.getName()).info(e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            Logger.getLogger(ManagerFiles.class.getName()).info(e.getMessage());
        }
        return _config;
    }

}
