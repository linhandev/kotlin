// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 272 -> sentence 272
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 272 -> sentence 272
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 272 -> sentence 272
 * NUMBER: 1
 * DESCRIPTION: precise types when Outer reads private nested classes through public wrappers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class TokenOuter {
    private class Nested(val id: Int)
    fun make(): Int = Nested(1).id
}

fun case1() {
    val o = TokenOuter()
    o checkType { check<TokenOuter>() }
    o.make() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class CodeOuter {
    private class Nested {
        fun code(): Int = 2
    }
    fun open(): Int = Nested().code()
}

fun case2() {
    val o = CodeOuter()
    o checkType { check<CodeOuter>() }
    o.open() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class LabelOuter {
    private class Nested(val label: String)
    fun text(): String = Nested("ok").label
    fun length(): Int = Nested("ok").label.length
}

fun case3() {
    val o = LabelOuter()
    o checkType { check<LabelOuter>() }
    o.text() checkType { check<String>() }
    o.length() checkType { check<Int>() }
}
