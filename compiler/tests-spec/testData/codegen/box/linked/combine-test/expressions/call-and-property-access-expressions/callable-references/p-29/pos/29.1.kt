// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: bound callable reference on parenthesized expression result (s.uppercase())::length creates () -> Int, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(s: String): Int {
    val f: () -> Int = (s.uppercase())::length
    return f()
}

fun box(): String {
    if (test("hello") != 5) return "NOK1"
    if (test("abc") != 3) return "NOK2"
    return "OK"
}
