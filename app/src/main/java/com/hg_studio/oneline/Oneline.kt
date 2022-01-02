package com.hg_studio.oneline

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.internal.operators.flowable.FlowableGenerate

@Entity(tableName = "OnelineTable")
data class Oneline (@PrimaryKey(autoGenerate = true) var idx: Int =0,
        val oneline: String ="",
        val datetime: String ="",
        val writer: Int =0,
        val isDelete: String="N")

//val isCheck: String ="N"