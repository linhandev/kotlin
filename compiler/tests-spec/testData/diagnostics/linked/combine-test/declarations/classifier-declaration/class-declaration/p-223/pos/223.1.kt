// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 223 -> sentence 223
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 223 -> sentence 223
 *                inheritance, overriding -> paragraph 223 -> sentence 223
 * NUMBER: 1
 * DESCRIPTION: type inference when a class override of dual interface defaults is observed through superinterface-typed references
 * HELPERS: checkType
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

fun case1() {
    val c = OverrideNine()
    c checkType { check<OverrideNine>() }
    checkSubtype<LeftFun>(c)
    checkSubtype<RightFun>(c)
    c.f() checkType { check<Int>() }
    val asLeft: LeftFun = c
    asLeft.f() checkType { check<Int>() }
}

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

fun case2() {
    val c = OverrideOk()
    c checkType { check<OverrideOk>() }
    checkSubtype<LeftTag>(c)
    checkSubtype<RightTag>(c)
    c.tag() checkType { check<String>() }
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

fun case3() {
    val c = OverrideZero()
    c checkType { check<OverrideZero>() }
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    c.n checkType { check<Int>() }
}
