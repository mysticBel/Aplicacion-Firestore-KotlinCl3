package com.example.aplicacionminceturcl3.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionminceturcl3.domain.entity.RestauranteModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.lang.Exception

class RestauranteViewModel: ViewModel() {

    //variables
    private lateinit var firestore: FirebaseFirestore
    private val _getList = MutableLiveData<List<RestauranteModel>>()
    val getList : LiveData<List<RestauranteModel>> = _getList

    private fun getListApi() : MutableList<RestauranteModel>{
        firestore = FirebaseFirestore.getInstance()
        val listRestaurantes : MutableList<RestauranteModel> = ArrayList()

        firestore.collection("restaurantes").get()
            .addOnSuccessListener { documentList ->
                for (document in documentList){
                    val descripcion = document.getString("descripcion")
                    val imagen = document.getString("imagen")
                    val geopoint = document.getGeoPoint("geopoint")
                    val nombre = document.getString("nombre")

                    var restaurante = RestauranteModel(
                        descripcion!!,
                        geopoint!!,
                        imagen!!,
                        nombre!!)
                    listRestaurantes.add(restaurante)
                }
                _getList.postValue(listRestaurantes)
            }
        return listRestaurantes
    }

    fun getAll() = viewModelScope.launch {
        try {
            getListApi()
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}