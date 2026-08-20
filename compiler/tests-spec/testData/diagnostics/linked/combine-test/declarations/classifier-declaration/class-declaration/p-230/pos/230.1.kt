// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 230 -> sentence 230
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 230 -> sentence 230
 *                inheritance, overriding -> paragraph 230 -> sentence 230
 *                expressions, object-literals -> paragraph 230 -> sentence 230
 * NUMBER: 1
 * DESCRIPTION: type inference when anonymous objects override dual conflicting interface defaults
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

fun case1() {
    val a = object : LeftFun, RightFun {
        override fun f(): Int = 7
    }
    checkSubtype<LeftFun>(a)
    checkSubtype<RightFun>(a)
    a.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

fun case2() {
    val t = object : LeftTag, RightTag {
        override fun tag(): String = "ok"
    }
    checkSubtype<LeftTag>(t)
    checkSubtype<RightTag>(t)
    t.tag() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 3
}

interface RightVal {
    val n: Int get() = 4
}

fun case3() {
    val v = object : LeftVal, RightVal {
        override val n: Int get() = super<LeftVal>.n + super<RightVal>.n
    }
    checkSubtype<LeftVal>(v)
    checkSubtype<RightVal>(v)
    v.n checkType { check<Int>() }
}
