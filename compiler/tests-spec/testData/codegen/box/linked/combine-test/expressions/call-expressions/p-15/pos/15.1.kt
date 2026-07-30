/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 15 -> sentence 15
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: constructor call may omit argument with a default value
 */

// TESTCASE NUMBER: 1
data class User(val name: String, val active: Boolean = true)

fun box(): String {
    if (User("Alice").active != true) return "NOK"
    return "OK"
}
