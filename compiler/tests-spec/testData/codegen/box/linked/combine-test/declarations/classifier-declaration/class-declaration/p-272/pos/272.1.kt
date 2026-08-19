// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 272 -> sentence 272
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 272 -> sentence 272
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 272 -> sentence 272
 * NUMBER: 1
 * DESCRIPTION: an outer class may use its private nested classes internally via public wrappers; covers nested with property, nested with function, and nested primary-constructor property; contrasts with previous-point outside-Outer failure and with p-263 top-level private file scope
 */

// TESTCASE NUMBER: 1
class TokenOuter {
    private class Nested(val id: Int)
    fun make(): Int = Nested(1).id
}

// TESTCASE NUMBER: 2
class CodeOuter {
    private class Nested {
        fun code(): Int = 2
    }
    fun open(): Int = Nested().code()
}

// TESTCASE NUMBER: 3
class LabelOuter {
    private class Nested(val label: String)
    fun text(): String = Nested("ok").label
    fun length(): Int = Nested("ok").label.length
}

fun box(): String {
    if (TokenOuter().make() != 1) return "NOK: token"
    val t = TokenOuter()
    if (t.make() != 1) return "NOK: via-token"

    if (CodeOuter().open() != 2) return "NOK: code"
    val c = CodeOuter()
    if (c.open() != 2) return "NOK: via-code"

    if (LabelOuter().text() != "ok") return "NOK: label-text"
    if (LabelOuter().length() != 2) return "NOK: label-length"
    if (LabelOuter().text().length != 2) return "NOK: label-len2"
    val l = LabelOuter()
    if (l.text() != "ok" || l.length() != 2) return "NOK: via-label"
    return "OK"
}
