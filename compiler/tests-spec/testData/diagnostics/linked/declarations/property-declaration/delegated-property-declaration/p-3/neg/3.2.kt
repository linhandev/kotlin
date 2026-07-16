// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: provideDelegate with wrong operator signature
 */

import kotlin.reflect.KProperty

// TESTCASE NUMBER: 1
class WrongProvideDelegate {
    <!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun provideDelegate(): String = "bad"

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "ok"
}

val wrongSignature: String by <!DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE!>WrongProvideDelegate()<!>
