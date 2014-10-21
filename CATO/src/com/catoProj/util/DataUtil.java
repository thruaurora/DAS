package com.catoProj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.achartengine.chart.PointStyle;

import android.graphics.Color;

public class DataUtil {
	private boolean hasnew=false;
	private int qtime=0; //query
	private int jump=0;
	private int jumpadd=0;
	private int trainLenth=20; //length of the train
	private int startLoc=60; //start location of the train
	private static DataUtil instance;
	private HashMap speedData=null;
	private HashMap heightChartData=null;
	private HashMap stationChartData=null;
	private double[] AltX=null;
	private double[] AltY=null;
	private double[] AdvS=null;
	private double[] LmtS=null;
	private double[] ActS=null;//real Location of the train
	private double[] Locx=null;
	private double[] RActS=null;//real speed of the train
	private int[] Color=null;
	private Station[] Station=null;
	private int[] StationColor=null;
	private PointStyle[] StationStyle=null;
	private int linenumber=2;
	private String hilight="";
	private long starttime= 0;
	private boolean started=false;
	private List zerostaList=null;
	

	private DataUtil() {
	}

	public static DataUtil getInstance() {
		if (instance == null)
			synchronized (DataUtil.class) {
				instance = new DataUtil();
				instance.getIniData();
				instance.getStaData();
				Date dt= new Date();
				instance.setStarttime(dt.getTime());
				
			}
		return instance;
	}
	
	public void readSpeedData(){

		//read data from somewhere
		int Speed=120;
		int SpeedAdvice=123;
		String ClockArrive="00:00:00";
		double Distance=00;
		String NextStation="--";
		
			
		//example, build speedData
		speedData=new HashMap();
		speedData.put("textSpeedAdvice", String.valueOf(Math.round(AdvS[jump+startLoc])));
		speedData.put("textSpeed", String.valueOf(RActS[jump+startLoc]));
		speedData.put("textSpeeddif", String.valueOf(Math.round(RActS[jump+startLoc]-AdvS[jump+startLoc])));
		speedData.put("imageSpeedUp",Math.round(RActS[jump+startLoc]-AdvS[jump+startLoc]));
		speedData.put("textSpeeddif", String.valueOf(Math.round(RActS[jump+startLoc]-AdvS[jump+startLoc])));
		Station speedst=null;
		for(int i=0;i<Station.length;i++){
			if(Station[i].getsName().equalsIgnoreCase("_R")) {
				continue;
			}else{
				if((int)Station[i].getsX()-startLoc>jump){
					speedst=Station[i];
					break;
				}
			}
		}
		
		if(speedst!=null){
			NextStation=speedst.getsName();
			Distance=Math.round(25*(speedst.getsX()-jump)/100)/10;
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("HH:mm:ss");
			ClockArrive=format1.format(this.starttime+speedst.getArriveTime());
		}
		
		
		speedData.put("textClockArrive", ClockArrive);
		speedData.put("textDistance", String.valueOf(Distance));
		speedData.put("textNextStation", NextStation);

		System.out.println("RRRRRRRRRRR:"+String.valueOf(jump)+"    TTTTTTTT:"+String.valueOf(RActS.length));		
		if(jump<RActS.length-1){
			this.hasnew=true;
		}else{
			this.hasnew=false;
		}
	}
	
	public void readHeightData(){
		//example, build heightChartDara
		
		
		heightChartData=null;
		heightChartData=new HashMap();
		String[] titles = new String[] { "Altitude", "Location", "Advice Speed", "Advice Speed History", "Actual Speed", "Current Location", "Limit Speed"};
		//Define X/Y axis
		List<double[]> xvalues = new ArrayList<double[]>();
		List<double[]> yvalues = new ArrayList<double[]>();
		
		//axis for Altitude
		//xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
	    double[] altX=new double[1];
	    if(jump<AltX.length-startLoc){
	    	altX=new double[AltX.length-jump];
	    	for(int i=0;i<altX.length;i++){
	    		altX[i]=AltX[i];
	    	}
	    }else{
	    	altX=new double[startLoc];
	    	for(int i=0;i<altX.length;i++){
	    		altX[i]=AltX[i];
	    	}
	    }
	    xvalues.add(altX);
	    
	  //yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27,28,29,30,31,32,33,34,35,36,37,38,39,39,38,38,37,37,40,40,40,41,42,42,42,43,43,43,43,43,43,43,44,44,45,45,46,46,47,47,48,48,49,49,50,50,48,48,48,49,49,49,50,50,50,50,51,51,51,52,52,52,53,53,53,54,54,55,55,55,56,56,56,56,57,57,58,58,58,59,59,60,60,61,61,62,63,64,65,65,65,66,67,68,68,69,69,69,70,70,70,70,70,70,68,68,68,69,69,69,69,69,67,67,67,67,66,65,64,63,62,61,60,59,58,57,56,55,53,51,50,48,46,42,40,38,36,36,34,34,34,34,35,35,34,34,32,32,32,32,32,32,32,32,32,32,32,32,32,30,30,30,30,30,30,30,30,30,30,30,30,30,30,32,32,32});
	    double[] altY=new double[1];
	    if(jump<AltY.length-startLoc){
	    	altY=new double[AltY.length-jump];
	    	for(int i=0;i<altY.length;i++){
	    		altY[i]=AltY[jump+i];
	    	}
	    }else{
	    	altY=new double[startLoc];
	    	for(int i=0;i<altY.length;i++){
	    		altY[i]=AltY[AltY.length-startLoc+i];
	    	}
	    }
	    yvalues.add(altY);
	    
	  //axis for Location
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    double[] locX=new double[trainLenth];
	    for(int i=0;i<locX.length;i++){
	    	locX[i]=AltX[startLoc-trainLenth+i];
	    }
	    xvalues.add(locX);
	  //yvalues.add(new double[] {20,21,22,22,22,25,26,27,28,29,30,30,30,30,30,30,30,29,28,27,26,25,25,25,25,27});
	    double[] locY=new double[trainLenth];	    
	    if(jump<AltY.length-startLoc){
	    	for(int i=0;i<locY.length;i++){
		    	locY[i]=AltY[jump+startLoc-trainLenth+i];
		    }
	    }else{
	    	for(int i=0;i<locY.length;i++){
		    	locY[i]=AltY[AltY.length-trainLenth-1+i];
		    }
	    }
	    yvalues.add(locY);
	  
	    
	  //axis for Advice Speed
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200});
	    double[] advsX=new double[1];
	    if(jump<AdvS.length-startLoc){
	    	advsX=new double[AdvS.length-jump];
	    	for(int i=0;i<advsX.length;i++){
	    		advsX[i]=AltX[i];
	    	}
	    }else{
	    	advsX=new double[startLoc];
	    	for(int i=0;i<advsX.length;i++){
	    		advsX[i]=AltX[i];
	    	}
	    }
	    xvalues.add(advsX);
	  //yvalues for Advice Speed
	    //yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114,113,113,113,113,113,113,113,113,113,113,113,113,113,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,111,111,110,110,110,110,109,109,108,108,108,107,107,106,106,105,105,104,104,103,103,103,102,102,102,101,101,100,100,100,100,100,99,99,99,99,99,99,100,100,101,101,101,101,101,102,102,102,102,103,103,104,104,104,105,105,105,105,105,104,104,103,103,102,102,101,101,100,100,99,97,95,93,91,89,87,85,83,80,77,73,70,67,64,60,56,50,46,43,40,37,34,30,27,24,20,18,16,14,12,10,8,6,4,2,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60});
	    double[] advsY=new double[1];
	    if(jump<AdvS.length-startLoc){
	    	advsY=new double[AdvS.length-jump];
	    	for(int i=0;i<advsY.length;i++){
	    		advsY[i]=AdvS[jump+i];
	    	}
	    }else{
	    	advsY=new double[startLoc];
	    	for(int i=0;i<advsY.length;i++){
	    		advsY[i]=AdvS[AdvS.length-startLoc+i];
	    	}
	    }
	    yvalues.add(advsY);
	    
	  //axis for Advice Speed History
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    double[] advshX=new double[startLoc];
	    for(int i=0;i<startLoc;i++){
	    	advshX[i]=AltX[i];
	    }
	    xvalues.add(advshX);
	  //yvalues.add(new double[] {110,111,112,113,113,113,114,113,113,113,112,113,114,113,113,113,113,113,113,113,113,113,113,113,113,114});
	    double[] advshY=new double[startLoc];
	    if(jump<AdvS.length-startLoc){
		   	for(int i=0;i<startLoc;i++){
		   		advshY[i]=AdvS[jump+i];
		    }
		}else{
		   	for(int i=0;i<startLoc;i++){
		   		advshY[i]=AdvS[AdvS.length-startLoc+i];
		    }
		}
	    yvalues.add(advshY);
	    
	  //axis for Actual Speed
	    //xvalues.add(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
	    double[] actsX=new double[startLoc];
	    for(int i=0;i<startLoc;i++){
	    	actsX[i]=AltX[i];
	    }
	    xvalues.add(actsX);
	  //yvalues.add(new double[] {108,109,110,110,110,110,109,109,109,109,108,107,107,107,107,108,108,108,108,108,109,110,110,109,109,109});
	    double[] actsY=new double[startLoc];
	    
	    if(jump<RActS.length-startLoc){
		   	for(int i=0;i<startLoc;i++){
		    	actsY[i]=RActS[jump+i];
		    }
		}else{
		   	for(int i=0;i<startLoc;i++){
		    	actsY[i]=RActS[RActS.length-startLoc+i];
		    }
		}
	    yvalues.add(actsY);
	    
	  //axis for Current Location
	    xvalues.add(new double[] {startLoc-1,startLoc-1});
	  //yvalues for Current Location
	    yvalues.add(new double[] {0,220});
	    
	  //axis for Limit Speed
	    //x
	    double[] lmtX=new double[1];
	    if(jump<LmtS.length-startLoc){
	    	lmtX=new double[LmtS.length-jump];
	    	for(int i=0;i<lmtX.length;i++){
	    		lmtX[i]=AltX[i];
	    	}
	    }else{
	    	lmtX=new double[startLoc];
	    	for(int i=0;i<lmtX.length;i++){
	    		lmtX[i]=AltX[i];
	    	}
	    }
	    xvalues.add(lmtX);
	    
	    //y
	    double[] lmtY=new double[1];
	    if(jump<LmtS.length-startLoc){
	    	lmtY=new double[LmtS.length-jump];
	    	for(int i=0;i<lmtY.length;i++){
	    		lmtY[i]=LmtS[jump+i];
	    	}
	    }else{
	    	lmtY=new double[startLoc];
	    	for(int i=0;i<lmtY.length;i++){
	    		lmtY[i]=LmtS[LmtS.length-startLoc+i];
	    	}
	    }
	    yvalues.add(lmtY);
	    
	    
	    heightChartData.put("titles", titles);
	    heightChartData.put("xvalues", xvalues);
	    heightChartData.put("yvalues", yvalues);

	    qtime=qtime+1;
	    if(qtime<Locx.length-1){
	    	jumpadd=(int)(Locx[qtime]-Locx[qtime-1]);
	    }else{
	    	jumpadd=0;
	    }
    	jump=jump+jumpadd;
	    
	}
	
	public void cacStationCS(){
		linenumber=2;
		for(int i=0;i<this.Station.length;i++){
			Station cstation=Station[i];
			if(cstation.getsName().trim().equalsIgnoreCase("_R")){
				if((int)cstation.getsX()-startLoc>jump){
					linenumber+=1;
				}
			}else{
				if((int)cstation.getsX()+trainLenth-startLoc>jump){
					linenumber+=cstation.getRailNumber();
				}
			}
		}
	}
	
	public void readStationData(){
		hilight="";
		//example, build StationChartData
	    stationChartData=new HashMap();

	    List<double[]> xvalues = new ArrayList<double[]>();
	    List<double[]> yvalues = new ArrayList<double[]>();
		
		this.cacStationCS();
		
		System.out.println("linenumber="+String.valueOf(linenumber));
		
		StationColor=new int[linenumber];//{ Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE};
		StationStyle=new PointStyle[linenumber];// { PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT};
		String[] titles = new String[linenumber]; // { "Rail U", "Rail N", "Pc1", "Pc4", "Kva3", "trainline1", "trainline2", "trainline3"};
		
		for(int i=0;i<StationStyle.length;i++){
			StationStyle[i]=PointStyle.POINT;
		}
		
		titles[0]="Rail N";
		StationColor[0]=android.graphics.Color.BLACK;
		xvalues.add(new double[] {0,1200});
		yvalues.add(new double[] {15,15});
		

		titles[1]="Rail U";
		StationColor[1]=android.graphics.Color.BLACK;
		xvalues.add(new double[] {0,1200});
		yvalues.add(new double[] {30,30});
		
		int curline=1;
		
		for(int i=0;i<Station.length;i++){
			Station cstation=Station[i];
			if(cstation.getsName().trim().equalsIgnoreCase("_R")){
				if((int)cstation.getsX()-startLoc>jump){
					curline+=1;					
					titles[curline]="Rail"+String.valueOf(curline);
					StationColor[curline]=android.graphics.Color.BLUE;
					hilight=hilight+"["+String.valueOf(curline)+"],";
					if(i==0){
						xvalues.add(new double[] {startLoc,cstation.getsX()-jump});
					}else{
						Station tstation=Station[i-1];
						if(tstation.getsX()+trainLenth-jump>startLoc){
							xvalues.add(new double[] {tstation.getsX()+trainLenth-jump,cstation.getsX()-jump});
						}else{
							xvalues.add(new double[] {startLoc,cstation.getsX()-jump});
						}
					}
					if(cstation.getTrailNumber()==1){
						yvalues.add(new double[] {15,15});
					}else{
						yvalues.add(new double[] {30,30});
					}
				}
			}else{
				if((int)cstation.getsX()+trainLenth-startLoc>jump){
					for(int j=0;j<cstation.getRailNumber();j++){
						curline+=1;					
						titles[curline]=cstation.getsName()+String.valueOf(curline);
						if(j+1==cstation.getTrailNumber()){
							StationColor[curline]=android.graphics.Color.BLUE;
							hilight=hilight+"["+String.valueOf(curline)+"],";
						}else{
							StationColor[curline]=android.graphics.Color.BLACK;
						}
						if(cstation.getsX()-jump>startLoc){
							xvalues.add(new double[] {cstation.getsX()-jump,cstation.getsX()+trainLenth-jump});
						}else{
							xvalues.add(new double[] {startLoc,cstation.getsX()+trainLenth-jump});
						}
						if(cstation.getRailNumber()==3){
							yvalues.add(new double[] {(j+1)*15,(j+1)*15});
						}else{
							yvalues.add(new double[] {j*15,j*15});
						}
						
					}
				}
			}
		}
		
	    stationChartData.put("titles", titles);
	    stationChartData.put("xvalues", xvalues);
	    stationChartData.put("yvalues", yvalues);
	    started=true;
	    System.out.println("hilight="+hilight);
	}
	
	public void getIniData(){
		String result = "";
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=AltX");
		//System.out.println(result);
		String[] altxdata=result.split(",");
		AltX=new double[altxdata.length];
		for(int i=0;i<altxdata.length;i++){
			AltX[i]=Double.valueOf(altxdata[i]).doubleValue();
		}
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=AltY");
		//System.out.println(result);
		String[] altydata=result.split(",");
		AltY=new double[altydata.length];
		for(int i=0;i<altydata.length;i++){
			AltY[i]=Double.valueOf(altydata[i]).doubleValue();
		}
		
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=LMTS");
		//System.out.println(result);
		String[] lmtsdata=result.split(",");
		LmtS=new double[lmtsdata.length+startLoc];
		for(int i=0;i<startLoc;i++){//fill in ziro lmtspeed for train
			LmtS[i]=Double.valueOf(lmtsdata[0]).doubleValue();
		}		
		for(int i=0;i<lmtsdata.length;i++){
			LmtS[startLoc+i]=Double.valueOf(lmtsdata[i]).doubleValue();
		}
		
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=AdvS");
		//System.out.println(result);
		String[] advsdata=result.split(",");
		AdvS=new double[advsdata.length+startLoc];
		for(int i=0;i<startLoc;i++){//fill in ziro advspeed for train
			AdvS[i]=0;
		}
		for(int i=0;i<advsdata.length;i++){
			AdvS[startLoc+i]=Double.valueOf(advsdata[i]).doubleValue();
		}
		
		
		/*result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=ActS");
		//System.out.println(result);
		String[] actsdata=result.split(",");
		ActS=new double[actsdata.length];
		for(int i=0;i<actsdata.length;i++){
			ActS[i]=Double.valueOf(actsdata[i]).doubleValue();
		}
		
		Locx=new double[actsdata.length]; //Location of train
		for(int i=0;i<ActS.length;i++){
			Locx[i]=Math.round(ActS[i]/25);
		}
		
		RActS=new double[actsdata.length+startLoc];
		for(int i=0;i<startLoc;i++){//fill in ziro speed for train
			RActS[i]=0;
		}
		for(int i=0;i<ActS.length;i++){
			if(i==0){
				RActS[startLoc+i]=Math.round(3.6*ActS[i]);
			}else{
				RActS[startLoc+i]=Math.round(3.6*(ActS[i]-ActS[i-1]));
			}
		}
		
		for(int i=0;i<RActS.length;i++){
			System.out.println("RActS["+String.valueOf(i)+"]:"+String.valueOf(RActS[i]));
		}*/
		
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=COLOR");
		//System.out.println(result);
		String[] colordata=result.split(",");
		Color=new int[colordata.length];
		for(int i=0;i<colordata.length;i++){
			Color[i]=Integer.valueOf(colordata[i]).intValue();
		}
		
		/*result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=Station");
		//System.out.println(result);
		String[] stationdata=result.split(",");
		Station=new Station[stationdata.length];
		for(int i=0;i<stationdata.length;i++){
			Station st=new Station();
			String[] ststr=stationdata[i].split("\"");
			st.setsName(ststr[0]);
			st.setsX(Double.valueOf(ststr[1]).doubleValue());
			st.setRailNumber(Integer.valueOf(ststr[2]).intValue());
			st.setTrailNumber(Integer.valueOf(ststr[3]).intValue());
			st.setStop(ststr[4]);
			st.setArriveTime(Long.valueOf(ststr[5]).longValue());
			st.setOutTime(Long.valueOf(ststr[6]).longValue());
			st.setoTrain(ststr[7]);
			st.setH(ststr[8]);
			System.out.println(st.toString());
			Station[i]=st;
		}*/
	}
	
	public void getLocData(){
		String result = "";
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=ActS");
		//System.out.println(result);
		String[] actsdata=result.split(",");
		ActS=new double[actsdata.length];
		for(int i=0;i<actsdata.length;i++){
			ActS[i]=Double.valueOf(actsdata[i]).doubleValue();
		}
		
		Locx=new double[actsdata.length]; //Location of train
		for(int i=0;i<ActS.length;i++){
			Locx[i]=Math.round(ActS[i]/25);
		}
		
		RActS=new double[actsdata.length+startLoc];
		for(int i=0;i<startLoc;i++){//fill in ziro speed for train
			RActS[i]=0;
		}
		for(int i=0;i<ActS.length;i++){
			if(i==0){
				RActS[startLoc+i]=Math.round(3.6*ActS[i]);
			}else{
				RActS[startLoc+i]=Math.round(3.6*(ActS[i]-ActS[i-1]));
			}
		}

	}
	
	public List getStationTxt(){
		List stationlist =new ArrayList();
		for(int i=0;i<Station.length;i++){
			if(Station[i].getsName().equalsIgnoreCase("_R")) {
				continue;
			}else{
				if((int)Station[i].getsX()-startLoc>jump){
					stationlist.add(Station[i]);
				}
			}
		}
		return stationlist;
	}
	
	public void getStaData(){
		String result = "";
		
		result = HttpUtil.queryStringForPost("http://192.168.1.11:8080/CATOWeb/LoadMessage?mode=Station");
		//System.out.println(result);
		String[] stationdata=result.split(",");
		Station=new Station[stationdata.length];
		for(int i=0;i<stationdata.length;i++){
			Station st=new Station();
			String[] ststr=stationdata[i].split("\"");
			st.setsName(ststr[0]);
			st.setsX(Double.valueOf(ststr[1]).doubleValue());
			st.setRailNumber(Integer.valueOf(ststr[2]).intValue());
			st.setTrailNumber(Integer.valueOf(ststr[3]).intValue());
			st.setStop(ststr[4]);
			st.setArriveTime(Long.valueOf(ststr[5]).longValue());
			st.setOutTime(Long.valueOf(ststr[6]).longValue());
			st.setoTrain(ststr[7]);
			st.setH(ststr[8]);
			System.out.println(st.toString());
			Station[i]=st;
		}

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

	public boolean Hasnew() {
		return hasnew;
	}

	public int[] getStationColor() {
		return StationColor;
	}

	public PointStyle[] getStationStyle() {
		return StationStyle;
	}

	public String getHilight() {
		return hilight;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public int getJump() {
		return jump;
	}

	public boolean isStarted() {
		return started;
	}

	public List getZerostaList() {
		return zerostaList;
	}

	public void setZerostaList(List zerostaList) {
		this.zerostaList = zerostaList;
	}

	public int getJumpadd() {
		return jumpadd;
	}
}
