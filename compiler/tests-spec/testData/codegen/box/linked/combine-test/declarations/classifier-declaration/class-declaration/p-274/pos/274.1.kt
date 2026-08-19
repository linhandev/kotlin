// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 274 -> sentence 274
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 274 -> sentence 274
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 274 -> sentence 274
 *                declarations, property-declaration -> paragraph 274 -> sentence 274
 * NUMBER: 1
 * DESCRIPTION: an inner class may read outer-instance private members; covers primary-constructor property, body property, and private function; contrasts with next-point non-inner Nested failure, with p-266 same-class method access, and with p-142 inner + type-parameter focus
 */

// TESTCASE NUMBER: 1
class TokenOuter(private val secret: Int) {
    inner class Inner {
        fun get(): Int = secret
    }
}

// TESTCASE NUMBER: 2
class CodeOuter {
    private val code: Int = 2
    inner class Inner {
        fun open(): Int = code
    }
}

// TESTCASE NUMBER: 3
class LabelOuter(private val seed: String) {
    private fun label(): String = seed
    inner class Inner {
        fun text(): String = label()
        fun length(): Int = seed.length
    }
}

fun box(): String {
    if (TokenOuter(1).Inner().get() != 1) return "NOK: token-1"
    if (TokenOuter(7).Inner().get() != 7) return "NOK: token-7"
    val t = TokenOuter(3)
    if (t.Inner().get() != 3) return "NOK: via-token"

    if (CodeOuter().Inner().open() != 2) return "NOK: code"
    val c = CodeOuter()
    if (c.Inner().open() != 2) return "NOK: via-code"

    if (LabelOuter("ok").Inner().text() != "ok") return "NOK: label-text"
    if (LabelOuter("ok").Inner().length() != 2) return "NOK: label-length"
    if (LabelOuter("ab").Inner().text() != "ab") return "NOK: label-ab"
    val l = LabelOuter("ok")
    if (l.Inner().text() != "ok" || l.Inner().length() != 2) return "NOK: via-label"
    return "OK"
}
