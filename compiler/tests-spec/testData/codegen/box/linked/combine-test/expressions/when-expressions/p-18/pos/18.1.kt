// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 18 -> sentence 18
 *                declarations, classifier-declaration, object-declaration -> paragraph 18 -> sentence 18
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 18 -> sentence 18
 *                type-inference, smart-casts -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: when expression with sealed object branch matched by object and class branch matched by is with smart cast
 */

// TESTCASE NUMBER: 1
sealed class Token {
    object EOF : Token()
    class Word(val text: String) : Token()
}

fun test(t: Token): Int = when (t) {
    Token.EOF -> 0
    is Token.Word -> t.text.length
}

fun box(): String {
    if (test(Token.EOF) != 0) return "NOK"
    if (test(Token.Word("hello")) != 5) return "NOK"
    return "OK"
}
