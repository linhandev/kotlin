// FIR_IDENTICAL
// LANGUAGE: +ProhibitTypeParametersForLocalVariables
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: top-level and member non-extension properties and local variable cannot declare type parameters
 */

// TESTCASE NUMBER: 1
val <<!TYPE_PARAMETER_OF_PROPERTY_NOT_USED_IN_RECEIVER!>T<!>> nonExtensionProperty: Int
    get() = 1

// TESTCASE NUMBER: 2
class MemberHost {
    val <<!TYPE_PARAMETER_OF_PROPERTY_NOT_USED_IN_RECEIVER!>T<!>> memberProperty: Int
        get() = 2
}

// TESTCASE NUMBER: 3
fun localTypeParameter() {
    val <!LOCAL_VARIABLE_WITH_TYPE_PARAMETERS!><T><!> localProperty = 0
}
