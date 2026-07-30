// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 229 -> sentence 229
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 229 -> sentence 229
 *                inheritance, overriding -> paragraph 229 -> sentence 229
 *                declarations, classifier-declaration, object-declaration -> paragraph 229 -> sentence 229
 * NUMBER: 1
 * DESCRIPTION: a named object implementing dual conflicting interface defaults must explicitly override to resolve; singleton identity preserved through superinterface refs; contrasts with p-223 class override dispatch and with next-point anonymous object override
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

object ObjZero : LeftFun, RightFun {
    override fun f(): Int = 0
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

object ObjOk : LeftTag, RightTag {
    override fun tag(): String = "ok"
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

object ObjNine : LeftVal, RightVal {
    override val n: Int get() = 9
}

fun box(): String {
    if (ObjZero.f() != 0) return "NOK: obj-fun"
    val asLeft: LeftFun = ObjZero
    if (asLeft.f() != 0) return "NOK: via-left-fun"
    val asRight: RightFun = ObjZero
    if (asRight.f() != 0) return "NOK: via-right-fun"
    if (asLeft !== ObjZero || asRight !== ObjZero) return "NOK: singleton-fun"
    if (object : LeftFun {}.f() != 1) return "NOK: left-alone"
    if (object : RightFun {}.f() != 2) return "NOK: right-alone"

    if (ObjOk.tag() != "ok") return "NOK: obj-tag"
    val asLeftTag: LeftTag = ObjOk
    if (asLeftTag.tag() != "ok") return "NOK: via-left-tag"
    val asRightTag: RightTag = ObjOk
    if (asRightTag.tag() != "ok") return "NOK: via-right-tag"
    if (asLeftTag !== ObjOk || asRightTag !== ObjOk) return "NOK: singleton-tag"

    if (ObjNine.n != 9) return "NOK: obj-val"
    val asLeftVal: LeftVal = ObjNine
    if (asLeftVal.n != 9) return "NOK: via-left-val"
    val asRightVal: RightVal = ObjNine
    if (asRightVal.n != 9) return "NOK: via-right-val"
    if (asLeftVal !== ObjNine || asRightVal !== ObjNine) return "NOK: singleton-val"
    return "OK"
}
