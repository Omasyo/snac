package com.keetr.snac.core.ui.utils

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.theme.SnacTheme

@Preview
@Composable
fun Preview() {
    SnacTheme {
        InlineText(style = MaterialTheme.typography.bodyLarge) {
            append(SnacIcons.Star)
            append("First")
            append(SnacIcons.People)
            append("Secont")
        }
    }
}

@Composable
fun InlineText(
    modifier: Modifier = Modifier,
    style: TextStyle,
    exec: @Composable InlineText.() -> Unit
) {
    val inlineText = InlineText()
    inlineText.exec()

    val inlineContentMap = inlineText.icons.associate { icon ->
        val (content, description) = icon
        val inlineTextContent = when (content) {
            is ImageVector -> buildInlineContent(style) {
                Icon(content, contentDescription = description)
            }

            is Painter -> buildInlineContent(style) {
                Icon(content, contentDescription = description, tint = Color.Unspecified)
            }
            else -> throw Exception()
        }
        Pair(content.toString(), inlineTextContent)
    }

    val text = buildAnnotatedString {
        for (content in inlineText.contents) {
            when (content) {
                is String -> append(content)
                is ImageVector -> appendInlineContent(content.toString(), content.name)
                is Painter -> appendInlineContent(content.toString(), content.toString())
            }
        }
    }

    Text(text, inlineContent = inlineContentMap, style = style, modifier = modifier)
}

@Composable
private fun buildInlineContent(textStyle: TextStyle, content: @Composable (String) -> Unit) =
    InlineTextContent(
        with(textStyle) {
            Placeholder(
                width = fontSize,
                height = fontSize,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        },
        children = content
    )


class InlineText internal constructor() {
//    interface InlineTextContent
//    class Text(val value: String) : InlineTextContent
//    class Vector(val value: ImageVector) : InlineTextContent
//    class InlinePainter(val value: Painter): InlineTextContent

    val contents = mutableListOf<Any>()
    val icons = mutableSetOf<Pair<Any, String?>>()
}

fun InlineText.append(text: String) { contents.add(text) }

fun InlineText.append(icon: ImageVector, contentDescription: String? = null) {
    contents.add(icon)
    icons.add(Pair(icon, contentDescription))
}

fun InlineText.append(icon: Painter, contentDescription: String? = null) {
    contents.add(icon)
    icons.add(Pair(icon, contentDescription))
}