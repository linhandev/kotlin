// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 210 -> sentence 210
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 210 -> sentence 210
 *                inheritance, overriding -> paragraph 210 -> sentence 210
 * NUMBER: 1
 * DESCRIPTION: type inference when a sub-interface overrides dual conflicting defaults and a class declaration inherits that resolution
 * HELPERS: checkType
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

fun case1() {
    val c = InheritResolvedFun()
    c checkType { check<InheritResolvedFun>() }
    checkSubtype<ResolvedFun>(c)
    checkSubtype<LeftFun>(c)
    checkSubtype<RightFun>(c)
    c.f() checkType { check<Int>() }
}

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

fun case2() {
    val c = InheritResolvedTag()
    c checkType { check<InheritResolvedTag>() }
    checkSubtype<ResolvedTag>(c)
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

interface ResolvedVal : LeftVal, RightVal {
    override val n: Int
        get() = 12
}

class InheritResolvedVal : ResolvedVal

fun case3() {
    val c = InheritResolvedVal()
    c checkType { check<InheritResolvedVal>() }
    checkSubtype<ResolvedVal>(c)
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    c.n checkType { check<Int>() }
}
