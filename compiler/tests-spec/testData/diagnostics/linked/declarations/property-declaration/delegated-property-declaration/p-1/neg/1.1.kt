// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: delegated read-only properties require suitable getValue operator
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class MissingGetValue

val badDelegate: Int by <!DELEGATE_SPECIAL_FUNCTION_MISSING!>MissingGetValue()<!>

// TESTCASE NUMBER: 2
class WrongReturnType {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "wrong"
}

val wrongType: Int by <!DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE!>WrongReturnType()<!>
