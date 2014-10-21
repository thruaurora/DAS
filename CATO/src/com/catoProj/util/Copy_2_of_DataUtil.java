package com.catoProj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Copy_2_of_DataUtil {
	private boolean hasnew=false;
	private int qtime=0;
	private int speedLocation=0;
	private int heightLocation=0;
	private int stationLocation=0;
	private int trainLenth=30;
	private int trainLoc=60;
	private static Copy_2_of_DataUtil instance;
	private HashMap speedData=null;
	private HashMap heightChartData=null;
	private HashMap stationChartData=null;
	private double[] AltX=null;
	private double[] AltY=null;
	private double[] AdvS=null;
	private double[] ActS=null;
	private double[] Locx=null;
	private double[] Locy=null;
	private double[] RActS=null;
	private int[] Color=null;
	

	private Copy_2_of_DataUtil() {
	}

	public static Copy_2_of_DataUtil getInstance() {
		if (instance == null)
			synchronized (Copy_2_of_DataUtil.class) {
				instance = new Copy_2_of_DataUtil();
				instance.getIniData();
			}
		return instance;
	}
	
	public void readSpeedData(){

		//read data from somewhere
		int Speed=120;
		int SpeedAdvice=123;
		String ClockArrive="13:04:00";
		double Distance=10.2;
		String NextStation="Kva";
		
			
		//example, build speedData
		speedData=new HashMap();
		speedData.put("textSpeed", String.valueOf(RActS[speedLocation]));
		speedData.put("textSpeedAdvice", String.valueOf(Math.round(AdvS[speedLocation])));
		speedData.put("textSpeeddif", String.valueOf(Math.round(RActS[speedLocation]-AdvS[speedLocation])));
		speedData.put("textClockArrive", ClockArrive);
		speedData.put("textDistance", String.valueOf(Distance));
		speedData.put("textNextStation", NextStation);

		System.out.println("RRRRRRRRRRR:"+String.valueOf(speedLocation)+"    TTTTTTTT:"+String.valueOf(RActS.length));
		if(speedLocation<RActS.length-1){
			//speedLocation=speedLocation+1;
			speedLocation=heightLocation;
			this.hasnew=true;
		}else{
			this.hasnew=false;
		}
	}
	
	public void readHeightData(){
		//example, build heightChartDara
		heightChartData=null;
		heightChartData=new HashMap();
		String[] titles = new String[] { "Altitude", "Location","Advice Speed","Advice Speed History","Actual Speed" ,"Current Location" };
		//Build X axis values
		List<double[]> xvalues = new ArrayList<double[]>();
		 //xvalues for Altitude
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
	    double[] altX=new double[1];
	    if(heightLocation<AltX.length-trainLoc){
	    	altX=new double[AltX.length-heightLocation];
	    	for(int i=0;i<altX.length;i++){
	    		altX[i]=AltX[i];
	    	}
	    }else{
	    	altX=new double[trainLoc];
	    	for(int i=0;i<altX.length;i++){
	    		altX[i]=AltX[i];
	    	}
	    }
	    xvalues.add(altX);
	    
	    //xvalues for Location
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    double[] locX=new double[trainLenth];
	    for(int i=0;i<locX.length;i++){
	    	locX[i]=AltX[trainLoc-trainLenth+i];
	    }
	    xvalues.add(locX);
	    
	    //xvalues for Advice Speed
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
	    double[] advsX=new double[1];
	    if(heightLocation<AdvS.length-trainLoc){
	    	advsX=new double[AdvS.length-heightLocation];
	    	for(int i=0;i<advsX.length;i++){
	    		advsX[i]=AltX[i];
	    	}
	    }else{
	    	advsX=new double[trainLoc];
	    	for(int i=0;i<advsX.length;i++){
	    		advsX[i]=AltX[i];
	    	}
	    }
	    xvalues.add(advsX);
	    
	    //xvalues for Advice Speed History
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    double[] advshX=new double[trainLoc];
	    for(int i=0;i<trainLoc;i++){
	    	advshX[i]=AltX[i];
	    }
	    xvalues.add(advshX);
	    	    
	    //xvalues for Actual Speed
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    double[] actsX=new double[trainLoc];
	    for(int i=0;i<trainLoc;i++){
	    	actsX[i]=AltX[i];
	    }
	    xvalues.add(actsX);
	    
	    //xvalues for Current Location
	    //xvalues.add(new double[] {26,26});
	    xvalues.add(new double[] {trainLoc-1,trainLoc-1});
	    
	    
	    //Build y axis values
	    List<double[]> yvalues = new ArrayList<double[]>();
	    //yvalues for Altitude
	    //yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27,28,29,30,31,32,33,34,35,36,37,38,39,39,38,38,37,37,40,40,40,41,42,42,42,43,43,43,43,43,43,43,44,44,45,45,46,46,47,47,48,48,49,49,50,50,48,48,48,49,49,49,50,50,50,50,51,51,51,52,52,52,53,53,53,54,54,55,55,55,56,56,56,56,57,57,58,58,58,59,59,60,60,61,61,62,63,64,65,65,65,66,67,68,68,69,69,69,70,70,70,70,70,70,68,68,68,69,69,69,69,69,67,67,67,67,66,65,64,63,62,61,60,59,58,57,56,55,53,51,50,48,46,42,40,38,36,36,34,34,34,34,35,35,34,34,32,32,32,32,32,32,32,32,32,32,32,32,32,30,30,30,30,30,30,30,30,30,30,30,30,30,30,32,32,32});
	    //yvalues.add(AltY);
	    double[] altY=new double[1];
	    if(heightLocation<AltY.length-trainLoc){
	    	altY=new double[AltY.length-heightLocation];
	    	for(int i=0;i<altY.length;i++){
	    		altY[i]=AltY[heightLocation+i];
	    	}
	    }else{
	    	altY=new double[trainLoc];
	    	for(int i=0;i<altY.length;i++){
	    		altY[i]=AltY[AltY.length-trainLoc+i];
	    	}
	    }
	    yvalues.add(altY);
	    
	    //yvalues for Location
	    //yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27});
	    double[] locY=new double[trainLenth];
	    if(heightLocation<Locy.length-trainLoc){
	    	for(int i=0;i<locY.length;i++){
		    	locY[i]=Locy[heightLocation+trainLoc-trainLenth+i];
		    }
	    }else{

	    	for(int i=0;i<locY.length;i++){
		    	locY[i]=Locy[AdvS.length-trainLenth-1+i];
		    }
	    }
	    yvalues.add(locY);
	    
	    //yvalues for Advice Speed
	    //yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114,113,113,113,113,113,113,113,113,113,113,113,113,113,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,111,111,110,110,110,110,109,109,108,108,108,107,107,106,106,105,105,104,104,103,103,103,102,102,102,101,101,100,100,100,100,100,99,99,99,99,99,99,100,100,101,101,101,101,101,102,102,102,102,103,103,104,104,104,105,105,105,105,105,104,104,103,103,102,102,101,101,100,100,99,97,95,93,91,89,87,85,83,80,77,73,70,67,64,60,56,50,46,43,40,37,34,30,27,24,20,18,16,14,12,10,8,6,4,2,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60});
	    double[] advsY=new double[1];
	    if(heightLocation<AdvS.length-trainLoc){
	    	advsY=new double[AdvS.length-heightLocation];
	    	for(int i=0;i<advsY.length;i++){
	    		advsY[i]=AdvS[heightLocation+i];
	    	}
	    }else{
	    	advsY=new double[trainLoc];
	    	for(int i=0;i<advsY.length;i++){
	    		advsY[i]=AdvS[AdvS.length-trainLoc+i];
	    	}
	    }
	    yvalues.add(advsY);
	    
	    //yvalues for Advice Speed History
	    //yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114});
	    double[] advshY=new double[trainLoc];
	    if(heightLocation<AdvS.length-trainLoc){
		   	for(int i=0;i<trainLoc;i++){
		   		advshY[i]=AdvS[heightLocation+i];
		    }
		}else{
		   	for(int i=0;i<trainLoc;i++){
		   		advshY[i]=AdvS[AdvS.length-trainLoc+i];
		    }
		}
	    yvalues.add(advshY);
	    
	    //yvalues for Actual Speed
	    //yvalues.add(new double[] {108,109,110,110,110,110,109,109,109,109,108,107,107,107,107,108,108,108,108,108,109,110,110,109,109,109});
	    double[] actsY=new double[trainLoc];
	    
	    if(heightLocation<RActS.length-trainLoc){
		   	for(int i=0;i<trainLoc;i++){
		    	actsY[i]=RActS[heightLocation+i];
		    }
		}else{
		   	for(int i=0;i<trainLoc;i++){
		    	actsY[i]=RActS[RActS.length-trainLoc+i];
		    }
		}
	    yvalues.add(actsY);
	    
	    
	    //yvalues for Current Location
	    //yvalues.add(new double[] {0,109});
	    yvalues.add(new double[] {0,220});
	    
	    
	    
	    heightChartData.put("titles", titles);
	    heightChartData.put("xvalues", xvalues);
	    heightChartData.put("yvalues", yvalues);
	    
	    
	    if(heightLocation<RActS.length-1){
	    	//heightLocation=heightLocation+1;
	    	heightLocation=Arrays.binarySearch(AltX, Locx[qtime]);
		}
	    qtime=qtime+1;
	}
	
	public void readStationData(){
		//example, build StationChartData
	    stationChartData=new HashMap();
	    //String[] titles = new String[] { "Rail U", "Rail N", "Pc1", "Pc4", "Kva3", "trainline1", "trainline2", "trainline3"};
	    String[] titles = new String[] { "Rail U", "Rail N", "Pc1", "Pc4", "Kva3", "trainline1", "trainline2", "trainline3"};
	    //Build X axis values
	    //List<double[]> xvalues = new ArrayList<double[]>();
	    List<double[]> xvalues = new ArrayList<double[]>();
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
	    List<double[]> yvalues = new ArrayList<double[]>();
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
	    if(stationLocation<RActS.length-1){
	    	stationLocation=stationLocation+1;
		}
	}
	
	public void getIniData(){
		String result = "";
		result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=AltX");
		//System.out.println(result);
		String[] altxdata=result.split(",");
		AltX=new double[altxdata.length];
		for(int i=0;i<altxdata.length;i++){
			AltX[i]=Double.valueOf(altxdata[i]).doubleValue();
		}
		result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=AltY");
		//System.out.println(result);
		String[] altydata=result.split(",");
		AltY=new double[altydata.length];
		for(int i=0;i<altydata.length;i++){
			AltY[i]=Double.valueOf(altydata[i]).doubleValue();
		}
		result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=AdvS");
		//System.out.println(result);
		String[] advsdata=result.split(",");
		AdvS=new double[advsdata.length];
		for(int i=0;i<advsdata.length;i++){
			AdvS[i]=Double.valueOf(advsdata[i]).doubleValue();
		}
		
		
		/*result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=ActS");
		//System.out.println(result);
		String[] actsdata=result.split(",");
		ActS=new double[actsdata.length];
		for(int i=0;i<actsdata.length;i++){
			ActS[i]=Double.valueOf(actsdata[i]).doubleValue();
		}
		
		Locx=new double[actsdata.length];
		for(int i=0;i<ActS.length;i++){
			Locx[i]=Math.round(ActS[i]/25);
		}
		
		RActS=new double[actsdata.length];
		for(int i=0;i<ActS.length;i++){
			if(i==0){
				RActS[i]=Math.round(3.6*ActS[i]);
			}else{
				RActS[i]=Math.round(3.6*(ActS[i]-ActS[i-1]));
			}
		}

		Locy=new double[actsdata.length];
		for(int i=0;i<ActS.length;i++){
			int loc=Arrays.binarySearch(AltX, Locx[i]);
			if(loc>-1){
				Locy[i]=AltY[loc];
			}else{
				Locy[i]=0;
			}
			
		}*/
		
		result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=COLOR");
		//System.out.println(result);
		String[] colordata=result.split(",");
		Color=new int[colordata.length];
		for(int i=0;i<colordata.length;i++){
			Color[i]=Integer.valueOf(colordata[i]).intValue();
		}
	}
	

	
	public void getLocData(){
		String result = "";
		result = HttpUtil.queryStringForPost("http://192.168.1.10:8080/CATOWeb/LoadMessage?mode=ActS");
		//System.out.println(result);
		String[] actsdata=result.split(",");
		ActS=new double[actsdata.length];
		for(int i=0;i<actsdata.length;i++){
			ActS[i]=Double.valueOf(actsdata[i]).doubleValue();
		}
		
		Locx=new double[actsdata.length];
		for(int i=0;i<ActS.length;i++){
			Locx[i]=Math.round(ActS[i]/25);
		}
		
		RActS=new double[actsdata.length];
		for(int i=0;i<ActS.length;i++){
			if(i==0){
				RActS[i]=Math.round(3.6*ActS[i]);
			}else{
				RActS[i]=Math.round(3.6*(ActS[i]-ActS[i-1]));
			}
		}

		Locy=new double[actsdata.length];
		for(int i=0;i<ActS.length;i++){
			int loc=Arrays.binarySearch(AltX, Locx[i]);
			if(loc>-1){
				Locy[i]=AltY[loc];
			}else{
				Locy[i]=0;
			}
			
		}

	}

	public boolean Hasnew() {
		return hasnew;
	}

	public HashMap getSpeedData() {
		return speedData;
	}

	public HashMap getHeightChartData() {
		return heightChartData;
	}

	public HashMap getStationChartData() {
		return stationChartData;
	}

	public int[] getColor() {
		return Color;
	}
}
