package com.historymakers.resume.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.historymakers.resume.model.Profile
import com.historymakers.resume.utils.Constants.profileCollectionPath
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class ProfileApiService @Inject constructor(
    private val firebaseInstance: FirebaseFirestore
) {
    suspend fun fetchProfile(): Profile {
        val snapshot = try {
            firebaseInstance.collection(profileCollectionPath).document(profileCollectionPath).get().await()
        } catch (e: Exception) {
            null
        }
        return snapshot?.toObject(Profile::class.java)!!
    }
}