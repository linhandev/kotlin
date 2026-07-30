// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: local class class literal Local::class returns KClass<*> from within the declaring function, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<*> {
    class Local
    return Local::class
}

fun box(): String {
    if (test().simpleName != "Local") return "NOK"
    return "OK"
}
