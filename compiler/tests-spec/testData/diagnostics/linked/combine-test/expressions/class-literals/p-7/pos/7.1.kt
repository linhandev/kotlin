// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: KClass variable infers as nullable KClass<String>? when conditionally assigned class literal or null, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean) {
    val k: kotlin.reflect.KClass<String>? = if (flag) String::class else null
    checkSubtype<kotlin.reflect.KClass<String>?>(k)
}
