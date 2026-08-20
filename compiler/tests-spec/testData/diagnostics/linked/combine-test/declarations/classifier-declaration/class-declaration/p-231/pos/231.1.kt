// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 231 -> sentence 231
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 231 -> sentence 231
 *                inheritance, inheriting -> paragraph 231 -> sentence 231
 * NUMBER: 1
 * DESCRIPTION: type inference when a class implements a generic interface producer by fixing type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

class IntBox : Box<Int> {
    override fun get(): Int = 1
}

fun case1() {
    val b = IntBox()
    b checkType { check<IntBox>() }
    checkSubtype<Box<Int>>(b)
    b.get() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    fun value(): T
}

class StringHolder : Holder<String> {
    override fun value(): String = "ok"
}

fun case2() {
    val h = StringHolder()
    h checkType { check<StringHolder>() }
    checkSubtype<Holder<String>>(h)
    h.value() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface PairBox<A, B> {
    fun left(): A
    fun right(): B
}

class StringIntPair : PairBox<String, Int> {
    override fun left(): String = "x"
    override fun right(): Int = 7
}

fun case3() {
    val p = StringIntPair()
    p checkType { check<StringIntPair>() }
    checkSubtype<PairBox<String, Int>>(p)
    p.left() checkType { check<String>() }
    p.right() checkType { check<Int>() }
}
