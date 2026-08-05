// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: in operator fails when receiver type has no applicable contains convention
 */

// TESTCASE NUMBER: 1
class Box

fun test(x: Int): Boolean = x <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>in<!> Box()
