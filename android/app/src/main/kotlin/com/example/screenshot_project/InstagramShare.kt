package com.example.screenshot_project

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File

class InstagramShare {
    fun shareToInstagram(activity: Activity, imagePath : String){
        try{
            // Создаем URI для изображения с использованием FileProvider
            val imageFile = File(imagePath);
            val imageUri : Uri = FileProvider.getUriForFile(
                activity,
                "${activity.packageName}.fileprovider",
                imageFile
            )

            // Создаем интент для Instagram Stories
            val intent = Intent("com.instagram.share.ADD_TO_STORY")
            intent.setDataAndType(imageUri, "image/jpg")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            // Проверяем, что Instagram доступен
            if(activity.packageManager.resolveActivity(intent, 0) != null){
                activity.startActivity(intent);
            }else{
                Log.e("InstagramShare", "Instagram is not installed")
            }
        }catch (e: Exception){
            Log.e("InstagramShare", "Error sharing to Instagram", e)
        }
    }
}