package com.catoProj.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer.FillOutsideLine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.catoProj.basic.AbstractGraChart;
import com.catoProj.util.DataUtil;

public class StationChart extends AbstractGraChart {
	@Override
	public String getName() {
		return "Station Chart";
	}

	@Override
	public String getDesc() {
		return "Station Chart In Railway";
	}

	
	@Override
	public GraphicalView execute(Context context) {
		DataUtil du =DataUtil.getInstance();
		du.getStaData();
		du.readStationData();
	    HashMap stationData = du.getStationChartData();


	    //int[] colors = new int[] { Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE};
	    //PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT};
	    
	    
	    //XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    XYMultipleSeriesRenderer renderer = buildRenderer(du.getStationColor(), du.getStationStyle());
	    setChartSettings(renderer, "  ", "  ", "  ", 0.5,
	        300.50, 0, 50, Color.BLACK, Color.BLACK);
	    renderer.setXLabels(50);
	    renderer.setYLabels(5);
	    renderer.setChartTitleTextSize(20);
	    renderer.setTextTypeface("sans_serif", Typeface.BOLD);
	    renderer.setLabelsTextSize(14f);
	    renderer.setAxisTitleTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setMarginsColor(Color.WHITE);
	    renderer.setPanEnabled(false);
	    renderer.setZoomButtonsVisible(false);
	    renderer.setZoomEnabled(false, false);
	    renderer.setDisplayValues(false);
	    renderer.setShowAxes(false);
	    renderer.setXLabelsColor(Color.WHITE);
	    renderer.setYLabelsColor(0, Color.WHITE);
	    
	    int length = renderer.getSeriesRendererCount();

	    for (int i = 0; i < length; i++) {
	      XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
	      if(du.getHilight().indexOf("["+String.valueOf(i)+"],")>-1){
	    	  seriesRenderer.setLineWidth(4f);
	      }else{
	    	  seriesRenderer.setLineWidth(1f);
	      }
	     /* if (i > 1) {
	    	  	seriesRenderer.setLineWidth(3f);
		      }else{
		    	seriesRenderer.setLineWidth(1f);
		      }*/
	      seriesRenderer.setDisplayChartValues(false);
	      seriesRenderer.setChartValuesTextSize(10f);
	    }
//	    return ChartFactory.getCubeLineChartView(context, buildBarDataset(titles, values), renderer,0.5f);
	    return ChartFactory.getCubeLineChartView(context, buildDataset((String[])stationData.get("titles"), (List<double[]>)stationData.get("xvalues"), (List<double[]>)stationData.get("yvalues")), renderer,0.5f);
	}

}
