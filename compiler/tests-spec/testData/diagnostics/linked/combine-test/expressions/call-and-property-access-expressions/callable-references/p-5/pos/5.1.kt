// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: unbound property reference User::name infers function type (User) -> String, verifying type inference
 * HELPERS: checkType
 */

data class User(val name: String)

// TESTCASE NUMBER: 1
fun case1() {
    val g: (User) -> String = User::name
    checkSubtype<(User) -> String>(g)
}
