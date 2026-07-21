// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: secondary constructor cannot access primary constructor parameter in delegation call
 */

// TESTCASE NUMBER: 1
class A(x: Int) {
    constructor(y: String) : this(<!DEBUG_INFO_MISSING_UNRESOLVED!>x<!>)
}
