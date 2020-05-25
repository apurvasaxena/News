package com.news.shared.interfaces

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

interface CustomScopeInterface {
    @Documented
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    annotation class CustomScope
}
