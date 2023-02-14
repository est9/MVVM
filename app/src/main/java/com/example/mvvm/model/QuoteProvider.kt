package com.example.mvvm.model

class QuoteProvider {
    companion object{

        fun random():QuoteModel{
            val position: Int = (0..3).random()
            return quote[position]
        }


        private val quote = listOf<QuoteModel>(
            QuoteModel("Los ordenadores son inútiles. Sólo pueden darte respuestas", "Pablo Picasso"),
            QuoteModel("Los ordenadores son como los bikinis. Ahorran a la gente el hacer muchas conjeturas", "Sam Ewing"),
            QuoteModel("Tienen ordenadores, y pueden tener otras armas de destrucción masiva", "Janet Reno"),
            QuoteModel("Es genial trabajar con ordenadores. No discuten, lo recuerdan todo y no se beben tu cerveza", "Paul Leary"),

            )
    }
}