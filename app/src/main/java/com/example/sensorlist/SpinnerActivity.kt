package com.example.sensorlist

import android.app.Activity
import android.view.View
import android.widget.AdapterView

class SpinnerActivity(val showList: (position: Int) -> Unit) : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        showList(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        showList(-1)
    }
}
