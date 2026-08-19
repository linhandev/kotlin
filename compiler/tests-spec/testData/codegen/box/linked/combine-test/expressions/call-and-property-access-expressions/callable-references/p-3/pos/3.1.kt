// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 3 -> sentence 3
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: bound member function reference s::length typed as () -> Int and invoked with no arguments, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(s: String): Int {
    val f: () -> Int = s::length
    return f()
}

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    if (test("abc") != 3) return "NOK"
    return "OK"
}
