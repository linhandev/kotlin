// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                inheritance, inheriting -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: Sub::class infers KClass<Sub> assignable to KClass<Base> via covariance, verifying type inference
 * HELPERS: checkType
 */

open class Base
class Sub: Base()

// TESTCASE NUMBER: 1
fun case1() {
    val k: kotlin.reflect.KClass<out Base> = Sub::class
    checkSubtype<kotlin.reflect.KClass<out Base>>(k)
}
