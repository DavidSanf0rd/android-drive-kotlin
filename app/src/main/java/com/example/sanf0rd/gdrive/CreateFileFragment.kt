package com.example.sanf0rd.gdrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import butterknife.bindView
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.DriveApi
import com.google.android.gms.drive.DriveContents
import com.google.android.gms.drive.MetadataChangeSet
import org.jetbrains.anko.onClick
import java.io.OutputStreamWriter

/**
 * Created by sanf0rd on 11/03/17.
 */
class CreateFileFragment: Fragment() {

    lateinit var title: String
    val titleTextView: TextView by bindView(R.id.fragment_tittle)
    val createFileButton: Button by bindView(R.id.createButton)

    companion object Factory {
        fun newInstance(title: String): CreateFileFragment {
            val fragment = CreateFileFragment()

            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments.getString("title")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.main_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        titleTextView.text = title
        createFileButton.onClick {
            if (activity is MainActivity) {
                Drive.DriveApi.newDriveContents((activity as MainActivity).mGoogleApiClient)
                        .setResultCallback(resultCallback);
            }
        }
    }

    val resultCallback = object : ResultCallback<DriveApi.DriveContentsResult> {
        override fun onResult(result: DriveApi.DriveContentsResult) {
            if (result.status.isSuccess) {
                createFileOnGoogleDrive(result);
            }
        }
    }

    fun createFileOnGoogleDrive(result: DriveApi.DriveContentsResult) {

        val driveContents = result.driveContents

        object : Thread() {
            override fun run() {
                super.run()
                val googleApiClient = (activity as MainActivity).mGoogleApiClient

                val outputStream = driveContents.outputStream
                val writer = OutputStreamWriter(outputStream)

                writer.write("Hello World yayayay")
                writer.close()

                val metadata = MetadataChangeSet.Builder()
                        .setTitle("yayay").setMimeType("text/plain")
                        .setStarred(true).build()

                Drive.DriveApi.getRootFolder(googleApiClient)
                        .createFile(googleApiClient, metadata, driveContents)
                        .setResultCallback {
                            Toast.makeText(activity, "creating", Toast.LENGTH_LONG).show()
                        }
            }
        }.start()
    }
}