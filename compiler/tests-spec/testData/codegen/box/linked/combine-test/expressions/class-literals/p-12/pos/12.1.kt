// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: type alias UserId::class shares the same class literal as underlying String::class, verifying runtime semantics
 */

typealias UserId = String

// TESTCASE NUMBER: 1
fun test(): Boolean = UserId::class == String::class

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
