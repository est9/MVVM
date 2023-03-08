package com.example.mvvm.domain

import com.example.mvvm.data.QuoteRepository
import com.example.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

//import org.junit.jupiter.api.Assertions.*

class GetRandomQuoteUseCaseTest{
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }
    @Test
    fun `when database is empty then return null` ()= runBlocking{
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() }returns emptyList()

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response== null)
    }

    @Test
    fun `when database is not empty then return quote`()= runBlocking {
        //Given
        val quoteList = listOf(Quote("Holi","AristiDevs"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == quoteList.first())
    }
}