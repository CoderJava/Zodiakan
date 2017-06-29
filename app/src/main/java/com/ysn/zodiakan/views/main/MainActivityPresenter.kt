package com.ysn.zodiakan.views.main

import com.ysn.zodiakan.views.base.Presenter
import com.ysn.zodiakan.views.base.View
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by root on 26/06/17.
 */
class MainActivityPresenter : Presenter<MainActivityView> {

    private val TAG = MainActivityPresenter::class.java.simpleName
    private var mainActivityView: MainActivityView? = null

    override fun onAttach(view: View) {
        mainActivityView = view as MainActivityView
    }

    override fun onDetach() {
        mainActivityView = null
    }

    fun onLoadData() {
        val dateNow = SimpleDateFormat("dd MMM yyyy", Locale.US).format(Date())
        var dateNowVer2 = SimpleDateFormat("dd MM yyyy", Locale.US).format(Date())
        // substring month
        /*if (dateNowVer2.startsWith("0", 3, false)) {
            dateNowVer2 = dateNowVer2.substring(0, 3) + "" + dateNowVer2.substring(4, dateNowVer2.length)
        }*/
        // substring dayOfMonth
        /*if (dateNowVer2.startsWith("0")) {
            dateNowVer2 = dateNowVer2.substring(1, dateNowVer2.length)
        }*/
        mainActivityView?.loadData(dateNow, dateNowVer2)
    }

    fun  onCheckZodiak(fullname: String, birthday: String) {}
    
}