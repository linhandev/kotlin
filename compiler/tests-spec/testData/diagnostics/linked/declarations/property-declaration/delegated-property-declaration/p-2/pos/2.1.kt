// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: delegated mutable properties use getValue and setValue operators
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class MutableDelegate {
    private var storage = 0

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = storage

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        storage = value
    }
}

var delegatedMutable: Int by MutableDelegate()

// TESTCASE NUMBER: 2
class ObservableDelegate {
    var current = 0

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = current

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        current = value
    }
}

var observed: Int by ObservableDelegate()
