// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 23 -> sentence 23
 *                expressions, function-literals, lambda-literals -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: callable reference String::length and lambda { it.length } are interchangeable as (String) -> Int arguments, verifying runtime semantics
 */

fun apply(f: (String) -> Int, s: String): Int = f(s)

// TESTCASE NUMBER: 1
fun test1(): Int = apply(String::length, "abc")

// TESTCASE NUMBER: 2
fun test2(): Int = apply({ it.length }, "abc")

fun box(): String {
    if (test1() != 3) return "NOK1"
    if (test2() != 3) return "NOK2"
    if (apply(String::length, "hello") != 5) return "NOK3"
    if (apply({ it.length }, "hello") != 5) return "NOK4"
    return "OK"
}
