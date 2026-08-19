// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                declarations, classifier-declaration, companion-object -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: companion object C.Companion::class infers KClass<C.Companion> distinct from KClass<C>, verifying type inference
 * HELPERS: checkType
 */

class C { companion object }

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<C>>(C::class)
    checkSubtype<kotlin.reflect.KClass<C.Companion>>(C.Companion::class)
}
