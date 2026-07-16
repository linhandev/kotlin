// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, named-and-anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: control structure body — without return type declaration function returns kotlin.Unit
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun blockBody1431(): Unit {
}

fun case_1() {
    checkSubtype<Unit>(blockBody1431())
}
