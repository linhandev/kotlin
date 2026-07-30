// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: nested class class literal Outer.Inner::class infers KClass<Outer.Inner> with fully qualified name, verifying type inference
 * HELPERS: checkType
 */

class Outer { class Inner }

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<Outer.Inner>>(Outer.Inner::class)
}
