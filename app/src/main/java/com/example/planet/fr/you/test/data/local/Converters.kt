package com.example.planet.fr.you.test.data.local

import android.net.Uri
import androidx.room.TypeConverter
import java.net.URL

class UriConverters {
    @TypeConverter
    fun fromString(value: String?): Uri? {
        return if (value == null) null else Uri.parse(value)
    }

    @TypeConverter
    fun toString(uri: Uri?): String? {
        return uri?.toString()
    }
}
class URLConverters {
    @TypeConverter
    fun fromString(value: String?): URL? {
        return if (value == null) null else URL(value)
    }

    @TypeConverter
    fun toString(url: URL?): String? {
        return url?.toString()
    }
}
