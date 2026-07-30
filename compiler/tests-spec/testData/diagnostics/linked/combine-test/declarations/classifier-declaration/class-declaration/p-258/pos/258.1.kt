// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 258 -> sentence 258
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 258 -> sentence 258
 *                declarations, property-declaration -> paragraph 258 -> sentence 258
 *                inheritance, inheriting -> paragraph 258 -> sentence 258
 * NUMBER: 1
 * DESCRIPTION: precise types when a primary-constructor override val binds a generic interface property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    val v: T
}

class IntBox(override val v: Int) : Box<Int>

fun case1() {
    val b = IntBox(2)
    b checkType { check<IntBox>() }
    checkSubtype<Box<Int>>(b)
    b.v checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    val current: T
}

class StringHolder(override val current: String) : Holder<String>

fun case2() {
    val h = StringHolder("ok")
    checkSubtype<Holder<String>>(h)
    h.current checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface PairLike<A, B> {
    val left: A
    val right: B
}

class MixPair(override val left: String, override val right: Int) : PairLike<String, Int>

fun case3() {
    val p = MixPair("x", 7)
    checkSubtype<PairLike<String, Int>>(p)
    p.left checkType { check<String>() }
    p.right checkType { check<Int>() }
}
