package com.keetr.snac.core.model

abstract class NavigationRoute(val format: String) {
    protected open val requiredArguments: List<String> = listOf()
    protected open val optionalArguments: List<String> = listOf()
//    protected abstract val root: String

//    val arguments get() = requiredArguments + optionalArguments


    open val route: String get() = format.format(*(
            requiredArguments + optionalArguments
            ).map { "{$it}" } .toTypedArray())
//        buildString {
//        append(root)
//        requiredArguments.forEach { argument ->
//            append("/{$argument}")
//        }
//        if (optionalArguments.isEmpty()) return@buildString
//        append("?")
//        val optional = optionalArguments.joinToString("&") { "$it={$it}" }
//        append(optional)
//    }

    open fun route(vararg args: Any) = format.format(*args)

//    open fun route(args: Map<String, Any>) = buildString {
//        append(root)
//        requiredArguments.forEach { argument ->
//            append("/${args[argument]}")
//        }
//        append("?")
//        val used = optionalArguments.filter { args.containsKey(it) }
//        val optional = used.joinToString("&") { "$it=${it}" }
//        append(optional)
//    }
}