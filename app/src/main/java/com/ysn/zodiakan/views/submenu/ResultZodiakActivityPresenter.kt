package com.ysn.zodiakan.views.submenu

import com.ysn.zodiakan.views.base.Presenter
import com.ysn.zodiakan.views.base.View

/**
 * Created by root on 30/06/17.
 */
class ResultZodiakActivityPresenter : Presenter<ResultZodiakActivityView> {

    private val TAG = ResultZodiakActivityPresenter::class.java.simpleName
    private var resultZodiakActivityView: ResultZodiakActivityView? = null

    override fun onAttach(view: View) {
        resultZodiakActivityView = view as ResultZodiakActivityView
    }

    override fun onDetach() {
        resultZodiakActivityView = null
    }


}