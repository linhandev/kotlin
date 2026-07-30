// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 47 -> sentence 47
 *                type-inference, introduction-1 -> paragraph 47 -> sentence 47
 *                declarations, function-declaration -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: generic extension function infers type argument at call site
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> T.twice(): Pair<T, T> = this to this

fun case_1() {
    val result = 1.twice()
    checkSubtype<Pair<Int, Int>>(result)
}

fun case_2() {
    val result = "a".twice()
    checkSubtype<Pair<String, String>>(result)
}
