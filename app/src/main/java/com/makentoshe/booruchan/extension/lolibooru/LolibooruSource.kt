package com.makentoshe.booruchan.extension.lolibooru

import com.makentoshe.booruchan.extension.base.Source
import com.makentoshe.booruchan.extension.base.factory.AutocompleteSearchFactory
import com.makentoshe.booruchan.extension.base.factory.FetchPostsFactory
import com.makentoshe.booruchan.extension.base.factory.HealthCheckFactory
import com.makentoshe.booruchan.extension.base.settings.SourceSearchSettings
import com.makentoshe.booruchan.extension.base.settings.SourceSettings

class LolibooruSource : Source {

    override val id: String
        get() = "lolibooru"

    override val host: String
        get() = "https://lolibooru.moe"

    override val title: String
        get() = "Lolibooru"

    override val settings: SourceSettings
        get() = SourceSettings(
            searchSettings = SourceSearchSettings(
                initialPageNumber = 1,
            ),
        )

    override val healthCheckFactory: HealthCheckFactory
        get() = LolibooruHealthCheckFactory(
            host = host,
        )

    override val fetchPostsFactory: FetchPostsFactory
        get() = LolibooruFetchPostsFactory(
            host = host,
            fetchPostsParser = LolibooruFetchPostsParser(),
            sourceSettings = settings,
        )

    override val autocompleteSearchFactory: AutocompleteSearchFactory
        get() = LolibooruAutocompleteSearchFactory(
            host = host,
            autocompleteSearchParser = LolibooruAutocompleteSearchParser(),
            sourceSettings = settings,
        )

}
