package com.example.demo.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class Log(val printParamsValues: Boolean = false, val callMethodWithNoParamsToString: String = "toString")
