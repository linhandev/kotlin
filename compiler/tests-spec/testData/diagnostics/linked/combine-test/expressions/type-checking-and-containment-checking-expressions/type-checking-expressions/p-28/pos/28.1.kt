// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: runtime-type-information, runtime-available-types -> paragraph 28 -> sentence 28
 *                type-system, introduction-1, type-kinds -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: star projection type List<*> is a runtime-available type — precise type inference for is-check
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Any? = null
    checkSubtype<Boolean>(x is List<*>)
    val y: Any? = "hello"
    checkSubtype<Boolean>(y is List<*>)
    val z: Any? = 42
    checkSubtype<Boolean>(z is List<*>)
}
