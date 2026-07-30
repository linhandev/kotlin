// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 212 -> sentence 212
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 212 -> sentence 212
 *                inheritance, overriding -> paragraph 212 -> sentence 212
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration resolves open-class vs interface-default conflict via override + qualified super
 * HELPERS: checkType
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

fun case1() {
    val c = ResolveClassAndIface()
    c checkType { check<ResolveClassAndIface>() }
    checkSubtype<BaseFun>(c)
    checkSubtype<IfaceFun>(c)
    c.f() checkType { check<Int>() }
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

fun case2() {
    val c = ResolveTag()
    c checkType { check<ResolveTag>() }
    checkSubtype<BaseTag>(c)
    checkSubtype<IfaceTag>(c)
    c.tag() checkType { check<String>() }
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

fun case3() {
    val c = ResolveVal()
    c checkType { check<ResolveVal>() }
    checkSubtype<BaseVal>(c)
    checkSubtype<IfaceVal>(c)
    c.n checkType { check<Int>() }
}
