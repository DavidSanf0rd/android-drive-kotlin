package com.example.sanf0rd.gdrive

import android.content.IntentSender
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import butterknife.bindView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.drive.Drive

class MainActivity : AppCompatActivity(),
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    var mGoogleApiClient: GoogleApiClient? = null

    val tabLayout: TabLayout by bindView(R.id.tab_layout)
    val viewPager: ViewPager by bindView(R.id.view_pager)
    var pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)


    }

    override fun onResume() {
        super.onResume()

        if (mGoogleApiClient == null) {

            mGoogleApiClient = GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build()
        }
        mGoogleApiClient?.connect()
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        // Called whenever the API client fails to connect.
        Log.i("DriveFailed", "GoogleApiClient connection failed: " + result.toString())

        if (!result.hasResolution()) {

            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show()
            return
        }

        /**
         *  The failure has a resolution. Resolve it.
         *  Called typically when the app is not yet authorized, and an  authorization
         *  dialog is displayed to the user.
         */

        try {
            result.startResolutionForResult(this, 1000)
        } catch (e: IntentSender.SendIntentException) {
            Log.e("DriveSendException", "Exception while starting resolution activity&quot")
        }
    }

    override fun onConnected(bundle: Bundle?) {
        Toast.makeText(this,"DriveConnected", Toast.LENGTH_LONG).show()
    }

    override fun onConnectionSuspended(bundle: Int) {
        Log.i("DriveSuspended", "GoogleApiClient connection suspended")
    }




    override fun onStop() {
        super.onStop()

        mGoogleApiClient?.disconnect()
    }
}
