// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: where constrains two type parameters independently
 */

// TESTCASE NUMBER: 1
class Mapper<A, B> where A : CharSequence, B : Comparable<B> { fun map(a: A, b: B): String = a.toString() + b }

fun test(): String = Mapper<String, Int>().map("a", 1)

fun box(): String {
    if (test() != "a1") return "NOK"
    return "OK"
}
