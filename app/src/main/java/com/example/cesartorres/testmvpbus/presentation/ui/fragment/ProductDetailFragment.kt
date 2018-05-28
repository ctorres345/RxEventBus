package com.example.cesartorres.testmvpbus.presentation.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cesartorres.testmvpbus.R
import com.example.cesartorres.testmvpbus.presentation.mvp.presenter.ProductDetailPresenter
import com.example.cesartorres.testmvpbus.presentation.mvp.view.contract.ProductDetailView
import kotlinx.android.synthetic.main.fragment_product_detail.*

/**
 * Created by cesar.torres on 3/12/2018.
 */
class ProductDetailFragment : Fragment(),ProductDetailView {

    private lateinit var presenter : ProductDetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProductDetailPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.register()
    }

    override fun onStop() {
        super.onStop()
        presenter.unregister()
    }

    override fun initializeUI() {

    }

    override fun showErrorMessage(errorMessage: String) { Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show() }

    override fun setProductName(productName: String) {
        tvProductName?.let {
            populateField(it,productName)
        }
    }

    override fun setDescription(description: String) {
        tvDescription?.let {
            populateField(it,description)
        }
    }

    override fun setPrice(price: Double) {
        tvPrice?.let {
            populateField(it,price.toString())
        }
    }

    override fun setType(type: String) {
        tvType?.let {
            populateField(it,type)
        }
    }

    override fun setResultScreen() { tvDummy.visibility = View.GONE }

    override fun setBlankScreen() {
        tvDummy.visibility = View.VISIBLE
        tvDescription.visibility = View.GONE
        tvPrice.visibility = View.GONE
        tvType.visibility = View.GONE
        tvProductName.visibility = View.GONE
    }

    private fun populateField(targetView : View, content : String){
        targetView.visibility = View.VISIBLE
        when(targetView){
            is TextView -> targetView.text = content
            is EditText -> targetView.setText(content)
            else -> Log.v("APP", "couldn't populate field, targetView type not handled")
        }
    }

    companion object {
        fun newInstance () : ProductDetailFragment{
            return ProductDetailFragment()
        }
    }

}