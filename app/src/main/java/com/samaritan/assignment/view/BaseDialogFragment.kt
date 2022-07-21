package com.samaritan.assignment.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.samaritan.assignment.R

open class BaseDialogFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog?.window?.attributes)
        lp.width = (requireContext().resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog?.window?.attributes = lp
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = true
        setStyle(DialogFragment.STYLE_NORMAL, R.style.full_screen_dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            )
        }
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
        return super.onCreateView(inflater, container, savedInstanceState)

    }

}