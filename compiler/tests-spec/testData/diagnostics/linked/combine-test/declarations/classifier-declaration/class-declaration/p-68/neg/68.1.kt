// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 68 -> sentence 68
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 68 -> sentence 68
 * NUMBER: 1
 * DESCRIPTION: private primary constructor property inaccessible outside class
 */

// TESTCASE NUMBER: 1
class User(private val secret: Int)

fun test() = User(1).<!INVISIBLE_MEMBER!>secret<!>
