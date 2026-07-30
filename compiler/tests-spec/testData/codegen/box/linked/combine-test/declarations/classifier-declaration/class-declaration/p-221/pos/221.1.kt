// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 221 -> sentence 221
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 221 -> sentence 221
 *                inheritance, overriding -> paragraph 221 -> sentence 221
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 221 -> sentence 221
 * NUMBER: 1
 * DESCRIPTION: class declaration can delegate multiple interfaces with distinct members via by to one impl; each interface default/body is supplied separately; contrasts with p-45 single-interface by and with next-point same-name dual-default by conflict
 */

// TESTCASE NUMBER: 1
interface LeftFa {
    fun fa(): Int = 1
}

interface RightFb {
    fun fb(): Int = 2
}

class ImplFun : LeftFa, RightFb

class DualByFun(a: ImplFun) : LeftFa by a, RightFb by a

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tagA(): String = "L"
}

interface RightTag {
    fun tagB(): String = "R"
}

class ImplTag : LeftTag, RightTag

class DualByTag(t: ImplTag) : LeftTag by t, RightTag by t

// TESTCASE NUMBER: 3
interface LeftVal {
    val xa: Int get() = 3
}

interface RightVal {
    val xb: Int get() = 4
}

class ImplVal : LeftVal, RightVal

class DualByVal(v: ImplVal) : LeftVal by v, RightVal by v

fun box(): String {
    if (DualByFun(ImplFun()).fa() + DualByFun(ImplFun()).fb() != 3) return "NOK: sum"
    val asLeftFun: LeftFa = DualByFun(ImplFun())
    if (asLeftFun.fa() != 1) return "NOK: via-left-fun"
    val asRightFun: RightFb = DualByFun(ImplFun())
    if (asRightFun.fb() != 2) return "NOK: via-right-fun"

    if (DualByTag(ImplTag()).tagA() + DualByTag(ImplTag()).tagB() != "LR") return "NOK: tags"
    val asLeftTag: LeftTag = DualByTag(ImplTag())
    if (asLeftTag.tagA() != "L") return "NOK: via-left-tag"
    val asRightTag: RightTag = DualByTag(ImplTag())
    if (asRightTag.tagB() != "R") return "NOK: via-right-tag"

    if (DualByVal(ImplVal()).xa * DualByVal(ImplVal()).xb != 12) return "NOK: vals"
    val asLeftVal: LeftVal = DualByVal(ImplVal())
    if (asLeftVal.xa != 3) return "NOK: via-left-val"
    val asRightVal: RightVal = DualByVal(ImplVal())
    if (asRightVal.xb != 4) return "NOK: via-right-val"
    return "OK"
}
