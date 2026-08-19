// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 8 -> sentence 8
 *                type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, when-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable enum subject and else branch covering null and remaining enum constants type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun case1() {
    val c: Color? = Color.RED
    checkSubtype<Int>(when (c) {
        Color.RED -> 1
        else -> 0
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val c: Color? = null
    checkSubtype<Int>(when (c) {
        Color.RED -> 1
        else -> 0
    })
}
