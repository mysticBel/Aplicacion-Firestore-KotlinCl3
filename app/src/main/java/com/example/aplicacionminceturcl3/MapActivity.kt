package com.example.aplicacionminceturcl3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() ,  OnMapReadyCallback {
    private lateinit var maps : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initValues()
    }
    private fun initValues(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        maps = p0
        var nombreRestaurante = intent.getStringExtra("nombre")!!
        var latitud = intent.getDoubleExtra("latitud", 0.0)
        var longitud = intent.getDoubleExtra("longitud", 0.0)
        val positionMarker = LatLng(latitud, longitud)
        maps.addMarker(
            MarkerOptions()
                .position(positionMarker)
                .title(nombreRestaurante))
        maps.animateCamera(
            CameraUpdateFactory.newLatLngZoom(positionMarker, 18f),
            4000, null)

    }
}