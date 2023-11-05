package com.makentoshe.booruchan.extension.lolibooru

import com.makentoshe.booruchan.extension.base.entity.NetworkPost
import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.extension.base.network.NetworkMethod
import com.makentoshe.booruchan.extension.base.network.NetworkRequest
import com.makentoshe.booruchan.extension.base.parser.FetchPostsParser
import com.makentoshe.booruchan.extension.lolibooru.entity.NetworkLolibooruPosts
import kotlinx.serialization.json.Json

class LolibooruFetchPostsFactory(
    private val host: String,
    fetchPostsParser: FetchPostsParser,
) : FetchPostsFactory(fetchPostsParser) {
    override fun buildRequest(request: FetchPostsRequest): NetworkRequest {
        return NetworkRequest(
            method = NetworkMethod.Get,
            url = "$host/post/index.json",
            parameters = mapOf(
                "limit" to request.count.toString(),
                "page" to request.page.toString(),
                "tags" to request.tags,
            )
        )
    }
}

class LolibooruFetchPostsParser : FetchPostsParser {

    private val json = Json { ignoreUnknownKeys = true }

    override fun parse(string: String): List<NetworkPost> {
        return json.decodeFromString<NetworkLolibooruPosts>(string)
    }
}
