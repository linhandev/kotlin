// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 274 -> sentence 274
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 274 -> sentence 274
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 274 -> sentence 274
 *                declarations, property-declaration -> paragraph 274 -> sentence 274
 * NUMBER: 1
 * DESCRIPTION: precise types when an inner class reads outer-instance private members through public wrappers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class TokenOuter(private val secret: Int) {
    inner class Inner {
        fun get(): Int = secret
    }
}

fun case1() {
    val o = TokenOuter(1)
    o checkType { check<TokenOuter>() }
    val inner = o.Inner()
    inner checkType { check<TokenOuter.Inner>() }
    inner.get() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class CodeOuter {
    private val code: Int = 2
    inner class Inner {
        fun open(): Int = code
    }
}

fun case2() {
    val o = CodeOuter()
    o.Inner().open() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class LabelOuter(private val seed: String) {
    private fun label(): String = seed
    inner class Inner {
        fun text(): String = label()
        fun length(): Int = seed.length
    }
}

fun case3() {
    val o = LabelOuter("ok")
    val inner = o.Inner()
    inner.text() checkType { check<String>() }
    inner.length() checkType { check<Int>() }
}
