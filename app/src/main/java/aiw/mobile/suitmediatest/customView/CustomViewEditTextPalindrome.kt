package aiw.mobile.suitmediatest.customView

import aiw.mobile.suitmediatest.R
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class CustomViewEditTextPalindrome @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    init {
        // Mengatur background warna putih
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))

        // Mengatur Font Family
        typeface = ResourcesCompat.getFont(context, R.font.poppins_medium)

        // Mengatur Size
        textSize = 16f

        // Mengatur background rounded
        background = ContextCompat.getDrawable(context, R.drawable.rounded_shape)

        // Menambahkan padding agar berjarak
        compoundDrawablePadding = 20

        // Mengatur warna hint
        setHintTextColor(ContextCompat.getColor(context, R.color.hintColor))

        // Mengatur hint text
        hint = "Palindrome"

        // Mengatur text alignment
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    }

}
