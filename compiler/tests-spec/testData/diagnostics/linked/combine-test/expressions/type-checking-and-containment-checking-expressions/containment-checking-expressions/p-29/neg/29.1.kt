// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 29 -> sentence 29
 *                declarations, classifier-declaration, companion-object -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: companion object non-operator contains does not participate in in-expression resolution
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun contains(x: Int): Boolean = true
    }
}

fun case1(x: Int): Boolean = x <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>in<!> Box()
