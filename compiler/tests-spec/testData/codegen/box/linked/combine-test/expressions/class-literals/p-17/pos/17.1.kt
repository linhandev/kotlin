// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: enum class class literal Color::class returns KClass<Color>, verifying runtime semantics
 */

enum class Color { RED }

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<Color> = Color::class

fun box(): String {
    if (test() != Color::class) return "NOK"
    return "OK"
}
