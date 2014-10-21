package com.catoProj.util;

public class Station {
	private String sName="";
	private double sX=0;
	private int railNumber=0;
	private int trailNumber=0;
	private String stop="F";
	private long arriveTime=0;
	private long outTime=0;
	private String oTrain="";
	private String h="";
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public double getsX() {
		return sX;
	}
	public void setsX(double sX) {
		this.sX = sX;
	}
	public int getRailNumber() {
		return railNumber;
	}
	public void setRailNumber(int railNumber) {
		this.railNumber = railNumber;
	}
	public int getTrailNumber() {
		return trailNumber;
	}
	public void setTrailNumber(int trailNumber) {
		this.trailNumber = trailNumber;
	}
	
	public String toString(){
		return this.getsName()+";"+String.valueOf(this.getsX())+";"+String.valueOf(this.getRailNumber())+";"+String.valueOf(this.getTrailNumber()+";"+this.getStop()+";"+String.valueOf(this.getArriveTime())+";"+String.valueOf(this.getOutTime())+";"+this.getoTrain()+";"+this.getH());
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public long getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(long arriveTime) {
		this.arriveTime = arriveTime;
	}
	public long getOutTime() {
		return outTime;
	}
	public void setOutTime(long outTime) {
		this.outTime = outTime;
	}
	public String getoTrain() {
		return oTrain;
	}
	public void setoTrain(String oTrain) {
		this.oTrain = oTrain;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}

}
