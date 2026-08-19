// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 171 -> sentence 171
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 171 -> sentence 171
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 171 -> sentence 171
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 171 -> sentence 171
 * NUMBER: 1
 * DESCRIPTION: interface implementation by class delegation is distinct from superclass constructor delegation in class declaration
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

// TESTCASE NUMBER: 2
open class Core(val n: Int) : I {
    override fun f(): Int = n
}

class ViaInherit : Core(7)

class ViaBy(c: Core) : I by c

// TESTCASE NUMBER: 3
interface Tag {
    fun tag(): String
}

class TagImpl(val t: String) : Tag {
    override fun tag(): String = t
}

open class Holder(val id: Int)

class Combo(h: Int, t: Tag) : Holder(h), Tag by t

fun viaDerived(): Triple<Int, Boolean, Boolean> {
    val d = Derived(Base())
    return Triple(d.f(), d is I, (d as Any) is Base)
}

fun viaContrast(): List<Any> {
    val inherit = ViaInherit()
    val by = ViaBy(Core(7))
    return listOf(
        inherit.f(), inherit is Core, inherit.n,
        by.f(), (by as Any) is Core, by is I
    )
}

fun viaCombo(): List<Any> {
    val c = Combo(3, TagImpl("ok"))
    return listOf(c.id, c.tag(), c is Holder, c is Tag, (c as Any) is TagImpl)
}

fun box(): String {
    if (viaDerived() != Triple(1, true, false)) return "NOK: derived"
    if (Derived(Base()).f() != 1) return "NOK: derived-f"

    if (viaContrast() != listOf(7, true, 7, 7, false, true)) return "NOK: contrast"
    if (ViaInherit() !is I) return "NOK: inherit-is-i"
    if (ViaBy(Core(4)).f() != 4) return "NOK: by-f"

    if (viaCombo() != listOf(3, "ok", true, true, false)) return "NOK: combo"
    if (Combo(9, TagImpl("z")).id != 9) return "NOK: combo-id"
    return "OK"
}
