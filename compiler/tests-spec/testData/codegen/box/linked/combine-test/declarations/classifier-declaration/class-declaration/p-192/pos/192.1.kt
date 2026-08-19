// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 192 -> sentence 192
 * PRIMARY LINKS: inheritance, overriding -> paragraph 192 -> sentence 192
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 192 -> sentence 192
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 192 -> sentence 192
 *                inheritance, inheriting -> paragraph 192 -> sentence 192
 * NUMBER: 1
 * DESCRIPTION: an abstract class may re-declare an inherited concrete open member as abstract override, forcing a concrete Leaf to implement it
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
    fun read(): Int = f()
}

abstract class Mid : Base() {
    abstract override fun f(): Int
}

class Leaf : Mid() {
    override fun f(): Int = 2
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
    fun banner(): String = "#$reading"
}

abstract class AbstractMeter : Meter() {
    abstract override val reading: Int
}

class ConcreteMeter : AbstractMeter() {
    override val reading: Int = 7
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
}

abstract class NeedsName : Named() {
    abstract override fun name(): String
    fun bracket(): String = "[${name()}]"
}

class LeafNamed : NeedsName() {
    override fun name(): String = "leaf"
}

fun box(): String {
    if (Base().f() != 1) return "NOK: base-f"
    if (Leaf().f() != 2) return "NOK: leaf-f"
    if (Leaf().read() != 2) return "NOK: leaf-read"
    if ((Leaf() as Mid).f() != 2) return "NOK: mid-ref"
    if ((Leaf() as Base).read() != 2) return "NOK: base-ref-read"

    if (Meter().banner() != "#1") return "NOK: meter"
    if (ConcreteMeter().banner() != "#7") return "NOK: concrete-meter"
    if ((ConcreteMeter() as Meter).reading != 7) return "NOK: meter-ref"

    if (Named().name() != "base") return "NOK: named"
    if (LeafNamed().bracket() != "[leaf]") return "NOK: leaf-bracket"
    if ((LeafNamed() as NeedsName).bracket() != "[leaf]") return "NOK: needs-name-ref"
    return "OK"
}
