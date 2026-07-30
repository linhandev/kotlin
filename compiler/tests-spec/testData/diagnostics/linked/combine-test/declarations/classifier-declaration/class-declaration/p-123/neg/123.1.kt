// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 123 -> sentence 123
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 123 -> sentence 123
 *                declarations, property-declaration, read-only-property-declaration -> paragraph 123 -> sentence 123
 * NUMBER: 1
 * DESCRIPTION: init block cannot assign val property more than once in class declaration
 */

// TESTCASE NUMBER: 1
class TwiceInt {
    val x: Int

    init {
        x = 1
        <!VAL_REASSIGNMENT!>x<!> = 2
    }
}

// TESTCASE NUMBER: 2
class TwiceString {
    val label: String

    init {
        label = "a"
        <!VAL_REASSIGNMENT!>label<!> = "b"
    }
}
