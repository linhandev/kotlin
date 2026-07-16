// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: enumValues top-level function returns array of all enum constants
 */

// TESTCASE NUMBER: 1
enum class State { A, B }

fun case1() {
    val arr = enumValues<State>()
}
