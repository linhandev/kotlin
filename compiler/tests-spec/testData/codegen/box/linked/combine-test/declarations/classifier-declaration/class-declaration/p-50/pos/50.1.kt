// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 50 -> sentence 50
 *                inheritance, inheriting -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: subclass may tighten parent type-parameter upper bound
 */

// TESTCASE NUMBER: 1
open class Base<T>

class Child<T : Number> : Base<T>()

fun test(): Base<Int> = Child<Int>()

fun box(): String {
    if (test() == null) return "NOK"
    return "OK"
}
