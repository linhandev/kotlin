// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: delegated read-only properties use getValue operator
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class ReadDelegate(private val value: Int) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = value
}

val delegatedReadOnly: Int by ReadDelegate(42)

// TESTCASE NUMBER: 2
val delegatedLazy: String by lazy { "lazy" }
