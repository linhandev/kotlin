// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 *                expressions, when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: when expression with enum subject and else branch covering remaining enum constants type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun case1() {
    val c = Color.RED
    checkSubtype<Int>(when (c) {
        Color.RED -> 1
        else -> 0
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val c = Color.GREEN
    checkSubtype<Int>(when (c) {
        Color.RED -> 1
        else -> 0
    })
}
