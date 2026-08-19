// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 *                expressions, when-expressions -> paragraph 5 -> sentence 5
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: when expression with function call subject checks exhaustiveness on returned enum type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun pick(): Color = Color.RED

fun case1() {
    checkSubtype<Int>(when (pick()) {
        Color.RED -> 1
        Color.GREEN -> 2
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    fun pickGreen(): Color = Color.GREEN
    checkSubtype<Int>(when (pickGreen()) {
        Color.RED -> 1
        Color.GREEN -> 2
    })
}
