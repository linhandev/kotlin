// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: KClass<out Any> can receive concrete class literal via covariance, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<out Any> = String::class

fun box(): String {
    if (test() != String::class) return "NOK"
    return "OK"
}
