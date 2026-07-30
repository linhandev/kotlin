// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 6 -> sentence 6
 *                type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable enum subject is exhaustive when null branch is covered type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun case1() {
    val c: Color? = Color.RED
    checkSubtype<Int>(when (c) {
        Color.RED -> 1
        Color.GREEN -> 2
        null -> -1
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val c: Color? = null
    checkSubtype<Int>(when (c) {
        Color.RED -> 1
        Color.GREEN -> 2
        null -> -1
    })
}
