// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 15 -> sentence 15
 *                declarations, declarations-with-type-parameters -> paragraph 15 -> sentence 15
 *                overload-resolution, resolving-callable-references -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: generic top-level function reference ::id disambiguated to id<Int> via expected function type (Int) -> Int, verifying runtime semantics
 */

fun <T> id(x: T): T = x

// TESTCASE NUMBER: 1
fun test1(): Int {
    val f: (Int) -> Int = ::id
    return f(42)
}

fun test2(): String {
    val f: (String) -> String = ::id
    return f("hello")
}

fun box(): String {
    if (test1() != 42) return "NOK"
    if (test2() != "hello") return "NOK"
    return "OK"
}
