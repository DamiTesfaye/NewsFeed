package com.e.newsfeed.data.models

import com.google.gson.annotations.Expose

data class Source (
    @Expose
    private val id: String,
    @Expose
    private val name: String
)