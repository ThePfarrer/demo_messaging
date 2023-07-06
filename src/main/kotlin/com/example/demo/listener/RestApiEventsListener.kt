package com.example.demo.listener

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.context.support.ServletRequestHandledEvent

@Component
class RestApiEventsListener: ApplicationListener<ApplicationEvent> {
    val latest: String = "/currency/latest"

//    @Autowired
//    lateinit var counterService: CounterServ

//    @Log()
    override fun onApplicationEvent(event: ApplicationEvent) {
        if (event is ServletRequestHandledEvent){

        }
    }
}