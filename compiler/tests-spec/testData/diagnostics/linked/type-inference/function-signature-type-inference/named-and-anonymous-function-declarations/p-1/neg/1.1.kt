// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, named-and-anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: expression body — declared return type constrains body and reports mismatch
 * HELPERS: checkType
 */

fun int1431(): Int = 42

// TESTCASE NUMBER: 1
fun case_1(): String = <!TYPE_MISMATCH!>int1431()<!>
