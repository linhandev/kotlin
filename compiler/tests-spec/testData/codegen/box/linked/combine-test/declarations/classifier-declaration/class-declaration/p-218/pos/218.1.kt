// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 218 -> sentence 218
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 218 -> sentence 218
 *                inheritance, overriding -> paragraph 218 -> sentence 218
 * NUMBER: 1
 * DESCRIPTION: class inheriting two conflicting interface defaults plus a third abstract same-named member still requires an explicit override; override resolves all three; contrasts with p-206 dual-default fail, p-209 default+abstract fail, and p-207 dual-default super resolution
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

interface AbstractFun {
    fun f(): Int
}

class ResolveTripleFun : LeftFun, RightFun, AbstractFun {
    override fun f(): Int = 0
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

interface AbstractTag {
    fun tag(): String
}

class ResolveTripleTag : LeftTag, RightTag, AbstractTag {
    override fun tag(): String = "ok"
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

interface AbstractVal {
    val n: Int
}

class ResolveTripleVal : LeftVal, RightVal, AbstractVal {
    override val n: Int
        get() = super<LeftVal>.n * super<RightVal>.n
}

fun box(): String {
    if (ResolveTripleFun().f() != 0) return "NOK: fun"
    val asLeftFun: LeftFun = ResolveTripleFun()
    if (asLeftFun.f() != 0) return "NOK: via-left-fun"
    val asRightFun: RightFun = ResolveTripleFun()
    if (asRightFun.f() != 0) return "NOK: via-right-fun"
    val asAbsFun: AbstractFun = ResolveTripleFun()
    if (asAbsFun.f() != 0) return "NOK: via-abs-fun"

    if (ResolveTripleTag().tag() != "ok") return "NOK: tag"
    val asLeftTag: LeftTag = ResolveTripleTag()
    if (asLeftTag.tag() != "ok") return "NOK: via-left-tag"
    val asRightTag: RightTag = ResolveTripleTag()
    if (asRightTag.tag() != "ok") return "NOK: via-right-tag"
    val asAbsTag: AbstractTag = ResolveTripleTag()
    if (asAbsTag.tag() != "ok") return "NOK: via-abs-tag"

    if (ResolveTripleVal().n != 12) return "NOK: val-product"
    val asLeftVal: LeftVal = ResolveTripleVal()
    if (asLeftVal.n != 12) return "NOK: via-left-val"
    val asRightVal: RightVal = ResolveTripleVal()
    if (asRightVal.n != 12) return "NOK: via-right-val"
    val asAbsVal: AbstractVal = ResolveTripleVal()
    if (asAbsVal.n != 12) return "NOK: via-abs-val"
    return "OK"
}
