// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 27 -> sentence 27
 *                type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                type-inference, smart-casts -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: after smart cast via requireNotNull, bound member reference s::length can be created on non-null String, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Int {
    requireNotNull(s)
    val f: () -> Int = s::length
    return f()
}

fun box(): String {
    if (test("hello") != 5) return "NOK1"
    if (test("abc") != 3) return "NOK2"
    return "OK"
}
