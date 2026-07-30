// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 112 -> sentence 112
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 112 -> sentence 112
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 112 -> sentence 112
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 112 -> sentence 112
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegation to primary constructor rejects mismatched parameter types in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String) {
    constructor(years: Int, marker: Boolean) : this(<!TYPE_MISMATCH!>years<!>)
}

// TESTCASE NUMBER: 2
class Count(val total: Int) {
    constructor(text: String, tagged: Boolean) : this(<!TYPE_MISMATCH!>text<!>)
}
