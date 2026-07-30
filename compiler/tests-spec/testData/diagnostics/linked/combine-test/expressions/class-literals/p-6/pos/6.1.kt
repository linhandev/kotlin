// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: class literal String::class infers KClass<String> and is assignable even when target is typed String?, verifying type inference
 * HELPERS: checkType
 */

val c: kotlin.reflect.KClass<String> = String::class

// TESTCASE NUMBER: 1
fun case1(t: String?) {
    checkSubtype<kotlin.reflect.KClass<String>>(c)
}
