// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: object declaration class literal Singleton::class infers KClass<Singleton>, verifying type inference
 * HELPERS: checkType
 */

object Singleton

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<Singleton>>(Singleton::class)
}
