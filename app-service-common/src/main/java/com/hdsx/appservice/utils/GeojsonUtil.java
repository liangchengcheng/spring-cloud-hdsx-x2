package com.hdsx.appservice.utils;

import com.vividsolutions.jts.geom.Geometry;
import org.geotools.geojson.geom.GeometryJSON;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Author:       |梁铖城
 * Email:        |1038127753@qq.com
 * Date:         |2015年11月21日15:28:25
 * Description:  |GeojsonUtil
 */
public class GeojsonUtil {

    public static String geometryToJson(Geometry geometry){
        String json = null;
        try{
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(geometry,writer);
            json = writer.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public static Geometry jsonToGeometry(String geoJson){
        GeometryJSON gjson = new GeometryJSON();
        Reader reader = new StringReader(geoJson);
        try{
            Geometry geometry = gjson.read(reader);
            return geometry;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
