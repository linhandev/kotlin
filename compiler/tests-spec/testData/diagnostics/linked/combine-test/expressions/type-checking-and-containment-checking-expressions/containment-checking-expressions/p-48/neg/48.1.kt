// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: invoke operator convention does not substitute for contains in in-expression resolution
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun invoke(x: Int): Boolean = true
}

fun case1(x: Int): Boolean = x <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>in<!> Box()
