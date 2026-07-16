// WITH_STDLIB

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 64 -> sentence 64
 * NUMBER: 3
 * DESCRIPTION: DELEGATE token on custom ReadOnlyProperty delegated property
 */
// TESTCASE NUMBER: 1

private object OkDelegate64 : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String = "kw-64-64-3"
}

class DelegateCustom64 {
    @delegate:Suppress("UNUSED")
    val token: String by OkDelegate64
}

fun box(): String {
    val expected = "kw-64-64-3"
    val result = DelegateCustom64().token
    if (result != expected) return "NOK"
    return "OK"
}
