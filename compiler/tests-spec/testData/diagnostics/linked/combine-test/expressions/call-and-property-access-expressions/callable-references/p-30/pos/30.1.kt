// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 30 -> sentence 30
 *                inheritance, inheriting -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: callable reference Sub::f resolves to the overridden declaration in Sub and infers (Sub) -> Int, verifying type inference
 * HELPERS: checkType
 */

open class Base { open fun f(): Int = 1 }
class Sub: Base() { override fun f(): Int = 2 }

// TESTCASE NUMBER: 1
fun case1() {
    val ref: (Sub) -> Int = Sub::f
    checkSubtype<(Sub) -> Int>(ref)
    checkSubtype<Int>(ref(Sub()))
}
