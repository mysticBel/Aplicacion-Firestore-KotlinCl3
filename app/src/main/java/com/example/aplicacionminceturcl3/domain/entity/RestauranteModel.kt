package com.example.aplicacionminceturcl3.domain.entity

import com.google.firebase.firestore.GeoPoint

data class RestauranteModel(
    var descripcion: String? = "",
    var geopoint: GeoPoint? = GeoPoint(0.0, 0.0),
    var imagen: String? = "",
    var nombre: String? = ""
)

