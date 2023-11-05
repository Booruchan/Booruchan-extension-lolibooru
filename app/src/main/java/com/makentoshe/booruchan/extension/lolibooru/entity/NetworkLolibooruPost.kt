package com.makentoshe.booruchan.extension.lolibooru.entity

import com.makentoshe.booruchan.extension.base.entity.NetworkPost
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias NetworkLolibooruPosts = ArrayList<NetworkLolibooruPost>

@Serializable
data class NetworkLolibooruPost(
    @SerialName("id")
    override val id: Int,
    @SerialName("preview_url")
    override val previewImageUrl: String,
    @SerialName("preview_height")
    override val previewImageHeight: Int,
    @SerialName("preview_width")
    override val previewImageWidth: Int,

    @SerialName("tags")
    private val tagsString: String,
) : NetworkPost {

    override val tags: List<String>
        get() = tagsString.split(" ")
}
