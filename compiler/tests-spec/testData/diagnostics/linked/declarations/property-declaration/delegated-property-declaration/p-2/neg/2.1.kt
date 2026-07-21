// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: delegated mutable properties require suitable setValue operator
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class ReadOnlyDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = 1
}

var missingSetter: Int by <!DELEGATE_SPECIAL_FUNCTION_MISSING!>ReadOnlyDelegate()<!>

// TESTCASE NUMBER: 2
class WrongSetter {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = 1

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {}
}

var wrongSetterType: Int by <!DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE!>WrongSetter()<!>
