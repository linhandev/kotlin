// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: instance ::class infers KClass<out Base> on statically typed Base receiver, verifying type inference
 * HELPERS: checkType
 */

open class Base
class Sub: Base()

// TESTCASE NUMBER: 1
fun case1(b: Base) {
    checkSubtype<kotlin.reflect.KClass<out Base>>(b::class)
}
