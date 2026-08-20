// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 285 -> sentence 285
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 285 -> sentence 285
 *                inheritance, inheriting -> paragraph 285 -> sentence 285
 * NUMBER: 1
 * DESCRIPTION: precise types when an internal open class is inherited by an internal subclass in the same module
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
internal open class TokenBase(val token: Int = 1)

internal class TokenSub : TokenBase(1)

fun tokenId(): Int = TokenSub().token

fun case1() {
    val s = TokenSub()
    s checkType { check<TokenSub>() }
    checkSubtype<TokenBase>(s)
    s.token checkType { check<Int>() }
    tokenId() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
internal open class CodeBase<T>(val code: T)

internal class CodeSub : CodeBase<Int>(7)

fun case2() {
    val s = CodeSub()
    checkSubtype<CodeBase<Int>>(s)
    s.code checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
internal open class LabelRoot(val label: String)

internal open class LabelMid(label: String, val mid: Int) : LabelRoot(label)

internal class LabelLeaf(label: String, mid: Int, val leaf: Int) : LabelMid(label, mid)

fun case3() {
    val s = LabelLeaf("L", 2, 3)
    checkSubtype<LabelRoot>(s)
    checkSubtype<LabelMid>(s)
    s.label checkType { check<String>() }
    s.mid checkType { check<Int>() }
    s.leaf checkType { check<Int>() }
}
