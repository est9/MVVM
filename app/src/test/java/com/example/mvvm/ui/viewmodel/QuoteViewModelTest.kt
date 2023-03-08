package com.example.mvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvm.domain.GetQuotesUseCase
import com.example.mvvm.domain.GetRandomQuoteUseCase
import com.example.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//import org.junit.jupiter.api.Assertions.*
@ExperimentalCoroutinesApi
class QuoteViewModelTest{
    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase
    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase
    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest {
        //Given
        val quotelist = listOf(Quote("Holi", "Aris"), Quote("Dame un like", "Otro Aris "))
        coEvery { getQuotesUseCase() } returns quotelist
        //When
        quoteViewModel.onCreate()
        //Then
        assert(quoteViewModel.quoteModel.value == quotelist.first())
    }
    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        //Given
        val quote = Quote("hello", "Esteban")
        coEvery { getRandomQuoteUseCase() } returns quote
        //When
        quoteViewModel.randomQuote()
        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }
    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest{
        //Given
        val quote = Quote("Hello2", "Esteban")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()
        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }
}