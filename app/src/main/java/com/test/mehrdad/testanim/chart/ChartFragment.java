package com.test.mehrdad.testanim.chart;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.mehrdad.testanim.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.RangeCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * ${copyright}.
 */
public class ChartFragment extends Fragment {

    private LinearLayout chartLyt;
    private Animation fadeAnim;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fadeAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_anim);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        chartLyt = (LinearLayout) v.findViewById(R.id.chart);

        chartLyt.addView(openChart(), 0);
        return v;
    }


    private View createTempGraph() {
        // We start creating the XYSeries to plot the temperature
        XYSeries series = new XYSeries("London Temperature hourly");

        Random r = new Random();
        int Low = 10;
        int High = 100;
        int[] x = new int[8];

        for (int i = 0; i < x.length; i++) {
//            int Result = r.nextInt(High-Low) + Low;
            int Result = (int) (Math.random() * (High - Low)) + Low;
            series.add(x[i], Result);
        }


        // Now we create the renderer
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setLineWidth(2);
        renderer.setColor(Color.RED);
        // Include low and max value
        renderer.setDisplayBoundingPoints(true);
        // we add point markers
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setPointStrokeWidth(3);


        // Now we add our series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);

        // Finaly we create the multiple series renderer to control the graph
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        // We want to avoid black border
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        // Disable Pan on two axis
        mRenderer.setPanEnabled(false, false);
        mRenderer.setYAxisMax(35);
        mRenderer.setYAxisMin(0);
        mRenderer.setShowGrid(true); // we show the grid
        GraphicalView chartView = ChartFactory.getLineChartView(getActivity(), dataset, mRenderer);

        // Enable chart click
        mRenderer.setClickEnabled(true);
        chartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // applyAnim(v, createPressGraph());
            }
        });

        return chartView;
    }
    //////////////////
    private View openChart() {
        int[] x = new int[8];
        int[] y = new int[5];

        // Creating an  XYSeries for Income
        XYSeries step1 = new XYSeries("line1");
        XYSeries step2 = new XYSeries("line2");

        Random r = new Random();
        int Low = 10;
        int High = 100;


        for (int i = 0; i < x.length; i++) {
//            int Result = r.nextInt(High-Low) + Low;
            int Result = (int) (Math.random() * (High - Low)) + Low;
            step1.add(x[i], Result);
        }

        for (int i = 0; i < y.length; i++) {
//            int Result = r.nextInt(High-Low) + Low;
            int Result = (int) (Math.random() * (High - Low)) + Low;
            step2.add(x[i], Result);
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("ChartTitle");
        multiRenderer.setXTitle("XTitle");
        multiRenderer.setYTitle("YTitle");
        multiRenderer.setZoomButtonsVisible(true);
//        for(int i=0;i<x.length;i++){
//            multiRenderer.addXTextLabel(i+1, "index " + i);
//        }

        dataset.addSeries(step1);
        XYSeriesRenderer step_1Renderer = new XYSeriesRenderer();
        step_1Renderer.setColor(Color.WHITE);
        step_1Renderer.setPointStyle(PointStyle.CIRCLE);
        step_1Renderer.setFillPoints(true);
        step_1Renderer.setLineWidth(2);
        step_1Renderer.setDisplayChartValues(true);
        multiRenderer.addSeriesRenderer(step_1Renderer);


        dataset.addSeries(step2);
        XYSeriesRenderer step_2Renderer = new XYSeriesRenderer();
        step_2Renderer.setColor(Color.RED);
        step_2Renderer.setPointStyle(PointStyle.CIRCLE);
        step_2Renderer.setFillPoints(true);
        step_2Renderer.setLineWidth(2);
        step_2Renderer.setDisplayChartValues(true);
        multiRenderer.addSeriesRenderer(step_2Renderer);


        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.BLACK);

        // Creating an intent to plot line chart using dataset and multipleRenderer
//        Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);

        GraphicalView chartView = ChartFactory.getBarChartView(getActivity(), dataset, multiRenderer, BarChart.Type.DEFAULT);

        GraphicalView chartView1 = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);

        // Enable chart click
        multiRenderer.setClickEnabled(true);
        chartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "view id = " + v.getClass().getName() , Toast.LENGTH_SHORT).show();
            }
        });


        // Start Activity
   return chartView1;


    }
    /////////////
    private View createPressGraph() {
        XYSeries series = new XYSeries("London Pressure hourly");

        // We start filling the series
//        int hour = 0;

        Random r = new Random();
        int Low = 10;
        int High = 100;
        int[] x = new int[8];

        for (int i = 0; i < x.length; i++) {
//            int Result = r.nextInt(High-Low) + Low;
            int Result = (int) (Math.random() * (High - Low)) + Low;
            series.add(x[i], Result);
        }
        // Now we create the renderer
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.BLUE);
        // Include low and max value
        renderer.setDisplayBoundingPoints(true);

        // Now we add our series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);

        // Finaly we create the multiple series renderer to control the graph
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        // We want to avoid black border
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        // Disable Pan on two axis
        mRenderer.setPanEnabled(false, false);

        mRenderer.setShowGrid(true); // we show the grid
        mRenderer.setBarSpacing(1);

        GraphicalView chartView = ChartFactory.getBarChartView(getActivity(), dataset, mRenderer, BarChart.Type.DEFAULT);

        // Enable chart click
        mRenderer.setClickEnabled(true);
        chartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyAnim(v, createForecastTemp());
            }
        });
        return chartView;
    }

    private View createForecastTemp() {
        RangeCategorySeries series = new RangeCategorySeries("London next days temperature");

        // We start filling the series
        int hour = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd,MMM");

        // We create the multiple series renderer to control the graph
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

                for(int i=0;i< 8 ;i++){

                   // series.add();
                    mRenderer.addXTextLabel(i + 1, "index " + i);
        }

//        for (DayForecast df : dayForecast) {
//            series.add(df.forecastTemp.min, df.forecastTemp.max);
//            mRenderer.addXTextLabel(hour++, sdf.format(df.timestamp));
//        }

        //renderer.setGradientStop(20, Color.RED);

        // Now we add our series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries());


        // We want to avoid black border
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        // Disable Pan on two axis
        mRenderer.setPanEnabled(false, false);

        mRenderer.setShowGrid(true); // we show the grid
        mRenderer.setBarSpacing(0.5);
        mRenderer.setMargins(new int[]{30, 70, 10, 0});
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        renderer.setDisplayChartValues(true);
        mRenderer.addSeriesRenderer(renderer);

        mRenderer.setYAxisMax(30.0);
        mRenderer.setYAxisMin(0.0);

        renderer.setChartValuesTextSize(12);
        renderer.setChartValuesFormat(new DecimalFormat("#.##"));
        renderer.setColor(Color.GREEN);
        GraphicalView chartView = ChartFactory.getRangeBarChartView(getActivity(), dataset, mRenderer, BarChart.Type.DEFAULT);

        // Enable chart click
        mRenderer.setClickEnabled(true);
        chartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyAnim(v, createTempGraph());
            }
        });
        return chartView;
    }

    private void applyAnim(final View v, final View nextView) {

        Animation.AnimationListener list = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                chartLyt.removeViewAt(0);
                chartLyt.addView(nextView, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        fadeAnim.setAnimationListener(list);
        v.startAnimation(fadeAnim);
    }
}