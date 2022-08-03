package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val token = "eCUA2yQQT0CZa3LAPemJPy:APA91bHQ6MKl4pK6o1WAM0k1lNss9_31TLtT8QAcQp97gJaiZ75NdVlpI6y1TSGgHGGzmSW1k0IhQNG_dBP6sSg7ANjZj3ncKgO5tRAnR0dHqe3kWUvoKOTROVuT7z-Ru8GoH3usvMVE"

    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageNewPost = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "userName": "Fedr",
          "content": "Beautiful weather"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageUnknown = Message.builder()
        .putData("action", "UNKNOWN")
        .putData("content", """{
          "userName": "Bond",
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messageLike)
    FirebaseMessaging.getInstance().send(messageNewPost)
    FirebaseMessaging.getInstance().send(messageUnknown)
}
