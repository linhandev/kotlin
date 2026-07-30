// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                type-inference, local-type-inference -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: custom get return type matches declared String
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun get(i: Int): String = "x"
}

fun test(): String = Box()[0]

fun box(): String {
    if (test() != "x") return "NOK"
    return "OK"
}
