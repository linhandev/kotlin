// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: multi type-parameter class construction
 */

// TESTCASE NUMBER: 1
class PairBox<A, B>(val first: A, val second: B)

fun test(): PairBox<Int, String> = PairBox(1, "a")

fun box(): String {
    if (test().first != 1 || test().second != "a") return "NOK"
    return "OK"
}
