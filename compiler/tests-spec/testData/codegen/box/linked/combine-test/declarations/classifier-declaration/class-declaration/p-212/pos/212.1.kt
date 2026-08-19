// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 212 -> sentence 212
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 212 -> sentence 212
 *                inheritance, overriding -> paragraph 212 -> sentence 212
 * NUMBER: 1
 * DESCRIPTION: open class member body conflicting with interface default requires override; qualified super<Base>/super<I> combine both; contrasts with p-207 dual-interface defaults and with next-point non-open class member conflict
 */

// TESTCASE NUMBER: 1
open class BaseFun {
    open fun f(): Int = 0
}

interface IfaceFun {
    fun f(): Int = 1
}

class ResolveClassAndIface : BaseFun(), IfaceFun {
    override fun f(): Int = super<BaseFun>.f() + super<IfaceFun>.f()
}

// TESTCASE NUMBER: 2
open class BaseTag {
    open fun tag(): String = "B"
}

interface IfaceTag {
    fun tag(): String = "I"
}

class ResolveTag : BaseTag(), IfaceTag {
    override fun tag(): String = super<BaseTag>.tag() + super<IfaceTag>.tag()
}

// TESTCASE NUMBER: 3
open class BaseVal {
    open val n: Int = 2
}

interface IfaceVal {
    val n: Int get() = 3
}

class ResolveVal : BaseVal(), IfaceVal {
    override val n: Int
        get() = super<BaseVal>.n * super<IfaceVal>.n
}

fun box(): String {
    if (ResolveClassAndIface().f() != 1) return "NOK: sum"
    val asBase: BaseFun = ResolveClassAndIface()
    if (asBase.f() != 1) return "NOK: via-base"
    val asIface: IfaceFun = ResolveClassAndIface()
    if (asIface.f() != 1) return "NOK: via-iface"
    if (BaseFun().f() != 0) return "NOK: base-alone"
    if (object : IfaceFun {}.f() != 1) return "NOK: iface-alone"

    if (ResolveTag().tag() != "BI") return "NOK: concat"
    val asBaseTag: BaseTag = ResolveTag()
    if (asBaseTag.tag() != "BI") return "NOK: via-base-tag"
    val asIfaceTag: IfaceTag = ResolveTag()
    if (asIfaceTag.tag() != "BI") return "NOK: via-iface-tag"

    if (ResolveVal().n != 6) return "NOK: val-product"
    val asBaseVal: BaseVal = ResolveVal()
    if (asBaseVal.n != 6) return "NOK: via-base-val"
    val asIfaceVal: IfaceVal = ResolveVal()
    if (asIfaceVal.n != 6) return "NOK: via-iface-val"
    return "OK"
}
