package com.catoProj.cato;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.GraphicalView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.catoProj.element.HeightChart;
import com.catoProj.element.StationChart;
import com.catoProj.util.DataUtil;
import com.catoProj.util.Station;

public class MainActivity extends ActionBarActivity {
	private PlaceholderFragment mainfragment=null; 
	private Timer timer = new Timer();  
    private TimerTask task; 
    private Handler handler; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			 StrictMode.setThreadPolicy(policy);
			}

		

		if (savedInstanceState == null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			mainfragment=new PlaceholderFragment();
			fragmentTransaction.add(R.id.container, mainfragment);
			fragmentTransaction.commit();
			
			

			handler = new Handler() {
	        	@Override
	        	public void handleMessage(Message msg) {
	        		updateChart();
	        		super.handleMessage(msg);
	        	}
	        };
	        
	        task = new TimerTask() {
	        	@Override
	        	public void run() {
	        		Message message = new Message();
	        	    message.what = 1;
	        	    handler.sendMessage(message);
	        	}
	        };
	        timer.schedule(task, 1000, 1000);
	        
	        
	        
			//getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		//if (id == R.id.action_refresh) {
		//	this.refreshSpeed();
		//	return true;
		//}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private View fragView;
		public PlaceholderFragment() {
		}
		public View getFragView(){
			return fragView;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

//			TextView hellotext=(TextView) rootView.findViewById(R.id.TextView3);
//			hellotext.setTextColor(Color.BLUE);
//			hellotext.setText(R.string.hello_sx);
			
			RelativeLayout graLayout=(RelativeLayout)rootView.findViewById(R.id.GraArea);
			GraphicalView heightChart =new HeightChart().execute(rootView.getContext());
			heightChart.setId(99812);
			//System.out.println("dfffffffffffffffffffffffff"+String.valueOf(heightChart.getId()));
			graLayout.addView(heightChart);
			
			

			
			RelativeLayout stationLayout=(RelativeLayout)rootView.findViewById(R.id.StationArea);
			

			DataUtil du=DataUtil.getInstance();
			du.setZerostaList(du.getStationTxt());
			List stlist=du.getZerostaList();
			
			GraphicalView stationChart =new StationChart().execute(rootView.getContext());
			//stationChart.repaint();
			stationChart.setId(99813);
			//System.out.println("fsddddddddddddddddddddddddd"+String.valueOf(stationChart.getId()));
			stationLayout.addView(stationChart);

			for(int i=0;i<stlist.size();i++){
				Station st=(Station)stlist.get(i);
				TextView stnametxt=new TextView(rootView.getContext());
				stnametxt.setId(99900+i);
				stnametxt.setText(st.getsName()+"\n "+st.getStop());
				stnametxt.setTextSize(10);
				stnametxt.setTextColor(Color.RED);
				stationLayout.addView(stnametxt);

				
				LayoutParams params = (LayoutParams)stnametxt.getLayoutParams();
		        params.topMargin = 30;
		        //350 is the startLine
		        params.leftMargin=70+((int)(st.getsX()*3.7));
		        stnametxt.setLayoutParams(params);
				
			}
			
			fragView=rootView;
			return rootView;
		}
	}
	
	public void refreshSpeed(){
		DataUtil du=DataUtil.getInstance();
		du.readSpeedData();
		View rootview=this.mainfragment.getFragView();
		if(du.Hasnew()){
			HashMap data=du.getSpeedData();
			TextView textSpeed=(TextView) rootview.findViewById(R.id.textSpeed);
			TextView textSpeedAdvice=(TextView) rootview.findViewById(R.id.textSpeedAdvice);
			TextView textSpeeddif=(TextView) rootview.findViewById(R.id.textSpeeddif);
			TextView textClockArrive=(TextView) rootview.findViewById(R.id.textClockArrive);
			TextView textDistance=(TextView) rootview.findViewById(R.id.textDistance);
			TextView textNextStation=(TextView) rootview.findViewById(R.id.textNextStation);
			ImageView speeduppic=(ImageView) rootview.findViewById(R.id.imageSpeedUp);
			
			textSpeed.setText((CharSequence) data.get("textSpeed"));

			textSpeedAdvice.setTextColor(Color.BLUE);
			textSpeedAdvice.setText((CharSequence) data.get("textSpeedAdvice"));
			
			textSpeeddif.setTextColor(Color.BLUE);
			textSpeeddif.setText((CharSequence) data.get("textSpeeddif"));
			
			textClockArrive.setTextColor(Color.GREEN);
			textClockArrive.setText((CharSequence) data.get("textClockArrive"));
			textDistance.setText((CharSequence) data.get("textDistance"));
			textNextStation.setText((CharSequence) data.get("textNextStation"));


			LayoutParams params = (LayoutParams)speeduppic.getLayoutParams();
	        params.topMargin = (int) ((Long)data.get("imageSpeedUp")).longValue()*-5+10;
	        speeduppic.setLayoutParams(params);
	        
			if(((Long)data.get("imageSpeedUp")).longValue()>0){
				speeduppic.setImageResource(R.drawable.down);
				
			}else{
				if(((Long)data.get("imageSpeedUp")).longValue()==0){
					speeduppic.setImageResource(R.drawable.bar);
				}else{
					speeduppic.setImageResource(R.drawable.up);
				}
			}
		}
		
		
		//du.clear();
	}

public void refreshGra(){
	View rootView=this.mainfragment.getFragView();
	RelativeLayout graLayout=(RelativeLayout)rootView.findViewById(R.id.GraArea);
	GraphicalView heightChart=(GraphicalView)graLayout.findViewById(99812);
	graLayout.removeView(heightChart);
	GraphicalView newheightChart =new HeightChart().execute(rootView.getContext());
	newheightChart.setId(99812);
	graLayout.addView(newheightChart);

}
public void refreshStation(){
	DataUtil du=DataUtil.getInstance();
	
	View rootView=this.mainfragment.getFragView();
	TextView textRailU=(TextView) rootView.findViewById(R.id.textViewRailU);
	textRailU.setTextColor(Color.RED);
	TextView textRailN=(TextView) rootView.findViewById(R.id.TextViewRailN);
	textRailN.setTextColor(Color.RED);
	
	RelativeLayout stationLayout=(RelativeLayout)rootView.findViewById(R.id.StationArea);
	GraphicalView stationChart =(GraphicalView)stationLayout.findViewById(99813);
	stationLayout.removeView(stationChart);
	GraphicalView newstationChart =new StationChart().execute(rootView.getContext());
	newstationChart.setId(99813);
	stationLayout.addView(newstationChart);

	List stList=du.getStationTxt();
	
	LinearLayout LineStation = (LinearLayout) rootView.findViewById(R.id.LineStation);
	LineStation.removeAllViews();

	TextView toline=new TextView(this);
	toline.setText("-----------------------------------------------------");
	toline.setTextSize(10);
	LineStation.addView(toline);
	
	for(int i=stList.size()-1;i>-1;i--){
		Station st=(Station)stList.get(i);
		String arrtime="";
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("HH:mm:ss");
		//ClockArrive=format1.format(this.starttime+speedst.getArriveTime());
		if(st.getStop().trim().equalsIgnoreCase("F")){
			arrtime=">"+format1.format(du.getStarttime()+st.getArriveTime());
		}
		if(st.getStop().trim().equalsIgnoreCase("S")){
			arrtime="<"+format1.format(du.getStarttime()+st.getArriveTime())+"  "+format1.format(du.getStarttime()+st.getOutTime());
		}
		TextView sttxt=new TextView(this);
		sttxt.setText(st.getStop()+"  "+st.getsName()+"  "+String.valueOf(st.getTrailNumber())+"  "+st.getH()+"  "+st.getoTrain()+"  "+arrtime);
		sttxt.setTextSize(10);
		LineStation.addView(sttxt);

		TextView line=new TextView(this);
		line.setText("-----------------------------------------------------");
		line.setTextSize(10);
		LineStation.addView(line);
	}
	
	List stlist=du.getZerostaList();
	for(int i=0;i<stlist.size();i++){
		Station st=(Station)stlist.get(i);
		TextView stnametxt=(TextView) rootView.findViewById(99900+i);
		LayoutParams params = (LayoutParams)stnametxt.getLayoutParams();
        params.topMargin = 30;
        //350 is the startLine
        int oldleft=params.leftMargin;
        System.out.println("oldleft="+String.valueOf(oldleft)+"     du.getJump()="+String.valueOf(du.getJump()));
        params.leftMargin=oldleft-du.getJumpadd()*4;
        if( params.leftMargin<350){
        	 params.leftMargin=-50;
        }
        stnametxt.setLayoutParams(params);
	}

	
}	
public void updateChart(){
	this.refreshSpeed();
	this.refreshGra();
	this.refreshStation();
	
}

	
	  @Override  
	    public void onDestroy() {  
	        timer.cancel();  
	        super.onDestroy();  
	    }  

}
