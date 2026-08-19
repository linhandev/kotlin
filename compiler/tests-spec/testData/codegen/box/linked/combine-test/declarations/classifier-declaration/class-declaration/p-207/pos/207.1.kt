// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 207 -> sentence 207
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 207 -> sentence 207
 *                inheritance, overriding -> paragraph 207 -> sentence 207
 * NUMBER: 1
 * DESCRIPTION: dual interface default implementations resolved in a class declaration via override + qualified super<IF>; runtime combines both defaults
 */

// TESTCASE NUMBER: 1
interface LeftDefault {
    fun f(): Int = 1
}

interface RightDefault {
    fun f(): Int = 2
}

class ResolveSum : LeftDefault, RightDefault {
    override fun f(): Int = super<LeftDefault>.f() + super<RightDefault>.f()
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

class ResolveConcat : LeftTag, RightTag {
    override fun tag(): String = super<LeftTag>.tag() + super<RightTag>.tag()
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

class ResolveVal : LeftVal, RightVal {
    override val n: Int
        get() = super<LeftVal>.n * super<RightVal>.n
}

fun box(): String {
    if (ResolveSum().f() != 3) return "NOK: sum"
    val asLeft: LeftDefault = ResolveSum()
    if (asLeft.f() != 3) return "NOK: via-left"
    val asRight: RightDefault = ResolveSum()
    if (asRight.f() != 3) return "NOK: via-right"

    if (ResolveConcat().tag() != "LR") return "NOK: concat"
    val asLeftTag: LeftTag = ResolveConcat()
    if (asLeftTag.tag() != "LR") return "NOK: via-left-tag"
    val asRightTag: RightTag = ResolveConcat()
    if (asRightTag.tag() != "LR") return "NOK: via-right-tag"

    if (ResolveVal().n != 12) return "NOK: val-product"
    val asLeftVal: LeftVal = ResolveVal()
    if (asLeftVal.n != 12) return "NOK: via-left-val"
    val asRightVal: RightVal = ResolveVal()
    if (asRightVal.n != 12) return "NOK: via-right-val"
    return "OK"
}
