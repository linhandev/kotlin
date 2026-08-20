// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 397 -> sentence 397
 * declarations, declaration-visibility -> paragraph 397 -> sentence 397
 * declarations, function-declaration -> paragraph 397 -> sentence 397
 * operator-overloading, overview -> paragraph 397 -> sentence 397
 * NUMBER: 1
 * DESCRIPTION: private operator fun can be called only inside class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vec(val x: Int) { private operator fun plus(o: Vec): Vec = Vec(x + o.x); fun add(o: Vec): Vec = this + o }

// TESTCASE NUMBER: 1
fun test(): Int = Vec(1).add(Vec(2)).x

fun case1() {
    checkSubtype<Int>(test())
}
