// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: upper-bounded type parameter flows through class methods
 */

// TESTCASE NUMBER: 1
class Pipe<T : CharSequence>(val v: T) { fun copy(): T = v }

fun test(): CharSequence = Pipe("x").copy()

fun box(): String {
    if (test() != "x") return "NOK"
    return "OK"
}
