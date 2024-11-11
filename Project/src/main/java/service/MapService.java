package service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapService {
	private JSONService jsonService;
	
	public MapService() {
		jsonService = new JSONService();
	}
	
	public String get(String siteCode) {
		return jsonService.parseJson(getConnection(siteCode));
	}
	
	private InputStream getConnection(String siteCode) {
		
		try {
		URL url = new URL(String.format("http://openapi.seoul.go.kr:8088/674e4358466c6b6837304941454e54/json/LOCALDATA_104201_%s/1/1000", siteCode));
		System.out.println("MapRepository url:"+url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			return conn.getInputStream();
		}else
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
