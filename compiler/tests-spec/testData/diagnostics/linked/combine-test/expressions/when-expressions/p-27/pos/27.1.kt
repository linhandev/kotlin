// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: when expression branch with in operator invoking custom contains operator convention type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun contains(x: Int): Boolean = x > 0
}

fun case1() {
    val x = 5
    checkSubtype<String>(when (x) {
        in Box() -> "positive"
        else -> "other"
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = -1
    checkSubtype<String>(when (x) {
        in Box() -> "positive"
        else -> "other"
    })
}
