package com.makentoshe.booruchan.extension.lolibooru

import com.makentoshe.booruchan.extension.base.entity.NetworkAutocomplete
import com.makentoshe.booruchan.extension.base.entity.NetworkPost
import com.makentoshe.booruchan.extension.base.factory.AutocompleteSearchFactory
import com.makentoshe.booruchan.extension.base.network.NetworkMethod
import com.makentoshe.booruchan.extension.base.network.NetworkRequest
import com.makentoshe.booruchan.extension.base.parser.AutocompleteSearchParser
import com.makentoshe.booruchan.extension.lolibooru.entity.NetworkLolibooruAutocompletes
import com.makentoshe.booruchan.extension.lolibooru.entity.NetworkLolibooruPosts
import kotlinx.serialization.json.Json

class LolibooruAutocompleteSearchFactory(
    private val host: String,
    autocompleteSearchParser: AutocompleteSearchParser,
) : AutocompleteSearchFactory(autocompleteSearchParser) {
    override fun buildRequest(request: AutocompleteSearchRequest): NetworkRequest {
        return NetworkRequest(
            method = NetworkMethod.Get,
            url = host.plus("/tag/index.json"),
            parameters = mapOf(
                "limit" to "10",
                "order" to "count",
                "page" to "0",
                "name" to "${request.autocomplete}*",
            )
        )
    }
}

class LolibooruAutocompleteSearchParser : AutocompleteSearchParser {

    private val json = Json { ignoreUnknownKeys = true }

    override fun parse(string: String): List<NetworkAutocomplete> {
        return json.decodeFromString<NetworkLolibooruAutocompletes>(string)
    }
}
