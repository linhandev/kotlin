// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 14 -> sentence 14
 *                inheritance, inheriting -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: subclass fixes parent type argument to Int
 */

// TESTCASE NUMBER: 1
open class Box<T>(val v: T)

class IntBox : Box<Int>(1)

fun test(): Int = IntBox().v

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
