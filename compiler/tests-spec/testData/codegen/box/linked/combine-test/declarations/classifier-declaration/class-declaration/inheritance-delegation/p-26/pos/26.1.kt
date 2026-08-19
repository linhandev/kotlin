// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: nullable interface receiver with local class delegation
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

fun box(): String {
    if (test(Impl()) != "ok") return "NOK"
    return "OK"
}
