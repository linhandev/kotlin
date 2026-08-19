// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 54 -> sentence 54
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: member where stacks with class-level Number upper bound
 */

// TESTCASE NUMBER: 1
class C<T : Number> { fun <U> combine(t: T, u: U): String where U : Comparable<U> = t.toString() + u.toString() }

fun test(): String = C<Int>().combine(1, 2)

fun box(): String {
    if (test() != "12") return "NOK"
    return "OK"
}
