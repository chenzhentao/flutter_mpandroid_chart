package com.szwyx.rxb.home.XueQingFenXi.bean.t_score_detail

import com.szwyx.rxb.home.XueQingFenXi.bean.grade_classScore.Trend


data class XueQingGrade_StudentScoreDetailBean(
        val code: String,
        val msg: String,
        val returnValue: ReturnValue,
        val status: String
)

data class ReturnValue(
        val avgScore: Float,
        val classRate: Int,
        val classUp: Int,
        val currentScore: Float,
        val gradeRate: Int,
        val gradeUp: Int,
        val maxScore: Float,
        val minScore: Float,
        val oneStudentList: List<Trend>,
        val scoreList: List<Trend>,
        val trendList: List<Trend>,
        val upScore: Float
)





