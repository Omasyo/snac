package com.keetr.snac.core.ui

import androidx.compose.animation.core.Easing

object BounceEasing : Easing {
    override fun transform(fraction: Float): Float {
        val t = fraction * 1.1226f
        return if (t < 0.3535f) bounce(t)
        else if (t < 0.7408f) bounce(t - 0.54719f) + 0.7f
        else if (t < 0.9644f) bounce(t - 0.8526f) + 0.9f
        else bounce(t - 1.0435f) + 0.95f
    }

    private fun bounce(t: Float): Float {
        return t * t * 8.0f
    }
}

object AnticipateOvershootEasing : Easing {
    private const val s = 2.0f * 1.5f

    override fun transform(fraction: Float): Float {
        return if (fraction < 0.5f) 0.5f * a(fraction * 2.0f) else 0.5f * (o(
            fraction * 2.0f - 2.0f
        ) + 2.0f)
    }

    private fun a(t: Float): Float {
        return t * t * ((s + 1) * t - s)
    }

    private fun o(t: Float): Float {
        return t * t * ((s + 1) * t + s)
    }

}

object DecelerateEasing : Easing {
    override fun transform(fraction: Float): Float {
        return (1.0f - (1.0f - fraction) * (1.0f - fraction))
    }

}