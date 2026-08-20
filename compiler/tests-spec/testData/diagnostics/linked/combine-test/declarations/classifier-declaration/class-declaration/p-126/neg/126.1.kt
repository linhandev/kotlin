// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 126 -> sentence 126
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 126 -> sentence 126
 * NUMBER: 1
 * DESCRIPTION: property initializer cannot read sibling val before init block assigns it in class declaration
 */

// TESTCASE NUMBER: 1
class ForwardInt {
    val x: Int
    val y = <!UNINITIALIZED_VARIABLE!>x<!>

    init {
        x = 1
    }
}

// TESTCASE NUMBER: 2
class ForwardString {
    val label: String
    val copy = <!UNINITIALIZED_VARIABLE!>label<!>

    init {
        label = "ok"
    }
}
