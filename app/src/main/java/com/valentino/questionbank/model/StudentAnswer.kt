package com.valentino.questionbank.model

data class StudentAnswer(var sid : Int,
                         var rationale : String,
                         var rating: Rating,
                         var initialAnswer : String,
                         var finalAnswer : String)