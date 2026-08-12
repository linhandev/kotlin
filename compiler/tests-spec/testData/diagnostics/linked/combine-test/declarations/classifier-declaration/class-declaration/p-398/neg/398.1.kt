// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 398 -> sentence 398
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 398 -> sentence 398
 *                declarations, function-declaration -> paragraph 398 -> sentence 398
 * NUMBER: 1
 * DESCRIPTION: private operator fun cannot be invoked from outside class
 */

// TESTCASE NUMBER: 1
class Vec(val x: Int) { private operator fun plus(o: Vec): Vec = Vec(x + o.x) }
fun test() = <!INVISIBLE_MEMBER!>Vec(1) + Vec(2)<!>
