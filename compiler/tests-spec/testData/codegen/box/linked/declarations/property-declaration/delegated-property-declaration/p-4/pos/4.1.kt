// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Map getValue and setValue operators delegate property access at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

operator fun <V, R : V> Map<in String, V>.getValue(thisRef: Any?, property: KProperty<*>): R =
    getOrElse(property.name) { throw NoSuchElementException(property.name) } as R

operator fun <V> MutableMap<in String, V>.setValue(thisRef: Any?, property: KProperty<*>, newValue: V) {
    set(property.name, newValue)
}

fun handleConfig(config: MutableMap<String, Any?>): String {
    val parent by config
    val host: String by config
    var port: Int by config

    port = 443

    return "$parent: going to $host:$port"
}

fun box(): String {
    val result = handleConfig(
        mutableMapOf(
            "parent" to "",
            "host" to "https://kotlinlang.org/",
        ),
    )
    return if (result == ": going to https://kotlinlang.org/:443") "OK" else "NOK result=$result"
}
