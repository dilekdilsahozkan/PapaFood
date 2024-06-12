package com.dilsahozkan.papafood.workmanager

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dilsahozkan.papafood.R

class NotificationWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        val recipeName = inputData.getString("title")
        recipeName?.let {
            showNotification(it, applicationContext)
        }
        return Result.success()
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, context: Context) {
        val builder = NotificationCompat.Builder(context, "recipe_channel")
            .setSmallIcon(R.drawable.ic_notification)

        with(NotificationManagerCompat.from(context)) {
            notify(123, builder.build())
        }
    }
}