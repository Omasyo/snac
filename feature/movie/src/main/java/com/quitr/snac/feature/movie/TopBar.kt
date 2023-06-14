package com.quitr.snac.feature.movie

import android.util.Log
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.core.math.MathUtils
import kotlin.math.roundToInt

@Composable
fun rememberTopBarState(
    initialHeightOffsetLimit: Float = -Float.MAX_VALUE,
    initialHeightOffset: Float = 0f
) = rememberSaveable(saver = TopBarState.Saver) {
    TopBarState(
        initialHeightOffsetLimit,
        initialHeightOffset
    )
}

interface TopBarScrollBehavior {
    val state: TopBarState

    //    val isPinned: Boolean
    val snapAnimationSpec: AnimationSpec<Float>?
    val flingAnimationSpec: DecayAnimationSpec<Float>?
    val nestedScrollConnection: NestedScrollConnection
}

@Stable
class TopBarState(
    initialHeightOffsetLimit: Float,
    initialHeightOffset: Float,
) {
    var heightOffsetLimit by mutableStateOf(initialHeightOffsetLimit)

    var heightOffset: Float
        get() = _heightOffset.value
        set(newOffset) {
            _heightOffset.value = newOffset//.coerceIn(heightOffsetLimit, 0f)
        }

    val collapsedFraction: Float
        get() = if (heightOffset != 0f) {
            heightOffset / heightOffsetLimit
        } else {
            0f
        }

    companion object {
        val Saver: Saver<TopBarState, *> = listSaver(
            save = { listOf(it.heightOffsetLimit, it.heightOffset) },
            restore = {
                TopBarState(
                    initialHeightOffsetLimit = it[0],
                    initialHeightOffset = it[1]
                )
            }
        )
    }

    private var _heightOffset = mutableStateOf(initialHeightOffset)

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String = "Example String",
    scrollBehavior: TopBarScrollBehavior = defaultScrollBehavior()
) {
    Box(modifier) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Green)
                .offset {
                    IntOffset(0, scrollBehavior.state.heightOffset.roundToInt())
                }
        )
        TopAppBar( title = { Text("My Top App Bar") })
    }
}

@Composable
fun defaultScrollBehavior(
    state: TopBarState = rememberTopBarState(),
    snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
    flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
): TopBarScrollBehavior = DefaultScrollBehavior(
    state, snapAnimationSpec, flingAnimationSpec
)

private class DefaultScrollBehavior(
    override val state: TopBarState,
    override val snapAnimationSpec: AnimationSpec<Float>?,
    override val flingAnimationSpec: DecayAnimationSpec<Float>?,
) : TopBarScrollBehavior {
    override val nestedScrollConnection =
        object : NestedScrollConnection {
            fun onScroll(available: Offset): Offset {
                val newOffset = state.heightOffset + available.y
                state.heightOffset = MathUtils.clamp(newOffset, 500f, 0f)
                val excess = newOffset - state.heightOffset

                return Offset(0f, available.y - excess)
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                Log.d("TAG", "onPreScroll: available $available")
                return if (available.y > 0) Offset.Zero else onScroll(available)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                Log.d("TAG", "onPostScroll: available $available source $source offset ${state.heightOffset}")
                return if (available.y < 0) Offset.Zero else onScroll(available)
            }
        }
}