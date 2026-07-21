// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: primary constructor parameter visible in init block
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class A(x: Int) {
    init {
        x checkType { check<Int>() }
        <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Int")!>x<!>
    }
}
