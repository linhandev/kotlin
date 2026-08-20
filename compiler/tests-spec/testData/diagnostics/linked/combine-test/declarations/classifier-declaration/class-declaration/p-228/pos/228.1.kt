// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 228 -> sentence 228
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 228 -> sentence 228
 *                inheritance, overriding -> paragraph 228 -> sentence 228
 * NUMBER: 1
 * DESCRIPTION: type inference when override + super<Default> resolves default+abstract same-named dual interface members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface DefaultFun {
    fun f(): Int = 1
}

interface AbstractFun {
    fun f(): Int
}

class PlusFive : DefaultFun, AbstractFun {
    override fun f(): Int = super<DefaultFun>.f() + 5
}

fun case1() {
    val c = PlusFive()
    c checkType { check<PlusFive>() }
    checkSubtype<DefaultFun>(c)
    checkSubtype<AbstractFun>(c)
    c.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface DefaultTag {
    fun tag(): String = "D"
}

interface AbstractTag {
    fun tag(): String
}

class WrapTag : DefaultTag, AbstractTag {
    override fun tag(): String = super<DefaultTag>.tag() + "!"
}

fun case2() {
    val c = WrapTag()
    c checkType { check<WrapTag>() }
    checkSubtype<DefaultTag>(c)
    checkSubtype<AbstractTag>(c)
    c.tag() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface DefaultVal {
    val n: Int get() = 7
}

interface AbstractVal {
    val n: Int
}

class PlusVal : DefaultVal, AbstractVal {
    override val n: Int get() = super<DefaultVal>.n + 3
}

fun case3() {
    val c = PlusVal()
    c checkType { check<PlusVal>() }
    checkSubtype<DefaultVal>(c)
    checkSubtype<AbstractVal>(c)
    c.n checkType { check<Int>() }
}
