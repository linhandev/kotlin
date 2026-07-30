// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 15 -> sentence 15
 *                inheritance, inheriting -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: subclass forwards type parameter to parent
 */

// TESTCASE NUMBER: 1
open class Box<T>(val v: T)

class Child<T>(v: T) : Box<T>(v)

fun test(): String = Child("a").v

fun box(): String {
    if (test() != "a") return "NOK"
    return "OK"
}
