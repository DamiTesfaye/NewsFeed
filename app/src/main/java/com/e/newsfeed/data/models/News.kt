package com.e.newsfeed.data.models

import com.google.gson.annotations.Expose

data class News(
    @Expose
    private val status: String,
    @Expose
    private val totalResults: String,
    @Expose
    private val articles: List<Article>
)
