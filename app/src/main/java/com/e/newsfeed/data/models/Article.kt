package com.e.newsfeed.data.models

import com.google.gson.annotations.Expose

data class Article(
    @Expose
    private val source: Source,
    @Expose
    private val author: String,
    @Expose
    private val title: String,
    @Expose
    private val description: String,
    @Expose
    private val url: String,
    @Expose
    private val urlToImage: String,
    @Expose
    private val publishedAt: String,
    @Expose
    private val content: String

)