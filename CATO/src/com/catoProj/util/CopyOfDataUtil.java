package com.catoProj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CopyOfDataUtil {
private boolean hasnew=false;
private HashMap speedData=null;
private HashMap heightChartData=null;
private HashMap stationChartData=null;

private double[] xvalue=null;
private double[] altitudevalue=null;
private double[] advs=null;


public boolean readdata(){
	//read data from somewhere
	int Speed=120;
	int SpeedAdvice=123;
	String ClockArrive="13:04:00";
	double Distance=10.2;
	String NextStation="Kva";
	
		
	//example, build speedData
	speedData=new HashMap();
	speedData.put("textSpeed", String.valueOf(Speed));
	speedData.put("textSpeedAdvice", String.valueOf(SpeedAdvice));
	speedData.put("textSpeeddif", String.valueOf(Speed-SpeedAdvice));
	speedData.put("textClockArrive", ClockArrive);
	speedData.put("textDistance", String.valueOf(Distance));
	speedData.put("textNextStation", NextStation);
	
	//example, build heightChartDara
	heightChartData=new HashMap();
	String[] titles = new String[] { "Altitude", "Location","Advice Speed","Advice Speed History","Actual Speed" ,"Current Location" };
	//Build X axis values
	List<double[]> xvalues = new ArrayList<double[]>();
	 //xvalues for Altitude
    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
    //xvalues for Location
    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
    //xvalues for Advice Speed
    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
    //xvalues for Advice Speed History
    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
    //xvalues for Actual Speed
    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
    //xvalues for Current Location
    xvalues.add(new double[] {26,26});
    
    //Build y axis values
    List<double[]> yvalues = new ArrayList<double[]>();
    //yvalues for Altitude
    yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27,28,29,30,31,32,33,34,35,36,37,38,39,39,38,38,37,37,40,40,40,41,42,42,42,43,43,43,43,43,43,43,44,44,45,45,46,46,47,47,48,48,49,49,50,50,48,48,48,49,49,49,50,50,50,50,51,51,51,52,52,52,53,53,53,54,54,55,55,55,56,56,56,56,57,57,58,58,58,59,59,60,60,61,61,62,63,64,65,65,65,66,67,68,68,69,69,69,70,70,70,70,70,70,68,68,68,69,69,69,69,69,67,67,67,67,66,65,64,63,62,61,60,59,58,57,56,55,53,51,50,48,46,42,40,38,36,36,34,34,34,34,35,35,34,34,32,32,32,32,32,32,32,32,32,32,32,32,32,30,30,30,30,30,30,30,30,30,30,30,30,30,30,32,32,32});
    //yvalues for Location
    yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27});
    //yvalues for Advice Speed
    yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114,113,113,113,113,113,113,113,113,113,113,113,113,113,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,111,111,110,110,110,110,109,109,108,108,108,107,107,106,106,105,105,104,104,103,103,103,102,102,102,101,101,100,100,100,100,100,99,99,99,99,99,99,100,100,101,101,101,101,101,102,102,102,102,103,103,104,104,104,105,105,105,105,105,104,104,103,103,102,102,101,101,100,100,99,97,95,93,91,89,87,85,83,80,77,73,70,67,64,60,56,50,46,43,40,37,34,30,27,24,20,18,16,14,12,10,8,6,4,2,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60});
    //yvalues for Advice Speed History
    yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114});
    //yvalues for Actual Speed
    //yvalues.add(new double[] {108,109,110,110,110,110,109,109,109,109,108,107,107,107,107,108,108,108,108,108,109,110,110,109,109,109});
    yvalues.add(new double[] {108,109,110,110,110,110,109,109,109,109,108,107,107,107,107,108,108,108,108,108,109,110,110,109,109,109});
    //yvalues for Current Location
    yvalues.add(new double[] {0,109});
    heightChartData.put("titles", titles);
    heightChartData.put("xvalues", xvalues);
    heightChartData.put("yvalues", yvalues);
    
    
	//example, build StationChartData
    stationChartData=new HashMap();
    //String[] titles = new String[] { "Rail U", "Rail N", "Pc1", "Pc4", "Kva3", "trainline1", "trainline2", "trainline3"};
    titles = new String[] { "Rail U", "Rail N", "Pc1", "Pc4", "Kva3", "trainline1", "trainline2", "trainline3"};
    //Build X axis values
    //List<double[]> xvalues = new ArrayList<double[]>();
    xvalues = new ArrayList<double[]>();
    //xvalues for Rail U
    xvalues.add(new double[] {0,200});
    //xvalues for Rail N
    xvalues.add(new double[] {0,200});
    //xvalues for Pc1
    xvalues.add(new double[] {100,120});
    //xvalues for Pc4
    xvalues.add(new double[] {100,120});
    //xvalues for Kva3
    xvalues.add(new double[] {150,170});
    //xvalues for trainline1
    xvalues.add(new double[] {50,150});
    //xvalues for trainline2
    xvalues.add(new double[] {150,170});
    //xvalues for trainline3
    xvalues.add(new double[] {170,200});

    
  //Build y axis Rail U
    //List<double[]> yvalues = new ArrayList<double[]>();
    yvalues = new ArrayList<double[]>();
    //yvalues for Altitude
    yvalues.add(new double[] {10,10});
    //yvalues for Rail N
    yvalues.add(new double[] {20,20});
    //yvalues for Pc1
    yvalues.add(new double[] {0,0});
    //yvalues for Pc4
    yvalues.add(new double[] {30,30});
    //yvalues for Kva3
    yvalues.add(new double[] {30,30});
    //yvalues for trainline1
    yvalues.add(new double[] {20,20});
    //yvalues for trainline2
    yvalues.add(new double[] {30,30});
    //yvalues for trainline3
    yvalues.add(new double[] {20,20});
    stationChartData.put("titles", titles);
    stationChartData.put("xvalues", xvalues);
    stationChartData.put("yvalues", yvalues);
    
	

    getIniData();
	
	this.hasnew=true;
	return this.hasnew;
	
}
public boolean hasnew(){
	return this.hasnew;
}
public HashMap getdata(){
	return this.speedData;
}
public HashMap getHeightdata(){
	return this.heightChartData;
}
public HashMap getStationdata(){
	return this.stationChartData;
}
public void clear(){
	this.speedData.clear();
	this.speedData=null;
	this.hasnew=false;
	this.heightChartData.clear();
	this.heightChartData=null;
	this.stationChartData.clear();
	this.stationChartData=null;
}

public void getIniData(){
	String result = "";
	result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=AltX");
	System.out.println(result);
	result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=AltY");
	System.out.println(result);
	result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=AdvS");
	System.out.println(result);
	result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=ActS");
	System.out.println(result);
	
	//example, build heightChartDara
		heightChartData=new HashMap();
		String[] titles = new String[] { "Altitude", "Location","Advice Speed","Advice Speed History","Actual Speed" ,"Current Location" };
		//Build X axis values
		List<double[]> xvalues = new ArrayList<double[]>();
		 //xvalues for Altitude
	    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
	    //xvalues for Location
	    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    //xvalues for Advice Speed
	    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
	    //xvalues for Advice Speed History
	    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    //xvalues for Actual Speed
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    //xvalues for Current Location
	    xvalues.add(new double[] {26,26});
	    
	    //Build y axis values
	    List<double[]> yvalues = new ArrayList<double[]>();
	    //yvalues for Altitude
	    yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27,28,29,30,31,32,33,34,35,36,37,38,39,39,38,38,37,37,40,40,40,41,42,42,42,43,43,43,43,43,43,43,44,44,45,45,46,46,47,47,48,48,49,49,50,50,48,48,48,49,49,49,50,50,50,50,51,51,51,52,52,52,53,53,53,54,54,55,55,55,56,56,56,56,57,57,58,58,58,59,59,60,60,61,61,62,63,64,65,65,65,66,67,68,68,69,69,69,70,70,70,70,70,70,68,68,68,69,69,69,69,69,67,67,67,67,66,65,64,63,62,61,60,59,58,57,56,55,53,51,50,48,46,42,40,38,36,36,34,34,34,34,35,35,34,34,32,32,32,32,32,32,32,32,32,32,32,32,32,30,30,30,30,30,30,30,30,30,30,30,30,30,30,32,32,32});
	    //yvalues for Location
	    yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27});
	    //yvalues for Advice Speed
	    yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114,113,113,113,113,113,113,113,113,113,113,113,113,113,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,111,111,110,110,110,110,109,109,108,108,108,107,107,106,106,105,105,104,104,103,103,103,102,102,102,101,101,100,100,100,100,100,99,99,99,99,99,99,100,100,101,101,101,101,101,102,102,102,102,103,103,104,104,104,105,105,105,105,105,104,104,103,103,102,102,101,101,100,100,99,97,95,93,91,89,87,85,83,80,77,73,70,67,64,60,56,50,46,43,40,37,34,30,27,24,20,18,16,14,12,10,8,6,4,2,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60});
	    //yvalues for Advice Speed History
	    yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114});
	    //yvalues for Actual Speed
	    //yvalues.add(new double[] {108,109,110,110,110,110,109,109,109,109,108,107,107,107,107,108,108,108,108,108,109,110,110,109,109,109});
	    yvalues.add(new double[] {108,109,110,110,110,110,109,109,109,109,108,107,107,107,107,108,108,108,108,108,109,110,110,109,109,109});
	    //yvalues for Current Location
	    yvalues.add(new double[] {0,109});
	    heightChartData.put("titles", titles);
	    heightChartData.put("xvalues", xvalues);
	    heightChartData.put("yvalues", yvalues);
}

}
