package com.teravin.kotlin_base_template.database.sample

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = SampleContract.TABLE_NAME)
data class Sample(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = SampleContract.SAMPLE_ID)
    val id: UUID = UUID.randomUUID(),

    @SerializedName("sample")
    @ColumnInfo(name = SampleContract.SAMPLE)
    val sample: String,

    @ColumnInfo(name = SampleContract.LAST_UPDATED)
    val lastUpdated: Date = Date()
): Parcelable