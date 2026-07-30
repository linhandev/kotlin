// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 223 -> sentence 223
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 223 -> sentence 223
 *                inheritance, overriding -> paragraph 223 -> sentence 223
 * NUMBER: 1
 * DESCRIPTION: after a class overrides dual conflicting interface defaults, calls through any superinterface-typed reference dynamically dispatch to the class override; contrasts with p-207 super-combined defaults and with next-point unresolved interface-level conflict
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

class OverrideNine : LeftFun, RightFun {
    override fun f(): Int = 9
}

fun callViaLeft(a: LeftFun = OverrideNine()): Int = a.f()
fun callViaRight(b: RightFun = OverrideNine()): Int = b.f()

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

class OverrideOk : LeftTag, RightTag {
    override fun tag(): String = "ok"
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

class OverrideZero : LeftVal, RightVal {
    override val n: Int get() = 0
}

fun box(): String {
    if (OverrideNine().f() != 9) return "NOK: direct-fun"
    if (callViaLeft() != 9) return "NOK: via-left-default"
    if (callViaRight() != 9) return "NOK: via-right-default"
    val asLeft: LeftFun = OverrideNine()
    if (asLeft.f() != 9) return "NOK: via-left"
    val asRight: RightFun = OverrideNine()
    if (asRight.f() != 9) return "NOK: via-right"
    if (object : LeftFun {}.f() != 1) return "NOK: left-default-alone"
    if (object : RightFun {}.f() != 2) return "NOK: right-default-alone"

    if (OverrideOk().tag() != "ok") return "NOK: direct-tag"
    val asLeftTag: LeftTag = OverrideOk()
    if (asLeftTag.tag() != "ok") return "NOK: via-left-tag"
    val asRightTag: RightTag = OverrideOk()
    if (asRightTag.tag() != "ok") return "NOK: via-right-tag"

    if (OverrideZero().n != 0) return "NOK: direct-val"
    val asLeftVal: LeftVal = OverrideZero()
    if (asLeftVal.n != 0) return "NOK: via-left-val"
    val asRightVal: RightVal = OverrideZero()
    if (asRightVal.n != 0) return "NOK: via-right-val"
    return "OK"
}
