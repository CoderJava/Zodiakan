package com.ysn.zodiakan.views.main

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.ysn.zodiakan.api.ApiService
import com.ysn.zodiakan.views.base.Presenter
import com.ysn.zodiakan.views.base.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by root on 26/06/17.
 */
class MainActivityPresenter : Presenter<MainActivityView> {

    private val TAG = MainActivityPresenter::class.java.simpleName
    private var mainActivityView: MainActivityView? = null
    private val baseUrl = "https://script.google.com"
    private val key = "AKfycbw7gKzP-WYV2F5mc9RaR7yE3Ve1yN91Tjs91hp_jHSE02dSv9w"
    private lateinit var retrofit: Retrofit

    override fun onAttach(view: View) {
        mainActivityView = view as MainActivityView
    }

    override fun onDetach() {
        mainActivityView = null
    }

    fun onLoadData() {
        val dateNow = SimpleDateFormat("dd MMM yyyy", Locale.US).format(Date())
        val dateNowVer2 = SimpleDateFormat("dd MM yyyy", Locale.US).format(Date())
        mainActivityView?.loadData(dateNow, dateNowVer2)
    }

    fun onCheckZodiak(fullname: String, birthday: String) {
        initRetrofit()
        val resultCallGetZodiak = retrofit.create(ApiService::class.java)

        // Without Rx
        /*resultCallGetZodiak.getZodiak(key, fullname, birthday)
                .enqueue(object : Callback<Zodiak> {
                    override fun onResponse(call: Call<Zodiak>?, response: Response<Zodiak>?) {
                        val zodiak = response?.body()
                        if (zodiak?.status?.toLowerCase() == "success") {
                            mainActivityView?.checkZodiak(zodiak)
                        } else {
                            mainActivityView?.checkZodiakFailed()
                        }
                    }

                    override fun onFailure(call: Call<Zodiak>?, t: Throwable?) {
                        t?.printStackTrace()
                        mainActivityView?.checkZodiakFailed()
                    }
                })*/

        // With Rx
        resultCallGetZodiak.getZodiak(key, fullname, birthday)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            zodiak ->
                            if (zodiak.status.toLowerCase() == "success")
                                mainActivityView?.checkZodiak(zodiak)
                            else
                                mainActivityView?.checkZodiakFailed()
                        },
                        {
                            throwable ->
                            throwable.printStackTrace()
                            mainActivityView?.checkZodiakFailed()
                        }
                )
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}