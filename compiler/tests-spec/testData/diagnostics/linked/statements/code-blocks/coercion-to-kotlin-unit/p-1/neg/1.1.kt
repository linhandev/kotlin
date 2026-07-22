// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks, coercion-to-kotlin-unit -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: { work() } assigned to () -> Int reports TYPE_MISMATCH because work() returns Unit
 */

fun work() {}

// TESTCASE NUMBER: 1
fun case1() {
    val f: () -> Int = { <!TYPE_MISMATCH!>work()<!> }
}
