// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 100 -> sentence 100
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 100 -> sentence 100
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 100 -> sentence 100
 *                syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * NUMBER: 1
 * DESCRIPTION: secondary constructor cannot delegate to both this() and super() in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child(val y: Int) : Base(0) {
    constructor(v: Int, y: Int) : this(y)<!SYNTAX!>,<!> <!SYNTAX!>super<!><!SYNTAX!>(<!><!SYNTAX!>v<!><!SYNTAX!>)<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{}<!>
}
