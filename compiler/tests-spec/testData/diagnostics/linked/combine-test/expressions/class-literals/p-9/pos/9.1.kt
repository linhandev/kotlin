// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                declarations, declarations-with-type-parameters -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: raw List::class infers KClass<List<*>> without specific type arguments, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<List<*>>>(List::class)
}
