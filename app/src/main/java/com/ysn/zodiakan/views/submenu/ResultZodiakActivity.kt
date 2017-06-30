package com.ysn.zodiakan.views.submenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.ysn.zodiakan.R
import com.ysn.zodiakan.internal.model.zodiak.Zodiak
import kotlinx.android.synthetic.main.activity_result_zodiak.*

class ResultZodiakActivity : AppCompatActivity(), ResultZodiakActivityView {

    private val TAG = ResultZodiakActivity::class.java.simpleName
    private var resultZodiakActivityPresenter: ResultZodiakActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_zodiak)
        initPresenter()
        onAttachView()
        doLoadData()
    }

    private fun doLoadData() {
        val bundle = intent
        val (data, _) = bundle.getSerializableExtra("zodiak") as Zodiak
        text_view_zodiak_name_activity_result_zodiak.text = data.zodiak
        text_view_full_name_user_activity_result_zodiak.text = data.nama.toUpperCase()
        text_view_birthday_user_activity_result_zodiak.text = data.lahir
        text_view_age_user_activity_result_zodiak.text = data.usia
        text_view_birthday_remnant_activity_result_zodiak.text = data.ultah

    }

    private fun initPresenter() {
        resultZodiakActivityPresenter = ResultZodiakActivityPresenter()
    }

    override fun onAttachView() {
        resultZodiakActivityPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        resultZodiakActivityPresenter?.onDetach()
    }
}
