// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: inner class Outer.Inner::class infers KClass<Outer.Inner> distinct from KClass<Outer>, verifying type inference
 * HELPERS: checkType
 */

class Outer { inner class Inner }

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<Outer>>(Outer::class)
    checkSubtype<kotlin.reflect.KClass<Outer.Inner>>(Outer.Inner::class)
}
