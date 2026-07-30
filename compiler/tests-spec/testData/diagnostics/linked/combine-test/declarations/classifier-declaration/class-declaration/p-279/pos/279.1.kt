// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 279 -> sentence 279
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 279 -> sentence 279
 *                inheritance, inheriting -> paragraph 279 -> sentence 279
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 279 -> sentence 279
 * NUMBER: 1
 * DESCRIPTION: precise types when subclasses are constructed via protected primary constructors
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class TokenBase protected constructor(val id: Int)

class TokenSub(id: Int) : TokenBase(id)

fun case1() {
    val s = TokenSub(1)
    s checkType { check<TokenSub>() }
    checkSubtype<TokenBase>(s)
    s.id checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class CodeBase<T> protected constructor(val value: T)

class CodeSub(value: String) : CodeBase<String>(value)

fun case2() {
    val s = CodeSub("ok")
    checkSubtype<CodeBase<String>>(s)
    s.value checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class LabelBase protected constructor(val label: String, val code: Int)

class LabelSub(label: String) : LabelBase(label, label.length)

fun case3() {
    val s = LabelSub("ok")
    checkSubtype<LabelBase>(s)
    s.label checkType { check<String>() }
    s.code checkType { check<Int>() }
}
