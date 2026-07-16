// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: delegated properties may use provideDelegate before getValue and setValue
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class ProvidedDelegate(private val value: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = value
}

class Provider {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ProvidedDelegate =
        ProvidedDelegate("provided")
}

val providedReadOnly: String by Provider()

// TESTCASE NUMBER: 2
class MutableProvided(private var storage: Int) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = storage

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        storage = value
    }
}

class MutableProvider {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): MutableProvided =
        MutableProvided(0)
}

var providedMutable: Int by MutableProvider()
