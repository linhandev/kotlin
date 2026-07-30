// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 211 -> sentence 211
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 211 -> sentence 211
 *                inheritance, overriding -> paragraph 211 -> sentence 211
 * NUMBER: 1
 * DESCRIPTION: type inference when empty class declarations inherit a sub-interface that already resolved dual conflicting defaults
 * HELPERS: checkType
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

class EmptyImplA : ResolvedAtIface
class EmptyImplB : ResolvedAtIface {}

fun case1() {
    val a = EmptyImplA()
    a checkType { check<EmptyImplA>() }
    checkSubtype<ResolvedAtIface>(a)
    checkSubtype<LeftFun>(a)
    checkSubtype<RightFun>(a)
    a.f() checkType { check<Int>() }

    val b = EmptyImplB()
    b checkType { check<EmptyImplB>() }
    checkSubtype<ResolvedAtIface>(b)
    b.f() checkType { check<Int>() }
}

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

fun case2() {
    val c = EmptyTagImpl()
    c checkType { check<EmptyTagImpl>() }
    checkSubtype<ResolvedTagAtIface>(c)
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

interface ResolvedValAtIface : LeftVal, RightVal {
    override val n: Int
        get() = 0
}

class EmptyValImpl : ResolvedValAtIface

fun case3() {
    val c = EmptyValImpl()
    c checkType { check<EmptyValImpl>() }
    checkSubtype<ResolvedValAtIface>(c)
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    c.n checkType { check<Int>() }
}
