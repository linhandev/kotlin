// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 18 -> sentence 18
 *                declarations, classifier-declaration, object-declaration -> paragraph 18 -> sentence 18
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 18 -> sentence 18
 *                type-inference, smart-casts -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed object branch matched by object and class branch matched by is with smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Token {
    object EOF : Token()
    class Word(val text: String) : Token()
}

fun case1() {
    val t: Token = Token.EOF
    checkSubtype<Int>(when (t) {
        Token.EOF -> 0
        is Token.Word -> t.text.length
    })
}

// TESTCASE NUMBER: 2
fun case2() {
    val t: Token = Token.Word("hello")
    checkSubtype<Int>(when (t) {
        Token.EOF -> 0
        is Token.Word -> t.text.length
    })
}
