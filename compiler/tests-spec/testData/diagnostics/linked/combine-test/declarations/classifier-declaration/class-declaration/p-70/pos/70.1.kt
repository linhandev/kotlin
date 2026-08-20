// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 70 -> sentence 70
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegates to primary constructor
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String) { constructor(nick: String, age: Int) : this(nick) }

fun test(): User = User("Ann", 1)

fun case1() {
    checkSubtype<User>(test())
}
