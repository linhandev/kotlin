// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 267 -> sentence 267
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 267 -> sentence 267
 *                inheritance, inheriting -> paragraph 267 -> sentence 267
 * NUMBER: 1
 * DESCRIPTION: precise types when subclasses read protected members through public wrappers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class TokenBase {
    protected val token = 1
}

class TokenSub : TokenBase() {
    fun read(): Int = token
}

fun case1() {
    val s = TokenSub()
    s checkType { check<TokenSub>() }
    checkSubtype<TokenBase>(s)
    s.read() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class CodeBase {
    protected fun code(): Int = 2
}

class CodeSub : CodeBase() {
    fun open(): Int = code()
}

fun case2() {
    val s = CodeSub()
    checkSubtype<CodeBase>(s)
    s.open() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class LabelBase(protected val label: String)

class LabelSub(label: String) : LabelBase(label) {
    fun text(): String = label
    fun length(): Int = label.length
}

fun case3() {
    val s = LabelSub("ok")
    checkSubtype<LabelBase>(s)
    s.text() checkType { check<String>() }
    s.length() checkType { check<Int>() }
}
