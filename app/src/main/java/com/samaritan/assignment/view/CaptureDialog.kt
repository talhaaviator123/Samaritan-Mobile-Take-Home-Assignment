package com.samaritan.assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.samaritan.assignment.R
import com.samaritan.assignment.databinding.CaptureDialogBinding
import com.samaritan.assignment.viewmodel.PokemonsDetailsViewModel
import java.text.SimpleDateFormat
import java.util.*


class CaptureDialog(var viewModel: PokemonsDetailsViewModel) : BaseDialogFragment() {

    lateinit var binding: CaptureDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.capture_dialog, container, false
        )
        binding.viewmodel = viewModel
        dialog?.setCancelable(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCapture.setOnClickListener {
            viewModel.addPokemon()
            dialog?.dismiss()
        }
        getTodayDate()
    }

    private fun getTodayDate() {
        val c: Date = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        viewModel.pokemonDetailsObserverModel.capturedDate = df.format(c)
    }
}