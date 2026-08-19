// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: nullable interface receiver with local class delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

class Impl : I {
    override fun foo() = "ok"
}

fun test(i: I?): String? = i?.let {
    class D(d: I) : I by d
    D(it).foo()
}

fun case_1() {
    checkSubtype<String?>(test(Impl()))
}
