// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 20 -> sentence 20
 *                declarations, declarations-with-type-parameters -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: generic data class component1 preserves type argument
 */

// TESTCASE NUMBER: 1
data class Box<T>(val v: T)

fun test(): String {
    val (x) = Box("a")
    return x
}

fun box(): String {
    if (test() != "a") return "NOK"
    return "OK"
}
