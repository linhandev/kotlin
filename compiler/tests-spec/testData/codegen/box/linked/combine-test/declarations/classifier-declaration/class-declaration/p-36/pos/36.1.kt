// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: where clause multiple upper bounds CharSequence and Comparable
 */

// TESTCASE NUMBER: 1
class TextRepo<T> where T : CharSequence, T : Comparable<T> { fun ok(x: T): Boolean = x.length > 0 && x >= x }

fun test(): Boolean = TextRepo<String>().ok("a")

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
