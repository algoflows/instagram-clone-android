package com.example.instagramclone.data

data class UserData(
  var userId: String? = null,
  var name: String? = null,
  var username: String? = null,
  var bio: String? = null,
  var imageUrl: String? = null,
  var following: List<String>? = null,
) {
  fun toMap() = mapOf(
    "userId" to userId,
    "name" to name,
    "username" to username,
    "bio" to bio,
    "imageUrl" to imageUrl,
    "following" to following
  )
}