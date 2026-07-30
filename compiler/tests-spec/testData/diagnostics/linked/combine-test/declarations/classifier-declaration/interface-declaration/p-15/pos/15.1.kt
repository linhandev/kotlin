// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 15 -> sentence 15
 *                inheritance, overriding -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: type inference after resolving multi-interface default conflict via override + super<IF>
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface DefaultA {
    fun f(): Int = 1
}

interface DefaultB {
    fun f(): Int = 2
}

class ResolveWithSuper : DefaultA, DefaultB {
    override fun f(): Int = super<DefaultA>.f() + super<DefaultB>.f()
}

fun case1() {
    val c = ResolveWithSuper()
    checkSubtype<ResolveWithSuper>(c)
    checkSubtype<Int>(c.f())
    checkSubtype<DefaultA>(c)
    checkSubtype<DefaultB>(c)
}
