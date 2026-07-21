// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: delegated val cannot use setValue-only delegate
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class SetOnly {
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {}
}

val readOnly: Int by <!DELEGATE_SPECIAL_FUNCTION_MISSING!>SetOnly()<!>
