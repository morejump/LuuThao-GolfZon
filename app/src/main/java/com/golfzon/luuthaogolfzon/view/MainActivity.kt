package com.golfzon.luuthaogolfzon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.golfzon.luuthaogolfzon.R
import com.golfzon.luuthaogolfzon.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()
        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                countriesAdapter.updateCountries(countries)
            }
        })
        viewModel.loading.observe(this, Observer { done ->
            loading_view.visibility = if (done) View.VISIBLE else View.GONE

        })
        viewModel.countryLoadError.observe(this, Observer { isError ->
            listError.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }

}