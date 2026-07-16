// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: provideDelegate operator must have correct signature; missing or inapplicable operator is rejected
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class BadProvider {
    fun provideDelegate(thisRef: Any?, property: KProperty<*>): Int = 1
}

val missingOperator: Int by <!DELEGATE_SPECIAL_FUNCTION_MISSING!>BadProvider()<!>

// TESTCASE NUMBER: 2
class WrongProvideDelegate {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun provideDelegate(): String = "bad"

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "ok"
}

val wrongSignature: String by <!DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE!>WrongProvideDelegate()<!>
