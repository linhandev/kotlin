// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 219 -> sentence 219
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 219 -> sentence 219
 *                inheritance, overriding -> paragraph 219 -> sentence 219
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration uses a covariant override to satisfy two interfaces with the same more general return type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface LeftCreate {
    fun create(): Number
}

interface RightCreate {
    fun create(): Number
}

class IntCreate : LeftCreate, RightCreate {
    override fun create(): Int = 1
}

fun case1() {
    val c = IntCreate()
    c checkType { check<IntCreate>() }
    checkSubtype<LeftCreate>(c)
    checkSubtype<RightCreate>(c)
    c.create() checkType { check<Int>() }
    val asLeft: LeftCreate = c
    asLeft.create() checkType { check<Number>() }
}

// TESTCASE NUMBER: 2
interface LeftText {
    fun text(): CharSequence
}

interface RightText {
    fun text(): CharSequence
}

class StringText : LeftText, RightText {
    override fun text(): String = "ok"
}

fun case2() {
    val c = StringText()
    c checkType { check<StringText>() }
    checkSubtype<LeftText>(c)
    checkSubtype<RightText>(c)
    c.text() checkType { check<String>() }
    val asLeft: LeftText = c
    asLeft.text() checkType { check<CharSequence>() }
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Number
}

interface RightVal {
    val n: Number
}

class IntVal : LeftVal, RightVal {
    override val n: Int = 7
}

fun case3() {
    val c = IntVal()
    c checkType { check<IntVal>() }
    checkSubtype<LeftVal>(c)
    checkSubtype<RightVal>(c)
    c.n checkType { check<Int>() }
    val asLeft: LeftVal = c
    asLeft.n checkType { check<Number>() }
}
