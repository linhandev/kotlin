// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 210 -> sentence 210
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 210 -> sentence 210
 *                inheritance, overriding -> paragraph 210 -> sentence 210
 * NUMBER: 1
 * DESCRIPTION: sub-interface must override when inheriting two conflicting interface defaults; implementing class then inherits the interface-level resolution; contrasts with p-207 class-level super<IF> and p-206 unresolved dual defaults
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

interface ResolvedFun : LeftFun, RightFun {
    override fun f(): Int = 3
}

class InheritResolvedFun : ResolvedFun

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

interface ResolvedTag : LeftTag, RightTag {
    override fun tag(): String = "LR"
}

class InheritResolvedTag : ResolvedTag

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

interface ResolvedVal : LeftVal, RightVal {
    override val n: Int
        get() = 12
}

class InheritResolvedVal : ResolvedVal

fun box(): String {
    if (InheritResolvedFun().f() != 3) return "NOK: inherit-resolved-fun"
    val asLeft: LeftFun = InheritResolvedFun()
    if (asLeft.f() != 3) return "NOK: via-left-fun"
    val asRight: RightFun = InheritResolvedFun()
    if (asRight.f() != 3) return "NOK: via-right-fun"
    val asResolved: ResolvedFun = InheritResolvedFun()
    if (asResolved.f() != 3) return "NOK: via-resolved-fun"

    if (InheritResolvedTag().tag() != "LR") return "NOK: inherit-resolved-tag"
    val asLeftTag: LeftTag = InheritResolvedTag()
    if (asLeftTag.tag() != "LR") return "NOK: via-left-tag"
    val asRightTag: RightTag = InheritResolvedTag()
    if (asRightTag.tag() != "LR") return "NOK: via-right-tag"

    if (InheritResolvedVal().n != 12) return "NOK: inherit-resolved-val"
    val asLeftVal: LeftVal = InheritResolvedVal()
    if (asLeftVal.n != 12) return "NOK: via-left-val"
    val asRightVal: RightVal = InheritResolvedVal()
    if (asRightVal.n != 12) return "NOK: via-right-val"
    return "OK"
}
