// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: KClass variable can be nullable, holding either a class literal or null, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean): kotlin.reflect.KClass<String>? = if (flag) String::class else null

fun box(): String {
    if (test(true) != String::class) return "NOK1"
    if (test(false) != null) return "NOK2"
    return "OK"
}
