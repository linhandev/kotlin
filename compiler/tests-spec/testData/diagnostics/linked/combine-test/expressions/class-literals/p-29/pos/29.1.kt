// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: local class class literal Local::class infers KClass<Local> within declaring function, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    class Local
    checkSubtype<kotlin.reflect.KClass<Local>>(Local::class)
}
