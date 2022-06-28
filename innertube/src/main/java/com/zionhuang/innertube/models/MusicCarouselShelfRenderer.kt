package com.zionhuang.innertube.models

import kotlinx.serialization.Serializable

@Serializable
data class MusicCarouselShelfRenderer(
    val header: Header,
    val contents: List<Content>,
    val itemSize: String,
    val numItemsPerColumn: Int?,
) {
    fun getViewType() = when {
        contents[0].musicTwoRowItemRenderer != null -> Section.ViewType.BLOCK
        contents[0].musicResponsiveListItemRenderer != null -> Section.ViewType.LIST
        contents[0].musicNavigationButtonRenderer != null -> Section.ViewType.LIST
        else -> Section.ViewType.LIST
    }

    @Serializable
    data class Header(
        val musicCarouselShelfBasicHeaderRenderer: MusicCarouselShelfBasicHeaderRenderer,
    ) {
        @Serializable
        data class MusicCarouselShelfBasicHeaderRenderer(
            val strapline: Runs?,
            val title: Runs,
            val thumbnail: ThumbnailRenderer?,
            val moreContentButton: Button?,
        )
    }

    @Serializable
    data class Content(
        val musicTwoRowItemRenderer: MusicTwoRowItemRenderer?,
        val musicResponsiveListItemRenderer: MusicResponsiveListItemRenderer?,
        val musicNavigationButtonRenderer: MusicNavigationButtonRenderer?, // navigation button in explore tab
    ) {
        fun toItem() = musicTwoRowItemRenderer?.toItem()
            ?: musicResponsiveListItemRenderer?.toItem()
            ?: musicNavigationButtonRenderer?.toItem()!!
    }
}
