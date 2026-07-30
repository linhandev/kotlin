// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 27 -> sentence 27
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: anonymous object cannot extend a sealed class
 */

// TESTCASE NUMBER: 1
sealed class Expr

fun case_1() {
    val anon = object : <!SEALED_SUPERTYPE!>Expr<!>() {}
}
