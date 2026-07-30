// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 9 -> sentence 9
 *                expressions, when-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: when expression with Any subject does not require enum exhaustiveness when else branch is present type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun case1() {
    val x: Any = Color.RED
    checkSubtype<Int>(when (x) {
        Color.RED -> 1
        Color.GREEN -> 2
        else -> 0
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = "other"
    checkSubtype<Int>(when (x) {
        Color.RED -> 1
        Color.GREEN -> 2
        else -> 0
    })
}
