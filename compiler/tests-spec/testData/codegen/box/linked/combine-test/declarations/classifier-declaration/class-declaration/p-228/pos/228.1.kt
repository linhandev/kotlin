// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 228 -> sentence 228
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 228 -> sentence 228
 *                inheritance, overriding -> paragraph 228 -> sentence 228
 * NUMBER: 1
 * DESCRIPTION: when only one of two same-named interface members has a default, class override may call super<Default> and still satisfy the abstract side; contrasts with p-209 missing override, p-207 both-defaults super sum, and p-225 val dual-default getters
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

fun box(): String {
    if (PlusFive().f() != 6) return "NOK: plus-five"
    val asDefaultFun: DefaultFun = PlusFive()
    if (asDefaultFun.f() != 6) return "NOK: via-default-fun"
    val asAbstractFun: AbstractFun = PlusFive()
    if (asAbstractFun.f() != 6) return "NOK: via-abstract-fun"
    if (object : DefaultFun {}.f() != 1) return "NOK: default-alone"

    if (WrapTag().tag() != "D!") return "NOK: wrap-tag"
    val asDefaultTag: DefaultTag = WrapTag()
    if (asDefaultTag.tag() != "D!") return "NOK: via-default-tag"
    val asAbstractTag: AbstractTag = WrapTag()
    if (asAbstractTag.tag() != "D!") return "NOK: via-abstract-tag"

    if (PlusVal().n != 10) return "NOK: plus-val"
    val asDefaultVal: DefaultVal = PlusVal()
    if (asDefaultVal.n != 10) return "NOK: via-default-val"
    val asAbstractVal: AbstractVal = PlusVal()
    if (asAbstractVal.n != 10) return "NOK: via-abstract-val"
    return "OK"
}
