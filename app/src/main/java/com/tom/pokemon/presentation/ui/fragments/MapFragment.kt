package com.tom.pokemon.presentation.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.tom.pokemon.R
import com.tom.pokemon.data.model.PokeMonLocation
import com.tom.pokemon.databinding.FragmentMapBinding
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.presentation.utils.REQUEST_CODE
import com.tom.pokemon.presentation.utils.isPermissionGranted
import com.tom.pokemon.presentation.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class MapFragment(private val pokeMonDetail: PokeMonDetail) : Fragment(), OnMapReadyCallback{
    private lateinit var binding: FragmentMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//    private val viewModel by viewModel<SearchViewModel>()
    private val viewModel by activityViewModels<SearchViewModel>()

    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = getFusedLocationProviderClient(requireActivity().applicationContext)

        checkMyPermissionLocation()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        viewModel.pokeMonDetail.observe(viewLifecycleOwner){
            addLocation(it.data!!.location!!)
        }
//        if(pokeMonDetail.location != null) {
//            addLocation(pokeMonDetail.location!!)
//        }
    }

    private fun addLocation(list: List<PokeMonLocation>){
        if(list.isEmpty()){
            Toast.makeText(context, "서식지 정보가 없습니다.", Toast.LENGTH_LONG).show()
            return
        }

        list.forEach{
            val location = LatLng(it.lat, it.lng)
            googleMap.addMarker(MarkerOptions().position(location).title("${it.id}"))
        }

        LatLngBounds.builder().let{builder ->
            list.forEach{builder.include(LatLng(it.lat, it.lng))}
            builder
        }.build()

//        googleMap.moveCamera(
//            CameraUpdateFactory.newLatLngZoom(list[0].let{
//                LatLng(it.lat, it.lng)
//            }, 15f)
//        )
    }

    private fun checkMyPermissionLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                        REQUIRED_PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(),
                        REQUIRED_PERMISSIONS[1]) != PackageManager.PERMISSION_GRANTED ) {
            requestPermission(requireActivity())
        } else {
            fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener {
                        Log.e("Location", "$it")
                        updateGoogleMap(it)
                    }
        }
    }
    private fun updateGoogleMap(location: Location){
        val options = MarkerOptions()
        options.position(LatLng(location.latitude, location.longitude))
        val icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
        options.icon(icon)
        val marker = googleMap.addMarker(options)

//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                marker.position,
//                16f
//        ))
    }

    private fun requestPermission(fragmentActivity: FragmentActivity) {
        ActivityCompat.requestPermissions(fragmentActivity,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode != REQUEST_CODE) {
            return
        }
        if (isPermissionGranted(REQUIRED_PERMISSIONS, grantResults)) {
            checkMyPermissionLocation()
        } else {
            Toast.makeText(requireContext(), "위치정보사용을 허락 하지않아 앱을 중지합니다",
                    Toast.LENGTH_SHORT).show()
        }
    }
}