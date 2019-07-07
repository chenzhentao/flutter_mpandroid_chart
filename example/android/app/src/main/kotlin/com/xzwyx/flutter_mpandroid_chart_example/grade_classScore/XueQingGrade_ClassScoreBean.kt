package com.szwyx.rxb.home.XueQingFenXi.bean.grade_classScore

import android.os.Parcel
import android.os.Parcelable

data class XueQingGrade_ClassScoreBean(
    val code: String,
    val msg: String,
    val returnValue: ReturnValue,
    val status: String
)

data class ReturnValue(
    val cricleList: List<Cricle>,
    val maxScore: Float,
    val minScore: Float,
    val scoreList: List<Score>,
    val trendList: List<Trend>
)

data class Score(
    val avgScore: Float,
    val className: String,
    val examName: String,
    val gradeName: String,
    val id: Int
)

data class Trend(
    val avgScore: Float,
    val examName: String,
    val id: Int
) : Parcelable {
  constructor(parcel: Parcel) : this(
          parcel.readFloat(),
          parcel.readString(),
          parcel.readInt()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeFloat(avgScore)
    parcel.writeString(examName)
    parcel.writeInt(id)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Trend> {
    override fun createFromParcel(parcel: Parcel): Trend {
      return Trend(parcel)
    }

    override fun newArray(size: Int): Array<Trend?> {
      return arrayOfNulls(size)
    }
  }
}

data class Cricle (
        var color:Int,
    val scorePercent: Float,
    val section: String?,
    val sum: Int
) : Parcelable {
  constructor(parcel: Parcel) : this(
          parcel.readInt(),
          parcel.readFloat(),
          parcel.readString(),
          parcel.readInt()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(color)
    parcel.writeFloat(scorePercent)
    parcel.writeString(section)
    parcel.writeInt(sum)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Cricle> {
    override fun createFromParcel(parcel: Parcel): Cricle {
      return Cricle(parcel)
    }

    override fun newArray(size: Int): Array<Cricle?> {
      return arrayOfNulls(size)
    }
  }
}