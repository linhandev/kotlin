// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: provideDelegate checks keys before getValue and setValue are used
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

operator fun <V> MutableMap<in String, V>.provideDelegate(thisRef: Any?, property: KProperty<*>): MutableMap<in String, V> =
    if (containsKey(property.name)) this else throw NoSuchElementException(property.name)

operator fun <V, R : V> Map<in String, V>.getValue(thisRef: Any?, property: KProperty<*>): R =
    getOrElse(property.name) { throw NoSuchElementException(property.name) } as R

fun readExisting(config: MutableMap<String, Any?>): String {
    val host: String by config
    return host
}

fun box(): String {
    val config = mutableMapOf<String, Any?>("host" to "kotlin")
    val ok = readExisting(config)

    val missing = try {
        readExisting(mutableMapOf<String, Any?>())
        "unexpected"
    } catch (_: NoSuchElementException) {
        "missing"
    }

    return if (ok == "kotlin" && missing == "missing") "OK" else "NOK ok=$ok missing=$missing"
}
