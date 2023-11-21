package com.makentoshe.booruchan.extension.lolibooru

import com.makentoshe.booruchan.extension.base.entity.NetworkAutocomplete
import com.makentoshe.booruchan.extension.base.factory.AutocompleteSearchFactory
import com.makentoshe.booruchan.extension.base.network.NetworkMethod
import com.makentoshe.booruchan.extension.base.network.NetworkRequest
import com.makentoshe.booruchan.extension.base.parser.AutocompleteSearchParser
import com.makentoshe.booruchan.extension.base.settings.SourceSettings
import com.makentoshe.booruchan.extension.lolibooru.entity.NetworkLolibooruAutocompletes
import kotlinx.serialization.json.Json

class LolibooruAutocompleteSearchFactory(
    private val host: String,
    autocompleteSearchParser: AutocompleteSearchParser,
    sourceSettings: SourceSettings,
) : AutocompleteSearchFactory(
    autocompleteSearchParser = autocompleteSearchParser,
    sourceSettings = sourceSettings,
) {
    private val tagCharacters = listOf(settings.searchTagAnd, settings.searchTagNot, settings.searchTagOr).map { it[0] }

    override fun buildRequest(request: AutocompleteSearchRequest): NetworkRequest {
        return NetworkRequest(
            method = NetworkMethod.Get,
            url = host.plus("/tag/index.json"),
            parameters = mapOf(
                "limit" to "10",
                "order" to "count",
                "page" to "0",
                "name" to "${trimTagCharacters(request.autocomplete)}*",
            )
        )
    }

    private fun trimTagCharacters(input: String): String {
        return dropFirstTagCharacters(dropLastTagCharacters(input))
    }

    private fun dropFirstTagCharacters(input: String): String {
        var startIndex = 0
        while (startIndex < input.length && input[startIndex] in tagCharacters) {
            startIndex++
        }
        return input.substring(startIndex)
    }

    private fun dropLastTagCharacters(input: String): String {
        var endIndex = input.length - 1
        while (endIndex >= 0 && input[endIndex] in tagCharacters) {
            endIndex--
        }
        return input.substring(0, endIndex + 1)
    }
}

class LolibooruAutocompleteSearchParser : AutocompleteSearchParser {

    private val json = Json { ignoreUnknownKeys = true }

    override fun parse(string: String): List<NetworkAutocomplete> {
        return json.decodeFromString<NetworkLolibooruAutocompletes>(string)
    }
}
