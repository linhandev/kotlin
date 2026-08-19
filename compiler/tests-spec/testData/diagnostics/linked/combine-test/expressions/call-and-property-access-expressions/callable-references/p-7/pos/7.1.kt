// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 7 -> sentence 7
 *                declarations, classifier-declaration, constructor-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: constructor reference ::User infers function type (String) -> User, distinct from member property references, verifying type inference
 * HELPERS: checkType
 */

data class User(val name: String)

// TESTCASE NUMBER: 1
fun case1() {
    val ctor: (String) -> User = ::User
    checkSubtype<(String) -> User>(ctor)
}
