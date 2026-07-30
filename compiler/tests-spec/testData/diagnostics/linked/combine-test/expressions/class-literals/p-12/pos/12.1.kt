// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: type alias UserId::class infers KClass<String> same as String::class, verifying type inference
 * HELPERS: checkType
 */

typealias UserId = String

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<String>>(UserId::class)
    checkSubtype<Boolean>(UserId::class == String::class)
}
