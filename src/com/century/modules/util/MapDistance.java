package com.century.modules.util;

import java.util.HashMap;
import java.util.Map;

public class MapDistance {
	
	private static double EARTH_RADIUS = 6378.137; 
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
      
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为 米）
     * 参数为String类型
     * @param lat1 用户纬度
     * @param lng1 用户经度
     * @param lat2 商家纬度
     * @param lng2 商家经度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
          
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * (EARTH_RADIUS * 1000);
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
            substring(0, distanceStr.indexOf("."));
          
        return distanceStr;
    }
      
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map<String, String> getAround(String latStr, String lngStr, String raidus) {
        Map<String, String> map = new HashMap<String, String>();
          
        Double latitude = Double.parseDouble(latStr);// 传值给纬度
        Double longitude = Double.parseDouble(lngStr);// 传值给经度
  
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
          
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLng = longitude - radiusLng;
        // 获取最大经度
        Double maxLng = longitude + radiusLng;
          
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLat = latitude - radiusLat;
        // 获取最大纬度
        Double maxLat = latitude + radiusLat;
          
        map.put("minLat", minLat + "");
        map.put("maxLat", maxLat + "");
        map.put("minLng", minLng + "");
        map.put("maxLng", maxLng + "");
          
        return map;
    }
    
    
    /**
     * 传入距离单位（米），返回一定步长的近似距离值(0舍1入)
     * @author: huiyang.yu
     * @param lat1 用户纬度
     * @param lng1 用户经度
     * @param lat2 商家纬度
     * @param lng2 商家经度
     * @param meterStr 相隔距离步长
     * @return
     */
    public static String getAboutDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str,String meterStr){
    	Integer distance = Integer.parseInt(getDistance(lat1Str, lng1Str, lat2Str, lng2Str));
    	Integer meter = Integer.parseInt(meterStr);
    	String aboutDistance = "";
    	if (distance % meter == 0) {
    		aboutDistance = ((distance / meter) *  meter ) + "";
		}else {
			aboutDistance = ((distance / meter + 1) * (meter)) + "";
		}
    	return aboutDistance;
    }
      
    public static void main(String[] args) {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123
//        System.out.println(getDistance("36.68484","117.11811","36.66123","117.00999000000002"));
        
//        System.out.println(getDistance("24.625326","118.082672","24.622193","118.081213"));
    	System.out.println(getAboutDistance("24.625326","118.082672","24.622193","118.081213","50"));
        System.out.println(getAround("36.68484","117.11811", "13000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
          
    }
}
