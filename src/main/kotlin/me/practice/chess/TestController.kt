package me.practice.chess

import org.apache.catalina.connector.Connector
import org.apache.coyote.http2.Http2Protocol
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController {
    @GetMapping("test")
    fun test(): String {
        return "successful"
    }
}

@Bean
fun tomcatCustomizer(): ConfigurableServletWebServerFactory {
    val factory = TomcatServletWebServerFactory()
    factory.addConnectorCustomizers(TomcatConnectorCustomizer { connector: Connector ->
        connector.addUpgradeProtocol(
            Http2Protocol()
        )
    })
    return factory
}