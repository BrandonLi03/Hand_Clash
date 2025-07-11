package com.example.handclash.pop_up

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.handclash.R
import com.example.handclash.databinding.FragmentPausePopUpBinding
import com.example.handclash.game_page
import com.example.handclash.home_page

class pause_pop_up : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pause_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val home_btn = view.findViewById<LinearLayout>(R.id.home_btn)
        val replay_btn = view.findViewById<LinearLayout>(R.id.replay_btn)
        val resume_btn = view.findViewById<LinearLayout>(R.id.resume_btn)

        home_btn.setOnClickListener{
            dismiss()
            val intent = Intent(requireContext(), home_page::class.java)
            startActivity(intent)
        }
        replay_btn.setOnClickListener {
            dismiss()
            val intent = Intent(requireContext(), game_page::class.java)
            startActivity(intent)
        }
        resume_btn.setOnClickListener {
            dismiss()
        }
    }
}