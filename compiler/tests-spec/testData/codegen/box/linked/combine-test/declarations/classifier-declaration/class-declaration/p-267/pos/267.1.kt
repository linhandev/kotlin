// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 267 -> sentence 267
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 267 -> sentence 267
 *                inheritance, inheriting -> paragraph 267 -> sentence 267
 * NUMBER: 1
 * DESCRIPTION: protected members of an open class are visible in subclasses via public wrappers; contrasts with p-32/p-158 overriding and multi-level focus, and with next-point outside-class failure
 */

// TESTCASE NUMBER: 1
open class TokenBase {
    protected val token = 1
}

class TokenSub : TokenBase() {
    fun read(): Int = token
}

// TESTCASE NUMBER: 2
open class CodeBase {
    protected fun code(): Int = 2
}

class CodeSub : CodeBase() {
    fun open(): Int = code()
}

// TESTCASE NUMBER: 3
open class LabelBase(protected val label: String)

class LabelSub(label: String) : LabelBase(label) {
    fun text(): String = label
    fun length(): Int = label.length
}

fun box(): String {
    if (TokenSub().read() != 1) return "NOK: token"
    val asToken: TokenBase = TokenSub()
    if ((asToken as TokenSub).read() != 1) return "NOK: via-token"

    if (CodeSub().open() != 2) return "NOK: code"
    val asCode: CodeBase = CodeSub()
    if ((asCode as CodeSub).open() != 2) return "NOK: via-code"

    if (LabelSub("ok").text() != "ok") return "NOK: label-text"
    if (LabelSub("ok").length() != 2) return "NOK: label-length"
    if (LabelSub("ab").text() != "ab") return "NOK: label-ab"
    val asLabel: LabelBase = LabelSub("ok")
    if ((asLabel as LabelSub).text() != "ok") return "NOK: via-label"
    return "OK"
}
