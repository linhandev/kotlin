// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 39 -> sentence 39
 *                type-inference, introduction-1 -> paragraph 39 -> sentence 39
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: generic constructor call infers type arguments from value arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Pair<Int, String>>(Pair(1, "a"))
    checkSubtype<Pair<String, Int>>(Pair("x", 2))
}
