// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 35 -> sentence 35
 *                expressions, range-expressions -> paragraph 35 -> sentence 35
 *                expressions, when-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: when used as statement with range branch only for Int subject compiles without exhaustiveness error
 */

// TESTCASE NUMBER: 1
fun case1() {
    test(5)
}

// TESTCASE NUMBER: 2
fun case2() {
    test(0)
}

// TESTCASE NUMBER: 3
fun case3() {
    test(11)
}

fun test(x: Int) {
    when (x) {
        in 1..10 -> println("small")
    }
}
