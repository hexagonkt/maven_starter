package org.example

import com.hexagonkt.helpers.logger
import com.hexagonkt.http.server.*
import com.hexagonkt.http.server.jetty.JettyServletAdapter
import com.hexagonkt.injection.InjectionManager

internal val injector: InjectionManager = InjectionManager.apply {
    bindObject<ServerPort>(JettyServletAdapter())
}

internal val server: Server = Server {
    before {
        response.headers["Server"] = "Servlet/3.1"
    }

    get("/text") {
        ok("Hello, World!", "text/plain")
    }
}

internal fun main() {
    logger.info { injector }
    server.start()
}
