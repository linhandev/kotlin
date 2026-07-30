// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 8 -> sentence 8
 *                type-system, type-kinds, type-parameters -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: in operator overload resolution prefers concrete Int contains over generic contains and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

operator fun <T> Box.contains(x: T): Boolean = false

operator fun Box.contains(x: Int): Boolean = true

fun case1() {
    checkSubtype<Boolean>(1 in Box())
}
