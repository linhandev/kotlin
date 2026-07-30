// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: enum entry class literal Color.RED::class infers KClass<Color.RED>, verifying type inference
 * HELPERS: checkType
 */

enum class Color { RED }

// TESTCASE NUMBER: 1
fun case1() {
    val k: kotlin.reflect.KClass<out Color> = Color.RED::class
    checkSubtype<kotlin.reflect.KClass<out Color>>(k)
}
