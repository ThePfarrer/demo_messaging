package com.example.demo.aop

import com.example.demo.event.CurrencyConversionEvent
import com.example.demo.exception.BadCodeRuntimeException
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Aspect
@Component
class CurrencyConversionAudit(val publisher: ApplicationEventPublisher) {
    @Pointcut("execution(* com.example.demo.service.*Service.*(..))")
    fun exceptionPointCut() {
    }

    @AfterThrowing(pointcut = "exceptionPointCut()", throwing = "ex")
    fun badCodeException(jp: JoinPoint, ex: BadCodeRuntimeException) {
        if (ex.conversion != null) {
            publisher.publishEvent(CurrencyConversionEvent(jp.target, ex.message, ex.conversion))
        }
    }

}


