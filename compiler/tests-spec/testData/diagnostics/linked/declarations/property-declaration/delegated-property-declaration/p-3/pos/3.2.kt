// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: local delegated property uses provideDelegate operator before getValue
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class Box(val value: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = value
}

class Factory {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): Box = Box("local")
}

fun localProvided(): String {
    val x: String by Factory()
    return x
}
