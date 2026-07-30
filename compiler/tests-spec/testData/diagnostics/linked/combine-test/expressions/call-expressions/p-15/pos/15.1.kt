// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 15 -> sentence 15
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: constructor call may omit argument with a default value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String, val active: Boolean = true)

fun case_1() {
    checkSubtype<Boolean>(User("Alice").active)
}
