// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: delegated var with vetoable pattern
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class Vetoable(private var storage: Int, private val onChange: (Int, Int) -> Boolean) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = storage
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (onChange(storage, value)) storage = value
    }
}

var counter: Int by Vetoable(0) { _, new -> new >= 0 }
