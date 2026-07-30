// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: in operator overload resolution selects most specific contains overload and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

operator fun Box.contains(x: Int): Boolean = true

operator fun Box.contains(x: Number): Boolean = false

fun case1() {
    checkSubtype<Boolean>(1 in Box())
}
