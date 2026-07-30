// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 285 -> sentence 285
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 285 -> sentence 285
 *                inheritance, inheriting -> paragraph 285 -> sentence 285
 * NUMBER: 1
 * DESCRIPTION: an internal open class can be inherited by another internal class in the same module; covers plain, generic, and multi-level inheritance; contrasts with p-262 internal usability focus and with next-point private companion inside-class access
 */

// TESTCASE NUMBER: 1
internal open class TokenBase(val token: Int = 1)

internal class TokenSub : TokenBase(1)

fun tokenId(): Int = TokenSub().token

// TESTCASE NUMBER: 2
internal open class CodeBase<T>(val code: T)

internal class CodeSub : CodeBase<Int>(7)

fun codeValue(): Int = CodeSub().code

// TESTCASE NUMBER: 3
internal open class LabelRoot(val label: String)

internal open class LabelMid(label: String, val mid: Int) : LabelRoot(label)

internal class LabelLeaf(label: String, mid: Int, val leaf: Int) : LabelMid(label, mid)

fun leafSum(): Int = LabelLeaf("L", 2, 3).mid + LabelLeaf("L", 2, 3).leaf

fun box(): String {
    if (tokenId() != 1) return "NOK: token"
    val asToken: TokenBase = TokenSub()
    if (asToken.token != 1) return "NOK: via-token"

    if (codeValue() != 7) return "NOK: code"
    val asCode: CodeBase<Int> = CodeSub()
    if (asCode.code != 7) return "NOK: via-code"

    if (leafSum() != 5) return "NOK: leaf-sum"
    val asRoot: LabelRoot = LabelLeaf("L", 2, 3)
    if (asRoot.label != "L") return "NOK: via-root"
    return "OK"
}
