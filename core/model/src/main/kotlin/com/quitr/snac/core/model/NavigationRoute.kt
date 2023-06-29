package com.quitr.snac.core.model

abstract class NavigationRoute {
    protected open val requiredArguments: List<String> = listOf()
    protected open val optionalArguments: List<String> = listOf()
    protected abstract val root: String

//    val arguments get() = requiredArguments + optionalArguments
    val route: String get() = buildString {
        append(root)
        requiredArguments.forEach { argument ->
            append("/{$argument}")
        }
        if (optionalArguments.isEmpty()) return@buildString
        append("?")
        val optional = optionalArguments.joinToString("&") { "$it={$it}" }
        append(optional)
    }

    fun route(args: Map<String, Any>) = buildString {
        append(root)
        requiredArguments.forEach { argument ->
            append("/${args[argument]}")
        }
        append("?")
        val used = optionalArguments.filter { args.containsKey(it) }
        val optional = used.joinToString("&") { "$it=${it}" }
        append(optional)
    }
}