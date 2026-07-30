// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 14 -> sentence 14
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: extension function call may omit argument with a default value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun String.padTo(len: Int = 10): String = padEnd(len)

fun case_1() {
    checkSubtype<String>("hi".padTo())
}
