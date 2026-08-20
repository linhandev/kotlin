// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 113 -> sentence 113
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 113 -> sentence 113
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 113 -> sentence 113
 * NUMBER: 1
 * DESCRIPTION: secondary constructor delegation to primary constructor rejects too many arguments in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String) {
    constructor(marker: Boolean) : <!NONE_APPLICABLE!>this<!>("a", "b")
}

// TESTCASE NUMBER: 2
class PairHolder(val first: String, val second: Int) {
    constructor(tagged: Boolean) : <!NONE_APPLICABLE!>this<!>("x", 1, 2)
}
