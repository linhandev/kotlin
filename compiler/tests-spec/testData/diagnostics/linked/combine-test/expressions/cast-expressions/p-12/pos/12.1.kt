// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 12 -> sentence 12
 *                type-inference, smart-casts -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: as after is reports USELESS_CAST
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hi"
    if (x is String) {
        checkSubtype<Int>((x <!USELESS_CAST!>as String<!>).length)
    }
}
