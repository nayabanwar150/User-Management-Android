package com.example.restapi.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.restapi.R


object Util {
    fun showProgressDialog(progressDialog: ProgressDialog,msg: String) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(true)
        progressDialog.setMessage(msg)
        progressDialog.show()
    }

    fun hideProgressDialog(progressDialog: ProgressDialog) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.dismiss()
    }

    fun showDialog(context: Context): AlertDialog{
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.loading_dailog, null)
        builder.setView(view)
        return builder.create()
    }
}