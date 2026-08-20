// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 259 -> sentence 259
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 259 -> sentence 259
 *                inheritance, inheriting -> paragraph 259 -> sentence 259
 * NUMBER: 1
 * DESCRIPTION: precise static types when the same generic interface implementation is used with different type arguments; classifier equality is Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T>

class BoxImpl<T> : Box<T>

fun case1() {
    val a: Box<Int> = BoxImpl()
    val b: Box<String> = BoxImpl()
    a checkType { check<Box<Int>>() }
    b checkType { check<Box<String>>() }
    checkSubtype<BoxImpl<Int>>(BoxImpl())
    checkSubtype<BoxImpl<String>>(BoxImpl())
    (a::class == b::class) checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    val current: T
}

class HolderImpl<T>(override val current: T) : Holder<T>

fun case2() {
    val h: Holder<Int> = HolderImpl(1)
    checkSubtype<Holder<Int>>(h)
    h.current checkType { check<Int>() }
    val s: Holder<String> = HolderImpl("x")
    s.current checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface Factory<T> {
    fun create(): T
}

class FactoryImpl<T>(private val value: T) : Factory<T> {
    override fun create(): T = value
}

fun case3() {
    val f: Factory<Boolean> = FactoryImpl(true)
    checkSubtype<Factory<Boolean>>(f)
    f.create() checkType { check<Boolean>() }
}
