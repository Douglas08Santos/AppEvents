package com.example.appevents.model

interface BaseListModel {
    val type: Int

    companion object {
        val TYPE_EMPTY = 0
        val TYPE_TEXT = 101
        val TYPE_TEXT_AND_IMAGE = 102
    }
}
