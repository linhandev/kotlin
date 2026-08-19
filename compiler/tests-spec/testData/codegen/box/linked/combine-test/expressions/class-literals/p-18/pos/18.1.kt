// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: enum entry class literal Color.RED::class returns KClass<Color.RED>, verifying runtime semantics
 */

enum class Color { RED }

// TESTCASE NUMBER: 1
fun test(): kotlin.reflect.KClass<Color> = Color::class

fun box(): String {
    if (!Color::class.isInstance(Color.RED)) return "NOK1"
    if (Color::class.isInstance("not a color")) return "NOK2"
    if (test() != Color::class) return "NOK3"
    return "OK"
}
