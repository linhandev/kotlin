// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 284 -> sentence 284
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 284 -> sentence 284
 *                inheritance, overriding -> paragraph 284 -> sentence 284
 *                inheritance, inheriting -> paragraph 284 -> sentence 284
 * NUMBER: 1
 * DESCRIPTION: override may widen visibility so protected/abstract/generic members become publicly callable; contrasts with previous-point narrowing failure, with p-187 non-abstract open-class focus, and with declaration-visibility p-6
 */

// TESTCASE NUMBER: 1
abstract class TokenBase {
    protected abstract fun token(): Int
    fun read(): Int = token()
}

class TokenChild : TokenBase() {
    public override fun token(): Int = 2
}

// TESTCASE NUMBER: 2
abstract class CodeBase<T> {
    protected abstract fun code(): T
    fun probe(): T = code()
}

class CodeChild : CodeBase<Int>() {
    public override fun code(): Int = 7
}

// TESTCASE NUMBER: 3
open class LabelBase {
    protected open val label: String = "base"
    fun banner(): String = "[$label]"
}

class LabelChild : LabelBase() {
    public override val label: String = "shown"
}

fun box(): String {
    if (TokenChild().token() != 2) return "NOK: token"
    if (TokenChild().read() != 2) return "NOK: token-read"
    val asToken: TokenBase = TokenChild()
    if (asToken.read() != 2) return "NOK: via-token"

    if (CodeChild().code() != 7) return "NOK: code"
    if (CodeChild().probe() != 7) return "NOK: code-probe"
    val asCode: CodeBase<Int> = CodeChild()
    if (asCode.probe() != 7) return "NOK: via-code"

    if (LabelChild().label != "shown") return "NOK: label"
    if (LabelChild().banner() != "[shown]") return "NOK: banner"
    if (LabelBase().banner() != "[base]") return "NOK: base-banner"
    val asLabel: LabelBase = LabelChild()
    if (asLabel.banner() != "[shown]") return "NOK: via-label"
    return "OK"
}
