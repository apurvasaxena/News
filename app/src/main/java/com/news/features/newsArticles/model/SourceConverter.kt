package com.news.features.newsArticles.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class SourceConverter {
    @TypeConverter
    fun fromStringToSource(value: String): Source {
        return Gson().fromJson(value, Source::class.java)
    }

    @TypeConverter
    fun fromSourceToString(source: Source): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}
