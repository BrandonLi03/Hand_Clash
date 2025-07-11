package com.example.handclash.pop_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.handclash.R
import com.example.handclash.databinding.FragmentThirdGuidePopUpBinding

class third_guide_pop_up: DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_guide_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val previous_btn = view.findViewById<ImageButton>(R.id.previous_btn)
        val close_btn = view.findViewById<TextView>(R.id.close_btn)

        previous_btn.setOnClickListener{
            dismiss()
            val showPopUp = second_guide_pop_up()
            showPopUp.show(parentFragmentManager, "showed")
        }

        close_btn.setOnClickListener{
            dismiss()
        }

    }
}