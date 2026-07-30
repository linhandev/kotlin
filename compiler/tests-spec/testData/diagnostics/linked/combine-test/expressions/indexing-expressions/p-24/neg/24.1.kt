// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                type-inference, local-type-inference -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: index result String cannot be Int
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun get(i: Int): String = "x"
}

fun test(): Int = <!TYPE_MISMATCH!>Box()[0]<!>
