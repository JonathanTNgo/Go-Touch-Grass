package com.john.gotouchgrass.viewmodel

import com.john.gotouchgrass.R


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import java.util.concurrent.TimeUnit

class ReminderDialogFragment() : DialogFragment() {

//    private val viewModel: GrassViewModel by viewModels {
//        GrassViewModelFactory(requireActivity().application)
//    }
    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("entering", "log")
        var application = requireActivity().application
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.grass_reminder)
                .setItems(R.array.grass_schedule_array) { _, position ->
                    when (position) {
                        0 ->
                            viewModel
                                .scheduleReminder(5, TimeUnit.SECONDS, application)
                        1 ->
                            viewModel
                                .scheduleReminder(1, TimeUnit.DAYS, application)
                        2 ->
                            viewModel
                                .scheduleReminder(7, TimeUnit.DAYS, application)
                        3 ->
                            viewModel
                                .scheduleReminder(30, TimeUnit.DAYS, application)
                    }
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
