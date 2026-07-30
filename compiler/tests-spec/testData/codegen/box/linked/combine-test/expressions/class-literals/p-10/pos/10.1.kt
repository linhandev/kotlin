// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 10 -> sentence 10
 *                declarations, declarations-with-type-parameters -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: bare List::class has type KClass<List<*>> and equals itself, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): Boolean {
    val k: kotlin.reflect.KClass<List<*>> = List::class
    return k == List::class
}

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
