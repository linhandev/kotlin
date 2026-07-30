// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: bound member reference s::length infers type () -> Int and captures receiver, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val s = "hi"
    val f = s::length
    checkSubtype<() -> Int>(f)
}
