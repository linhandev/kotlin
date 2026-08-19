// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: enum class class literal Color::class infers KClass<Color>, verifying type inference
 * HELPERS: checkType
 */

enum class Color { RED }

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<kotlin.reflect.KClass<Color>>(Color::class)
}
