// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: unbound member reference J::m infers (J) -> Int and is assignable to matching variable, verifying type inference
 * HELPERS: checkType
 */

class J { fun m(): Int = 1 }

// TESTCASE NUMBER: 1
fun case1() {
    val f: (J) -> Int = J::m
    checkSubtype<(J) -> Int>(f)
}
