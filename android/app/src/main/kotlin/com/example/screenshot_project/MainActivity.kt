package com.example.screenshot_project

import android.content.Intent
import android.net.Uri
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity(){
    private val CHANNEL = "com.example.your_project_name/share"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "shareToInstagram") {
//                val uri = call.argument<String>("uri")
//                val sourceApplication = call.argument<String>("sourceApplication")
//                if (uri != null && sourceApplication != null) {
//                    shareToInstagramStory(Uri.parse(uri), sourceApplication)
//                    result.success(null)
//                } else {
//                    result.error("INVALID_ARGUMENTS", "URI or SourceApplication is null", null)
//                }
                val imagePath = call.argument<String>("imagePath")
                if(imagePath != null){
                    InstagramShare().shareToInstagram(this, imagePath)
                    result.success("Success")
                }else {
                    result.error("INVALID_ARGUMENT", "Image path is null", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }

//    private fun shareToInstagramStory(backgroundAssetUri: Uri, sourceApplication: String) {
//        val intent = Intent("com.instagram.share.ADD_TO_STORY")
//        intent.setDataAndType(backgroundAssetUri, "image/jpeg")
//        intent.putExtra("source_application", sourceApplication)
//        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//
//        if (packageManager.resolveActivity(intent, 0) != null) {
//            startActivity(intent)
//        }
//    }
}
