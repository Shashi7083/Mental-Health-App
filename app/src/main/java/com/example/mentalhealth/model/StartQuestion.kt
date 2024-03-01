package com.example.mentalhealth.model

data class StartQuestion(
    val question : String,
    val options : ArrayList<String>
)


val questions = arrayListOf(
    StartQuestion(
        question = "Which area of your life do you want to imporve ?",
        options = arrayListOf(
            "Relationships",
            "Mental Health",
            "Physical Health",
            "Career",
            "Something else"
        )
    ),
    StartQuestion(
        question = "How many Hours of Sleep do you usually get?",
        options = arrayListOf(
            "Less than 6 hours",
            "6-7 hours",
            "7-8 hours",
            "More than 8 hours",
            "Something else"
        )
    ),StartQuestion(
        question = "Which area of your life do you want to imporve ?",
        options = arrayListOf(
            "Relationships",
            "Mental Health",
            "Physical Health",
            "Career",
            "Something else"
        )
    ),
    StartQuestion(
        question = "How many Hours of Sleep do you usually get?",
        options = arrayListOf(
            "Less than 6 hours",
            "6-7 hours",
            "7-8 hours",
            "More than 8 hours",
            "Something else"
        )
    )
)