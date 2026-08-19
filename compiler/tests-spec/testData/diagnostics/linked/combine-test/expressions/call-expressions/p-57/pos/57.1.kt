// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 57 -> sentence 57
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 57 -> sentence 57
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: callable reference as function return value infers (String) -> Int, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): (String) -> Int = String::length

fun case_1() {
    val f = test()
    checkSubtype<Int>(f("abc"))
}
