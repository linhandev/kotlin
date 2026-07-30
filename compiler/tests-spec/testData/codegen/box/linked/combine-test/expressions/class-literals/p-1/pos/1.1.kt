// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class literal String::class returns KClass<String> and equals itself, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<String> = String::class

fun box(): String {
    if (test() != String::class) return "NOK"
    if (String::class != String::class) return "NOK"
    return "OK"
}
