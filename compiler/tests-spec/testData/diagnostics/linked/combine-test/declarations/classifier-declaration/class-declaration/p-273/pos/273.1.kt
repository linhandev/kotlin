// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 273 -> sentence 273
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 273 -> sentence 273
 *                inheritance, inheriting -> paragraph 273 -> sentence 273
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 273 -> sentence 273
 * NUMBER: 1
 * DESCRIPTION: precise types when a subclass reads Outer protected nested classes through public wrappers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class TokenOuter {
    protected class Nested(val id: Int)
}

class TokenSub : TokenOuter() {
    fun make(): Int = Nested(1).id
}

fun case1() {
    val s = TokenSub()
    s checkType { check<TokenSub>() }
    checkSubtype<TokenOuter>(s)
    s.make() checkType { check<Int>() }
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

fun case2() {
    val s = CodeSub()
    checkSubtype<CodeOuter>(s)
    s.open() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class LabelOuter {
    protected class Nested(val label: String)
}

class LabelSub : LabelOuter() {
    fun text(): String = Nested("ok").label
    fun length(): Int = Nested("ok").label.length
}

fun case3() {
    val s = LabelSub()
    checkSubtype<LabelOuter>(s)
    s.text() checkType { check<String>() }
    s.length() checkType { check<Int>() }
}
