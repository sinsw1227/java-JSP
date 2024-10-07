package service;

import java.util.HashMap;
import java.util.Map;

//import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
//import org.geotools.api.referencing.operation.MathTransform;
//import org.geotools.geometry.jts.JTS;
//import org.geotools.referencing.CRS;
//import org.locationtech.jts.geom.Coordinate;
//import org.locationtech.jts.geom.GeometryFactory;
//import org.locationtech.jts.geom.Point;
//import org.locationtech.jts.geom.Geometry;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class CoordinateService {
//	public Map<String, String> change(String y, String x) {
//		Map<String, String> re = new HashMap<String, String>();
//		try {
//			System.setProperty("org.geotools.referencing.forceXY", "true");
//			GeometryFactory geometryFactory = new GeometryFactory();
//			CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:5186");
//			CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:4326");
//			Coordinate coordinate = new Coordinate(Double.parseDouble(x), Double.parseDouble(y));
//			Geometry point = geometryFactory.createPoint(coordinate);
//
//			MathTransform transform = CRS.findMathTransform(sourceCrs, targetCrs, true);
//			Point transFormedPoint = (Point) JTS.transform(point, transform);
//			
//			re.put("x", String.valueOf(transFormedPoint.getX()));
//			re.put("y", String.valueOf(transFormedPoint.getY()));
//			
//			System.out.println("CoordinateService transfrom coordinate success old:"+point+", new :"+transFormedPoint);
//		}catch(Exception e) {
//			System.out.println("CoordinateService transfrom fail");
//			e.printStackTrace();
//		}
//		return re;
//	}
	
	public Map<String, String> change(String x, String y) {
		Map<String, String> re = new HashMap();
		try {

			// CRS 객체 생성
			CRSFactory crsFactory = new CRSFactory();

			// WGS84 system 정의
			//5174 epsg2097
			String epsg5174 = "+proj=tmerc +lat_0=38 +lon_0=127.0028902777778 +k=1 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs";
	        CoordinateReferenceSystem crs5186 = crsFactory.createFromParameters("EPSG:5174", epsg5174);

	        // EPSG:3857 (Web Mercator)
	        //EPSG:4326
	        String epsg4326 = "+proj=longlat +datum=WGS84 +no_defs";
	        CoordinateReferenceSystem crs3857 = crsFactory.createFromParameters("EPSG:4326", epsg4326);

			// 변환할 좌표계 정보 생성
			ProjCoordinate p = new ProjCoordinate();
			p.y = Double.valueOf(x);
			p.x = Double.valueOf(y);

			// 변환된 좌표를 담을 객체 생성
			ProjCoordinate p2 = new ProjCoordinate();

			CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
			// 변환 시스템 지정. (원본 시스템, 변환 시스템)
			CoordinateTransform coordinateTransform = ctFactory.createTransform(crs5186, crs3857);
			// 좌표 변환
			coordinateTransform.transform(p, p2);

			// 변환된 좌표
			//double x = projCoordinate.x;
			//double y = projCoordinate.y;
			
			re.put("y", String.valueOf(p2.y));
			re.put("x", String.valueOf(p2.x));
			System.out.println("Coordinate Service change() ("+ p.y +","+ p.x +")->("+ String.valueOf(p2.y)+","+String.valueOf(p2.x)+")");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}