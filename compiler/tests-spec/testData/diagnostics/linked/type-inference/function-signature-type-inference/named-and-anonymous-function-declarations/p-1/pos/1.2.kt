// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, named-and-anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: expression body — declared return type constrains generic call in foo/bar example
 * HELPERS: checkType
 */

fun <T> foo1431(): T = null!!

fun bar1431(): Int = foo1431()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(bar1431())
}
