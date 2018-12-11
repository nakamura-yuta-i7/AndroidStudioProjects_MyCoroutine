package com.nkmr.mycoroutine

import android.annotation.SuppressLint
import android.util.Log.d
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlin.coroutines.experimental.suspendCoroutine

class UploadTaskDaemon {
    companion object {
        fun watch() {
            d("UploadTaskDaemon.watch", "koko1")

            var self = UploadTaskDaemon()
            var i = 0
            async(CommonPool) {
                while (true) {
                    delay(1000)
                    i++
                    d("UploadTaskDaemon.watch", "while koko1.  i: $i")
                    var task = {
                        if (i == 2) "task"
                        else null
                    }()
                    if (task!=null) {
                        self.startTask(task)
                    }
                }
            }
        }
    }

    @SuppressLint("LongLogTag")
    suspend fun startTask(task: String): Any? = suspendCoroutine {
        var cont = it
        d("UploadTaskDaemon.startTask", "koko1")

        cont.resume(null)

        try {
        // Driveにルートフォルダが無ければ作る
        val rootFolder = MyGoogleDriveShattoRootFolder()
        rootFolder.googleApi = MyApplication.instance
        val driveApi = rootFolder
        driveApi.googleApi = MyApplication.instance.googleApiClient!!
        val rootFolderId = rootFolder.getRootFolderId()

        d("UploadTaskDaemon.startTask", "koko2")

        // ルートフォルダ内に子フォルダを作る
        // 子フォルダ
        val childDriveId = rootFolder.createFolderInFolderAsync(
            // フォルダ名はイベント日時とイベント名とする
            name = "2017-09-17" + " " + "イベント名",
            parentFolderId = rootFolderId
        )

        d("UploadTaskDaemon.startTask", "koko3")

        val childFolderId = driveApi.getFolderIdByDriveIdAsync(childDriveId)

        // 写真を一枚ずつアップロードしていく
//        try {
//            d("UploadTaskDaemon.startTask", "koko4")
//
//            var pictures = task.pictures.filter {
//                // フィルタで絞込
//                // まだアップロードが完了していない写真を対象とする
//                picture -> picture.uploadedAt == null
//            }
//            pictures.forEach { picture ->
//                picture.status = Picture.Status.uploading.toString()
//                picture.save()
//
//                d("UploadTaskDaemon.startTask", "koko5")
//
//                try {
//                    driveApi.uploadBitmapAsync(
//                        name = task.gCalEvtSummary + " " + task.gCalEvtStartDate.toString(),
//                        bitmap = BitmapFactory.decodeFile(picture.originalTmpPath),
//                        folderId = childFolderId
//                    )
//                    picture.errorMessage = null
//                    picture.status = Picture.Status.uploaded.toString()
//                    picture.uploadedAt = Date()
//                    picture.save()
//
//                    // ここでoriginalTmpPathのファイルを物理削除
//                    var tmpDir = ShattoTmpImgsDir(MyApplication.instance)
//                    tmpDir.deleteFile(picture.originalTmpPath)
//
//                    d("UploadTaskDaemon.startTask", "koko6")
//
//                } catch (e: Throwable) {
//
//                    picture.errorMessage = e.toString()
//                    picture.status = Picture.Status.error.toString()
//                    picture.save()
//
//                    d("UploadTaskDaemon.startTask", "koko7")
//
//                    throw e
//                }
//            }

            d("UploadTaskDaemon.startTask", "koko8")

        } catch (e: Throwable) {

            d("UploadTaskDaemon.startTask", "koko9")
        }
    }
}

class MyApplication {
    object instance {
        val googleApiClient: Any? = null

    }

}

class MyGoogleDriveShattoRootFolder {
    var googleApi: Any? = null
    fun getRootFolderId(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun createFolderInFolderAsync(name: Any, parentFolderId: Any): Any {
        return "folder in folder ID"
    }

    fun getFolderIdByDriveIdAsync(childDriveId: Any): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
