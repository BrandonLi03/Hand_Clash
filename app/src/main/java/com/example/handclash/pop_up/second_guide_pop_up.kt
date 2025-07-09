package com.example.handclash.pop_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.handclash.R
import com.example.handclash.databinding.FragmentSecondGuidePopUpBinding

class second_guide_pop_up : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_guide_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val next_btn = view.findViewById<ImageButton>(R.id.next_btn)
        val previous_btn = view.findViewById<ImageButton>(R.id.previous_btn)

        next_btn.setOnClickListener{
            dismiss()
            val showPopUp = third_guide_pop_up()
            showPopUp.show(parentFragmentManager, "showed")
        }
        previous_btn.setOnClickListener{
            dismiss()
            val showPopUp = first_guide_pop_up()
            showPopUp.show(parentFragmentManager, "showed")
        }
    }
}