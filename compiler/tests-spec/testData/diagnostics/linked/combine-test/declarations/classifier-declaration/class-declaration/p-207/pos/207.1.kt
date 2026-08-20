// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 207 -> sentence 207
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 207 -> sentence 207
 *                inheritance, overriding -> paragraph 207 -> sentence 207
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration resolves dual interface defaults via override + qualified super<IF>
 * HELPERS: checkType
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

fun case1() {
    val c = ResolveSum()
    c checkType { check<ResolveSum>() }
    checkSubtype<LeftDefault>(c)
    checkSubtype<RightDefault>(c)
    c.f() checkType { check<Int>() }
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

fun case2() {
    val c = ResolveConcat()
    c checkType { check<ResolveConcat>() }
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

class ResolveVal : LeftVal, RightVal {
    override val n: Int
        get() = super<LeftVal>.n * super<RightVal>.n
}

fun case3() {
    val c = ResolveVal()
    c checkType { check<ResolveVal>() }
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    c.n checkType { check<Int>() }
}
