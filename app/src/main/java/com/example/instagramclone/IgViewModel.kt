package com.example.instagramclone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.instagramclone.data.Event
import com.example.instagramclone.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val USERS = "users"

@HiltViewModel
class IgViewModel @Inject constructor(
  val auth: FirebaseAuth,
  val db: FirebaseFirestore,
  val storage: FirebaseStorage
) : ViewModel() {

  val signedIn = mutableStateOf(false)
  val inProgress = mutableStateOf(false)
  val userData = mutableStateOf<UserData?>(null)
  val popupNotifcation = mutableStateOf<Event<String>?>(null)
  fun onSignup(username: String, email: String, password: String) {
    inProgress.value = true

    db.collection(USERS).whereEqualTo("username", username).get()
      .addOnSuccessListener { document ->
        // if document exists then username is taken
        if (document.size() < 0) {
          handleException(customMessage = "Username already exists")
          inProgress.value = false
        }

        // if document does not exist then username is available
        if (document.isEmpty) {
          auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
              if (task.isSuccessful) {
                signedIn.value = true
                inProgress.value = false
              } else {
                handleException(task.exception, "Signup failed")
              }
            }
        }
      }
      .addOnFailureListener {
        handleException(it)
      }
  }

  fun handleException(e: Exception? = null, customMessage: String = "") {
    inProgress.value = false
    e?.printStackTrace()
    val errorMsg = e?.localizedMessage ?: ""
    val message = if (customMessage.isEmpty()) errorMsg else "$customMessage $errorMsg"
    popupNotifcation.value = Event(message)
  }
}