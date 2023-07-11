package com.example.demo.aop

import com.example.demo.annotation.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class CodeLogger {
    @Pointcut("execution(@com.example.demo.annotation.Log * com.example.demo..*.*(..)) && @annotation(codeLog)")
    fun codeLogger(codeLog: Log?) {
    }

    @Before("codeLogger(codeLog)")
    fun doCodeLogger(jp: JoinPoint, codeLog: Log) {
        val str = StringBuilder(NEXT_LINE)
        str.append(DASH_LINE)
        str.append(NEXT_LINE)
        str.append(" Class: " + jp.target.javaClass.simpleName)
        str.append(NEXT_LINE)
        str.append("Method: " + jp.signature.name)
        str.append(NEXT_LINE)
        if (codeLog.printParamsValues) {
            val args = jp.args
            str.append(NEXT_LINE)
            for (obj in args) {
                str.append(" Param: " + obj.javaClass.simpleName)
                str.append(NEXT_LINE)
                try {
                    val methodToCall: String = codeLog.callMethodWithNoParamsToString
                    if ("toString" == methodToCall) str.append(" Value: $obj") else str.append(
                        " Value: " +
                                obj.javaClass
                                    .getDeclaredMethod(methodToCall, *arrayOf())
                                    .invoke(obj, *arrayOf())
                    )
                } catch (e: Exception) {
                    str.append(" Value: [ERROR]> " + e.message)
                }
                str.append(NEXT_LINE)
            }
        }
        str.append(DASH_LINE)
        log.info(str.toString())
    }

    companion object {
        private const val DASH_LINE = "==================================="
        private const val NEXT_LINE = "\n"
        private val log = LoggerFactory.getLogger(CodeLogger::class.java)
    }
}
