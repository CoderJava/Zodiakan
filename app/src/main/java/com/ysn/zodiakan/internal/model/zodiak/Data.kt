package com.ysn.zodiakan.internal.model.zodiak

import java.io.Serializable

/**
 * Created by root on 30/06/17.
 */
data class Data (
        val nama: String,
        val lahir: String,
        val usia: String,
        val ultah: String,
        val zodiak: String
) : Serializable
