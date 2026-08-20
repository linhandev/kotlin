// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 229 -> sentence 229
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 229 -> sentence 229
 *                inheritance, overriding -> paragraph 229 -> sentence 229
 *                declarations, classifier-declaration, object-declaration -> paragraph 229 -> sentence 229
 * NUMBER: 1
 * DESCRIPTION: type inference when a named object overrides dual conflicting interface defaults
 * HELPERS: checkType
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

fun case1() {
    ObjZero checkType { check<ObjZero>() }
    checkSubtype<LeftFun>(ObjZero)
    checkSubtype<RightFun>(ObjZero)
    ObjZero.f() checkType { check<Int>() }
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

fun case2() {
    ObjOk checkType { check<ObjOk>() }
    checkSubtype<LeftTag>(ObjOk)
    checkSubtype<RightTag>(ObjOk)
    ObjOk.tag() checkType { check<String>() }
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

fun case3() {
    ObjNine checkType { check<ObjNine>() }
    checkSubtype<LeftVal>(ObjNine)
    checkSubtype<RightVal>(ObjNine)
    ObjNine.n checkType { check<Int>() }
}
