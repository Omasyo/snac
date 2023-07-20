package com.keetr.snac.core.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SnacClapper() {
    val transition = rememberInfiniteTransition()

    val imageVector = ImageVector.Builder(
        defaultWidth = 48.dp,
        defaultHeight = 48.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {

        val topRotation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1000


                },
                repeatMode = RepeatMode.Reverse
            )
        )

        group(
            "top"
        ) {
            path(
                stroke = SolidColor(MaterialTheme.colorScheme.primary),
                strokeLineWidth = 2f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 5f)
                lineToRelative(0f, 6f)
                lineToRelative(18f, 0f)
                lineToRelative(0f, -6f)
                close()
                moveTo(4f, 5f)
                repeat(4) {
                    moveToRelative(0f, 6f)
                    lineToRelative(4f, -6f)
                }
            }
        }
        path(
            "bottom",
            stroke = SolidColor(MaterialTheme.colorScheme.primary),
            strokeLineWidth = 2f,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(3f, 11f)
            lineToRelative(0f, 8f)
            lineToRelative(18f, 0f)
            lineToRelative(0f, -8f)
            close()
        }

    }.build()

    Image(imageVector = imageVector, contentDescription = null)

}