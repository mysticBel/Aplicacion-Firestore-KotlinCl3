package com.example.aplicacionminceturcl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionminceturcl3.data.RestauranteViewModel
import com.example.aplicacionminceturcl3.databinding.ActivityMainBinding
import com.example.aplicacionminceturcl3.domain.entity.RestauranteModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), MainAdapter.ICardRestaurante {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewModel: RestauranteViewModel
    //Firestore
    private lateinit var firestore : FirebaseFirestore
    private var listRestaurantes : MutableList<RestauranteModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()

        }

    private fun initValues(){
        viewModel = ViewModelProvider(this)[RestauranteViewModel::class.java]
        firestore = FirebaseFirestore.getInstance()
        mainAdapter = MainAdapter(listRestaurantes, this)
        binding.rvRestaurantes.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvRestaurantes.adapter = mainAdapter
    }

    private fun initObservers(){
        viewModel.getList.observe(this){
            mainAdapter.update(it)
        }
        viewModel.getAll()
    }

    override fun onRestauranteCardClick(item: RestauranteModel) {
                startActivity(Intent(this, MapActivity::class.java).apply {
                    putExtra("nombre", item.nombre)
                    putExtra("latitud", item.geopoint?.latitude)
                        putExtra("longitud", item.geopoint?.longitude)

                })
        }
}


