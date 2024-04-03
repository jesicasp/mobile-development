package com.dicoding.githubuser

import com.dicoding.githubuser.data.remote.retrofit.ApiConfig
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiConfigTest {

    @Test
    fun `test BASE_URL in ApiConfig`() {
        val expectedBaseUrl = BuildConfig.BASE_URL

        val actualBaseUrl = ApiConfig.getBaseUrl()

        assertEquals(expectedBaseUrl, actualBaseUrl)
    }
}