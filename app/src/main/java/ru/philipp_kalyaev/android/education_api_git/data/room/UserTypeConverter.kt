package ru.philipp_kalyaev.android.education_api_git.data.room

import android.graphics.Bitmap
import androidx.room.TypeConverter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class UserTypeConverter {
    @TypeConverter
    fun fromUrltoBitmap(
        url: String,
    ): Bitmap {
        return Picasso.get()
            .load(url)
            .resize(100, 100)
            .transform(CropCircleTransformation()).get()

    }
}
