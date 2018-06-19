package com.aranirahan.myfootballapi.presenter

import com.aranirahan.myfootballapi.model.api.ApiRepository
import com.aranirahan.myfootballapi.model.api.TheSportDBApi
import com.aranirahan.myfootballapi.model.item.MatchEvent
import com.aranirahan.myfootballapi.model.item.MatchEventResponse
import com.aranirahan.myfootballapi.view.myInterface.TeamsView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PastMatchPresenterTest {

    @Mock
    private
    lateinit var view: TeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PastMatchPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PastMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val matchEvent: MutableList<MatchEvent> = mutableListOf()
        val response = MatchEventResponse(matchEvent)
        val match = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPastMatchEvent(match)), MatchEventResponse::class.java))
                .thenReturn(response)
        presenter.getMatchList(match)

        verify(view).showLoading()
        verify(view).showMatchEventList(matchEvent)
        verify(view).hideLoading()

    }
}