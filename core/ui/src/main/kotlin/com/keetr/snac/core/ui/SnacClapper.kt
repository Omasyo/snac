package com.keetr.snac.core.ui

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.AnticipateOvershootInterpolator

@Preview
@Composable
fun SnacClapper(modifier: Modifier = Modifier) {

    val duration = 1500
    val transition = rememberInfiniteTransition()

    val imageVector = ImageVector.Builder(
        defaultWidth = 48.dp,
        defaultHeight = 48.dp,
        viewportWidth = 48f,
        viewportHeight = 48f
    ).apply {

        val rotation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = duration
                    0f at 0 with FastOutSlowInEasing
                    -30f at 200 with AnticipateOvershootEasing
                    15f at 900 with DecelerateEasing
                    0f at 1100

                },
                repeatMode = RepeatMode.Restart
            )
        )
        val translationX = transition.animateFloat(
            initialValue = 0f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = duration
                    0f at 0 with FastOutSlowInEasing
                    -1f at 200 with FastOutSlowInEasing
                    0f at 700
                },
                repeatMode = RepeatMode.Restart
            )
        )
        val translationY = transition.animateFloat(
            initialValue = 0f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = duration
                    0f at 0 with FastOutSlowInEasing
                    -1f at 200 with FastOutSlowInEasing
                    0f at 700
                },
                repeatMode = RepeatMode.Restart
            )
        )

        group(
            rotate = rotation.value,
            translationX = translationX.value,
            translationY = translationY.value,
            pivotX = 15f,
            pivotY = 23f
        ) {

            val topRotation = transition.animateFloat(
                initialValue = 0f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = duration
                        0f at 0 with FastOutSlowInEasing
                        -30f at 200 with BounceEasing
                        0f at 900

                    },
                    repeatMode = RepeatMode.Restart
                )
            )

            group(
                "top",
                rotate = topRotation.value,
                pivotX = 15f,
                pivotY = 23f
            ) {
                path(
                    stroke = SolidColor(MaterialTheme.colorScheme.primary),
                    strokeLineWidth = 2f,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(15f, 17f)
                    lineToRelative(0f, 6f)
                    lineToRelative(18f, 0f)
                    lineToRelative(0f, -6f)
                    close()
                    moveTo(16f, 17f)
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
                moveTo(15f, 23f)
                lineToRelative(0f, 8f)
                lineToRelative(18f, 0f)
                lineToRelative(0f, -8f)
                close()
            }
        }

    }.build()

    Image(
        imageVector = imageVector,
        contentDescription = null,
        modifier = modifier
    )

}