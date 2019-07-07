package com.xzwyx.flutter_mpandroid_chart_example

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.google.gson.Gson
import com.szwyx.rxb.home.XueQingFenXi.bean.grade_classScore.Trend
import com.szwyx.rxb.home.XueQingFenXi.bean.t_score_detail.XueQingGrade_StudentScoreDetailBean

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import java.util.*

val classLineName: String = "classLine"
val classPointName: String = "classPoint"
val gradeLineName: String = "gradeLine"
val gradePointName: String = "gradePoint"
val studentLineName: String = "studentLine"
val studentPointName: String = "studentPoint"
val dataTemp = "{\"status\":\"1\",\"msg\":\"操作成功！\"," +
        "\"returnValue\":{\"oneStudentList\":[{\"avgScore\":40.0,\"id\":56," +
        "\"examName\":\"期中考试成绩（上学期）\"},{\"avgScore\":80.0,\"id\":55," +
        "\"examName\":\"市级联合考试成绩\"},{\"avgScore\":80.0,\"id\":54," +
        "\"examName\":\"4月份月考成绩\"},{\"avgScore\":50.0,\"id\":53," +
        "\"examName\":\"3月份月考成绩\"}],\"scoreList\":[{\"avgScore\":40.0," +
        "\"id\":56,\"examName\":\"期中考试成绩（上学期）\"},{\"avgScore\":90.0," +
        "\"id\":55,\"examName\":\"市级联合考试成绩\"},{\"avgScore\":20.0,\"id\":54," +
        "\"examName\":\"4月份月考成绩\"},{\"avgScore\":70.0,\"id\":53," +
        "\"examName\":\"3月份月考成绩\"}],\"trendList\":[{\"avgScore\":90.0,\"id\":56," +
        "\"examName\":\"期中考试成绩（上学期）\"}," +
        "{\"avgScore\":50.0,\"id\":55,\"examName\":\"市级联合考试成绩\"}," +
        "{\"avgScore\":10.0,\"id\":54,\"examName\":\"4月份月考成绩\"}," +
        "{\"avgScore\":70.0,\"id\":53,\"examName\":\"3月份月考成绩\"}]," +
        "\"gradeUp\":0,\"classUp\":0},\"code\":\"2000\"}"

class MyView(context: Context, messenger: BinaryMessenger, id: Int, params: Map<String, Any>) : PlatformView, MethodChannel.MethodCallHandler {
    private var context: Context
    private val myLineChart: LineChart
    private var mutlLineChartManager: MutlLineChartManager
    private val mScoreData: MutableList<Trend> = ArrayList()
    private val mScoreAllData: ArrayList<Trend> = ArrayList()
    private val studentValue: ArrayList<Entry> = ArrayList()
    private val classValue: ArrayList<Entry> = ArrayList()
    private val gradeValue: ArrayList<Entry> = ArrayList()
    private val xValues: ArrayList<String> = ArrayList()
    init {
        var myNativeView = LineChart(context)
        mutlLineChartManager = MutlLineChartManager()
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val var2 = context.resources.displayMetrics
        val var3 = ((300.toFloat() * var2.density).toDouble() + 0.5).toInt()
        layoutParams.height =var3/*(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height-100*/
        layoutParams.width =(context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width
        myNativeView.layoutParams = layoutParams

        this.myLineChart = myNativeView

        val methodChannel = MethodChannel(messenger, "plugins.nightfarmer.top/myview_$id")
        this.context = context
        methodChannel.setMethodCallHandler(this)
//        loadStudentScoreDetailSuccess(Gson().fromJson(dataTemp, XueQingGrade_StudentScoreDetailBean::class.java))
    }

    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        // 在接口的回调方法中可以接收到来自Flutter的调用
        if ("loadDetailSuccess" == methodCall.method) {
            val stringDetail = methodCall.arguments as String
            loadStudentScoreDetailSuccess(Gson().fromJson(dataTemp, XueQingGrade_StudentScoreDetailBean::class.java))
            result.success(null)
        } else if ("updatePoint" == methodCall.method) {
            val indexPostion = Random().nextInt(studentValue.size)
            updatePoint(indexPostion)
            result.success(null)
        }
    }

    override fun getView(): View {
        return myLineChart
    }

    override fun dispose() {

    }

    var higherScore: Float = 0f

    fun loadStudentScoreDetailSuccess(fromJson: XueQingGrade_StudentScoreDetailBean?) {
        val returnValue = fromJson?.returnValue
        returnValue?.maxScore?.let { higherScore = it }


        mScoreAllData.clear()
        mScoreData.clear()
        if (returnValue != null) {

            mScoreAllData.addAll(returnValue.oneStudentList)

            mScoreData.addAll(mScoreAllData)

        }
        if (xValues.size == 0) {
            dealDaliyBightClassDatas(returnValue)
            mutlLineChartManager?.showLineChart(context, myLineChart, xValues)
        } else {
            updateDaliyBightClassDatas(returnValue)

        }

        updatePoint(0)
    }

    private fun dealDaliyBightClassDatas(bean: com.szwyx.rxb.home.XueQingFenXi.bean.t_score_detail.ReturnValue?) {
        val oneStudentList = bean?.oneStudentList?.reversed()
        val scoreList = bean?.scoreList?.reversed()
        val trendList = bean?.trendList?.reversed()

        if (null != oneStudentList && scoreList != null && trendList != null) {
            var beanSize = Math.min(oneStudentList.size, scoreList.size)
            beanSize = Math.min(beanSize, trendList.size)
            for (i in 0 until beanSize) {

                val oneStudentbean = oneStudentList[i]
                val classbean = scoreList[i]
                val gradebean = trendList[i]

                xValues.add(oneStudentbean.examName)
                studentValue.add(Entry(i.toFloat(), java.lang.Float.valueOf(oneStudentbean.avgScore)))
                classValue.add(Entry(i.toFloat(), java.lang.Float.valueOf(classbean.avgScore)))
                gradeValue.add(Entry(i.toFloat(), java.lang.Float.valueOf(gradebean.avgScore)))
            }
        }


        mutlLineChartManager?.initSingleLineChart(context, studentValue, studentLineName, R.color.yin_se)
        mutlLineChartManager?.initSingleLineChart(context, classValue, classLineName, R.color.d_background)
        mutlLineChartManager?.initSingleLineChart(context, gradeValue, gradeLineName, R.color.grade_score_line)
    }


    private fun updateDaliyBightClassDatas(bean: com.szwyx.rxb.home.XueQingFenXi.bean.t_score_detail.ReturnValue?) {
        val oneStudentList = bean?.oneStudentList?.reversed()
        val scoreList = bean?.scoreList?.reversed()
        val trendList = bean?.trendList?.reversed()

        studentValue.clear()
        classValue.clear()
        gradeValue.clear()
        if (null != oneStudentList && scoreList != null && trendList != null) {
            var beanSize = Math.min(oneStudentList.size, scoreList.size)
            beanSize = Math.min(beanSize, trendList.size)
            var higherScore = 0f
            for (i in 0 until beanSize) {
                val oneStudentbean = oneStudentList[i]
                val classbean = scoreList[i]
                val gradebean = trendList[i]



                higherScore = Math.max(higherScore, oneStudentbean.avgScore)

                higherScore = Math.max(higherScore, classbean.avgScore)

                higherScore = Math.max(higherScore, gradebean.avgScore)

                studentValue.add(Entry(i.toFloat(), oneStudentbean.avgScore))
                classValue.add(Entry(i.toFloat(), classbean.avgScore))
                gradeValue.add(Entry(i.toFloat(), gradebean.avgScore))
            }

            mutlLineChartManager?.updateSingleLineChart(myLineChart, studentValue, studentLineName, R.color.yin_se, higherScore + 10)
            mutlLineChartManager?.updateSingleLineChart(myLineChart, classValue, classLineName, R.color.d_background, higherScore + 10)
            mutlLineChartManager?.updateSingleLineChart(myLineChart, gradeValue, gradeLineName, R.color.grade_score_line, higherScore + 10)
        }
    }

    private fun updatePoint(checkedPosition: Int) {

        val index = studentValue.size - 1 - checkedPosition
        if (index >= 0 && index < classValue.size && index < gradeValue.size && index < xValues.size) {

            val studentEntry = studentValue.get(index)

            mutlLineChartManager?.updatePointLineChart(myLineChart, studentEntry, studentPointName, Color.rgb(223, 163, 93))
//            lineChartStudentScore.setText(studentEntry.y.toString())

            val classEntry = classValue.get(index)

            mutlLineChartManager?.updatePointLineChart(myLineChart, classEntry, classPointName, Color.rgb(64, 187, 238))
//            lineChartClassScore.setText(classEntry.y.toString())

            val gradeEntry = gradeValue.get(index)
            mutlLineChartManager?.updatePointLineChart(myLineChart, gradeEntry, gradePointName, Color.rgb(223, 66, 225))//df44e1
//            lineChartGradeScore.setText(gradeEntry.y.toString())

//            lineChartTestName.setText(xValues[index])
            /* if (index == classValue.size - 1) {
                 ((linechart.marker) as CercleMarkerView).let {
                     it.setOffset(-it.width.toFloat(), 0f)
                 }
             } else {
                 ((linechart.marker) as CercleMarkerView).setOffset(0f, 0f)
             }*/


//            myLineChart.highlightValue(studentEntry.x, 0)
//            myLineChart.maxHighlightDistance = studentEntry.x
//            imageMarkerView?.setOnClickListener { XLog.e("   点击事件发生") }

        }
    }
}
