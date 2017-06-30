package com.ysn.zodiakan.views.main

import com.ysn.zodiakan.internal.model.zodiak.Zodiak
import com.ysn.zodiakan.views.base.View

/**
 * Created by root on 26/06/17.
 */
interface MainActivityView : View {

    fun loadData(dateNow: String?, dateNowVer2: String)

    fun  checkZodiak(zodiak: Zodiak)

    fun checkZodiakFailed()

}