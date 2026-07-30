// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 109 -> sentence 109
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 109 -> sentence 109
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 109 -> sentence 109
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 109 -> sentence 109
 *                declarations, declaration-visibility -> paragraph 109 -> sentence 109
 * NUMBER: 1
 * DESCRIPTION: private primary constructor is reachable only via companion factory or secondary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class Token private constructor(val v: Int, val source: String) {
    companion object {
        fun viaCompanion(): Token = Token(1, "companion")
    }

    constructor(v: Int) : this(v, "secondary")
}

fun companionValue(): Int = Token.viaCompanion().v

fun companionSource(): String = Token.viaCompanion().source

fun secondaryValue(): Int = Token(2).v

fun secondarySource(): String = Token(2).source

fun box(): String {
    if (companionValue() != 1) return "NOK: companion value"
    if (companionSource() != "companion") return "NOK: companion source"
    if (secondaryValue() != 2) return "NOK: secondary value"
    if (secondarySource() != "secondary") return "NOK: secondary source"
    return "OK"
}
