// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: class literal String::class returns KClass<String> regardless of nullable variable type, verifying runtime semantics
 */

val c: kotlin.reflect.KClass<String> = String::class

// TESTCASE NUMBER: 1
fun test(t: String?): kotlin.reflect.KClass<String> = c

fun box(): String {
    if (test("a") != String::class) return "NOK1"
    if (test(null) != String::class) return "NOK2"
    return "OK"
}
