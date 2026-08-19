// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 279 -> sentence 279
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 279 -> sentence 279
 *                inheritance, inheriting -> paragraph 279 -> sentence 279
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 279 -> sentence 279
 * NUMBER: 1
 * DESCRIPTION: a protected primary constructor is callable from subclasses via inheritance; covers plain class, generic class, and multi-arg constructor; contrasts with next-point outside-hierarchy failure and with p-267 protected members
 */

// TESTCASE NUMBER: 1
open class TokenBase protected constructor(val id: Int)

class TokenSub(id: Int) : TokenBase(id)

// TESTCASE NUMBER: 2
open class CodeBase<T> protected constructor(val value: T)

class CodeSub(value: String) : CodeBase<String>(value)

// TESTCASE NUMBER: 3
open class LabelBase protected constructor(val label: String, val code: Int)

class LabelSub(label: String) : LabelBase(label, label.length)

fun box(): String {
    if (TokenSub(1).id != 1) return "NOK: token-1"
    if (TokenSub(7).id != 7) return "NOK: token-7"
    val asToken: TokenBase = TokenSub(3)
    if (asToken.id != 3) return "NOK: via-token"

    if (CodeSub("ok").value != "ok") return "NOK: code"
    if (CodeSub("ab").value != "ab") return "NOK: code-ab"
    val asCode: CodeBase<String> = CodeSub("ok")
    if (asCode.value != "ok") return "NOK: via-code"

    if (LabelSub("ok").label != "ok") return "NOK: label"
    if (LabelSub("ok").code != 2) return "NOK: label-code"
    if (LabelSub("ab").code != 2) return "NOK: label-ab"
    val asLabel: LabelBase = LabelSub("ok")
    if (asLabel.label != "ok" || asLabel.code != 2) return "NOK: via-label"
    return "OK"
}
