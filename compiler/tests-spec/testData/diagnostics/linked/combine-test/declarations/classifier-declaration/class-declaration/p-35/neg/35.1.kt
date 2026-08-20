// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 35 -> sentence 35
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: unbounded T has no length member
 */

// TESTCASE NUMBER: 1
class AnyBox<T>(val v: T) { fun len(): Int = v.<!UNRESOLVED_REFERENCE!>length<!> }

fun test() = AnyBox("a")
