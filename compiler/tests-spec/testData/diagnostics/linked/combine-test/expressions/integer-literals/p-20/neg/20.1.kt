// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 20 -> sentence 20
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 20 -> sentence 20
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: Int separator literal inferred as Int cannot be assigned to Long variable without conversion even beside valid Long separator literal
 */

// TESTCASE NUMBER: 1
fun case1(): Long {
    val valid: Long = 1_000L
    val i: Int = 1_000
    val x: Long = <!TYPE_MISMATCH!>i<!>
    return valid
}
