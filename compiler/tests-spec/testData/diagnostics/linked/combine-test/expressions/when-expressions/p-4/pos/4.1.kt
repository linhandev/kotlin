// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 *                expressions, when-expressions -> paragraph 4 -> sentence 4
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: when expression with multiple enum constants in one branch is still exhaustive type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun case1() {
    val c = Color.RED
    checkSubtype<Int>(when (c) {
        Color.RED, Color.GREEN -> 1
        Color.BLUE -> 2
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val c = Color.BLUE
    checkSubtype<Int>(when (c) {
        Color.RED, Color.GREEN -> 1
        Color.BLUE -> 2
    })
}
