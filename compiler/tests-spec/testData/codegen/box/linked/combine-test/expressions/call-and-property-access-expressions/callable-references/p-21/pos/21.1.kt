// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: unbound member reference J::m infers (J) -> Int and is invocable with a receiver argument, verifying runtime semantics
 */

class J { fun m(): Int = 1 }

val f: (J) -> Int = J::m

// TESTCASE NUMBER: 1
fun test(j: J): Int = f(j)

fun box(): String {
    if (test(J()) != 1) return "NOK"
    if (f(J()) != 1) return "NOK"
    return "OK"
}
