// WITH_STDLIB

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 2
 * DESCRIPTION: BY token in custom ReadOnlyProperty delegated property
 */
// TESTCASE NUMBER: 1

private object OkDelegate75 : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String = "kw-75-75-2"
}

class ByCustom75 {
    val label: String by OkDelegate75
}

fun box(): String {
    val expected = "kw-75-75-2"
    val result = ByCustom75().label
    if (result != expected) return "NOK"
    return "OK"
}
