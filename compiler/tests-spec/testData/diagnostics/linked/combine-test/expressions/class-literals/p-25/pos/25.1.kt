// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: KClass.isInstance returns Boolean, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(String::class.isInstance("a"))
    checkSubtype<Boolean>(String::class.isInstance(42))
}
