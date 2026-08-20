// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 218 -> sentence 218
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 218 -> sentence 218
 *                inheritance, overriding -> paragraph 218 -> sentence 218
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration overrides after inheriting two conflicting interface defaults plus a third abstract same-named member
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

interface AbstractFun {
    fun f(): Int
}

class ResolveTripleFun : LeftFun, RightFun, AbstractFun {
    override fun f(): Int = 0
}

fun case1() {
    val c = ResolveTripleFun()
    c checkType { check<ResolveTripleFun>() }
    checkSubtype<LeftFun>(c)
    checkSubtype<RightFun>(c)
    checkSubtype<AbstractFun>(c)
    c.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

interface AbstractTag {
    fun tag(): String
}

class ResolveTripleTag : LeftTag, RightTag, AbstractTag {
    override fun tag(): String = "ok"
}

fun case2() {
    val c = ResolveTripleTag()
    c checkType { check<ResolveTripleTag>() }
    checkSubtype<LeftTag>(c)
    checkSubtype<RightTag>(c)
    checkSubtype<AbstractTag>(c)
    c.tag() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

interface AbstractVal {
    val n: Int
}

class ResolveTripleVal : LeftVal, RightVal, AbstractVal {
    override val n: Int
        get() = super<LeftVal>.n * super<RightVal>.n
}

fun case3() {
    val c = ResolveTripleVal()
    c checkType { check<ResolveTripleVal>() }
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    checkSubtype<AbstractVal>(c)
    c.n checkType { check<Int>() }
}
