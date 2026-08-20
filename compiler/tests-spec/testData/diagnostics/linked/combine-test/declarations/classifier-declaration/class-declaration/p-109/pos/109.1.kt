// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 109 -> sentence 109
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 109 -> sentence 109
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 109 -> sentence 109
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 109 -> sentence 109
 *                declarations, declaration-visibility -> paragraph 109 -> sentence 109
 * NUMBER: 1
 * DESCRIPTION: private primary constructor is reachable only via companion factory or secondary constructor type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Token private constructor(val v: Int, val source: String) {
    companion object {
        fun viaCompanion(): Token = Token(1, "companion")
    }

    constructor(v: Int) : this(v, "secondary")
}

fun case1() {
    val viaCompanion = Token.viaCompanion()
    viaCompanion checkType { check<Token>() }
    viaCompanion.v checkType { check<Int>() }
    viaCompanion.source checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaSecondary = Token(2)
    viaSecondary checkType { check<Token>() }
    viaSecondary.v checkType { check<Int>() }
    viaSecondary.source checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaSecondaryOther = Token(9)
    viaSecondaryOther checkType { check<Token>() }
    viaSecondaryOther.v checkType { check<Int>() }
    viaSecondaryOther.source checkType { check<String>() }
}
