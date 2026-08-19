// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 273 -> sentence 273
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 273 -> sentence 273
 *                inheritance, inheriting -> paragraph 273 -> sentence 273
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 273 -> sentence 273
 * NUMBER: 1
 * DESCRIPTION: a subclass of Outer may use Outer protected nested classes via public wrappers (must not expose Nested from public API); covers nested with property, nested with function, and nested primary-constructor property; contrasts with p-271/p-272 private Nested and with p-267 protected members
 */

// TESTCASE NUMBER: 1
open class TokenOuter {
    protected class Nested(val id: Int)
}

class TokenSub : TokenOuter() {
    fun make(): Int = Nested(1).id
}

// TESTCASE NUMBER: 2
open class CodeOuter {
    protected class Nested {
        fun code(): Int = 2
    }
}

class CodeSub : CodeOuter() {
    fun open(): Int = Nested().code()
}

// TESTCASE NUMBER: 3
open class LabelOuter {
    protected class Nested(val label: String)
}

class LabelSub : LabelOuter() {
    fun text(): String = Nested("ok").label
    fun length(): Int = Nested("ok").label.length
}

fun box(): String {
    if (TokenSub().make() != 1) return "NOK: token"
    val asToken: TokenOuter = TokenSub()
    if ((asToken as TokenSub).make() != 1) return "NOK: via-token"

    if (CodeSub().open() != 2) return "NOK: code"
    val asCode: CodeOuter = CodeSub()
    if ((asCode as CodeSub).open() != 2) return "NOK: via-code"

    if (LabelSub().text() != "ok") return "NOK: label-text"
    if (LabelSub().length() != 2) return "NOK: label-length"
    if (LabelSub().text().length != 2) return "NOK: label-len2"
    val asLabel: LabelOuter = LabelSub()
    if ((asLabel as LabelSub).text() != "ok") return "NOK: via-label"
    return "OK"
}
