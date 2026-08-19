// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 32 -> sentence 32
 *                type-inference, introduction-1 -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: explicit type argument overrides inferred type argument type inference check
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun case_1() {
    checkSubtype<Any>(id<Any>(1))
    checkSubtype<Number>(id<Number>(42))
    checkSubtype<String>(id<String>("world"))
    checkSubtype<Int>(id(1))
}
