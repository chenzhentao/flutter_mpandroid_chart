/*
package com.xzwyx.flutter_mpandroid_chart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class MutlLineChartManager {
    public MutlLineChartManager() {
        this.dataSets.clear();
    }

    MutlLineChartManager getInstance() {

        return new MutlLineChartManager();
    }

    */
/**
     * @param context 上下文
     * @param yValue  折线在y轴的值
     * @Description:创建两条折线
     *//*

    public void initSingleLineChart(Context context,
                                    final ArrayList<Entry> yValue, String lineName, int color) {

        //设置折线的样式
        LineDataSet dataSet = new LineDataSet(yValue, lineName);

        dataSet.setLineWidth(2);

//        dataSet.setCircleColor(Color.rgb(63, 81, 181));
//        dataSet.setCircleColorHole(Color.rgb(255, 64, 129));
        dataSet.setDrawValues(false);
//        dataSet.setDrawCubic(false);
        dataSet.setDrawCircles(false);
        dataSet.setCubicIntensity(0.2f);
        dataSet.setValueTextSize(14);
        dataSet.setCircleRadius(1);
//        dataSet.setValueTextColor(Color.rgb(94, 170, 225));
       */
/* dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {

                return String.valueOf(v);
            }
        });*//*

        int[] mColor = new int[]{
                color,
        };//Color.rgb(164, 113, 229)
        dataSet.setColors(mColor, context);
        dataSets.add(dataSet);
    }

    public void updatePointLineChart(LineChart mLineChart,
                                     final Entry yValue, String label, int color) {


        LineData data = mLineChart.getLineData();
        if (data == null) {
            data = new LineData();
            mLineChart.setData(data);
        }
        ILineDataSet set = data.getDataSetByLabel(label, false);
        if (set == null) {
            set = createSet(label, color);

        } else {
            set.clear();
        }
        */
/*if (set instanceof LineDataSet) {
            ((LineDataSet) set).setCircleColor(color);
            ((LineDataSet) set).setHighLightColor(color);
        }*//*


        set.addEntry(yValue);

        if (data.contains(set)) {
            data.removeDataSet(set);
        }

        data.addDataSet(set);
        data.notifyDataChanged();
        mLineChart.notifyDataSetChanged();
        mLineChart.moveViewToX(yValue.getX());

    }

    public void updateSingleLineChart(LineChart mLineChart,
                                      final ArrayList<Entry> yValue, String label, int color, float yAlisMax) {


        LineData data = mLineChart.getLineData();
        if (data == null) {
            data = new LineData();
            mLineChart.setData(data);
        }
        ILineDataSet set = data.getDataSetByLabel(label, false);
        if (set == null) {
            set = createSet(label, color);

        } */
/*else {
            set.clear();
        }*//*

        */
/*if (set instanceof LineDataSet) {
            ((LineDataSet) set).setCircleColor(color);
            ((LineDataSet) set).setHighLightColor(color);
        }*//*

       */
/* if (data.contains(set)) {
            data.removeDataSet(set);
        }*//*

        ((LineDataSet) set).setValues(yValue);
        data.addDataSet(set);
        data.notifyDataChanged();
        mLineChart.notifyDataSetChanged();
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        yAxisLeft.setAxisMaximum(yAlisMax);
        //设置一页最大显示个数为6，超出部分就滑动
//        float ratio = xValues.size() / 6.0f;
        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
//        mLineChart.z
//        mLineChart.zoom(mLineChart.getScaleX(), mLineChart.getScaleY(), 0, 0);
        mLineChart.invalidate();
    }

    private LineDataSet createSet(String label, int color) {

        LineDataSet set = new LineDataSet(null, label);
        set.setLineWidth(2f);
        set.setCircleRadius(4.5f);
        set.setColor(color);
        set.setCircleColor(color);
        set.setHighLightColor(color);
//        set.setCircleColorHole(color);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setValueTextSize(10f);
        set.setDrawValues(false);

//        set.setDrawCubic(false);
        set.setCubicIntensity(0.2f);


//        dataSet.setValueTextColor(Color.rgb(94, 170, 225));
        set.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {

                return String.valueOf(v);
            }
        });

        return set;
    }

    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();


    public void showLineChart(Context context, LineChart mLineChart, ArrayList<String> xValues) {
        initDataStyle(context, mLineChart, xValues);
        //构建一个LineData  将dataSets放入
        LineData lineData = new LineData(dataSets);
        mLineChart.setBorderWidth(2.5f);

        mLineChart.setData(lineData);

//        mLineChart.zoomToCenter(1f, 1f);
//        YAxis.AxisDependency dasda = mLineChart.getAxisLeft().getAxisDependency();
//        mLineChart.setVisibleYRangeMaximum(150, dasda);

//        mLineChart.moveViewToX(lineData.getEntryCount());
        //设置动画效果
        mLineChart.animateY(2000, Easing.Linear);
        mLineChart.animateX(2000, Easing.Linear);

        mLineChart.setDrawMarkers(true);
        mLineChart.invalidate();
    }

    */
/**
     * @param context
     * @param mLineChart
     * @Description:初始化图表的样式
     *//*

    private void initDataStyle(Context context, LineChart mLineChart, final ArrayList<String> xValues ) {
        mLineChart.setDescription(null);

        // 是否在折线图上添加边框
        mLineChart.setDrawBorders(false);

       */
/* // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        mLineChart
                .set("还没有数据");*//*


        // 是否绘制背景颜色。
        // 如果mLineChart.setDrawGridBackground(false)，
        // 那么mLineChart.setGridBackgroundColor(Color.CYAN)将失效;
        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);

        // 触摸//设置图表是否支持触控操作
        mLineChart.setTouchEnabled(true);

        // 拖拽
        mLineChart.setDragEnabled(true);
        mLineChart.setPinchZoom(false);
        mLineChart.setScaleEnabled(false);
        mLineChart.setBackgroundColor(Color.WHITE);
        mLineChart.animateX(2000);  // 沿x轴动画，时间2000毫秒。



        Legend mLegend = mLineChart.getLegend();

//        mLegend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
        mLegend.setForm(Legend.LegendForm.SQUARE);// 样式

        mLegend.setFormSize(0f);// 字体
        mLegend.setTextColor(Color.rgb(255, 255, 255));// 颜色
        mLegend.setTextSize(0.1f);

        //设置x轴的样式
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(20);
        xAxis.setAxisLineColor(context.getResources().getColor(R.color.d_background));
//        xAxis.setSpaceBetweenLabels(0);
        xAxis.setAxisLineWidth(3);
//        if (xValues.size() < 6)
        xAxis.setGranularity(1f);//设置1,x轴的刻度就是1的倍数
        xAxis.setTextColor(context.getResources().getColor(R.color.text2));
        xAxis.setDrawGridLines(true);
        //设置是否显示x轴
        xAxis.setEnabled(true);
//        xAxis.mAxisRange = 6;
//        xAxis.setLabelCount(6,true);
        xAxis.setDrawAxisLine(true);
        xAxis.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                int value1 = (int) value;
                if (value1 >= 0 && (value - value1 == 0) && value1 < xValues.size()) {
                    if (value1 >= 0)
                        return xValues.get(value1);
                    else return "";
                } else {
                    return "";
                }
            }
        });
        //设置左边y轴的样式
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        yAxisLeft.setAxisLineColor(context.getResources().getColor(R.color.d_background));
        yAxisLeft.setAxisLineWidth(3);
//        yAxisLeft.mAxisRange = 6f;
//        yAxisLeft.setShowOnlyMinMax(false);
        yAxisLeft.setStartAtZero(true);
        yAxisLeft.setDrawGridLines(true);
//        yAxisLeft.setLabelCount(7, false);
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置右边y轴的样式
        YAxis yAxisRight = mLineChart.getAxisRight();
        yAxisRight.setEnabled(false);
//        mLineChart.setVisibleXRangeMinimum(1);
//        mLineChart.setVisibleXRangeMaximum(6);

        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = xValues.size() / 6.0f;
        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
//        mLineChart.z
        mLineChart.zoom(ratio, mLineChart.getScaleY(), 0, 0);

    }


}
*/
