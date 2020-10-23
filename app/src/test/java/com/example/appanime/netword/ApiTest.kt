package com.example.appanime.netword

import com.example.appanime.model.remote.ApiInterface
import com.example.appanime.model.remote.pojoAnime.Anime
import com.example.appanime.model.remote.pojoAnime.Top
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mApiInterface: ApiInterface

    @Before
    fun setUp(){
        mMockWebServer = MockWebServer()
        val mRetrofic = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiInterface = mRetrofic.create(ApiInterface::class.java)
    }


    @After
    fun shutDown(){
        mMockWebServer.shutdown()
    }


    // No me funciono y nose porque
    @Test
    fun getAllAnime_happy_case() = runBlocking {

        //given
        val mResultList = listOf<Top>(Top("Jul 2010",64,
            "https://cdn.myanimelist.net/images/anime/1223/96541.jpg?s=faffcb677a5eacd17bf761edd78bfb3f",
            5114, 1983740,1, 9.22,"Apr 2009", "Fullmetal Alchemist: Brotherhood"
        ,"TV", "https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood"))

        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mResultList)))

        //when
        val result = mApiInterface.getDataFromAniCoru()

        //then
        assertThat(result).isNotNull()
        val body = result.body()
        assertThat(body)
        val request =  mMockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/top/anime")


    }

}