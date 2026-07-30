// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 23 -> sentence 23
 *                declarations, property-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: object implementing interface properties initializes them in the declaration
 */

// TESTCASE NUMBER: 1
interface S {
    val v: Int
}

object O : S {
    override val v: Int = 1
}

fun test(): Int = O.v

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
