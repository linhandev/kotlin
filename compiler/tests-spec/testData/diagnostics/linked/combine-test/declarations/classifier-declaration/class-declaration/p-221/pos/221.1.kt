// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 221 -> sentence 221
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 221 -> sentence 221
 *                inheritance, overriding -> paragraph 221 -> sentence 221
 *                declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 221 -> sentence 221
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration delegates multiple distinct-member interfaces via by to one impl
 * HELPERS: checkType
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

fun case1() {
    val c = DualByFun(ImplFun())
    c checkType { check<DualByFun>() }
    checkSubtype<LeftFa>(c)
    checkSubtype<RightFb>(c)
    c.fa() checkType { check<Int>() }
    c.fb() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tagA(): String = "L"
}

interface RightTag {
    fun tagB(): String = "R"
}

class ImplTag : LeftTag, RightTag

class DualByTag(t: ImplTag) : LeftTag by t, RightTag by t

fun case2() {
    val c = DualByTag(ImplTag())
    c checkType { check<DualByTag>() }
    checkSubtype<LeftTag>(c)
    checkSubtype<RightTag>(c)
    c.tagA() checkType { check<String>() }
    c.tagB() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val xa: Int get() = 3
}

interface RightVal {
    val xb: Int get() = 4
}

class ImplVal : LeftVal, RightVal

class DualByVal(v: ImplVal) : LeftVal by v, RightVal by v

fun case3() {
    val c = DualByVal(ImplVal())
    c checkType { check<DualByVal>() }
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    c.xa checkType { check<Int>() }
    c.xb checkType { check<Int>() }
}
