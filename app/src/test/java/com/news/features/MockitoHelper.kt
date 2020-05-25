package com.news.features

import org.mockito.ArgumentCaptor

object MockitoHelper {
    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}
