// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: where clause multiple upper bounds
 */

// TESTCASE NUMBER: 1
class Repo<T> where T : CharSequence, T : Comparable<T> { fun len(x: T): Int = x.length }

fun test(): Int = Repo<String>().len("ab")

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
