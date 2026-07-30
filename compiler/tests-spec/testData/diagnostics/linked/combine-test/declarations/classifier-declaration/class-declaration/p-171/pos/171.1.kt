// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 171 -> sentence 171
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 171 -> sentence 171
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 171 -> sentence 171
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 171 -> sentence 171
 * NUMBER: 1
 * DESCRIPTION: type inference distinguishing interface class-delegation from superclass constructor delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

class Base : I {
    override fun f(): Int = 1
    fun baseOnly(): Int = 10
}

class Derived(b: Base) : I by b

fun case1() {
    val d = Derived(Base())
    d checkType { check<Derived>() }
    checkSubtype<I>(d)
    d.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Core(val n: Int) : I {
    override fun f(): Int = n
}

class ViaInherit : Core(7)

class ViaBy(c: Core) : I by c

fun case2() {
    val inherit = ViaInherit()
    inherit checkType { check<ViaInherit>() }
    checkSubtype<Core>(inherit)
    checkSubtype<I>(inherit)
    inherit.n checkType { check<Int>() }

    val by = ViaBy(Core(7))
    by checkType { check<ViaBy>() }
    checkSubtype<I>(by)
    by.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
interface Tag {
    fun tag(): String
}

class TagImpl(val t: String) : Tag {
    override fun tag(): String = t
}

open class Holder(val id: Int)

class Combo(h: Int, t: Tag) : Holder(h), Tag by t

fun case3() {
    val c = Combo(3, TagImpl("ok"))
    c checkType { check<Combo>() }
    checkSubtype<Holder>(c)
    checkSubtype<Tag>(c)
    c.id checkType { check<Int>() }
    c.tag() checkType { check<String>() }
}
