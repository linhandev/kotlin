// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 88 -> sentence 88
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 88 -> sentence 88
 *                declarations, property-declaration -> paragraph 88 -> sentence 88
 *                statements, assignments -> paragraph 88 -> sentence 88
 * NUMBER: 1
 * DESCRIPTION: val primary constructor property not reassignable outside
 */

// TESTCASE NUMBER: 1
class User(val name: String)

fun test() {
    User("a").<!VAL_REASSIGNMENT!>name<!> = "b"
}
