package com.historymakers.resume.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.historymakers.resume.model.Profile
import com.historymakers.resume.model.WorkHistory
import com.historymakers.resume.utils.Constants.companyList
import com.historymakers.resume.utils.Constants.companyPath
import com.historymakers.resume.utils.Constants.workHistoryPath
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class WorkHistoryListApiService @Inject constructor(
    private val firebaseInstance: FirebaseFirestore
) {
    suspend fun fetchProfile(): List<WorkHistory> {
        val snapshot = try {
            firebaseInstance.collection(workHistoryPath).document(companyPath).collection(
                companyList
            ).get()
                .await()
        } catch (e: Exception) {
            null
        }
        val list = mutableListOf<WorkHistory>()
        snapshot?.documents?.forEach {
            val workHistory = WorkHistory(
                it.data?.get("name").toString() as String,
                it.data?.get("role").toString(),
                it.data?.get("job_description").toString(),
                it.data?.get("duration").toString(),
                it.data?.get("logo").toString()
            )
            list.add(workHistory)
        }
        return list
    }
}