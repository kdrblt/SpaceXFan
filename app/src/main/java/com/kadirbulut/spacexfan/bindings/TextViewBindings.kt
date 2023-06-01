package com.kadirbulut.spacexfan.bindings

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.kadirbulut.spacexfan.common.util.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

/*
 *For data binding binder ; converts api date format to item format
 */
@BindingAdapter("formatDate")
fun setFormattedDateText(view: View, date: String?) {
    var outputDate: String? = null
    try {
        val textView: AppCompatTextView = view as AppCompatTextView
        val curFormatter =
            SimpleDateFormat(Constants.API_DATE_FORMAT, Locale.ENGLISH)
        val postFormatter =
            SimpleDateFormat(Constants.UPCOMING_LAUNCHES_DATE_FORMAT, Locale.ENGLISH)
        val dateObj = curFormatter.parse(date.toString())
        outputDate = dateObj?.let { postFormatter.format(it) }
        textView.text = outputDate
    } catch (e: ParseException) {
        e.printStackTrace()
    }
}
