// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unbound member function reference String::length stored as (String) -> Int and invoked with receiver argument, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
val f: (String) -> Int = String::length
fun test(s: String): Int = f(s)

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
