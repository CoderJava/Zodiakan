package com.ysn.zodiakan.views.base

/**
 * Created by root on 26/06/17.
 */
interface Presenter<T : View> {

    fun onAttach(view: View)

    fun onDetach()

}