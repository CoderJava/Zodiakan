package com.ysn.zodiakan.views.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.ysn.zodiakan.R
import com.ysn.zodiakan.internal.model.zodiak.Zodiak
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainActivityView, View.OnClickListener,
        DatePickerDialog.OnDateSetListener {

    private val TAG = MainActivity::class.java.simpleName
    private var mainActivityPresenter: MainActivityPresenter? = null
    private var year: Int? = 0
    private var month: Int? = 0
    private var dayOfMonth: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        onAttachView()
        doLoadData()
        initialListener()
    }

    private fun initialListener() {
        button_cek_zodiak_activity_main.setOnClickListener(this)
        edit_text_birthday_user_activity_main.setOnClickListener(this)
    }

    private fun doLoadData() {
        mainActivityPresenter?.onLoadData()
    }

    private fun initPresenter() {
        mainActivityPresenter = MainActivityPresenter()
    }

    override fun onAttachView() {
        mainActivityPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        mainActivityPresenter?.onDetach()
    }

    override fun loadData(dateNow: String?, dateNowVer2: String) {
        year = dateNowVer2.split(" ")[2].toInt()
        month = dateNowVer2.split(" ")[1].toInt()
        dayOfMonth = dateNowVer2.split(" ")[0].toInt()
        edit_text_birthday_user_activity_main.setText(dateNow)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_cek_zodiak_activity_main -> {
                // todo: button cek zodiak clicked
                val fullname = edit_text_full_name_activity_main.text.toString().trim()
                val birthday = edit_text_birthday_user_activity_main.text.toString()
                        .trim().replace(" ", "-", false)
                showProgress()
                mainActivityPresenter?.onCheckZodiak(fullname, birthday)
            }
            R.id.edit_text_birthday_user_activity_main -> {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(this, this, year, month, dayOfMonth)
                datePickerDialog.show()
            }
            else -> {
                // nothing to do in here
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // 29-5-2017 -> 29 Juni 2017
        var strMonth = ""
        if (month == 0) {
            strMonth = "Jan"
        } else if (month == 1) {
            strMonth = "Feb"
        } else if (month == 2) {
            strMonth = "Mar"
        } else if (month == 3) {
            strMonth = "Apr"
        } else if (month == 4) {
            strMonth = "May"
        } else if (month == 5) {
            strMonth = "Jun"
        } else if (month == 6) {
            strMonth = "Jul"
        } else if (month == 7) {
            strMonth = "Aug"
        } else if (month == 8) {
            strMonth = "Sep"
        } else if (month == 9) {
            strMonth = "Oct"
        } else if (month == 10) {
            strMonth = "Nov"
        } else if (month == 11) {
            strMonth = "Dec"
        }
        this.year = year
        this.month = month.plus(1)
        this.dayOfMonth = dayOfMonth
        edit_text_birthday_user_activity_main.setText("$dayOfMonth $strMonth $year")
    }

    override fun checkZodiak(zodiak: Zodiak) {
        hideProgress()
        Log.d(TAG, "zodiak: $zodiak")
    }

    override fun checkZodiakFailed() {
        hideProgress()
        Snackbar.make(
                findViewById(R.id.coordinator_layout_container_activity_main) as CoordinatorLayout,
                "Your connection is problem. Please try again", Snackbar.LENGTH_LONG)
                .show()
    }

    private fun showProgress() {
        button_cek_zodiak_activity_main.visibility = View.GONE
        progress_bar_activity_main.visibility = View.VISIBLE
        edit_text_full_name_activity_main.isEnabled = false
        edit_text_birthday_user_activity_main.isEnabled = false
    }

    private fun hideProgress() {
        button_cek_zodiak_activity_main.visibility = View.VISIBLE
        progress_bar_activity_main.visibility = View.GONE
        edit_text_full_name_activity_main.isEnabled = true
        edit_text_birthday_user_activity_main.isEnabled = true
    }
}
