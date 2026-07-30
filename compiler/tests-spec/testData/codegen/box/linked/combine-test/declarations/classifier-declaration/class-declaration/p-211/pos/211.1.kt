// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 211 -> sentence 211
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 211 -> sentence 211
 *                inheritance, overriding -> paragraph 211 -> sentence 211
 * NUMBER: 1
 * DESCRIPTION: after a sub-interface resolves dual conflicting defaults, empty implementing classes need no further override; contrasts with p-210 (interface-level resolution focus) and p-206 (unresolved dual defaults)
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

interface ResolvedAtIface : LeftFun, RightFun {
    override fun f(): Int = 0
}

// Empty class bodies: no class-level override after interface-level resolution.
class EmptyImplA : ResolvedAtIface
class EmptyImplB : ResolvedAtIface {}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

interface ResolvedTagAtIface : LeftTag, RightTag {
    override fun tag(): String = "ok"
}

class EmptyTagImpl : ResolvedTagAtIface

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

interface ResolvedValAtIface : LeftVal, RightVal {
    override val n: Int
        get() = 0
}

class EmptyValImpl : ResolvedValAtIface

fun box(): String {
    if (EmptyImplA().f() != 0) return "NOK: empty-a"
    if (EmptyImplB().f() != 0) return "NOK: empty-b"
    val asResolved: ResolvedAtIface = EmptyImplA()
    if (asResolved.f() != 0) return "NOK: via-resolved"
    val asLeft: LeftFun = EmptyImplB()
    if (asLeft.f() != 0) return "NOK: via-left"
    val asRight: RightFun = EmptyImplA()
    if (asRight.f() != 0) return "NOK: via-right"

    if (EmptyTagImpl().tag() != "ok") return "NOK: empty-tag"
    val asLeftTag: LeftTag = EmptyTagImpl()
    if (asLeftTag.tag() != "ok") return "NOK: via-left-tag"
    val asRightTag: RightTag = EmptyTagImpl()
    if (asRightTag.tag() != "ok") return "NOK: via-right-tag"

    if (EmptyValImpl().n != 0) return "NOK: empty-val"
    val asLeftVal: LeftVal = EmptyValImpl()
    if (asLeftVal.n != 0) return "NOK: via-left-val"
    val asRightVal: RightVal = EmptyValImpl()
    if (asRightVal.n != 0) return "NOK: via-right-val"
    return "OK"
}
