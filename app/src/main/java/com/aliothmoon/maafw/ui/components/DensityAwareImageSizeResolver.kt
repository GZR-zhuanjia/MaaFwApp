package com.aliothmoon.maafw.ui.components

import io.noties.markwon.image.ImageSize
import io.noties.markwon.image.ImageSizeResolverDef
import kotlin.math.roundToInt

/** Markwon 默认把 HTML 的 px / 无单位尺寸当物理像素；改用 Compose 密度，避免高密度屏上图标过小。 */
internal class DensityAwareImageSizeResolver(private val density: Float) : ImageSizeResolverDef() {

    override fun resolveAbsolute(dimension: ImageSize.Dimension, original: Int, textSize: Float): Int =
        when (dimension.unit) {
            null, "px" -> (dimension.value * density).roundToInt()
            // em 已按物理字号换算；百分比和未声明尺寸的图片继续交给 Markwon 处理。
            else -> super.resolveAbsolute(dimension, original, textSize)
        }
}
