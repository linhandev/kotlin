// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 190 -> sentence 190
 * PRIMARY LINKS: inheritance, overriding -> paragraph 190 -> sentence 190
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 190 -> sentence 190
 *                inheritance, inheriting -> paragraph 190 -> sentence 190
 * NUMBER: 1
 * DESCRIPTION: open override keeps a member overridable so a grandchild can replace the Mid implementation in a class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
    fun read(): Int = f()
}

open class Mid : Base() {
    open override fun f(): Int = 2
}

class Leaf : Mid() {
    override fun f(): Int = 3
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
    fun banner(): String = "#$reading"
}

open class MidMeter : Meter() {
    open override val reading: Int = 2
}

class LeafMeter : MidMeter() {
    override val reading: Int = 3
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
}

open class MidNamed : Named() {
    open override fun name(): String = "mid"
    fun prefix(): String = "P:${name()}"
}

class LeafNamed : MidNamed() {
    override fun name(): String = "leaf"
}

fun box(): String {
    if (Base().f() != 1) return "NOK: base-f"
    if (Mid().f() != 2) return "NOK: mid-f"
    if (Leaf().f() != 3) return "NOK: leaf-f"
    if (Leaf().read() != 3) return "NOK: leaf-read"
    if ((Leaf() as Mid).f() != 3) return "NOK: mid-ref"
    if ((Leaf() as Base).f() != 3) return "NOK: base-ref"
    if ((Leaf() as Base).read() != 3) return "NOK: base-ref-read"

    if (Meter().banner() != "#1") return "NOK: meter"
    if (MidMeter().banner() != "#2") return "NOK: mid-meter"
    if (LeafMeter().banner() != "#3") return "NOK: leaf-meter"
    if ((LeafMeter() as Meter).banner() != "#3") return "NOK: meter-ref"

    if (Named().name() != "base") return "NOK: named"
    if (MidNamed().prefix() != "P:mid") return "NOK: mid-prefix"
    if (LeafNamed().prefix() != "P:leaf") return "NOK: leaf-prefix"
    if ((LeafNamed() as MidNamed).prefix() != "P:leaf") return "NOK: mid-named-ref"
    return "OK"
}
