package com.tom.pokemon.presentation.utils


import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

const val REQUEST_CODE = 500


internal fun isPermissionGranted(grantPermissions: Array<String>,
                                 grantResults: IntArray): Boolean {
    val permissionSize = grantPermissions.size
    for (i in 0 until permissionSize) when {
        Manifest.permission.ACCESS_FINE_LOCATION == grantPermissions[i] ->
            return grantResults[i] == PackageManager.PERMISSION_GRANTED
    }
    return false
}

class LocationSettingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(activity)
                .setMessage("단말기 위치설정이 필요합니다.")
                .setPositiveButton("확인") { _ , _ ->
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }.create()
    }
    companion object {
        fun newInstance(): LocationSettingDialog {
            return LocationSettingDialog()
        }
    }
}