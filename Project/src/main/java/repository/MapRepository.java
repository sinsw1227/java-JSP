package repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import service.CoordinateService;

public class MapRepository {
	String coordinate;
	private CoordinateService coordinateService;
	private String now;
	private String propertiesFile = "java/Project/MapRepository.properties";
	
	public MapRepository(){
//		try {
//			FileOutputStream output = new FileOutputStream("findme.txt");
//			System.out.println(System.getProperty("user.dir"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("MapRepositroy constructor() start");
		String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<LOCALDATA_104201>\n" +
				"    <list_total_count>6340</list_total_count>\n" +
				"    <RESULT>\n" +
				"        <CODE>INFO-000</CODE>\n" +
				"        <MESSAGE>정상 처리되었습니다</MESSAGE>\n" +
				"    </RESULT>\n" +
				"    <row>\n" +
				"        <OPNSFTEAMCODE>3040000</OPNSFTEAMCODE>\n" +
				"        <MGTNO>CDFH3301062023000004</MGTNO>\n" +
				"        <APVPERMYMD>2023-02-28</APVPERMYMD>\n" +
				"        <APVCANCELYMD/>\n" +
				"        <TRDSTATEGBN>01</TRDSTATEGBN>\n" +
				"        <TRDSTATENM>영업/정상</TRDSTATENM>\n" +
				"        <DTLSTATEGBN>13</DTLSTATEGBN>\n" +
				"        <DTLSTATENM>영업중</DTLSTATENM>\n" +
				"        <DCBYMD/>\n" +
				"        <CLGSTDT/>\n" +
				"        <CLGENDDT/>\n" +
				"        <ROPNYMD/>\n" +
				"        <SITETEL/>\n" +
				"        <SITEAREA/>\n" +
				"        <SITEPOSTNO/>\n" +
				"        <SITEWHLADDR>서울특별시 광진구 구의동 78-7 국제빌딩</SITEWHLADDR>\n" +
				"        <RDNWHLADDR>서울특별시 광진구 자양로 257, 국제빌딩 3층 (구의동)</RDNWHLADDR>\n" +
				"        <RDNPOSTNO>04992</RDNPOSTNO>\n" +
				"        <BPLCNM>발레전문체력 능동센터</BPLCNM>\n" +
				"        <LASTMODTS>2023-02-28 09:29:22</LASTMODTS>\n" +
				"        <UPDATEGBN>I</UPDATEGBN>\n" +
				"        <UPDATEDT>2022-12-03 00:03:00.0</UPDATEDT>\n" +
				"        <UPTAENM/>\n" +
				"        <X>207848.07465793</X>\n" +
				"        <Y>449668.698815134</Y>\n" +
				"        <CULPHYEDCOBNM/>\n" +
				"        <PUPRSENM/>\n" +
				"        <INSURJNYNCODE/>\n" +
				"        <LDERCNT/>\n" +
				"        <BDNGDNGNUM/>\n" +
				"        <BDNGYAREA/>\n" +
				"        <MEMCOLLTOTSTFNUM/>\n" +
				"        <DRMKCOBNM/>\n" +
				"        <BUPNM/>\n" +
				"    </row>\n" +
				"    <row>\n" +
				"        <OPNSFTEAMCODE>3150000</OPNSFTEAMCODE>\n" +
				"        <MGTNO>CDFH3301062021000014</MGTNO>\n" +
				"        <APVPERMYMD>2021-06-02</APVPERMYMD>\n" +
				"        <APVCANCELYMD/>\n" +
				"        <TRDSTATEGBN>01</TRDSTATEGBN>\n" +
				"        <TRDSTATENM>영업/정상</TRDSTATENM>\n" +
				"        <DTLSTATEGBN>13</DTLSTATEGBN>\n" +
				"        <DTLSTATENM>영업중</DTLSTATENM>\n" +
				"        <DCBYMD/>\n" +
				"        <CLGSTDT/>\n" +
				"        <CLGENDDT/>\n" +
				"        <ROPNYMD/>\n" +
				"        <SITETEL/>\n" +
				"        <SITEAREA/>\n" +
				"        <SITEPOSTNO/>\n" +
				"        <SITEWHLADDR>서울특별시 강서구 화곡동 343-62 라인씨티</SITEWHLADDR>\n" +
				"        <RDNWHLADDR>서울특별시 강서구 곰달래로 125, 401호 (화곡동, 라인씨티)</RDNWHLADDR>\n" +
				"        <RDNPOSTNO>07774</RDNPOSTNO>\n" +
				"        <BPLCNM>MY FIRST GYM</BPLCNM>\n" +
				"        <LASTMODTS>2023-02-28 09:13:49</LASTMODTS>\n" +
				"        <UPDATEGBN>U</UPDATEGBN>\n" +
				"        <UPDATEDT>2022-12-03 00:03:00.0</UPDATEDT>\n" +
				"        <UPTAENM/>\n" +
				"        <X>186386.861573478</X>\n" +
				"        <Y>447625.801061348</Y>\n" +
				"        <CULPHYEDCOBNM/>\n" +
				"        <PUPRSENM/>\n" +
				"        <INSURJNYNCODE/>\n" +
				"        <LDERCNT/>\n" +
				"        <BDNGDNGNUM/>\n" +
				"        <BDNGYAREA/>\n" +
				"        <MEMCOLLTOTSTFNUM/>\n" +
				"        <DRMKCOBNM/>\n" +
				"        <BUPNM/>\n" +
				"    </row>\n" +
				"    <row>\n" +
				"        <OPNSFTEAMCODE>3230000</OPNSFTEAMCODE>\n" +
				"        <MGTNO>CDFH3301062023000009</MGTNO>\n" +
				"        <APVPERMYMD>2023-03-02</APVPERMYMD>\n" +
				"        <APVCANCELYMD/>\n" +
				"        <TRDSTATEGBN>01</TRDSTATEGBN>\n" +
				"        <TRDSTATENM>영업/정상</TRDSTATENM>\n" +
				"        <DTLSTATEGBN>13</DTLSTATEGBN>\n" +
				"        <DTLSTATENM>영업중</DTLSTATENM>\n" +
				"        <DCBYMD/>\n" +
				"        <CLGSTDT/>\n" +
				"        <CLGENDDT/>\n" +
				"        <ROPNYMD/>\n" +
				"        <SITETEL/>\n" +
				"        <SITEAREA/>\n" +
				"        <SITEPOSTNO/>\n" +
				"        <SITEWHLADDR>서울특별시 송파구 가락동 166-8</SITEWHLADDR>\n" +
				"        <RDNWHLADDR>서울특별시 송파구 동남로 238, 오성빌딩 5층 (가락동)</RDNWHLADDR>\n" +
				"        <RDNPOSTNO>05769</RDNPOSTNO>\n" +
				"        <BPLCNM>조이필라위드짐</BPLCNM>\n" +
				"        <LASTMODTS>2023-03-02 09:24:08</LASTMODTS>\n" +
				"        <UPDATEGBN>I</UPDATEGBN>\n" +
				"        <UPDATEDT>2022-12-03 00:04:00.0</UPDATEDT>\n" +
				"        <UPTAENM/>\n" +
				"        <X>211741.980474587</X>\n" +
				"        <Y>443948.57600694</Y>\n" +
				"        <CULPHYEDCOBNM/>\n" +
				"        <PUPRSENM/>\n" +
				"        <INSURJNYNCODE/>\n" +
				"        <LDERCNT/>\n" +
				"        <BDNGDNGNUM/>\n" +
				"        <BDNGYAREA/>\n" +
				"        <MEMCOLLTOTSTFNUM/>\n" +
				"        <DRMKCOBNM/>\n" +
				"        <BUPNM/>\n" +
				"    </row>\n" +
				"</LOCALDATA_104201>";

		coordinateService = new CoordinateService();
		
		StringBuilder main = new StringBuilder();
		main.append("[");
		String next = "", name = "", x ="", y ="", site ="";
		BufferedReader bufferedReader;
		boolean status = false;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(getConnection()));
			//bufferedReader = new BufferedReader(new StringReader(xmlData));
			
			while((next = bufferedReader.readLine())!=null) {
				next = next.trim();
				if(next.equals("<body>")) {
					break;
				}
			}
			
			while((next = bufferedReader.readLine())!=null) {	
				//사업자명 찾기
				while((next = bufferedReader.readLine())!=null) {
					next = next.trim();
					if(next.startsWith("<bplcNm")) {
						if(next.contains("<bplcNm/>"))
							name = "사업자명 없음";
						else
							name = next.substring(8, next.length()-9);
						break;
					}
				}
				
				//주소 찾기
				while((next = bufferedReader.readLine())!=null) {
					next = next.trim();
					if(next.startsWith("<rdnWhlAddr")) {
						if(next.contains("<rdnWhlAddr/>"))
							site = "주소지 없음";
						else
							site = next.substring(12, next.length()-13);
						break;
					}
				}
				
				//영업 상태 찾기
//				while((next = bufferedReader.readLine())!=null) {
//					next = next.trim();
//					if(next.startsWith("<trdStateNm")) {
//						if(next.contains("영업/정상"))
//							status = true;
//						break;
//					}
//				}
//				//영업 확인
//				if(!status)
//					continue;
				
				//좌표 찾기
				while((next = bufferedReader.readLine())!=null) {
					next = next.trim();
					if(next.startsWith("<x")) {
						x = next.substring(3, next.length()-4);
						next = bufferedReader.readLine();
						next = next.trim();
						y = next.substring(3, next.length()-4);
						break;
					}
				}
				Map<String, String> re = coordinateService.change(y,x);
				// indexing 끝
				main.append(createString(name,site, re.get("x"), re.get("y")));
				status = false;
			}
			main.deleteCharAt(main.length() - 1);
			main.append("]");
			
			coordinate = main.toString();
			System.out.println(coordinate);
				
			//updateLastDate(now);
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		coordinateService = null;
	}
	
	public String get() {
		return coordinate;
	}
	
	private String createString(String name, String site, String x, String y) {
		return String.format("{\"name\":\"%s\", \"site\":\"%s\", \"x\":\"%s\", \"y\":\"%s\"},", name, site, x, y);
	}
	
	private InputStream getConnection() throws IOException{
		
//		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
//		urlBuilder.append("/" +  URLEncoder.encode("sample","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
//		urlBuilder.append("/" +  URLEncoder.encode("xml","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
//		urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
//		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
//		urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
//		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
//		
//		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
		
		StringBuilder urlBuilder = new StringBuilder("http://www.localdata.go.kr/platform/rest/TO0/openDataApi?authKey=Xp6Lix2f97p2BnQuPndyAV3OgIdEWbYAoxcct8vYerQ=&opnSvcId=10_42_01_P&state=01");
		//urlBuilder.append("&localCode=6110000");
		urlBuilder.append(String.format("&lastModTsBgn=%s&lastModTsEnd=%s",  getLastDate(), (now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))));
		//urlBuilder.append( String.format("&pageSize=30&pageIndex=1", ) );

		URL url = new URL(urlBuilder.toString());
		System.out.println("MapRepository url:"+url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			return conn.getInputStream();
		}else
			return null;
	}
	
	private String getLastDate() {
		Properties properties = new Properties();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(propertiesFile);
			properties.load(fileInputStream);
			fileInputStream.close();
			System.out.println("MapRepo getLatDate() => get Lastdate from properties success");
			return properties.getProperty("date");
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MapRepo getLatDate() =>get Lastdate from properties fail");
		return "20240925";
	}
	
	private void updateLastDate(String date) {
		Properties properties = new Properties();
		properties.setProperty("date", date);
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(propertiesFile);
			properties.store(fileOutputStream, "updated by java code");
			fileOutputStream.close();
			System.out.println("MapRepo getLatDate() => update Lastdate properties success");
		}catch (Exception e) {
			System.out.println("MapRepo getLatDate() => update Lastdate properties fail");
			e.printStackTrace();
		}
	}
}
