package com.example.demo.listener

import com.example.demo.event.CurrencyConversionEvent
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class CurrencyConversionEventListener : ApplicationListener<CurrencyConversionEvent> {
    val dashLine = "============"
    val nextLine = "\n"
    val log = LoggerFactory.getLogger(CurrencyConversionEvent::class.java)

    override fun onApplicationEvent(event: CurrencyConversionEvent) {
        val obj = event.source
        val str = StringBuilder(nextLine)
        str.append(dashLine)
        str.append(nextLine)
        str.append(" Class: ${obj.javaClass.simpleName}")
        str.append(nextLine)
        str.append("Message: ${event.message}")
        str.append(nextLine)
        str.append(" Value: ${event.conversion}")
        str.append(nextLine)
        str.append(dashLine)
        log.error(str.toString())

        TODO("Not yet implemented")
    }
}