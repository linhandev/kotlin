// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 284 -> sentence 284
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 284 -> sentence 284
 *                inheritance, overriding -> paragraph 284 -> sentence 284
 *                inheritance, inheriting -> paragraph 284 -> sentence 284
 * NUMBER: 1
 * DESCRIPTION: precise types when override widens visibility of abstract/generic/protected members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class TokenBase {
    protected abstract fun token(): Int
    fun read(): Int = token()
}

class TokenChild : TokenBase() {
    public override fun token(): Int = 2
}

fun case1() {
    val c = TokenChild()
    c checkType { check<TokenChild>() }
    checkSubtype<TokenBase>(c)
    c.token() checkType { check<Int>() }
    c.read() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
abstract class CodeBase<T> {
    protected abstract fun code(): T
    fun probe(): T = code()
}

class CodeChild : CodeBase<Int>() {
    public override fun code(): Int = 7
}

fun case2() {
    val c = CodeChild()
    checkSubtype<CodeBase<Int>>(c)
    c.code() checkType { check<Int>() }
    c.probe() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class LabelBase {
    protected open val label: String = "base"
    fun banner(): String = "[$label]"
}

class LabelChild : LabelBase() {
    public override val label: String = "shown"
}

fun case3() {
    val c = LabelChild()
    checkSubtype<LabelBase>(c)
    c.label checkType { check<String>() }
    c.banner() checkType { check<String>() }
}
