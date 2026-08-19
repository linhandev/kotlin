// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: property access in interpolation must be wrapped in ${} type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun case1() {
    val u = User("Alice")
    checkSubtype<String>("user=${u.name}")
}
