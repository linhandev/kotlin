// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: top-level function reference ::inc infers function type (Int) -> Int and is invocable, verifying runtime semantics
 */

fun inc(x: Int): Int = x + 1

val f: (Int) -> Int = ::inc

// TESTCASE NUMBER: 1
fun test(): Int = f(1)

fun box(): String {
    if (test() != 2) return "NOK"
    if (f(5) != 6) return "NOK"
    if (f(-1) != 0) return "NOK"
    return "OK"
}
