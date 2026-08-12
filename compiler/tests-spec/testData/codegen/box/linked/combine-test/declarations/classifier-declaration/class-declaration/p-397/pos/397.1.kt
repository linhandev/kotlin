// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 397 -> sentence 397
 * declarations, declaration-visibility -> paragraph 397 -> sentence 397
 * declarations, function-declaration -> paragraph 397 -> sentence 397
 * operator-overloading, overview -> paragraph 397 -> sentence 397
 * NUMBER: 1
 * DESCRIPTION: private operator fun can be called only inside class
 */

// TESTCASE NUMBER: 1
class Vec(val x: Int) { private operator fun plus(o: Vec): Vec = Vec(x + o.x); fun add(o: Vec): Vec = this + o }

// TESTCASE NUMBER: 1
fun test(): Int = Vec(1).add(Vec(2)).x

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
