package com.makentoshe.booruchan.extension.lolibooru

import com.makentoshe.booruchan.extension.base.factory.HealthCheckFactory
import com.makentoshe.booruchan.extension.base.network.NetworkMethod
import com.makentoshe.booruchan.extension.base.network.NetworkRequest

class LolibooruHealthCheckFactory(
    private val host: String,
) : HealthCheckFactory() {
    override fun buildRequest(): NetworkRequest {
        return NetworkRequest(method = NetworkMethod.Head, url = host)
    }
}